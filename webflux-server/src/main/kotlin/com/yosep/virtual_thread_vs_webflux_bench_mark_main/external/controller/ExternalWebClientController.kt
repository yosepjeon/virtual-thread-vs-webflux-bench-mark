package com.yosep.virtual_thread_vs_webflux_bench_mark_main.external.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@RestController
@RequestMapping("/external")
class ExternalWebClientController(
    private val externalClient: WebClient,
) {
    @GetMapping("/api-1", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun getExternalJson(): Map<String, Any> {
        return externalClient.get()
            .uri("/external/api-1")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .awaitBody()
    }
}