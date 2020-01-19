CREATE TABLE vilce.book
(
    eid          NUMBER(18, 0) NOT NULL,
    cover        varchar2(255) DEFAULT '',
    title        varchar2(255) DEFAULT '',
    author       varchar2(255) DEFAULT '',
    time         varchar2(20)  DEFAULT '',
    press        varchar2(255) DEFAULT '',
    introduction varchar2(255) DEFAULT NULL,
    cid          NUMBER(18, 0) DEFAULT NULL
);
