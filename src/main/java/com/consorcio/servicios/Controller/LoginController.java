package com.consorcio.servicios.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoginController {
    
    @PostMapping(value = "/demo")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Welcome from secure endpoint");
    }
    
}