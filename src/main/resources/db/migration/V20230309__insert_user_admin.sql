insert into public.user (id, username, email, password, created_at, created_by, updated_at, updated_by, deleted)
values  (1, 'admin', 'admin', '$2a$12$IHxBjjE5J/MADkUj2d0HvuqzNcvBogsYZZibA5YJdJADWcVxw0xmG', '2023-03-09 15:49:19.379399', 0, '2023-03-09 15:49:19.379399', 0, false),
        (2, 'nhi.tranthianh@ncc.asia', 'nhi.tranthianh@ncc.asia', '$2a$10$OV4OLHImyDMGH4r42zq2v.s2HxKHMHreOd18kOEIFwqa3MXW13Pq6', '2023-03-15 17:00:22.718141', 0, '2023-03-15 17:00:22.718141', 0, false);
INSERT INTO public.user_role (user_id, role_id) VALUES (1, 2), (2, 2);