package com.yosep.virtual_thread_vs_webflux_bench_mark_main

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories


@SpringBootApplication(
    scanBasePackages = ["com.yosep.virtual_thread_vs_webflux_bench_mark_main"]
)
//@EnableR2dbcRepositories(
//    basePackages = ["com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.repository"]
//)
class WebfluxServerApplication

fun main(args: Array<String>) {
    runApplication<WebfluxServerApplication>(*args)
}
