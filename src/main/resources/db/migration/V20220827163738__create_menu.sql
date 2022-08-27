create table if not exists tb_menu
(
    id        int auto_increment
        primary key,
    meta_str  varchar(255) not null,
    name      varchar(255) not null,
    parent_id int          not null,
    status    int          not null,
    constraint UK_kp4434gmu2msmrfsps1yptbap
        unique (name)
);
