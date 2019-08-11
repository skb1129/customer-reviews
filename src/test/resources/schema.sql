drop table if exists comment;
drop table if exists review;
drop table if exists product;

create table product (
       id int not null auto_increment,
        created_at datetime,
        updated_at datetime,
        cost varchar(255),
        description varchar(255),
        name varchar(255),
        primary key (id)
    );

create table review (
       id int not null auto_increment,
        created_at datetime,
        updated_at datetime,
        count bigint,
        product_id int,
        primary key (id),
        FOREIGN KEY (product_id) REFERENCES product(id)
    );



create table comment (
       id int not null auto_increment,
        created_at datetime,
        updated_at datetime,
        dislikes varchar(255),
        feedback varchar(255),
        likes varchar(255),
        review_id int,
        primary key (id),
        FOREIGN KEY (review_id) REFERENCES review(id)
    );