package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.repository;

import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.entity.TestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TestDataRepository extends JpaRepository<TestData, Long> {

    @Query(value = "SELECT *, SLEEP(:delay) FROM test_data WHERE id = :id", nativeQuery = true)
    Optional<TestData> findWithDelay(Long id, int delay);

    @Query(value = "INSERT INTO test_data (name, value_1) VALUES (:name, :value1), (SLEEP(:delay))", nativeQuery = true)
    void saveWithDelay(@Param("name") String name, @Param("value1") Integer value1, @Param("delay") int delay);
}
