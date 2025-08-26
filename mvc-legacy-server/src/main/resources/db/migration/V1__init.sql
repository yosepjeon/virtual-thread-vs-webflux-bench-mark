create table test_data (
    id          int auto_increment primary key,
    name        varchar(100) not null comment '이름'
    value_1       int not null comment '값',
) comment '테스트 데이터 테이블';