insert into user_settings_entity (id, days_amount, default_city) values (1, 3, 'Katowice');
insert into user_entity (user_name, password, settings_id) values ('admin', 'admin', 1);
insert into role (role) values ('ADMIN');
insert into role (role) values ('USER');
insert into users_role (role_id, user_id) values (1,1);
SELECT * FROM user_entity;
SELECT * FROM user_settings_entity;
SELECT us.* FROM user_settings_entity AS us JOIN user_entity AS ue ON ue.settings_id = us.id where ue.id = 1;