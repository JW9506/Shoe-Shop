package com.shoeshop.util;

import java.math.BigInteger;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtVerifier {

    private RSAKeyProvider keyProvider;

    static class GoogleRSAKeyProvider implements RSAKeyProvider {

        private static final String GOOGLE_CERTS_URL = "https://www.googleapis.com/oauth2/v3/certs";

        @Override
        public RSAPublicKey getPublicKeyById(String keyId) {
            try {
                URL url = new URL(GOOGLE_CERTS_URL);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jwks = objectMapper.readTree(url);
                JsonNode keys = jwks.get("keys");

                for (JsonNode key : keys) {
                    if (key.get("kid").asText().equals(keyId)) {
                        String n = key.get("n").asText();
                        String e = key.get("e").asText();
                        return constructRSAPublicKey(n, e);
                    }
                }

                throw new RuntimeException("Key ID not found");
            } catch (Exception ex) {
                throw new RuntimeException("Failed to fetch public key", ex);
            }
        }


        private RSAPublicKey constructRSAPublicKey(String modulus, String exponent) {
            try {
                // Decode the base64-encoded components
                byte[] modulusBytes = Base64.getUrlDecoder().decode(modulus);
                byte[] exponentBytes = Base64.getUrlDecoder().decode(exponent);

                // Convert bytes to BigIntegers
                BigInteger modulusBigInteger = new BigInteger(1, modulusBytes);
                BigInteger exponentBigInteger = new BigInteger(1, exponentBytes);

                // Create an RSAPublicKeySpec
                RSAPublicKeySpec spec = new RSAPublicKeySpec(modulusBigInteger, exponentBigInteger);

                // Generate the public key
                KeyFactory factory = KeyFactory.getInstance("RSA");
                return (RSAPublicKey) factory.generatePublic(spec);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException("Unable to construct public key", e);
            }
        }

        @Override
        public RSAPrivateKey getPrivateKey() {
            // Not implemented as only public key is needed for verification
            throw new UnsupportedOperationException("Private key not used for JWT verification");
        }

        @Override
        public String getPrivateKeyId() {
            // Not implemented as only public key is needed for verification
            throw new UnsupportedOperationException("Private key ID not used for JWT verification");
        }
    }

    public JwtVerifier() {
        this.keyProvider = new GoogleRSAKeyProvider();
    }

    public boolean verifyJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(keyProvider);
            DecodedJWT jwt = JWT.require(algorithm)
                                .withIssuer("https://accounts.google.com")
                                .build()
                                .verify(token);

            // TODO: Additional claims validation can go here
            log.info("{}", jwt.getClaims());

            // Check if the current time is after the expiration time
            return !isTokenExpired(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isTokenExpired(DecodedJWT jwt) {
        Date expirationTime = jwt.getExpiresAt(); // Get the expiration time
        Date currentTime = new Date();            // Get the current time

        // Check if the current time is after the expiration time
        return expirationTime != null && currentTime.after(expirationTime);
    }

    public String obtainJwtFromBearer(String bearer) {
        String jwt = "";
        if (bearer != null && bearer.startsWith("Bearer ")) {
            jwt = bearer.substring(7); // Remove "Bearer " prefix
        }
        return jwt;
    }
}
