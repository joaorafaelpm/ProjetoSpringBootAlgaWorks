create table if not exists veiculos  (
    id bigint not null auto_increment,
    proprietario_id bigint not null ,
    marca varchar(20) not null ,
    modelo varchar(20) not null ,
    placa varchar(7) unique ,
    status varchar(20) not null,
    data_cadastro datetime not null ,
    data_apreenssao datetime ,

    primary key (id),
    constraint fk_veiculo_proprietario foreign key (proprietario_id) references proprietarios(id)
);
