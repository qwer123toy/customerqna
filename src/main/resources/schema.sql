 create table if not exists customerqna(
article_id INT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(200) NOT NULL,
content TEXT NOT NULL,
username VARCHAR(20) NOT NULL,
password VARCHAR(64) NOT NULL,
views INT DEFAULT 0,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
is_secure bit DEFAULT 0,
is_deleted bit DEFAULT 0   
);

INSERT INTO customerqna (title, content, username, password) VALUES ('제목','내용','이름','1');
INSERT INTO customerqna (title, content, username, password) VALUES ('제목2','내용2','이름2','1');
INSERT INTO customerqna (title, content, username, password) VALUES ('제목3','내용2','이름3','aa');
INSERT INTO customerqna (title, content, username, password) VALUES ('제목4','내용2','이름4','1');
INSERT INTO customerqna (title, content, username, password) VALUES ('제목5','내용2','이름5','1');
INSERT INTO customerqna (title, content, username, password) VALUES ('제목6','내용2','이름6','1');
INSERT INTO customerqna (title, content, username, password) VALUES ('제목7','내용2','이름6','1');
INSERT INTO customerqna (title, content, username, password) VALUES ('제목4','내용2','이름4','1');
INSERT INTO customerqna (title, content, username, password) VALUES ('제목5','내용2','이름5','1');
INSERT INTO customerqna (title, content, username, password) VALUES ('제목6','내용2','이름6','1');
INSERT INTO customerqna (title, content, username, password) VALUES ('제목7','내용2','이름6','1');


update customerqna set is_secure=1;