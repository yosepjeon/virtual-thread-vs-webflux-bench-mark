package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.repository

import com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.entity.TestData
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class TestDataRepository2(private val databaseClient: DatabaseClient) {

    suspend fun saveWithDelay(name: String, value1: Int, delaySec: Int) {
        val query = """
            INSERT INTO test_data (name, value_1) VALUES (:name, :value1);
            SELECT SLEEP(:delaySec);
        """
        databaseClient.sql(query)
            .bind("name", name)
            .bind("value1", value1)
            .bind("delaySec", delaySec)
            .then()
            .awaitSingleOrNull()
    }

//    suspend fun findWithDelay(id: Long, delaySec: Int): TestData? {
//        val query = """
//            SELECT id, name, value_1 FROM test_data WHERE id = :id;
//            SELECT SLEEP(:delaySec);
//        """
//        return databaseClient.sql(query)
//            .bind("id", id)
//            .bind("delaySec", delaySec)
//            .map { row ->
//                TestData(
//                    id = row.get("id", Long::class.java),
//                    name = row.get("name", String::class.java) ?: "",
//                    value1 = row.get("value_1", Int::class.java) ?: 0
//                )
//            }
//            .one()
//            .awaitFirstOrNull()
//    }

    suspend fun findWithDelay(id: Long, delaySec: Int): TestData? {
        // 데이터 조회 쿼리
        val query = """
        SELECT id, name, value_1 FROM test_data WHERE id = :id
    """
        val result = databaseClient.sql(query)
            .bind("id", id)
            .map { row ->
                TestData(
                    id = row.get("id", Long::class.java),
                    name = row.get("name", String::class.java) ?: "",
                    value1 = row.get("value_1", Int::class.java) ?: 0
                )
            }
            .one()
            .awaitFirstOrNull()

        // 딜레이 추가
        databaseClient.sql("SELECT SLEEP(:delaySec)")
            .bind("delaySec", delaySec)
            .then()
            .awaitSingleOrNull()

        return result
    }
}