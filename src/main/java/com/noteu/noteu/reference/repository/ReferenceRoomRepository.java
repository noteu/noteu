package com.noteu.noteu.reference.repository;


import com.noteu.noteu.reference.entity.ReferenceRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ReferenceRoomRepository extends JpaRepository<ReferenceRoom, Long> {

    Page<ReferenceRoom> findAll(Pageable pageable);

    Page<ReferenceRoom> findByReferenceRoomTitleContaining(Pageable pageable, String searchWord);
}