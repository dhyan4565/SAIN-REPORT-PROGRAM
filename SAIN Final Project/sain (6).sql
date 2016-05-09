-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2016 at 08:20 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sain`
--
CREATE DATABASE IF NOT EXISTS `sain` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sain`;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseno` varchar(50) NOT NULL,
  `coursetitle` varchar(50) NOT NULL,
  `credits` int(11) NOT NULL,
  `grade` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `courseno`, `coursetitle`, `credits`, `grade`) VALUES
(5, '10', '10', 10, '10'),
(6, '11', '11', 11, '11');

-- --------------------------------------------------------

--
-- Table structure for table `coursetype`
--

DROP TABLE IF EXISTS `coursetype`;
CREATE TABLE IF NOT EXISTS `coursetype` (
  `coursetypeid` int(11) NOT NULL,
  `coursetype` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
CREATE TABLE IF NOT EXISTS `major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `majorid` varchar(50) NOT NULL,
  `title` varchar(50) NOT NULL,
  `totalcredits` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `major`
--

INSERT INTO `major` (`id`, `majorid`, `title`, `totalcredits`) VALUES
(6, '3', 'EE', 100);

-- --------------------------------------------------------

--
-- Table structure for table `majorcourses`
--

DROP TABLE IF EXISTS `majorcourses`;
CREATE TABLE IF NOT EXISTS `majorcourses` (
  `majorid` int(11) NOT NULL,
  `courseid` int(11) NOT NULL,
  `coursetypeid` varchar(50) NOT NULL,
  PRIMARY KEY (`majorid`,`courseid`,`coursetypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `majorcourses`
--

INSERT INTO `majorcourses` (`majorid`, `courseid`, `coursetypeid`) VALUES
(3, 1, 'MajorCrs'),
(3, 3, 'ScienceCrs'),
(3, 4, 'SocialSciCrs');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `majorid` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `date` date DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `gpa` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `name`, `majorid`, `username`, `password`, `date`, `address`, `gpa`) VALUES
(2, 'Dishant', '3', '3', '3', NULL, '3 3 3 3 3', 0),
(3, 'Jackie', '3', '4', '1245', NULL, 'Visnagar', 0);

-- --------------------------------------------------------

--
-- Table structure for table `studentcourse`
--

DROP TABLE IF EXISTS `studentcourse`;
CREATE TABLE IF NOT EXISTS `studentcourse` (
  `studentid` int(11) NOT NULL,
  `courseid` int(11) NOT NULL,
  `coursetype` varchar(50) NOT NULL,
  PRIMARY KEY (`studentid`,`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentcourse`
--

INSERT INTO `studentcourse` (`studentid`, `courseid`, `coursetype`) VALUES
(3, 10, 'Taking'),
(3, 11, 'Taking');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
