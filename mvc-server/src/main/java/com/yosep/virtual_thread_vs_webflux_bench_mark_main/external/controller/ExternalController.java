package com.yosep.virtual_thread_vs_webflux_bench_mark_main.external.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@RestController
@RequestMapping("/external")
public class ExternalController {

    private final WebClient externalClient;
    private final String externalServerUrl;

    public ExternalController(WebClient webClient, @Value("${external.server.url:http://localhost:20003}") String externalServerUrl) {
        this.externalClient = webClient;
        this.externalServerUrl = externalServerUrl;
    }

    @GetMapping(value = "/api-1", produces = "application/json")
    public Map getExternalJson() {
        return externalClient.get()
                .uri("/external/api-1")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}

//package com.yosep.virtual_thread_vs_webflux_bench_mark_main.external.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/external")
//public class ExternalController {
//
//    private final RestTemplate restTemplate;
//    private final String externalServerUrl;
//
//    public ExternalController(RestTemplate restTemplate, @Value("${external.server.url:http://localhost:20003}") String externalServerUrl) {
//        this.restTemplate = restTemplate;
//        this.externalServerUrl = externalServerUrl;
//    }
//
//    @GetMapping(value = "/api-1", produces = "application/json")
//    public ResponseEntity<Map> getExternalJson() {
//        String url = externalServerUrl + "/external/api-1";
//        Map response = restTemplate.getForObject(url, Map.class);
//        return ResponseEntity.ok(response);
//    }
//}
