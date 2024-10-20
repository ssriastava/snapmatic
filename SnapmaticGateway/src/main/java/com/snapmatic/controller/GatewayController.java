package com.snapmatic.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Random;

@RestController
public class GatewayController {

    private final DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate= new RestTemplate();

    public GatewayController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @PostMapping(value = "/auth/**", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> forwardRequest(HttpEntity<String> requestEntity, HttpServletRequest request) {
        List<ServiceInstance> instances = discoveryClient.getInstances("SNAPMATIC-AUTH");
        if(instances == null || instances.isEmpty()) {
            return ResponseEntity.status(503).body("No available instances for the authentication service.");
        }
        ServiceInstance instance = instances.get(new Random().nextInt(instances.size()));
        URI serviceUri = instance.getUri();

        String forwardUrl = serviceUri.toString() + "/auth/"+request.getRequestURI().split("//")[request.getRequestURI().length()-1];

        ResponseEntity<String> response = restTemplate.exchange(
                forwardUrl,
                HttpMethod.POST,
                new HttpEntity<>(requestEntity.getBody(), requestEntity.getHeaders()),
                String.class
        );

        return response;

    }


}
