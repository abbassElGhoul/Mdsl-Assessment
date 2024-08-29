package com.mdsl.assessment.institution;

import com.mdsl.assessment.customResponse.Response;
import com.mdsl.assessment.institution.Dto.CreateUpdateInstituteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/institution")
@RequiredArgsConstructor
@Log4j2
public class InstitutionController
{
    private final InstitutionService institutionService;

    @GetMapping("get-all")
    public Response getAll()
    {
        try
        {
            return institutionService.getAll();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("get-by-id")
    public Response getById(@RequestParam Integer id)
    {
        try
        {
            return institutionService.getById(id);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("get-active")
    public Response getActive()
    {
        try
        {
            return institutionService.getActive();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @PostMapping("create-update")
    public Response createOrUpdate(@RequestBody CreateUpdateInstituteDto createUpdateInstituteDto)
    {
        try
        {
            return institutionService.createOrUpdate(createUpdateInstituteDto);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @DeleteMapping("delete-by-id")
    public Response deleteInstituteById(@RequestParam Integer id)
    {
        try
        {
            return institutionService.deleteInstituteById(id);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
