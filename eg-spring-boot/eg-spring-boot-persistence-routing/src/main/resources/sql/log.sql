CREATE TABLE TB_USER_LOG(
id bigint primary key auto_increment,
uid bigint not null,
content varchar(100) not null
);

INSERT INTO TB_USER_LOG(uid,content)VALUES(1,'register');