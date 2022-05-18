CREATE TABLE `electrogrid`.`power_supplier` (
  `powerSupplierID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `NIC` VARCHAR(20) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`powerSupplierID`));



CREATE TABLE `electrogrid`.`power_grid` (
  `gridID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `resourceType` VARCHAR(45) NOT NULL,
  `resourceCategory` VARCHAR(45) NOT NULL,
  `totalCapacity` INT NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `powerSupplierID` INT NOT NULL,
  PRIMARY KEY (`gridID`),
  INDEX ``fk_powerSupplierID`_idx` (`powerSupplierID` ASC) VISIBLE,
  CONSTRAINT ``fk_powerSupplierID``
    FOREIGN KEY (`powerSupplierID`)
    REFERENCES `electrogrid`.`power_supplier` (`powerSupplierID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE electrogrid.power_grid ADD CONSTRAINT
resourceCategory CHECK (resourceCategory = 'Renewable' OR resourceCategory = 'Nonrenewable')