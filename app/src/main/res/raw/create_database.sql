CREATE TABLE IF NOT EXISTS movie ( _id integer primary key autoincrement, title text not null, genre text not null, director text not null);
INSERT INTO movie (title, genre, director) VALUES ('Man of Steel', 'Action', 'Zack Snyder');
INSERT INTO movie (title, genre, director) VALUES ('Batman v Superman', 'Action','Zack Snyder');
