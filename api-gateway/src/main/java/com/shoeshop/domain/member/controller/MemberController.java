package com.shoeshop.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/api/v1/hello")
    public String Hello() throws Exception {
        return "OK";
    }
}
