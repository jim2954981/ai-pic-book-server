create table paragraph
(
    id          bigint auto_increment
        primary key,
    work_id     bigint        default 0                 not null,
    content     varchar(3000) default ''                not null,
    pic         longblob                                null,
    create_time datetime      default CURRENT_TIMESTAMP not null,
    update_time datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    `order`     int           default 1                 not null
)
    charset = utf8;

create index idx_work_id
    on paragraph (work_id);
create table user
(
    id          bigint auto_increment
        primary key,
    wx_open_id  varchar(1000) default ''                not null,
    add_time    datetime      default CURRENT_TIMESTAMP not null,
    update_time datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint uniq_wx_open_id
        unique (wx_open_id)
)
    charset = utf8;
create table work
(
    id             bigint auto_increment
        primary key,
    user_id        bigint        default 0                 not null,
    title          varchar(100)  default ''                not null,
    story_abstract varchar(3000) default ''                not null,
    first_pic      longblob                                null,
    read_time      int           default 3                 not null,
    child_age      int           default 3                 not null,
    `character`    int           default 1                 not null,
    create_time    datetime      default CURRENT_TIMESTAMP not null,
    update_time    datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    charset = utf8;

create index idx_user_id
    on work (user_id);





