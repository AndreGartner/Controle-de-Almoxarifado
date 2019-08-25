-- --------------------------------------------------------
-- Servidor:                     localhost
-- Versão do servidor:           5.7.19 - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para db_almoxarifado
CREATE DATABASE IF NOT EXISTS `db_almoxarifado` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_almoxarifado`;

-- Copiando estrutura para tabela db_almoxarifado.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT,
  `NOME_CLIENTE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela db_almoxarifado.controle
CREATE TABLE IF NOT EXISTS `controle` (
  `ID_CONTROLE` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_DE_DEVOLUCAO` varchar(50) DEFAULT NULL,
  `DATA_DE_EMPRESTIMO` varchar(50) DEFAULT NULL,
  `Cliente_ID_CLIENTE` int(11) NOT NULL,
  `Funcionario_ID_FUNCIONARIO` int(11) NOT NULL,
  PRIMARY KEY (`ID_CONTROLE`),
  KEY `fk_Controle_Cliente_idx` (`Cliente_ID_CLIENTE`),
  KEY `fk_Controle_Funcionario1_idx` (`Funcionario_ID_FUNCIONARIO`),
  CONSTRAINT `fk_Controle_Cliente` FOREIGN KEY (`Cliente_ID_CLIENTE`) REFERENCES `cliente` (`ID_CLIENTE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Controle_Funcionario1` FOREIGN KEY (`Funcionario_ID_FUNCIONARIO`) REFERENCES `funcionario` (`ID_FUNCIONARIO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela db_almoxarifado.controle_produto
CREATE TABLE IF NOT EXISTS `controle_produto` (
  `Produto_ID_PRODUTO` int(11) NOT NULL,
  `Controle_ID_CONTROLE` int(11) NOT NULL,
  `QUANTIDADE_DE_PRODUTO` int(11) DEFAULT NULL,
  PRIMARY KEY (`Produto_ID_PRODUTO`,`Controle_ID_CONTROLE`),
  KEY `fk_Produto_has_Controle_Controle1_idx` (`Controle_ID_CONTROLE`),
  KEY `fk_Produto_has_Controle_Produto1_idx` (`Produto_ID_PRODUTO`),
  CONSTRAINT `fk_Produto_has_Controle_Controle1` FOREIGN KEY (`Controle_ID_CONTROLE`) REFERENCES `controle` (`ID_CONTROLE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_has_Controle_Produto1` FOREIGN KEY (`Produto_ID_PRODUTO`) REFERENCES `produto` (`ID_PRODUTO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela db_almoxarifado.funcionario
CREATE TABLE IF NOT EXISTS `funcionario` (
  `ID_FUNCIONARIO` int(11) NOT NULL AUTO_INCREMENT,
  `NOME_FUNCIONARIO` varchar(45) DEFAULT NULL,
  `SENHA_FUNCIONARIO` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_FUNCIONARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela db_almoxarifado.produto
CREATE TABLE IF NOT EXISTS `produto` (
  `ID_PRODUTO` int(11) NOT NULL AUTO_INCREMENT,
  `NOME_PRODUTO` varchar(45) DEFAULT NULL,
  `TIPO_PRODUTO` varchar(45) DEFAULT NULL,
  `ORIGEM_PRODUTO` varchar(45) DEFAULT NULL,
  `STATUS_PRODUTO` varchar(45) DEFAULT NULL,
  `DATA_ENTRADA_PROD` varchar(45) DEFAULT NULL,
  `QUANTIDADE_PRODUTO_ENTRADO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUTO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
