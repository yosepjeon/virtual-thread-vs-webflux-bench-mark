package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.repository

import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.entity.TestData
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TestDataRepository : ReactiveCrudRepository<TestData, Long>
