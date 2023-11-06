package com.noteu.noteu.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
public class TemplateController {

    @GetMapping("/test")
    public String layoutExtend() {
        return "/layout/test";
    }
}
