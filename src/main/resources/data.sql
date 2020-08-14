DROP TABLE IF EXISTS writters;
DROP TABLE IF EXISTS books;

CREATE TABLE WRITTERS( 
   ID INT IDENTITY PRIMARY KEY NOT NULL, 
  FIRSTNAME VARCHAR(250) NOT NULL,
  LASTNAME VARCHAR(250) NOT NULL
);

INSERT INTO WRITTERS (FIRSTNAME, LASTNAME) VALUES
('Николай', 'Гоголь'),
('Льюис', 'Кэрролл'),
('Джордж', 'Оруэлл');

CREATE TABLE BOOKS( 
   ID INT IDENTITY PRIMARY KEY NOT NULL, 
  BOOKNAME VARCHAR(250) NOT NULL,
  BOOKDESCRIPTION CLOB NOT NULL,
  BOOKAUTHORID INT NOT NULL,
  BOOKPRICE INT NOT NULL
);

INSERT INTO BOOKS (BOOKNAME, BOOKDESCRIPTION, BOOKAUTHORID, BOOKPRICE ) VALUES
('Алиса в Стране чудес', 'Приключения Алисы в Стране чуде', 2, 280),
('Охота на Снарка', 'Охота команды из девяти человек и бобра за таинственным Снарком', 2, 320),
('История с узелками', 'Одна из образцовых книг по математике', 2, 266),
('Мертвые души', 'Книга рассказывает о похождениях Павла Ивановича Чичикова, главного героя поэмы, бывшего коллежского советника, выдающего себя за помещика.', 1, 175),
('Ревизор', 'Комедия в пяти действиях', 1, 427),
('Вий', 'Vистическая повесть Н.В. Гоголя, впервые опубликованная в его сборнике «Миргород» (1835). Название повести — это имя славянского демонического существа мужского пола, с которым связан сюжет. ', 1, 333),
('1984', 'Про тоталитарный режим и методы управления людьми', 3, 445),
('Фунты лиха в Париже и Лондоне', 'Повесть, основанная на реальных событиях в жизни автора', 3, 530),
('Дни в Бирме', 'Роман основаный на автобиографическом материале', 3, 234);

