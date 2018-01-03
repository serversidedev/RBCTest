CREATE TABLE `rbctest`.`appconfig` (
  `appConfigId` INT NOT NULL,
  `version` VARCHAR(5) NOT NULL,
  `appCode` VARCHAR(5) NOT NULL,
  `file` BLOB NULL,
  PRIMARY KEY (`appConfigId`));
