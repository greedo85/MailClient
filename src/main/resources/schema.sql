CREATE TABLE IF NOT EXISTS mail
 (
     id INTEGER PRIMARY KEY AUTOINCREMENT,
     sent_from INT NOT NULL,
     subject TEXT,
     date TEXT NOT NULL UNIQUE,
     type TEXT NOT NULL,
     content TEXT,
     FOREIGN KEY (sent_from) REFERENCES address (id)
 )

 CREATE TABLE IF NOT EXISTS mail_address
  (
     id INTEGER PRIMARY KEY AUTOINCREMENT,
     id_mail INTEGER NOT NULL,
     id_address INTEGER NOT NULL,
     FOREIGN KEY (id_mail) REFERENCES mail(id),
     FOREIGN KEY (id_address) REFERENCES address(id)
  )

 CREATE TABLE IF NOT EXISTS address
 (
     id INTEGER PRIMARY KEY AUTOINCREMENT,
     mail_address TEXT NOT NULL UNIQUE
 )

 INSERT INTO ADDRESS VALUES (null,"mojmail@mojmail.pl")