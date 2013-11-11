-- phpMyAdmin SQL Dump
-- version 3.5.8.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 11, 2013 at 05:31 PM
-- Server version: 5.5.32-0ubuntu0.13.04.1
-- PHP Version: 5.4.9-4ubuntu2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `esi_pj`
--

-- --------------------------------------------------------

--
-- Table structure for table `Advogado`
--

CREATE TABLE IF NOT EXISTS `Advogado` (
  `idAdvogado` int(11) NOT NULL AUTO_INCREMENT,
  `nomeAdvogado` varchar(45) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `rg` varchar(45) NOT NULL,
  `oab` varchar(45) NOT NULL,
  `sexo` varchar(45) NOT NULL,
  `nascimento` date NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `idEndereco` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `complemento` varchar(45) NOT NULL,
  PRIMARY KEY (`idAdvogado`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  UNIQUE KEY `rg_UNIQUE` (`rg`),
  UNIQUE KEY `oab_UNIQUE` (`oab`),
  KEY `fk_Advogado_Endereco1_idx` (`idEndereco`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `Advogado`
--

INSERT INTO `Advogado` (`idAdvogado`, `nomeAdvogado`, `cpf`, `rg`, `oab`, `sexo`, `nascimento`, `telefone`, `idEndereco`, `numero`, `complemento`) VALUES
(1, 'Advogado do Diabo', '45455', '45454', '45454', 'masculino', '2013-10-01', '45', 1, 45, 'casa'),
(2, 'Paulo Cordeiro', '45', '45', '54', '54', '2013-10-09', '465', 1, 56, 'casa'),
(3, 'Antonio Marcos', '45456', '454', '454', 'masculino', '2013-10-09', '54', 1, 454, 'casa');

-- --------------------------------------------------------

--
-- Table structure for table `Bairro`
--

CREATE TABLE IF NOT EXISTS `Bairro` (
  `idBairro` int(11) NOT NULL AUTO_INCREMENT,
  `nomeBairro` varchar(45) NOT NULL,
  PRIMARY KEY (`idBairro`),
  UNIQUE KEY `nomeBairro_UNIQUE` (`nomeBairro`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Bairro`
--

INSERT INTO `Bairro` (`idBairro`, `nomeBairro`) VALUES
(1, 'teste');

-- --------------------------------------------------------

--
-- Table structure for table `Cidade`
--

CREATE TABLE IF NOT EXISTS `Cidade` (
  `idCidade` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCidade` varchar(45) NOT NULL,
  `idEstado` int(11) NOT NULL,
  PRIMARY KEY (`idCidade`),
  UNIQUE KEY `nomeCidade_UNIQUE` (`nomeCidade`),
  KEY `fk_Cidade_Estado1_idx` (`idEstado`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Cidade`
--

INSERT INTO `Cidade` (`idCidade`, `nomeCidade`, `idEstado`) VALUES
(1, 'teste', 1);

-- --------------------------------------------------------

--
-- Table structure for table `Cliente`
--

CREATE TABLE IF NOT EXISTS `Cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCliente` varchar(45) NOT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `rg` varchar(45) DEFAULT NULL,
  `cnpj` varchar(45) DEFAULT NULL,
  `sexo` varchar(45) NOT NULL,
  `nascimento` date NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `idEndereco` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `complemento` varchar(45) NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  UNIQUE KEY `rg_UNIQUE` (`rg`),
  UNIQUE KEY `cnpj_UNIQUE` (`cnpj`),
  KEY `fk_Cliente_Endereco1_idx` (`idEndereco`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `Cliente`
--

INSERT INTO `Cliente` (`idCliente`, `nomeCliente`, `cpf`, `rg`, `cnpj`, `sexo`, `nascimento`, `telefone`, `idEndereco`, `numero`, `complemento`) VALUES
(4, 'Joao da Silva', NULL, NULL, NULL, 'masculino', '2013-10-11', '454545', 1, 45, 'casa'),
(5, 'Antonio Medeiros', NULL, NULL, NULL, 'ha', '2013-10-16', '45', 1, 56, 'casa'),
(7, 'Eduardo', NULL, NULL, NULL, 'masculino', '2013-10-02', '454548', 1, 46, 'cas');

-- --------------------------------------------------------

--
-- Table structure for table `Endereco`
--

CREATE TABLE IF NOT EXISTS `Endereco` (
  `idEndereco` int(11) NOT NULL AUTO_INCREMENT,
  `cep` varchar(45) NOT NULL,
  `idLogradouro` int(11) NOT NULL,
  `idRua` int(11) NOT NULL,
  `idBairro` int(11) NOT NULL,
  `idCidade` int(11) NOT NULL,
  PRIMARY KEY (`idEndereco`),
  KEY `fk_Endereco_Logradouro_idx` (`idLogradouro`),
  KEY `fk_Endereco_Rua1_idx` (`idRua`),
  KEY `fk_Endereco_Bairro1_idx` (`idBairro`),
  KEY `fk_Endereco_Cidade1_idx` (`idCidade`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Endereco`
--

INSERT INTO `Endereco` (`idEndereco`, `cep`, `idLogradouro`, `idRua`, `idBairro`, `idCidade`) VALUES
(1, 'teste', 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Estado`
--

CREATE TABLE IF NOT EXISTS `Estado` (
  `idEstado` int(11) NOT NULL AUTO_INCREMENT,
  `nomeEstado` varchar(45) NOT NULL,
  `siglaEstado` varchar(45) NOT NULL,
  PRIMARY KEY (`idEstado`),
  UNIQUE KEY `nomeEstado_UNIQUE` (`nomeEstado`),
  UNIQUE KEY `siglaEstado_UNIQUE` (`siglaEstado`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Estado`
--

INSERT INTO `Estado` (`idEstado`, `nomeEstado`, `siglaEstado`) VALUES
(1, 'teste', 'te');

-- --------------------------------------------------------

--
-- Table structure for table `Forum`
--

CREATE TABLE IF NOT EXISTS `Forum` (
  `idForum` int(11) NOT NULL AUTO_INCREMENT,
  `nomeForum` varchar(45) NOT NULL,
  `numero` int(11) NOT NULL,
  `nascimento` date NOT NULL,
  `complemento` varchar(45) NOT NULL,
  `cnpj` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `idEndereco` int(11) NOT NULL,
  PRIMARY KEY (`idForum`),
  UNIQUE KEY `nomeForum_UNIQUE` (`nomeForum`),
  UNIQUE KEY `cnpj_UNIQUE` (`cnpj`),
  KEY `fk_Forum_Endereco1_idx` (`idEndereco`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `Forum`
--

INSERT INTO `Forum` (`idForum`, `nomeForum`, `numero`, `nascimento`, `complemento`, `cnpj`, `telefone`, `idEndereco`) VALUES
(1, 'Cascavel', 78, '2013-10-15', 'teste', '4545', '4554', 1),
(2, 'Foz do Iguassu', 45, '2013-10-16', 'casa', '6456', '445', 1);

-- --------------------------------------------------------

--
-- Table structure for table `Logradouro`
--

CREATE TABLE IF NOT EXISTS `Logradouro` (
  `idLogradouro` int(11) NOT NULL AUTO_INCREMENT,
  `nomeLogradouro` varchar(45) NOT NULL,
  PRIMARY KEY (`idLogradouro`),
  UNIQUE KEY `nomeLogradouro_UNIQUE` (`nomeLogradouro`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Logradouro`
--

INSERT INTO `Logradouro` (`idLogradouro`, `nomeLogradouro`) VALUES
(1, 'tste');

-- --------------------------------------------------------

--
-- Table structure for table `Processo`
--

CREATE TABLE IF NOT EXISTS `Processo` (
  `numeroProcesso` int(11) NOT NULL,
  `abertura` date NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `situacao` int(11) NOT NULL,
  `idForum` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idAdvogado` int(11) NOT NULL,
  `idTipoProcesso` int(11) NOT NULL,
  PRIMARY KEY (`numeroProcesso`),
  UNIQUE KEY `numeroProcesso_UNIQUE` (`numeroProcesso`),
  KEY `fk_Processo_Forum1_idx` (`idForum`),
  KEY `fk_Processo_Cliente1_idx` (`idCliente`),
  KEY `fk_Processo_Advogado1_idx` (`idAdvogado`),
  KEY `fk_Processo_TipoProcesso1_idx` (`idTipoProcesso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Processo`
--

INSERT INTO `Processo` (`numeroProcesso`, `abertura`, `descricao`, `situacao`, `idForum`, `idCliente`, `idAdvogado`, `idTipoProcesso`) VALUES
(74, '2000-02-01', 'Julgamentos', 0, 2, 4, 1, 2),
(584, '2013-10-01', 'TEste', 0, 2, 7, 3, 2),
(785, '2013-10-01', 'Teste', 0, 1, 4, 2, 2),
(787, '2013-10-01', 'Teste', 0, 1, 5, 2, 2),
(866, '2000-02-01', 'Processo Civil', 0, 1, 5, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `Rua`
--

CREATE TABLE IF NOT EXISTS `Rua` (
  `idRua` int(11) NOT NULL AUTO_INCREMENT,
  `nomeRua` varchar(45) NOT NULL,
  PRIMARY KEY (`idRua`),
  UNIQUE KEY `nomeRua_UNIQUE` (`nomeRua`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `Rua`
--

INSERT INTO `Rua` (`idRua`, `nomeRua`) VALUES
(1, 'teste');

-- --------------------------------------------------------

--
-- Table structure for table `TipoProcesso`
--

CREATE TABLE IF NOT EXISTS `TipoProcesso` (
  `idTipoProcesso` int(11) NOT NULL AUTO_INCREMENT,
  `nomeTipoProcesso` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoProcesso`),
  UNIQUE KEY `nomeTipoProcesso_UNIQUE` (`nomeTipoProcesso`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `TipoProcesso`
--

INSERT INTO `TipoProcesso` (`idTipoProcesso`, `nomeTipoProcesso`) VALUES
(2, 'Julgamento'),
(1, 'Processo Civil'),
(3, 'Processo Juridico');

-- --------------------------------------------------------

--
-- Table structure for table `TipoTramite`
--

CREATE TABLE IF NOT EXISTS `TipoTramite` (
  `idTipoTramite` int(11) NOT NULL AUTO_INCREMENT,
  `nomeTipoTramite` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoTramite`),
  UNIQUE KEY `nomeTipoTramite_UNIQUE` (`nomeTipoTramite`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `TipoTramite`
--

INSERT INTO `TipoTramite` (`idTipoTramite`, `nomeTipoTramite`) VALUES
(1, 'Abertura'),
(2, 'Julgamento');

-- --------------------------------------------------------

--
-- Table structure for table `Tramite`
--

CREATE TABLE IF NOT EXISTS `Tramite` (
  `numeroProcesso` int(11) NOT NULL,
  `idTipoTramite` int(11) NOT NULL,
  `obs` varchar(255) DEFAULT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`numeroProcesso`,`idTipoTramite`),
  KEY `fk_Processo_has_TipoTramite_TipoTramite1_idx` (`idTipoTramite`),
  KEY `fk_Processo_has_TipoTramite_Processo1_idx` (`numeroProcesso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Advogado`
--
ALTER TABLE `Advogado`
  ADD CONSTRAINT `fk_Advogado_Endereco1` FOREIGN KEY (`idEndereco`) REFERENCES `Endereco` (`idEndereco`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Cidade`
--
ALTER TABLE `Cidade`
  ADD CONSTRAINT `fk_Cidade_Estado1` FOREIGN KEY (`idEstado`) REFERENCES `Estado` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Cliente`
--
ALTER TABLE `Cliente`
  ADD CONSTRAINT `fk_Cliente_Endereco1` FOREIGN KEY (`idEndereco`) REFERENCES `Endereco` (`idEndereco`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Endereco`
--
ALTER TABLE `Endereco`
  ADD CONSTRAINT `fk_Endereco_Bairro1` FOREIGN KEY (`idBairro`) REFERENCES `Bairro` (`idBairro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Endereco_Cidade1` FOREIGN KEY (`idCidade`) REFERENCES `Cidade` (`idCidade`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Endereco_Logradouro` FOREIGN KEY (`idLogradouro`) REFERENCES `Logradouro` (`idLogradouro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Endereco_Rua1` FOREIGN KEY (`idRua`) REFERENCES `Rua` (`idRua`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Forum`
--
ALTER TABLE `Forum`
  ADD CONSTRAINT `fk_Forum_Endereco1` FOREIGN KEY (`idEndereco`) REFERENCES `Endereco` (`idEndereco`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Processo`
--
ALTER TABLE `Processo`
  ADD CONSTRAINT `fk_Processo_Advogado1` FOREIGN KEY (`idAdvogado`) REFERENCES `Advogado` (`idAdvogado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Processo_Cliente1` FOREIGN KEY (`idCliente`) REFERENCES `Cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Processo_Forum1` FOREIGN KEY (`idForum`) REFERENCES `Forum` (`idForum`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Processo_TipoProcesso1` FOREIGN KEY (`idTipoProcesso`) REFERENCES `TipoProcesso` (`idTipoProcesso`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `Tramite`
--
ALTER TABLE `Tramite`
  ADD CONSTRAINT `fk_Processo_has_TipoTramite_Processo1` FOREIGN KEY (`numeroProcesso`) REFERENCES `Processo` (`numeroProcesso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Processo_has_TipoTramite_TipoTramite1` FOREIGN KEY (`idTipoTramite`) REFERENCES `TipoTramite` (`idTipoTramite`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
