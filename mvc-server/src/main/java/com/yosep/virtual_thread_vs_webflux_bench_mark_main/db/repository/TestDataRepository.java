package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.repository;

import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.entity.TestData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDataRepository extends JpaRepository<TestData, Long> {
}
