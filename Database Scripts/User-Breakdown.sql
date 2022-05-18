--User Table

CREATE TABLE `electrogrid`.`user` (
  `userID` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `accountNumber` INT NOT NULL,
  `address` VARCHAR(100) NULL,
  `NIC` VARCHAR(20) NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`userID`));

ALTER TABLE user
ADD COLUMN reset_code VARCHAR(45) NOT NULL;

ALTER TABLE user
ADD COLUMN userRole VARCHAR(45) NOT NULL;

ALTER TABLE user 
ADD CONSTRAINT role_constraint CHECK (userRole = 'Admin' OR userRole = 'User' OR userRole = 'Supplier');


--Breakdown Table

CREATE TABLE `electrogrid`.`breakdown` (
  `breakdownID` INT NOT NULL AUTO_INCREMENT,
  `region` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `user` INT NOT NULL,
  PRIMARY KEY (`breakdownID`),
  INDEX `user_idx` (`user` ASC) VISIBLE,
  CONSTRAINT `user`
    FOREIGN KEY (`user`)
    REFERENCES `electrogrid`.`user` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE breakdown
ADD COLUMN date VARCHAR(45);

ALTER TABLE breakdown
ADD COLUMN status INT;

ALTER TABLE breakdown
ADD CONSTRAINT fk_status FOREIGN KEY(status) REFERENCES breakdown_status(value);


--Breakdown_Status Table

CREATE TABLE `electrogrid`.`breakdown_status` (
  `value` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`value`));

INSERT INTO `electrogrid`.`breakdown_status` (`value`, `description`) VALUES ('0', 'Pending');
INSERT INTO `electrogrid`.`breakdown_status` (`value`, `description`) VALUES ('1', 'Solving');
INSERT INTO `electrogrid`.`breakdown_status` (`value`, `description`) VALUES ('2', 'Resolved');