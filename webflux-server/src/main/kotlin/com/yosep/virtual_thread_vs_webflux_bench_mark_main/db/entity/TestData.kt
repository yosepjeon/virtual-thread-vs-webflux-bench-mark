package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("test_data")
data class TestData(
    @Id val id: Long?,
    val name: String,
    @Column("value_1") val value1: Int
)
