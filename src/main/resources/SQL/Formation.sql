-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Jeu 04 Septembre 2014 à 16:39
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `etude`
--
CREATE DATABASE IF NOT EXISTS `formations` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `etude`;

-- --------------------------------------------------------

--
-- Structure de la table `etudient`
--

CREATE TABLE IF NOT EXISTS `Participant` (
  `id` int(11) DEFAULT NULL PRIMARY KEY,
  `Nom` varchar(50) DEFAULT NULL,
  `Prenom` varchar(50) DEFAULT NULL,
    'dn' date ,
  `PROFILE` varchar(50) DEFAULT NULL

)  ENGINE=InnoDB DEFAULT CHARSET=latin1;