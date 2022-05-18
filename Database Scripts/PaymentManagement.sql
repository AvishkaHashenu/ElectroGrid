CREATE TABLE `electrogrid`.`user` (
`userID` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(45) NOT NULL,
`password` VARCHAR(45) NOT NULL,
`accountNumber` INT NOT NULL,
`address` VARCHAR(100) NULL,
`NIC` VARCHAR(20) NULL,
`phone` VARCHAR(45) NULL,
PRIMARY KEY (`userID`));





CREATE TABLE `electrogrid`.`payment_info` (
  `CreditID` INT NOT NULL,
  `UserID` INT NOT NULL,
  `SecurityNum` VARCHAR(45) NOT NULL,
  `ExpireDate` DATETIME NOT NULL,
  PRIMARY KEY (`CreditID`));
ALTER TABLE electrogrid.Payment_Info
ADD CONSTRAINT FK_UserPayment
FOREIGN KEY (UserID) REFERENCES
electrogrid.user(userID);

CREATE TABLE `electrogrid`.`payment` (
  `PaymentID` INT NOT NULL ,
  `UserID` INT NOT NULL ,
  `Payment_Method` VARCHAR(100) NOT NULL,
  `Payment_Date` DATETIME NOT NULL,
  `Amount` DECIMAL(2) NOT NULL,
  `Consumption_Unit` INT NOT NULL,
  `NoOfUnits` INT NOT NULL,
  PRIMARY KEY (`PaymentID`));


ALTER TABLE electrogrid.Payment
ADD CONSTRAINT FK_Payment
FOREIGN KEY (UserID) REFERENCES
electrogrid.user(userID);