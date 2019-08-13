drop table if exists comment;
drop table if exists review;
drop table if exists product;

create table product
(
    id          integer not null auto_increment,
    created_at  datetime,
    updated_at  datetime,
    cost        bigint,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);


create table review
(
    id          integer not null auto_increment,
    created_at  datetime,
    updated_at  datetime,
    count       bigint,
    description varchar(255),
    product_id  integer not null,
    primary key (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);


create table comment
(
    id         integer not null auto_increment,
    created_at datetime,
    updated_at datetime,
    dislikes   bigint,
    feedback   varchar(255),
    likes      bigint,
    review_id  integer not null,
    primary key (id),
    FOREIGN KEY (review_id) REFERENCES review (id)
);
