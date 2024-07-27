package com.goit.GoIT2_HW_15.test;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class TestService {

    public String getCurrentTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
    }
}
