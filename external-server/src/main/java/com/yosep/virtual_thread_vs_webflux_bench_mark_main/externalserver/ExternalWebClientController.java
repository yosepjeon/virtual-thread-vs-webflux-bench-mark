package com.yosep.virtual_thread_vs_webflux_bench_mark_main.externalserver;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ExternalWebClientController {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping(value = "/external/api-1", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map> getExternalJson() {
        int value = atomicInteger.incrementAndGet();
        Mono<Map> response = Mono.just(
                Map.of(
                        "message", "Hello from external server",
                        "value", value
                )
        );

        if (value % 2 == 0) {
            System.out.println("Delaying response: " + value);
            return response.delayElement(Duration.ofSeconds(10)).cast(Map.class);
        } else {
            return response.cast(Map.class);
        }
    }
}