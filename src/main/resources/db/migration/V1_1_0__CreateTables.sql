create
    sequence hibernate_sequence start
    1 increment 1;

create table category
(
    id   int4 not null,
    name varchar(255),
    primary key (id)
);

create table institution
(
    id          int8 not null,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);

create table donation
(
    id              int8 not null,
    street          varchar(255),
    city            varchar(255),
    pick_up_comment varchar(255),
    pick_up_date    date,
    pick_up_time    time,
    quantity        int4 not null,
    phone           varchar(64),
    zip_code        varchar(255),
    institution_id  int8 not null,
    primary key (id),
    constraint fk_institution
        foreign key (institution_id)
            references institution
);

create table donation_categories
(
    donation_id   int8 not null,
    categories_id int4 not null,
    primary key (donation_id, categories_id),
    constraint fk_category
        foreign key (categories_id)
            references category,
    constraint fk_donation
        foreign key (donation_id)
            references donation
);



