/*creating database*/
CREATE SCHEMA `electrogrid` ;

/*power_consumption table */
CREATE TABLE `electrogrid`.`power_consumption` (
  `Electricity_AccountNo` VARCHAR(12) NOT NULL,
  `CurrentReading` INT NOT NULL,
  `NoOfUnits` INT NOT NULL,
  `date` DATE NOT NULL,
  `monthYear` VARCHAR(45) NOT NULL,
  `type` VARCHAR(15) NOT NULL,
  `ReaderID` VARCHAR(45) NOT NULL,
  `userID` INT NOT NULL,
  PRIMARY KEY (`Electricity_AccountNo`));

ALTER TABLE `electrogrid`.`power_consumption` 
ADD COLUMN `recordID` VARCHAR(30) NOT NULL FIRST,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`recordID`);

/*Adding foriegn key constraint of userId of the user table to userId of consumption table*/
ALTER TABLE electrogrid.power_consumption
add CONSTRAINT foriegn_Key_userID FOREIGN KEY(userID) REFERENCES electrogrid.user(userID);

/*Adding database validation to check type inserted to the database  is commercial or residential*/
ALTER TABLE electrogrid.power_consumption ADD CONSTRAINT 
check_user_type_constraint CHECK (type = 'commercial' OR type = 'residential');





  /*power_capacity table*/

  CREATE TABLE `electrogrid`.`power_capacity` (
  `capacityID` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  `noOfUnits` INT NOT NULL,
  `supplierID` INT NOT NULL,
  PRIMARY KEY (`capacityID`));

  /*Adding foriegn key constraint of power supplier ID of the supplier table to supplier ID of capacity table*/
  ALTER TABLE electrogrid.power_capacity
  add CONSTRAINT foriegn_Key_supplierID FOREIGN KEY(supplierID) REFERENCES electrogrid.power_supplier(powerSupplierID);


  /*Check the capacity enter to the system is eco or non-eco*/
  ALTER TABLE electrogrid.power_capacity ADD CONSTRAINT 
  environmentally_friendly_constraint CHECK (Type = 'eco' OR type = 'non-eco');





