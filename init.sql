CREATE DATABASE IF NOT EXISTS test;

USE test;

CREATE TABLE IF NOT EXISTS `USERGENDER` (
  `UGID` int NOT NULL AUTO_INCREMENT,
  `UGLABEL` varchar(255) NOT NULL,
  `UGINSERTKEY` char(1) NOT NULL UNIQUE,
  `UGCREATDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UGMODIFDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UGMODIFUSER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UGID`)
);

DELETE FROM `USERGENDER`;

INSERT INTO `USERGENDER` (`UGID`, `UGLABEL`, `UGINSERTKEY`, `UGMODIFUSER`) VALUES
(1, 'Male', 'm', 'System'),
(2, 'Female', 'f', 'System');

CREATE TABLE IF NOT EXISTS `USERS` (
  `USRNUMBER` int NOT NULL AUTO_INCREMENT,
  `USRFIRSTNAME` varchar(255) NOT NULL,
  `USRLASTNAME` varchar(255) NOT NULL,
  `USRBIRTHDATE` date NOT NULL,
  `USRGENDER` int NOT NULL,
  `USRCREATDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USRMODIFDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USRMODIFUSER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USRNUMBER`),
  FOREIGN KEY (`USRGENDER`) REFERENCES `USERGENDER` (`UGID`)
);

CREATE TABLE IF NOT EXISTS `CHECKACCOUNT` (
  `CANUMBER` int NOT NULL AUTO_INCREMENT,
  `CANAME` varchar(255) NOT NULL,
  `CACREDIT` decimal(10,2) NOT NULL DEFAULT 0.00,
  `CAPIN` varchar(6) NOT NULL,
  `CAOVERDRAFTLIM` decimal(10,2),
  `CACREATDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CAMODIFDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CAMODIFUSER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CANUMBER`)
);

CREATE TABLE IF NOT EXISTS `USERCHECKACC` (
  `UCAUSERNR` int NOT NULL,
  `UCACHECKACC` int NOT NULL,
  `UCACREATDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UCAMODIFDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UCAMODIFUSER` varchar(255) NOT NULL,
  PRIMARY KEY (`UCAUSERNR`, `UCACHECKACC`),
  FOREIGN KEY (UCAUSERNR) REFERENCES `USERS` (`USRNUMBER`),
  FOREIGN KEY (UCACHECKACC) REFERENCES `CHECKACCOUNT` (`CANUMBER`)
);

CREATE TRIGGER `USERCHECKACC_UPDATE` BEFORE UPDATE ON `USERCHECKACC` FOR EACH ROW SET new.CAMODIFDATE := now();

CREATE TRIGGER `CHECKACCOUNT_UPDATE` BEFORE UPDATE ON `CHECKACCOUNT` FOR EACH ROW SET new.UCAMODIFDATE = now();

CREATE TRIGGER `USERGENDER_UPDATE` BEFORE UPDATE ON `USERGENDER` FOR EACH ROW SET new.UGMODIFDATE := now();

CREATE TRIGGER `USERS_UPDATE` BEFORE UPDATE ON `USERS` FOR EACH ROW SET new.USRMODIFDATE = now();

  