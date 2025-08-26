package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.controller

import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.entity.TestData
import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.repository.TestDataRepository
import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.repository.TestDataRepository2
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/db")
class DatabaseTestController(
    private val testDataRepository: TestDataRepository,
    private val testDataRepository2: TestDataRepository2,
) {

    @PostMapping("/insert-test")
    suspend fun insertTestData(): String {
        testDataRepository.save(TestData(null, "Test Name", 0)).awaitFirstOrNull()

        return "Database test successful!"
    }

    @GetMapping("/get-test/{id}")
    suspend fun getTestData(@PathVariable("id") id: Long): ResponseEntity<TestData> {
        val testData = testDataRepository.findById(id).awaitFirstOrNull()
        return if (testData != null) ResponseEntity.ok(testData) else ResponseEntity.notFound().build()
    }

    @PostMapping("/insert-delay-test")
    suspend fun insertDelayTestData(): String {
        testDataRepository2.saveWithDelay("Test Name", 0, 5)

        return "Database test successful!"
    }

    @GetMapping("/get-delay-test/{id}")
    suspend fun getDelayTestData(@PathVariable("id") id: Long): ResponseEntity<TestData> {
        val testData = testDataRepository2.findWithDelay(1, 5)
//            testDataRepository.findById(id).awaitFirstOrNull()
        return if (testData != null) ResponseEntity.ok(testData) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/delete-test/{id}")
    suspend fun deleteTestData(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        testDataRepository.deleteById(id).awaitFirstOrNull()
        return ResponseEntity.ok().build()
    }

    @PostMapping("/update-test/{id}")
    suspend fun updateTestData(@PathVariable("id") id: Long): ResponseEntity<TestData> {
        val testData = testDataRepository.findById(id).awaitFirstOrNull()
        return if (testData != null) {
            val updatedTestData = testData.copy(value1 = 1)
            testDataRepository.save(updatedTestData).awaitFirstOrNull()
            ResponseEntity.ok(updatedTestData)
        } else ResponseEntity.notFound().build()
    }
}
