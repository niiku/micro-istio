package com.joachimprinzbach.greetingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingRestController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "greeting")
    public GreetingDto greet() {
        LOGGER.info("Calling greeting");
        String response = restTemplate.getForObject("http://concat-service:8091/concat", String.class);
        LOGGER.info("Received value from concat service: " + response);
        return new GreetingDto("Hello " + response);
    }
}
