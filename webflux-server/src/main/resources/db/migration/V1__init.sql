-- db/migration/V1__init.sql
DROP TABLE IF EXISTS `test_data`;

CREATE TABLE IF NOT EXISTS `test_data` (
    `id`        BIGINT NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `name`      VARCHAR(100) NOT NULL COMMENT '이름',
    `value_1`   INT NOT NULL COMMENT '값',
    PRIMARY KEY (`id`)
    ) COMMENT='테스트 데이터 테이블'
    ENGINE=InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;
