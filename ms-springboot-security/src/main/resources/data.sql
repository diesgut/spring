INSERT INTO `roles` (name) VALUES ('ADMIN');
INSERT INTO `roles` (name) VALUES ('USER');

INSERT INTO `users` (username, password, enabled) VALUES ('admin', '$2a$10$xPYGtptul0VehDk/wG34Qev86KSui0PpeQY0sA0/wUVvbTkPIJOay', TRUE); --pass is admin
INSERT INTO `user_roles` (user_id, role_id) VALUES (1, 1);

INSERT INTO `users` (username, password, enabled) VALUES ('user', '$2a$10$xPYGtptul0VehDk/wG34Qev86KSui0PpeQY0sA0/wUVvbTkPIJOay', TRUE); --pass is admin
INSERT INTO `user_roles` (user_id, role_id) VALUES (2, 2);
