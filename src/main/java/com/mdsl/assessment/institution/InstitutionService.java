package com.mdsl.assessment.institution;

import com.mdsl.assessment.customResponse.Response;
import com.mdsl.assessment.institution.Dto.CreateUpdateInstituteDto;
import org.springframework.stereotype.Service;

@Service
public interface InstitutionService
{

    Response getAll();

    Response getById(Integer id);

    Response createOrUpdate(CreateUpdateInstituteDto createUpdateInstituteDto);

    Response getActive();

    Response deleteInstituteById(Integer id);
}
