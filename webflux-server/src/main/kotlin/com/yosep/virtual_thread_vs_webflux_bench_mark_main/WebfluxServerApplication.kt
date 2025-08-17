package com.yosep.virtual_thread_vs_webflux_bench_mark_main.com.yosep.virtual_thread_vs_webflux_bench_mark_main

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebfluxServerApplication

fun main(args: Array<String>) {
    runApplication<WebfluxServerApplication>(*args)
}
