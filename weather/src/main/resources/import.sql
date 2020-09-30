insert into user_entity (user_name, password) values ('admin', 'admin');
insert into role (role) values ('ADMIN');
insert into role (role) values ('USER');
insert into users_role (role_id, user_id) values (1,1);