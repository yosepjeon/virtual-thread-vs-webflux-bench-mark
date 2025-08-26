package com.yosep.virtual_thread_vs_webflux_bench_mark_main.db.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "test_data")
public class TestData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value_1", nullable = false)
    private Integer value1;

    public TestData() {
    }

    public TestData(String name, Integer value1) {
        this.name = name;
        this.value1 = value1;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getValue1() {
        return value1;
    }

    public void updateValue1(Integer value1) {
        this.value1 = value1;
    }
}
