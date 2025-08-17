package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.controller;

import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.entity.TestData;
import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.repository.TestDataRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class DatabaseTestController {

    private final TestDataRepository testDataRepository;

    public DatabaseTestController(TestDataRepository testDataRepository) {
        this.testDataRepository = testDataRepository;
    }

    @PostMapping("/test")
    public String testDatabase() {
        testDataRepository.save(new TestData("Test Name"));

        return "Database test successful!";
    }
}
