-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 20. Dez 2014 um 12:59
-- Server Version: 5.6.16
-- PHP-Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `crm`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `appointment`
--

CREATE TABLE IF NOT EXISTS `appointment` (
  `UUID` char(36) NOT NULL,
  `ContactUUID` char(36) DEFAULT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `Varchar` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `contact`
--

CREATE TABLE IF NOT EXISTS `contact` (
  `UUID` char(36) NOT NULL,
  `FirstName` varchar(100) NOT NULL,
  `LastName` varchar(100) NOT NULL,
  `Street` varchar(100) NOT NULL,
  `ZipCode` int(5) NOT NULL,
  `City` varchar(100) NOT NULL,
  `Country` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
