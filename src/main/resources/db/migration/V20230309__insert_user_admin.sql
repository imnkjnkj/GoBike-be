INSERT INTO public."user" (id, username, email, password, created_at, created_by, updated_at, updated_by, deleted)
VALUES (1, 'admin', 'admin', '$2a$12$IHxBjjE5J/MADkUj2d0HvuqzNcvBogsYZZibA5YJdJADWcVxw0xmG',
        '2023-03-09 15:49:19.379399', 0, '2023-03-09 15:49:19.379399', 0, false);
INSERT INTO public.user_role (user_id, role_id) VALUES (1, 2)