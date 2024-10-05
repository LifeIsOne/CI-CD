-- user_tb
INSERT INTO user_tb (username, password, email, created_at)
VALUES  ('Kenneth', '1234', 'Kenneth@nate.com',now()),
        ('Minsu', '1234', 'Minsu@nate.com',now()),
        ('Kim', '1234', 'Kim@nate.com',now()),
        ('Danny', '1234', 'Danny@nate.com',now()),
        ('Yongju', '1234', 'Yongju@nate.com',now()),
        ('Lee', '1234', 'Lee@nate.com',now());

-- board_tb
INSERT INTO board_tb(title, content, user_id, created_at)
VALUES  ('제목1', '내용1', 1, now()),
        ('제목2', '내용2', 1, now()),
        ('제목3', '내용3', 2, now()),
        ('제목4', '내용4', 3, now()),
        ('제목5', '내용5', 4, now()),
        ('제목6', '내용6', 5, now());

-- reply_tb
INSERT INTO reply_tb(comment, board_id, user_id, created_at)
VALUES  ('댓글1', 4, 1, now()),
        ('댓글2', 4, 1, now()),
        ('댓글3', 4, 2, now()),
        ('댓글4', 3, 2, now());