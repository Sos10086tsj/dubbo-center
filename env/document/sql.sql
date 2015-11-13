CREATE DATABASE `dubbocenter` /*!40100 DEFAULT CHARACTER SET utf8 */;

DROP TABLE IF EXISTS `dubbocenter`.`job`;
CREATE TABLE `dubbocenter`.`job` (
  `job_id` varchar(45) NOT NULL,
  `job_description` varchar(45) DEFAULT NULL,
  `cmd_startLocation` varchar(45) DEFAULT NULL,
  `cmd_stopLoacation` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `check_interval` int(11) DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `dubbocenter`.`job` 
ADD COLUMN `result` VARCHAR(45) NULL COMMENT '' AFTER `check_interval`;
