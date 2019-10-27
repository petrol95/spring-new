ALTER TABLE students
    COLLATE = latin1_general_ci ,
    ADD COLUMN `lastname` VARCHAR(50) NULL AFTER `firstname`,
    ADD COLUMN `phone` VARCHAR(50) NULL DEFAULT NULL AFTER `lastname`,
    ADD COLUMN `email` VARCHAR(50) NULL DEFAULT NULL AFTER `phone`,
    ADD COLUMN `user_id` INT(11) NULL AFTER `email`,
    ADD COLUMN `add_date` DATETIME NULL AFTER `user_id`,
    CHANGE COLUMN `name` `firstname` VARCHAR(50) NULL ,
    ADD INDEX `FK_USER_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE students
    ADD CONSTRAINT `FK_USER`
        FOREIGN KEY (`user_id`)
            REFERENCES user (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
