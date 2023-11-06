package com.noteu.noteu.reference.controller;

import com.noteu.noteu.reference.service.ReferenceRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/references")
public class ReferenceRoomController {

    private final ReferenceRoomService referenceRoomService;
}
