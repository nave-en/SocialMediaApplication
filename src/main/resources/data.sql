INSERT INTO USER_DETAILS (id, name, birth_date) VALUES
(1, 'superstar', CURRENT_DATE),
(2, 'star', CURRENT_DATE),
(3, 'super', CURRENT_DATE);

INSERT INTO POST (id, description, user_id) VALUES
(101, 'Completed Spring Boot', 1),
(102, 'Completed Docker', 1),
(103, 'Completed DSA', 2),
(104, 'Completed AWS', 3);