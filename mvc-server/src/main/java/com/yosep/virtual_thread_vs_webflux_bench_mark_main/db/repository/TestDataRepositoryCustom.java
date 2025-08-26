package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.repository;

import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.entity.TestData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class TestDataRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public TestData findWithDelay(Long id, int delay) {
        // 데이터 조회 쿼리
        String selectQuery = "SELECT id, name, value_1 FROM test_data WHERE id = :id";
        Object[] result = (Object[]) entityManager.createNativeQuery(selectQuery)
                .setParameter("id", id)
                .getSingleResult();

        // 딜레이 추가
        entityManager.createNativeQuery("SELECT SLEEP(:delay)")
                .setParameter("delay", delay)
                .getSingleResult(); // executeUpdate 대신 getSingleResult 사용

        return new TestData(
                ((Number) result[0]).longValue(),
                (String) result[1],
                ((Number) result[2]).intValue()
        );
    }

    @Transactional
    public void saveWithDelay(String name, int value1, int delay) {
        // 데이터 저장 쿼리
        String insertQuery = "INSERT INTO test_data (name, value_1) VALUES (:name, :value1)";
        entityManager.createNativeQuery(insertQuery)
                .setParameter("name", name)
                .setParameter("value1", value1)
                .executeUpdate();

        // 딜레이 추가
        entityManager.createNativeQuery("SELECT SLEEP(:delay)")
                .setParameter("delay", delay)
                .getSingleResult(); // SLEEP은 결과 집합을 반환하므로 executeUpdate 대신 getSingleResult 사용
    }
}