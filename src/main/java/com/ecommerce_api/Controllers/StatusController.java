package com.ecommerce_api.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class StatusController {
    @GetMapping("/status")
    public String GetStatus() {
        return "Server is running";
    }
}
