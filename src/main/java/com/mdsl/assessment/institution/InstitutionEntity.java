package com.mdsl.assessment.institution;


import com.mdsl.assessment.institution.Dto.CreateUpdateInstituteDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "INSTITUTION")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class InstitutionEntity
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer code;
    private String name;
    private Boolean status;

    public InstitutionEntity(CreateUpdateInstituteDto createUpdateInstituteDto)
    {
        this.code = createUpdateInstituteDto.getCode();
        this.name = createUpdateInstituteDto.getName();
        this.status = createUpdateInstituteDto.getStatus();
    }
}
