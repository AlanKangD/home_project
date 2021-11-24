create table m_board(
Write_no number(10) primary key,
Title varchar2(100),
Content varchar2(300),
Savedate date default sysdate,
Hit number(10) default 0,
Image_file_name varchar(100),
Id varchar(20) not null,
constraint fk_test01 foreign key(id) references membership(id) on delete cascade
);
create sequence m_board_seq;