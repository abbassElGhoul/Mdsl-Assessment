package com.mdsl.assessment.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Integer>
{

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO INSTITUTION (code, name, status) VALUES (?1, ?2, true)", nativeQuery = true)
    void createActive(int code, String name);

    List<InstitutionEntity> findByStatusTrue();
}
