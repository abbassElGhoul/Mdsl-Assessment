package com.mdsl.assessment.institution;

import com.mdsl.assessment.customResponse.Response;
import com.mdsl.assessment.institution.Dto.CreateUpdateInstituteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService
{
    private final InstitutionRepository institutionRepository;

    public Response getAll()
    {
        try
        {

            return new Response(institutionRepository.findAll());
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Response getById(Integer id)
    {
        try
        {
            return new Response(institutionRepository.findById(id));
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    public Response createOrUpdate(CreateUpdateInstituteDto createUpdateInstituteDto)
    {
        try
        {
            if (createUpdateInstituteDto.getId() != null)
            {
                Optional<InstitutionEntity> institute = institutionRepository.findById(createUpdateInstituteDto.getId());
                if (institute.isPresent())
                {
                    institute.get().setName(createUpdateInstituteDto.getName());
                    institute.get().setCode(createUpdateInstituteDto.getCode());
                    institute.get().setStatus(createUpdateInstituteDto.getStatus());
                    return new Response(institutionRepository.save(institute.get()));
                }
                else
                {
                    log.warn("creating institute with new id");
                }
            }
            InstitutionEntity institutionEntity = new InstitutionEntity(createUpdateInstituteDto);
            return  new Response(institutionRepository.save(institutionEntity));
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Response getActive()
    {
        try
        {
            return new Response(institutionRepository.findByStatusTrue());
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Response deleteInstituteById(Integer id)
    {
        {
            try
            {
                institutionRepository.deleteById(id);
                return new Response(id);
            }
            catch (Exception e)
            {
                log.error(e.getMessage());
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }

    }
}

