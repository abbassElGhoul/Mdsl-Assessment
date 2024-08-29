package com.mdsl.assessment.user;

import com.mdsl.assessment.security.PasswordUtil;
import com.mdsl.assessment.customResponse.Response;
import com.mdsl.assessment.security.jwt.JwtUtils;
import com.mdsl.assessment.user.Dto.LogInDto;
import com.mdsl.assessment.user.Dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public Response signUp(SignUpDto signUpDto)
    {
        try
        {
            Optional<UserEntity> user = userRepository.findByUserName(signUpDto.getUserName());
            if (user.isPresent())
            {
                return new Response(HttpStatus.CONFLICT, "User name already in use");
            }
            if (signUpDto.getPassword().equals(signUpDto.getConfirmPassword()))
            {
                UserEntity userEntity = new UserEntity(signUpDto.getUserName(),
                        PasswordUtil.hashPassword(signUpDto.getPassword()));
                return new Response(userRepository.save(userEntity));
            }
            else
            {
                return new Response(HttpStatus.BAD_REQUEST, "Passwords do not match");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Response logIn(LogInDto logInDto)
    {
        try
        {
            Optional<UserEntity> user = userRepository.findByUserName(logInDto.getUserName());
            if (user.isPresent())
            {

                if (PasswordUtil.checkPassword(logInDto.getPassword(), user.get().getPassword()))
                {
                    String jwt = jwtUtils.generateJwtToken(logInDto.getUserName());
                    return new Response(jwt);
                }
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new Response(HttpStatus.UNAUTHORIZED, "invalid user name");
    }
}
