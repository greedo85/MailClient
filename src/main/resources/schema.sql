CREATE TABLE IF NOT EXISTS mail
(
    id int PRIMARY_KEY,
    sent_from INT NOT NULL,
    subject TEXT,
    date TEXT NOT NULL UNIQUE,
    type TEXT NOT NULL,
    sent_to INT NOT NULL,
    content TEXT,
    FOREIGN KEY (sent_from) REFERENCES address (id),
    FOREIGN KEY (sent_to) REFERENCES address (id)
)


CREATE TABLE IF NOT EXISTS address
(
    id INT PRIMARY_KEY NOT NULL,
    mail_address TEXT NOT NULL
)