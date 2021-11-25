create table reply01(
id varchar(20),
title varchar(50),
content varchar(300),
write_group number(10),
write_date date default sysdate,
constraint fk_test02 foreign key(write_group) references m_board(write_no) on delete cascade,
constraint fk_test03 foreign key(id) references membership(id) on delete cascade
);