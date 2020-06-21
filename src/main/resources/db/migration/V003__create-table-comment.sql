create table comment (
	id bigint not null auto_increment,
    order_service_id bigint not null,
    description text not null,
    date_send datetime not null,
    primary key (id)
);

alter table comment add constraint fk_commnet_order_service
foreign key (order_service_id) references order_service (id);