package com.noteu.noteu.reference.controller;

import com.noteu.noteu.reference.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/references")
public class ReferenceController {

    private final ReferenceService referenceService;
}
