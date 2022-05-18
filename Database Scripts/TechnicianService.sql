CREATE TABLE `electrogrid`.`power_technician` (
  `TechnicianID` INT NOT NULL AUTO_INCREMENT,
  `TechnicianName` VARCHAR(50) NULL,
  `TechnicianAddress` VARCHAR(100) NULL,
  `TechnicianEmail` VARCHAR(50) NULL,
  `TechnicianPhone` INT NULL,
  `Type` VARCHAR(50) NULL,
  `ContractTech_Salary` DECIMAL(10,2) NULL,
  `HourlyTech_HourlyWages` DECIMAL(10,2) NULL,
  PRIMARY KEY (`TechnicianID`));


CREATE TABLE `electrogrid`.`technicians_attendance_details` (
  `Technician_AttendanceID` INT NOT NULL,
  `TechnicianID` INT NULL,
  `Date` DATETIME NULL,
  `AttendanceStatus` BINARY(1) NULL,
  PRIMARY KEY (`Technician_AttendanceID`));


ALTER TABLE electrogrid.technicians_attendance_details
ADD CONSTRAINT FK_Attendance
FOREIGN KEY (TechnicianID) REFERENCES
electrogrid.power_technician(TechnicianID);

ALTER TABLE electrogrid.power_technician ADD CONSTRAINT
type_constraint CHECK (Type = 'C' OR Type = 'c' OR Type = 'H' OR Type = 'h');

ALTER TABLE electrogrid.technicians_attendance_details ADD CONSTRAINT
type_constraint2 CHECK (AttendanceStatus = 0 OR AttendanceStatus = 1);