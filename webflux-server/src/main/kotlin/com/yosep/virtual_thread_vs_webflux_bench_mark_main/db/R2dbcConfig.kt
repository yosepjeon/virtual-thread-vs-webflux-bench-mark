package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.r2dbc.core.DatabaseClient

@Configuration
class R2dbcConfig(private val connectionFactory: ConnectionFactory) {

    @Bean
    fun databaseClient(): DatabaseClient {
        return DatabaseClient.create(connectionFactory)
    }

    @Bean
    fun r2dbcEntityTemplate(databaseClient: DatabaseClient): R2dbcEntityTemplate {
        return R2dbcEntityTemplate(databaseClient.connectionFactory)
    }
}