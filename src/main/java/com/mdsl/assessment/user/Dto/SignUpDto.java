package com.mdsl.assessment.user.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto
{
    private String userName;
    private String password;
    private String confirmPassword;
}
