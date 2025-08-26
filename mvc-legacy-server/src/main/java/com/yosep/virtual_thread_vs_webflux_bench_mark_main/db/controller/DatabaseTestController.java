package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.controller;

import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.entity.TestData;
import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.repository.TestDataRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/db")
public class DatabaseTestController {

    private final TestDataRepository testDataRepository;

    public DatabaseTestController(TestDataRepository testDataRepository) {
        this.testDataRepository = testDataRepository;
    }

    @Transactional
    @PostMapping("/insert-test")
    public String testDatabase() {
//        testDataRepository.save(new TestData("Test Name", 0));
        testDataRepository.saveWithDelay("Test Name", 0, 5);

        return "Database test successful!";
    }

    @GetMapping("/get-test/{id}")
    public ResponseEntity getTestData(
            @PathVariable("id") Long id
    ) {
//        return testDataRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());

        return testDataRepository.findWithDelay(id, 5)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @DeleteMapping("/delete-test/{id}")
    public ResponseEntity deleteTestData(
            @PathVariable("id") Long id
    ) {
        testDataRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("/update-test/{id}")
    public ResponseEntity updateTestData(
            @PathVariable("id") Long id) {
        return testDataRepository.findById(id)
                .map(testData -> {
                    testData.updateValue1(1);
                    testDataRepository.save(testData);
                    return ResponseEntity.ok(testData);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
