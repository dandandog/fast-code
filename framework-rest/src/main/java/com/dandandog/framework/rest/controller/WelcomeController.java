package com.dandandog.framework.rest.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JohnnyLiu
 */
@Slf4j
@RestController
@Api(value = "Default API", tags = {"Index"})
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome use fast code";
    }


}
