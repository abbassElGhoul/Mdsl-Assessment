package com.mdsl.assessment.user;

import com.mdsl.assessment.customResponse.Response;
import com.mdsl.assessment.user.Dto.LogInDto;
import com.mdsl.assessment.user.Dto.SignUpDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService
{
    Response signUp(SignUpDto signUpDto);

    Response logIn(LogInDto logInDto);
}
