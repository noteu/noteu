package com.noteu.noteu.reference.service.impl;

import com.noteu.noteu.reference.repository.ReferenceRoomRepository;
import com.noteu.noteu.reference.service.ReferenceRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReferenceRoomServiceImpl implements ReferenceRoomService {

    private final ReferenceRoomRepository referenceRoomRepository;
}
