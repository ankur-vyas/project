-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.73-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema dedupli
--

CREATE DATABASE IF NOT EXISTS dedupli;
USE dedupli;

--
-- Definition of table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`,`name`,`username`,`password`) VALUES 
 (1,'svs','svs','123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


--
-- Definition of table `dlog`
--

DROP TABLE IF EXISTS `dlog`;
CREATE TABLE `dlog` (
  `fileid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(75) NOT NULL DEFAULT '',
  `ddate` varchar(45) NOT NULL DEFAULT '',
  `filename` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dlog`
--

/*!40000 ALTER TABLE `dlog` DISABLE KEYS */;
INSERT INTO `dlog` (`fileid`,`user`,`ddate`,`filename`) VALUES 
 (147,'aa','2016/04/11 13:03:20','changes.txt'),
 (148,'aa','2016/04/11 13:03:58','Death Penalty Cases.txt'),
 (153,'aa','2016/04/13 19:26:48','backup.txt'),
 (154,'abc','2016/04/14 10:22:29','backup.txt');
/*!40000 ALTER TABLE `dlog` ENABLE KEYS */;


--
-- Definition of table `filedetails`
--

DROP TABLE IF EXISTS `filedetails`;
CREATE TABLE `filedetails` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `filename` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `hexvalue` varchar(80) DEFAULT NULL,
  `upfilename` varchar(45) NOT NULL,
  `location1` varchar(100) DEFAULT NULL,
  `location2` varchar(100) DEFAULT NULL,
  `upfdate` varchar(80) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `filedetails`
--

/*!40000 ALTER TABLE `filedetails` DISABLE KEYS */;
INSERT INTO `filedetails` (`id`,`filename`,`user`,`hexvalue`,`upfilename`,`location1`,`location2`,`upfdate`) VALUES 
 (147,'wagholi','aa','8ba806bccc53eadd5f3b5d04217d6935','changes.txt','D:/DeD/part1/changes1.txt','D:/DeD/part2/changes2.txt',''),
 (148,'abcd','aa','5ba5b9af637f690adb34f3eea2e016ab','Death Penalty Cases.txt','D:/DeD/part1/Death Penalty Cases1.txt','D:/DeD/part2/Death Penalty Cases2.txt','2016/04/11 13:00:50'),
 (154,'test','abc','599d9560bb2530c40037bda939ac3cba','backup.txt','D:/DeD/part1/backup1.txt','D:/DeD/part2/backup2.txt','2016/04/14 10:21:19'),
 (155,'test2','abc','1149c3db160623cd4189413ac0aaf63a','oracle.txt','D:/DeD/part1/oracle1.txt','D:/DeD/part2/oracle2.txt','2016/04/14 10:22:12');
/*!40000 ALTER TABLE `filedetails` ENABLE KEYS */;


--
-- Definition of table `requests`
--

DROP TABLE IF EXISTS `requests`;
CREATE TABLE `requests` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(45) NOT NULL,
  `fileid` varchar(45) NOT NULL,
  `filename` varchar(45) NOT NULL,
  `reqtime` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `requests`
--

/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `mobno` varchar(45) NOT NULL,
  `dob` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`,`name`,`mobno`,`dob`,`gender`,`email`,`password`) VALUES 
 (2,'cc','32423','2007-06-24','female','cc','bb'),
 (3,'aa','362682','2015-12-08','male','aa','aa'),
 (4,'ccdef','1232545','2016-01-12','male','abc','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
