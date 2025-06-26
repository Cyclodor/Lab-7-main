-- Очистка таблиц
DELETE FROM learners;
DELETE FROM courses;

-- Сброс автоинкремента (для PostgreSQL)
ALTER SEQUENCE learners_id_seq RESTART WITH 1;
ALTER SEQUENCE courses_id_seq RESTART WITH 1;

-- Инициализация курсов с явным id
INSERT INTO courses (id, course_name, department) VALUES 
(1, 'Java Programming', 'Computer Science'),
(2, 'Python Development', 'Computer Science'),
(3, 'Web Development', 'Information Technology'),
(4, 'Database Management', 'Computer Science'),
(5, 'Mobile App Development', 'Information Technology');

-- Инициализация студентов
INSERT INTO learners (full_name, given_name, family_name, enrollment_number, course_id) VALUES 
('John Doe', 'John', 'Doe', '2024001', 1),
('Jane Smith', 'Jane', 'Smith', '2024002', 1),
('Bob Johnson', 'Bob', 'Johnson', '2024003', 2),
('Alice Brown', 'Alice', 'Brown', '2024004', 3),
('Charlie Wilson', 'Charlie', 'Wilson', '2024005', 4); 