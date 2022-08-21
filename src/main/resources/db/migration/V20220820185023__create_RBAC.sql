create table if not exists tb_action
(
    id   int auto_increment
    primary key,
    code varchar(30) not null,
    constraint UK_o0wj725e9ldpv8c0ix1ajwx4u
    unique (code)
    );

create table if not exists tb_model
(
    id   int auto_increment
    primary key,
    code varchar(30) not null,
    constraint UK_hwuu9hkwl36lkuw9rs41i2snb
    unique (code)
    );

create table if not exists tb_permission
(
    id        int auto_increment
    primary key,
    code      varchar(30) not null,
    action_id int         null,
    model_id  int         null,
    constraint UK_645ijnp55ceby9me6kgrwtyfn
    unique (code),
    constraint FK33co2d7af1w2w9pq36cew7wwu
    foreign key (action_id) references tb_action (id),
    constraint FK6cxel6t3obntfb27kxa99jcwc
    foreign key (model_id) references tb_model (id)
    );

create table if not exists tb_role
(
    id        int auto_increment
    primary key,
    role_name varchar(30) not null,
    constraint UK_c9lijtmr0x68iu1vxftbu2u33
    unique (role_name)
    );

create table if not exists m_role_permission
(
    role_id       int not null,
    permission_id int not null,
    primary key (role_id, permission_id),
    constraint FK2v6bjs4yq2a7wrwwmib68prfq
    foreign key (role_id) references tb_role (id),
    constraint FKax4396y3j1gah6jd978xm2nl9
    foreign key (permission_id) references tb_permission (id)
    );

create table if not exists tb_user
(
    id       int auto_increment
    primary key,
    username varchar(30) not null,
    password varchar(30) not null,
    constraint UK_4wv83hfajry5tdoamn8wsqa6x
    unique (username)
    );

create table if not exists m_user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint FKf3ocdl42ic2vlcygu5c245ban
    foreign key (user_id) references tb_user (id),
    constraint FKpscow3kh16gq51lx5hqmg9p7o
    foreign key (role_id) references tb_role (id)
    );

