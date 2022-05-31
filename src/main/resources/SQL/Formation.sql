-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3325
-- Généré le : mar. 31 mai 2022 à 17:35
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
(4115, 'sous administrateur'),
(4116, 'Administrateur ');

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
  `idadmin` int(4) NOT NULL,
  `Iddomaine` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `administration`
--

INSERT INTO `administration` (`id`, `nom`, `prenom`, `email`, `tel`, `pwd`, `idadmin`, `Iddomaine`) VALUES
(4, 'Nouha', 'Nouha', 'Nouha@gmail.com', '22222222', '123', 17, 4116);

-- --------------------------------------------------------

--
-- Structure de la table `domaine`
--

CREATE TABLE `domaine` (
  `idDomaine` int(4) NOT NULL,
  `Libelle` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `domaine`
--

INSERT INTO `domaine` (`idDomaine`, `Libelle`) VALUES
(9, 'Agriculture'),
(10, 'Alimentation'),
(11, 'Animaux'),
(12, 'Architecture'),
(13, 'Armée'),
(14, 'Artisanat d’art'),
(15, 'Banque - Finance '),
(16, 'Biologie'),
(18, 'Culture'),
(20, 'Enseignement'),
(19, 'Gestion'),
(1, 'Informatique'),
(17, 'Marketing'),
(22, 'Sciences Physique'),
(23, 'Secrétariat');

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

--
-- Déchargement des données de la table `formateur`
--

INSERT INTO `formateur` (`id`, `nom`, `prenom`, `domaine`, `email`, `tel`) VALUES
(1, 'Souhail', 'Tourjmen', 19, 'Tourjmen@gmail.com', '508792'),
(2, 'Souhail', 'Tourjmen', 1, 'Tourjmen@gmail.com', '508792');

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id` int(11) NOT NULL,
  `intitule` varchar(25) NOT NULL,
  `domaine` int(4) NOT NULL,
  `nbjour` int(3) DEFAULT 1,
  `date` date NOT NULL,
  `idformateur` int(4) NOT NULL,
  `nbparticipant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id`, `intitule`, `domaine`, `nbjour`, `date`, `idformateur`, `nbparticipant`) VALUES
(4, 'Javascript', 1, 30, '2022-05-28', 1, 25),
(5, 'java', 1, 25, '2022-06-04', 2, 25),
(8, 'Html5', 17, 30, '2022-05-30', 4, 36),
(10, 'React', 1, 13, '2022-05-31', 3, 12);

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
(11, 'Souhail', 'Tourjmen', 'tourjmen45@gmail.com', '22224444', '123', 14);

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

CREATE TABLE `profil` (
  `Idprofil` int(4) NOT NULL,
  `labelle` varchar(50) DEFAULT NULL,
  `Iddomaine` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `profil`
--

INSERT INTO `profil` (`Idprofil`, `labelle`, `Iddomaine`) VALUES
(10, 'Administrateur / Administratrice base de données', 1),
(11, 'Analyste SOC (security operation center)', 1),
(12, 'Animateur / Animatrice multimédia', 1),
(13, 'Architecte Big Data', 1),
(14, 'Bio-informaticien', 1),
(15, 'chef de produit web', 1);

-- --------------------------------------------------------

--
-- Structure de la table `section`
--

CREATE TABLE `section` (
  `idsession` int(8) NOT NULL,
  `idparticipant` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `section`
--

INSERT INTO `section` (`idsession`, `idparticipant`) VALUES
(5, 0),
(5, 12),
(6, 12),
(8, 12);

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `Idsession` int(11) NOT NULL,
  `idformation` int(8) NOT NULL,
  `debut` date NOT NULL,
  `fin` date NOT NULL,
  `Nbparticipant` int(2) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`Idsession`, `idformation`, `debut`, `fin`, `Nbparticipant`) VALUES
(5, 4, '2022-05-31', '2022-06-30', 25),
(6, 5, '2022-05-31', '2022-06-04', 25),
(8, 8, '2022-06-05', '2023-06-11', 23);

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
('tourjmen45@gmail.com', '123', 'U'),
('tourjmen@gmail.com', '123', 'S'),
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
  ADD KEY `idamdni` (`idadmin`),
  ADD KEY `idom` (`Iddomaine`);

--
-- Index pour la table `domaine`
--
ALTER TABLE `domaine`
  ADD PRIMARY KEY (`idDomaine`),
  ADD UNIQUE KEY `Libelle` (`Libelle`);

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
  ADD UNIQUE KEY `idformateur_2` (`idformateur`),
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
  ADD PRIMARY KEY (`Idprofil`),
  ADD KEY `idmaine` (`Iddomaine`);

--
-- Index pour la table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`idsession`,`idparticipant`);

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`Idsession`,`idformation`) USING BTREE,
  ADD KEY `session_ibfk_1` (`idformation`);

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
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `domaine`
--
ALTER TABLE `domaine`
  MODIFY `idDomaine` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `formateur`
--
ALTER TABLE `formateur`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `particpant`
--
ALTER TABLE `particpant`
  MODIFY `idparticpant` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `profil`
--
ALTER TABLE `profil`
  MODIFY `Idprofil` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `Idsession` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD CONSTRAINT `domaine` FOREIGN KEY (`domaine`) REFERENCES `domaine` (`idDomaine`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `formation`
--
ALTER TABLE `formation`
  ADD CONSTRAINT `dm` FOREIGN KEY (`domaine`) REFERENCES `domaine` (`idDomaine`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `particpant`
--
ALTER TABLE `particpant`
  ADD CONSTRAINT `idprof` FOREIGN KEY (`idprofil`) REFERENCES `profil` (`Idprofil`);

--
-- Contraintes pour la table `profil`
--
ALTER TABLE `profil`
  ADD CONSTRAINT `idmaine` FOREIGN KEY (`Iddomaine`) REFERENCES `domaine` (`idDomaine`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `section`
--
ALTER TABLE `section`
  ADD CONSTRAINT `idsessio` FOREIGN KEY (`idsession`) REFERENCES `session` (`Idsession`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`idformation`) REFERENCES `formation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
