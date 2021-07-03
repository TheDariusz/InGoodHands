create table verification_token
(
    id          int8 not null,
    token       varchar(255),
    user_id     int8 not null,
    expiry_date timestamp,
    primary key (id),
    constraint fk_user
        foreign key (user_id)
            references "user"
);


