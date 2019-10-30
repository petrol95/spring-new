DROP TABLE IF EXISTS students;

CREATE TABLE students (
                      id int(11) NOT NULL AUTO_INCREMENT,
                      name varchar(50) DEFAULT NULL,
                      PRIMARY KEY(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=latin1;

INSERT INTO students (name)
VALUES ('Bob#1'), ('Bob#2'), ('Bob#3');