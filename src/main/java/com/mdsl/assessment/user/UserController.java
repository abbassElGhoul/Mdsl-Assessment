package com.mdsl.assessment.user;

import com.mdsl.assessment.customResponse.Response;
import com.mdsl.assessment.user.Dto.LogInDto;
import com.mdsl.assessment.user.Dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Log4j2
public class UserController
{
    private final UserService userService;

    //    private final AuthService authService;
    @PostMapping("sign-up")
    public Response signUp(@RequestBody SignUpDto signUpDto)
    {
        try
        {
            return userService.signUp(signUpDto);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("log-in")
    public Response login(@RequestBody LogInDto logInDto)
    {
        try
        {
            log.info(logInDto);
            return userService.logIn(logInDto);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
