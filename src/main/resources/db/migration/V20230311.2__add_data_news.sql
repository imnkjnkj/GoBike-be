INSERT INTO public.news (title, description, status, thumbnail, cover_image, user_id, created_at, created_by,
                         updated_at, updated_by, deleted, category_id)
VALUES ('SpaceX to launch first all-civilian crew to orbit',
        'SpaceX will launch the world''s first all-civilian crew to orbit, led by tech entrepreneur Jared Isaacman',
        'DRAFT', 'https://example.com/thumbnail1.jpg', 'https://example.com/cover_image1.jpg', 1, '2023-03-10 10:30:00',
        1, '2023-03-10 10:30:00', 1, false, 1),
       ('Apple unveils new iPhone 14 lineup',
        'Apple has unveiled its new iPhone 14 lineup, featuring improved cameras, longer battery life, and more',
        'PUBLISH', 'https://example.com/thumbnail2.jpg', 'https://example.com/cover_image2.jpg', 1,
        '2023-03-10 11:15:00', 1, '2023-03-10 11:15:00', 1, false, 1),
       ('Facebook launches new VR headset',
        'Facebook has launched its new VR headset, which promises a more immersive and realistic experience', 'DRAFT',
        'https://example.com/thumbnail3.jpg', 'https://example.com/cover_image3.jpg', 1, '2023-03-10 12:00:00', 1,
        '2023-03-10 12:00:00', 1, false, 1),
       ('New study finds link between coffee and longevity',
        'A new study has found that drinking coffee may be linked to a longer life span', 'PUBLISH',
        'https://example.com/thumbnail4.jpg', 'https://example.com/cover_image4.jpg', 1, '2023-03-10 12:30:00', 1,
        '2023-03-10 12:30:00', 1, false, 1),
       ('Amazon acquires streaming platform',
        'Amazon has acquired a popular streaming platform, expanding its reach in the entertainment industry', 'DRAFT',
        'https://example.com/thumbnail5.jpg', 'https://example.com/cover_image5.jpg', 1, '2023-03-10 13:00:00', 1,
        '2023-03-10 13:00:00', 1, false, 1),
       ('New study finds link between exercise and sleep',
        'A new study has found that regular exercise may help improve sleep quality and reduce insomnia', 'PUBLISH',
        'https://example.com/thumbnail6.jpg', 'https://example.com/cover_image6.jpg', 1, '2023-03-10 13:45:00', 1,
        '2023-03-10 13:45:00', 1, false, 1),
       ('Google unveils new AI-powered language translator',
        'Google has unveiled a new AI-powered language translator, capable of translating multiple languages', 'DRAFT',
        'https://example.com/thumbnail7.jpg', 'https://example.com/cover_image7.jpg', 1, '2023-03-10 14:30:00', 1,
        '2023-03-10 14:30:00', 1, false, 1);