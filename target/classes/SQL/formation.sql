-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3325
-- Généré le : ven. 27 mai 2022 à 02:37
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `formation`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` int(4) NOT NULL,
  `post` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `post`) VALUES
(4113, 'sous administrateur'),
(4115, 'sous administrateur'),
(4116, 'Administrateur '),
(4133, 'sous administrateur '),
(4175, 'sous administrateur'),
(4180, 'sous administrateur');

-- --------------------------------------------------------

--
-- Structure de la table `administration`
--

CREATE TABLE `administration` (
  `id` int(8) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tel` varchar(25) NOT NULL,
  `pwd` varchar(25) NOT NULL,
  `idadmin` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `administration`
--

INSERT INTO `administration` (`id`, `nom`, `prenom`, `email`, `tel`, `pwd`, `idadmin`) VALUES
(1, 'Noha', 'Noha', 'Noha@gmail.com', '22222222', 'admin123', 4116),
(2, 'souhail', 'tourjmen', 'tourjmen.souhail@gmail.com', '50879772', '123', 4115);

-- --------------------------------------------------------

--
-- Structure de la table `domaine`
--

CREATE TABLE `domaine` (
  `idDomaine` int(4) NOT NULL,
  `Libelle` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `formateur`
--

CREATE TABLE `formateur` (
  `id` int(8) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `domaine` int(4) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tel` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id` int(11) NOT NULL,
  `intitule` varchar(25) NOT NULL,
  `domaine` int(4) NOT NULL,
  `nbjour` int(3) NOT NULL DEFAULT 1,
  `anne` year(4) DEFAULT NULL,
  `mois` date NOT NULL,
  `idformateur` int(4) NOT NULL,
  `nbparticipant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `particpant`
--

CREATE TABLE `particpant` (
  `idparticpant` int(8) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tel` varchar(8) NOT NULL,
  `pwd` varchar(25) NOT NULL,
  `idprofil` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `particpant`
--

INSERT INTO `particpant` (`idparticpant`, `nom`, `prenom`, `email`, `tel`, `pwd`, `idprofil`) VALUES
(6, 'souhail', 'tourjmen', 'tourjm@gmail.com', '23566', '213', NULL),
(7, 'souhail', 'tourjmen', 'tourj@gmail.com', '1234', '123', NULL),
(8, 'souhail', 'tourjmen', 'tourj@gmail.com', '1234', '123', NULL),
(9, 'souhail', 'tourjmen', 'tourj@gmail.com', '1234', '123', NULL),
(10, 'souhail', 'tourjmen', 'tourj@gmail.com', '123', '123', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

CREATE TABLE `profil` (
  `Idprofil` int(4) NOT NULL,
  `labelle` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `Idsession` int(11) NOT NULL,
  `idformation` int(8) NOT NULL,
  `idparticpant` int(8) NOT NULL,
  `debut` date NOT NULL,
  `fin` date NOT NULL,
  `Nbparticipant` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `email` varchar(50) NOT NULL,
  `password` varchar(25) NOT NULL,
  `role` varchar(1) NOT NULL DEFAULT 'U'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`email`, `password`, `role`) VALUES
('admin', 'admin', 'A'),
('tourjmen.souhail@gmail.com', '123', 'S'),
('user', 'user', 'U');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `administration`
--
ALTER TABLE `administration`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `idamdni` (`idadmin`);

--
-- Index pour la table `domaine`
--
ALTER TABLE `domaine`
  ADD PRIMARY KEY (`idDomaine`);

--
-- Index pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `domaine` (`domaine`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dm` (`domaine`),
  ADD KEY `idformateur` (`idformateur`);

--
-- Index pour la table `particpant`
--
ALTER TABLE `particpant`
  ADD PRIMARY KEY (`idparticpant`,`email`),
  ADD KEY `idprof` (`idprofil`);

--
-- Index pour la table `profil`
--
ALTER TABLE `profil`
  ADD PRIMARY KEY (`Idprofil`);

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`Idsession`),
  ADD KEY `formation` (`idformation`),
  ADD KEY `particpant` (`idparticpant`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`,`password`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7031;

--
-- AUTO_INCREMENT pour la table `administration`
--
ALTER TABLE `administration`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `domaine`
--
ALTER TABLE `domaine`
  MODIFY `idDomaine` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `formateur`
--
ALTER TABLE `formateur`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `particpant`
--
ALTER TABLE `particpant`
  MODIFY `idparticpant` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `profil`
--
ALTER TABLE `profil`
  MODIFY `Idprofil` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `Idsession` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `administration`
--
ALTER TABLE `administration`
  ADD CONSTRAINT `idamdni` FOREIGN KEY (`idadmin`) REFERENCES `admin` (`id`);

--
-- Contraintes pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD CONSTRAINT `domaine` FOREIGN KEY (`domaine`) REFERENCES `domaine` (`idDomaine`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `formation`
--
ALTER TABLE `formation`
  ADD CONSTRAINT `dm` FOREIGN KEY (`domaine`) REFERENCES `domaine` (`idDomaine`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idformateur` FOREIGN KEY (`idformateur`) REFERENCES `formateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `particpant`
--
ALTER TABLE `particpant`
  ADD CONSTRAINT `idprof` FOREIGN KEY (`idprofil`) REFERENCES `profil` (`Idprofil`);

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `formation` FOREIGN KEY (`idformation`) REFERENCES `formation` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `particpant` FOREIGN KEY (`idparticpant`) REFERENCES `particpant` (`idparticpant`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
