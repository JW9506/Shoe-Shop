package foo.bar.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import foo.bar.response.BaseResponse;

@RequestMapping("/api/hello")
@RestController
public class MemberController {
    @GetMapping("/foo")
    public BaseResponse checkEmail() throws Exception {
        throw new Exception("this is an exception");
        // return new BaseResponse(SIGNUP);
    }
}
