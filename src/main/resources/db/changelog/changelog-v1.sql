CREATE TABLE `user`
(
    `id`        bigint(20) NOT NULL,
    `email`     varchar(255) DEFAULT NULL,
    `name`      varchar(255) DEFAULT NULL,
    `birthDate` varchar(255) DEFAULT NULL,
    `password`  varchar(255) DEFAULT NULL,
    `phoneNumber`     varchar(255) DEFAULT NULL,
    `gender`    varchar(255) DEFAULT NULL,
    `country`   varchar(255) DEFAULT NULL,
    `username`   varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `role`
(
    `id`        bigint(20) NOT NULL,
    `name`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user_roles`
(
    `user_id`        bigint(20) NOT NULL,
    `role_id`       bigint(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
        ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id)
        ON DELETE CASCADE
);

INSERT INTO `userregistrationdb`.`role`
(`id`,`name`) VALUES
(1,'ADMIN'),
(2,'USER');

INSERT INTO `userregistrationdb`.`user`
(`id`,`email`,`name`,`birthDate`,`password`,`phoneNumber`,`gender`,`country`,`username`) VALUES
(1,'test@gmail.com','test','2022-05-14','$2a$10$V4dErIvHdXFAg6wV8St.suGQq9h9VSvXNEONCIYmC.V1G03YJ3Qde',55213564,'female','France','test');

INSERT INTO `userregistrationdb`.`user_roles`
(`user_id`,`role_id`) VALUES
(1,1);
