create table order_service (
	id bigint not null auto_increment,
    customer_id bigint not null,
    description text not null,
    price decimal(10,2) not null,
    status varchar(20) not null,
    date_open datetime not null,
    date_close datetime,
    primary key (id)
);

alter table order_service add constraint fk_order_service_customer
foreign key (customer_id) references customer (id);