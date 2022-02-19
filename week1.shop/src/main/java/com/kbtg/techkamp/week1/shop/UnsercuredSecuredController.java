package com.kbtg.techkamp.week1.shop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/unsecured")
public class UnsercuredSecuredController {

    @GetMapping
    public ResponseEntity reachSecureEndpoint() {

        return new ResponseEntity("If your are reading this you reached a secure endpoint", HttpStatus.OK);
    }

    @GetMapping("test")
    public ResponseEntity reachSecureEndpointtest() {

        return new ResponseEntity("If rea", HttpStatus.OK);
    }
}