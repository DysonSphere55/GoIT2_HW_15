package com.goit.GoIT2_HW_15.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping(value = "/test")
@Controller
public class TestController {

    private final TestService testService;

    @GetMapping
    public ModelAndView test(@RequestParam(name = "name", required = false) String name) {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("name", name);
        modelAndView.addObject("time", testService.getCurrentTime());
        return modelAndView;
    }
}
