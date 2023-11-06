package com.noteu.noteu.reference.service.impl;

import com.noteu.noteu.reference.repository.ReferenceRepository;
import com.noteu.noteu.reference.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReferenceServiceImpl implements ReferenceService {

    private final ReferenceRepository referenceRepository;
}
