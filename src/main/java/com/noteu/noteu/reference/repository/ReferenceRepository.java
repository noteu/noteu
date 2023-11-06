package com.noteu.noteu.reference.repository;

import com.noteu.noteu.reference.entity.Reference;
import com.noteu.noteu.reference.service.ReferenceService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {

}