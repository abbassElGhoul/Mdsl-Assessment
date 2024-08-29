package com.mdsl.assessment.institution.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateInstituteDto
{
    private Integer id;
    private Integer code;
    private String name;
    private Boolean status;

}
