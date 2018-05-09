CREATE DATABASE  IF NOT EXISTS `thot` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci */;
USE `thot`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: thot
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autor` (
  `idAutor` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `nombres` varchar(35) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidos` varchar(35) COLLATE utf8_spanish2_ci NOT NULL,
  `fechaNac` date NOT NULL,
  `idPais` varchar(3) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idAutor`),
  KEY `FK_Autor_Pais` (`idPais`),
  CONSTRAINT `FK_Autor_Pais` FOREIGN KEY (`idPais`) REFERENCES `pais` (`idPais`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `idConfig` int(11) NOT NULL AUTO_INCREMENT,
  `mora` decimal(15,2) NOT NULL,
  PRIMARY KEY (`idConfig`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalle_autorlibro`
--

DROP TABLE IF EXISTS `detalle_autorlibro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_autorlibro` (
  `idDetalle_AutorLibro` int(11) NOT NULL AUTO_INCREMENT,
  `idAutor` varchar(6) COLLATE utf8_spanish2_ci NOT NULL,
  `idLibro` varchar(5) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idDetalle_AutorLibro`),
  KEY `FK_Detalle_AutorLibro_Autor` (`idAutor`),
  KEY `FK_Detalle_AutorLibro_Libro` (`idLibro`),
  CONSTRAINT `FK_Detalle_AutorLibro_Autor` FOREIGN KEY (`idAutor`) REFERENCES `autor` (`idAutor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Detalle_AutorLibro_Libro` FOREIGN KEY (`idLibro`) REFERENCES `libro` (`idLibro`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalle_librotema`
--

DROP TABLE IF EXISTS `detalle_librotema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_librotema` (
  `idDetalle_LibroTema` int(11) NOT NULL AUTO_INCREMENT,
  `idLibro` varchar(5) COLLATE utf8_spanish2_ci NOT NULL,
  `idTema` int(11) NOT NULL,
  PRIMARY KEY (`idDetalle_LibroTema`),
  KEY `FK_Detalle_LibroTema_Libro` (`idLibro`),
  KEY `FK_Detalle_LibroTema_Tema` (`idTema`),
  CONSTRAINT `FK_Detalle_LibroTema_Libro` FOREIGN KEY (`idLibro`) REFERENCES `libro` (`idLibro`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Detalle_LibroTema_Tema` FOREIGN KEY (`idTema`) REFERENCES `tema` (`idTema`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `detalle_prestamo`
--

DROP TABLE IF EXISTS `detalle_prestamo`;
/*!50001 DROP VIEW IF EXISTS `detalle_prestamo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `detalle_prestamo` AS SELECT 
 1 AS `idPrestamo`,
 1 AS `fecha_prestamo`,
 1 AS `fecha_devolucion`,
 1 AS `mora`,
 1 AS `estado`,
 1 AS `idUsuario`,
 1 AS `usuario`,
 1 AS `correo`,
 1 AS `idEjemplar`,
 1 AS `idLibro`,
 1 AS `titulo`,
 1 AS `isbn`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `detalle_reserva`
--

DROP TABLE IF EXISTS `detalle_reserva`;
/*!50001 DROP VIEW IF EXISTS `detalle_reserva`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `detalle_reserva` AS SELECT 
 1 AS `idReserva`,
 1 AS `fecha_reserva`,
 1 AS `fecha_vencimiento`,
 1 AS `estado`,
 1 AS `idUsuario`,
 1 AS `usuario`,
 1 AS `correo`,
 1 AS `idEjemplar`,
 1 AS `idLibro`,
 1 AS `titulo`,
 1 AS `isbn`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `ejemplar`
--

DROP TABLE IF EXISTS `ejemplar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ejemplar` (
  `idEjemplar` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `codigo` varchar(225) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `observaciones` mediumtext COLLATE utf8_spanish2_ci NOT NULL,
  `estado` enum('PM','D','R','P') COLLATE utf8_spanish2_ci NOT NULL DEFAULT 'PM' COMMENT 'PM: Pendiente de modificación\nD: Disponible\nR: Reservado\nP: Prestado',
  `idLibro` varchar(5) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idEjemplar`),
  KEY `FK_Ejemplar_Libro` (`idLibro`),
  CONSTRAINT `FK_Ejemplar_Libro` FOREIGN KEY (`idLibro`) REFERENCES `libro` (`idLibro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `thot`.`ejemplar_generar_codigo` BEFORE INSERT ON `ejemplar` FOR EACH ROW
BEGIN
	SET @aux = 0;
    SET @idLibro = NEW.idLibro;
    SET @ejemplaresExistentes = 0;
    
    SELECT count(idEjemplar) INTO @ejemplaresExistentes FROM ejemplar WHERE idLibro = @idLibro;
			
	IF @ejemplaresExistentes >= 0 AND @ejemplaresExistentes < 9 THEN SET @aux = 1;
	ELSEIF @ejemplaresExistentes >= 9 AND @ejemplaresExistentes < 99 THEN SET @aux = 2;
	ELSEIF @ejemplaresExistentes >= 99 AND @ejemplaresExistentes < 999 THEN SET @aux = 3;
	ELSEIF @ejemplaresExistentes >= 999 AND @ejemplaresExistentes < 9999 THEN SET @aux = 4;
	END IF;

	SET @idEjemplar = concat('E', @idLibro, repeat(0, 4 - @aux), (@ejemplaresExistentes + 1));
	
    SET NEW.idEjemplar = @idEjemplar;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!50001 DROP VIEW IF EXISTS `historial`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `historial` AS SELECT 
 1 AS `idProceso`,
 1 AS `inicio`,
 1 AS `limite`,
 1 AS `estado`,
 1 AS `tipoProceso`,
 1 AS `idUsuario`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `imprenta`
--

DROP TABLE IF EXISTS `imprenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imprenta` (
  `idImprenta` varchar(8) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(300) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idImprenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libro` (
  `idLibro` varchar(5) COLLATE utf8_spanish2_ci NOT NULL,
  `titulo` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `isbn` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `edicion` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(400) COLLATE utf8_spanish2_ci NOT NULL,
  `notas` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `imagen` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `ejemplares` int(11) NOT NULL,
  `idImprenta` varchar(8) COLLATE utf8_spanish2_ci NOT NULL,
  `idCategoria` int(11) NOT NULL,
  `fill` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idLibro`),
  UNIQUE KEY `U_Libro_Isbn` (`isbn`),
  KEY `FK_Libro_Imprenta` (`idImprenta`),
  KEY `FK_Libro_Categoria` (`idCategoria`),
  CONSTRAINT `FK_Libro_Categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`),
  CONSTRAINT `FK_Libro_Imprenta` FOREIGN KEY (`idImprenta`) REFERENCES `imprenta` (`idImprenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `thot`.`libro_registro_ejemplares` AFTER INSERT ON `libro` FOR EACH ROW
BEGIN

	SET @idLibro = NEW.idLibro;
    SET @cantEjemplares = NEW.ejemplares;
    SET @c = 0;
    
    IF NEW.fill = 0 THEN BEGIN
		WHILE @c < @cantEjemplares DO
			
			INSERT INTO ejemplar(codigo, observaciones, idLibro) 
			VALUES ('PENDIENTE', 'PENDIENTE', @idLibro);
            
			SET @c = @c + 1;
		END WHILE;
	END; END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `idPais` varchar(3) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idPais`),
  UNIQUE KEY `U_Pais_Nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prestamo` (
  `idPrestamo` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha_prestamo` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_devolucion` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `mora` decimal(15,2) NOT NULL DEFAULT '0.00',
  `estado` enum('EP','FO','VO') COLLATE utf8_spanish2_ci NOT NULL DEFAULT 'EP' COMMENT 'EP: En proceso\nFO: Finalizado\nVO: Vencido',
  `idEjemplar` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `idUsuario` varchar(5) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idPrestamo`),
  KEY `FK_Prestamo_Ejemplar_idx` (`idEjemplar`),
  KEY `FK_Prestamo_Usuario_idx` (`idUsuario`),
  CONSTRAINT `FK_Prestamo_Ejemplar` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplar` (`idEjemplar`),
  CONSTRAINT `FK_Prestamo_Usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `thot`.`prestamo_fecha_devolucion` BEFORE INSERT ON `prestamo` FOR EACH ROW
BEGIN
	IF NEW.fecha_devolucion = '0000-00-00 00:00:00' THEN
		SET NEW.fecha_devolucion = DATE_ADD(now(), INTERVAL 1 DAY);
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `thot`.`prestamo_cambio_estado_ejemplar` AFTER INSERT ON `prestamo` FOR EACH ROW
BEGIN
	SET @idEjemplar = NEW.idEjemplar;
    
    UPDATE Ejemplar e SET e.estado = 'P' WHERE e.idEjemplar = @idEjemplar;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `idReserva` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha_reserva` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_vencimiento` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `estado` enum('EO','VO','VE') COLLATE utf8_spanish2_ci NOT NULL DEFAULT 'VE' COMMENT 'EO: Efectuado\nVO: Vencido\nVE: Vigente\n',
  `idEjemplar` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `idUsuario` varchar(5) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idReserva`),
  KEY `FK_Reserva_Ejemplar_idx` (`idEjemplar`),
  KEY `FK_Reserva_Usuario_idx` (`idUsuario`),
  CONSTRAINT `FK_Reserva_Ejemplar` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplar` (`idEjemplar`),
  CONSTRAINT `FK_Reserva_Usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `thot`.`reserva_fecha_vencimiento` BEFORE INSERT ON `reserva` FOR EACH ROW
BEGIN
    IF NEW.fecha_vencimiento = '0000-00-00 00:00:00' THEN
		SET NEW.fecha_vencimiento = DATE_ADD(now(), INTERVAL 1 DAY);
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `thot`.`reserva_cambio_estado_ejemplar` AFTER INSERT ON `reserva` FOR EACH ROW
BEGIN
	SET @idEjemplar = NEW.idEjemplar;
    
    UPDATE Ejemplar e SET e.estado = 'R' WHERE e.idEjemplar= @idEjemplar;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tema`
--

DROP TABLE IF EXISTS `tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tema` (
  `idTema` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idTema`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_usuario`
--

DROP TABLE IF EXISTS `tipos_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_usuario` (
  `idTipoUsuario` varchar(1) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idTipoUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` varchar(5) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `correo` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `username` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `password` varchar(300) COLLATE utf8_spanish2_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `tipoUsuario` varchar(1) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `U_Usuario_Correo` (`correo`),
  KEY `FK_Usuario_TipoUsuario` (`tipoUsuario`),
  CONSTRAINT `FK_Usuario_TipoUsuario` FOREIGN KEY (`tipoUsuario`) REFERENCES `tipos_usuario` (`idTipoUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'thot'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `evento_verificar_procesos` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8 */ ;;
/*!50003 SET character_set_results = utf8 */ ;;
/*!50003 SET collation_connection  = utf8_general_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `evento_verificar_procesos` ON SCHEDULE EVERY 5 MINUTE STARTS '2018-05-03 17:52:44' ON COMPLETION PRESERVE ENABLE DO CALL thot.verificar_procesos() */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'thot'
--
/*!50003 DROP PROCEDURE IF EXISTS `calculo_mora_prestamo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calculo_mora_prestamo`(IN _idPrestamo VARCHAR(25))
BEGIN
    SET @mora = 0;
    SET @moraAcumulada = 0;
	SET @dias = 0;
    SET @diasAux = 0;
    SET @diasCobrados = 0;
    SET @estado = 0;
    
    SELECT mora into @mora FROM config WHERE idConfig = 1;
    
    SELECT datediff(current_timestamp(), p.fecha_devolucion), p.estado INTO @dias, @estado FROM prestamo p WHERE p.idPrestamo = _idPrestamo;
    
    IF @estado <> 'FO' THEN BEGIN
		SELECT (mora / @mora), mora INTO @diasCobrados, @moraAcumulada FROM prestamo p WHERE p.idPrestamo = _idPrestamo;
    
		SET @diasAux = @dias;
		SET @dias = @dias - @diasCobrados;
		
		IF @dias <= 0 THEN SET @dias = 0; END IF;
		
		IF @diasAux > 0 THEN SET @estado = 'VO'; 
		ELSEIF @diasAux <= 0 THEN SET @estado = 'EP';
		END IF;
		
		UPDATE prestamo p SET mora = (@moraAcumulada + (@mora * @dias)), estado = @estado WHERE p.idPrestamo = _idPrestamo;
    END; END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `efectuar_reserva` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `efectuar_reserva`(IN _idReserva VARCHAR(25), IN _fecha_devolucion timestamp, OUT _res int)
BEGIN
    SET @idUsuario = '';
    SET @idEjemplar = '';
    
    SELECT r.idUsuario, r.idEjemplar INTO @idUsuario, @idEjemplar FROM reserva r WHERE r.idReserva = _idReserva;
    
    CALL prestamo_libro(@idEjemplar, @idUsuario, _res, _fecha_devolucion);
    
    IF _res = 1 THEN BEGIN
		UPDATE reserva SET estado = 'EO' WHERE idReserva = _idReserva;
    END; END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `finalizar_prestamo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `finalizar_prestamo`(IN _idPrestamo VARCHAR(25), OUT _res int)
BEGIN
    SET @idEjemplar = 0;
    
    SELECT p.idEjemplar INTO @idEjemplar FROM prestamo p WHERE p.idPrestamo= _idPrestamo;

	UPDATE prestamo SET estado = 'FO' WHERE idPrestamo = _idPrestamo;
    UPDATE ejemplar SET estado = 'D' WHERE idEjemplar = @idEjemplar;
    
    SET _res = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(IN _user varchar(100), OUT _pass varchar(300), OUT _res int)
BEGIN
	SET @existe = 0;
    SET @valido = 0;
    
	SELECT COUNT(idUsuario)INTO @existe FROM Usuario WHERE
    idUsuario = _user OR username = _user OR correo = _user AND estado = 1;

	IF @existe > 0 THEN BEGIN
		SELECT password INTO _pass FROM Usuario 
		WHERE (STRCMP(idUsuario, _user) = 0 OR STRCMP(username, _user) = 0 OR STRCMP(correo, _user) = 0) AND estado = 1;
        
        SET _res = 1;
	END; END IF;
    
    IF @existe = 0 THEN SET _res = -1;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `prestamo_libro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `prestamo_libro`(IN idEjemplar varchar(10), IN idUsuario varchar(5), OUT out_res tinyint, IN devolucion timestamp)
BEGIN
	SET @estadoE = '';
    SELECT e.estado INTO @estadoE FROM ejemplar e WHERE e.idEjemplar = idEjemplar;
    
	IF @estadoE = 'D' OR @estadoE = 'R' THEN BEGIN
		
        SET @y = year(current_timestamp);
        SET @prestamos = 0;
        
        SELECT count(p.idPrestamo) INTO @prestamos FROM prestamo p 
        INNER JOIN usuario u ON u.idUsuario = p.idUsuario
        WHERE u.idUsuario = idUsuario;
        
        IF @prestamos >= 0 AND @prestamos < 10 THEN SET @aux = 1;
        ELSEIF @prestamos >= 10 AND @prestamos < 100 THEN SET @aux = 2;
        ELSEIF @prestamos >= 100 AND @prestamos < 1000 THEN SET @aux = 3;
        ELSEIF @prestamos >= 1000 AND @prestamos < 10000 THEN SET @aux = 4;
        END IF;
        
        SET @idPrestamo = concat('P', idUsuario, idEjemplar, @y, repeat(0, 4 - @aux), (@prestamos + 1));
        IF devolucion IS NULL THEN SET devolucion = '0000-00-00 00:00:00'; END IF;
        
		INSERT INTO prestamo(idPrestamo, fecha_devolucion, idEjemplar, idUsuario) VALUES(@idPrestamo, devolucion, idEjemplar, idUsuario);        
        SET out_res = 1;
	END; END IF;
    
    IF @estadoE <> 'D' AND @estadoE <> 'R' THEN BEGIN
		SET out_res = 0;
    END; END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `renovar_prestamo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `renovar_prestamo`(IN _idPrestamo varchar(25), OUT _res int)
BEGIN
	
    SET @existe = 0;
    SELECT COUNT(idPrestamo) INTO @existe FROM prestamo WHERE idPrestamo = _idPrestamo;
    
    IF @existe = 1 THEN BEGIN
		UPDATE prestamo SET fecha_devolucion = DATE_ADD(current_timestamp, INTERVAL 1 DAY), estado = 'EP' WHERE idPrestamo = _idPrestamo;
        SET _res = 1;
    END; END IF;
    
    IF @existe = 0 THEN SET _res = -1; END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reserva_libro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reserva_libro`(IN idEjemplar varchar(10), IN idUsuario varchar(5), OUT out_res tinyint, IN f_reserva timestamp, IN f_vencimiento timestamp)
BEGIN
    SET @estadoE = '';
    SELECT e.estado INTO @estadoE FROM ejemplar e WHERE e.idEjemplar = idEjemplar;
    
	IF @estadoE = 'D' THEN BEGIN
		
        SET @y = year(current_timestamp);
        SET @reservas = 0;
        
        SELECT count(r.idReserva) INTO @reservas FROM reserva r 
        INNER JOIN usuario u ON u.idUsuario = r.idUsuario
        WHERE u.idUsuario = idUsuario;
        
        IF @reservas >= 0 AND @reservas < 10 THEN SET @aux = 1;
        ELSEIF @reservas >= 10 AND @reservas < 100 THEN SET @aux = 2;
        ELSEIF @reservas >= 100 AND @reservas < 1000 THEN SET @aux = 3;
        ELSEIF @reservas >= 1000 AND @reservas < 10000 THEN SET @aux = 4;
        END IF;
        
        SET @idReserva = concat('R', idUsuario, idEjemplar, @y, repeat(0, 4 - @aux), (@reservas + 1));
        
        IF f_vencimiento IS NULL THEN SET f_vencimiento = '0000-00-00 00:00:00'; END IF;
        
		INSERT INTO reserva(idReserva, fecha_reserva, fecha_vencimiento, idEjemplar, idUsuario) VALUES(@idReserva, f_reserva, f_vencimiento, idEjemplar, idUsuario);        
        SET out_res = 1;
	END; END IF;
    
    IF @estadoE <> 'D' THEN BEGIN
		SET out_res = 0;
    END; END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verificar_prestamos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificar_prestamos`()
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
	DECLARE _id VARCHAR(25);
	DECLARE cur CURSOR FOR SELECT p.idPrestamo FROM prestamo p;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done := TRUE;

	OPEN cur;

	eachIdLoop: LOOP
		FETCH cur INTO _id;
		IF done THEN
			LEAVE eachIdLoop;
		END IF;
		
        CALL calculo_mora_prestamo(_id);
	END LOOP eachIdLoop;

	CLOSE cur;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verificar_procesos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificar_procesos`()
BEGIN
	CALL thot.verificar_prestamos();
	CALL thot.verificar_reservas();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verificar_reservas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificar_reservas`()
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
	DECLARE _id VARCHAR(25);
	DECLARE cur CURSOR FOR SELECT r.idReserva FROM reserva r;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done := TRUE;

	OPEN cur;

	eachIdLoop: LOOP
		FETCH cur INTO _id;
		IF done THEN
			LEAVE eachIdLoop;
		END IF;
		
        SET @dias = 0;
        SELECT datediff(current_timestamp(), fecha_vencimiento) INTO @dias FROM reserva WHERE idReserva = _id;
        
        IF @dias > 0 THEN BEGIN
			UPDATE reserva SET estado = 'VO' WHERE idReserva = _id;
        END; END IF;
	END LOOP eachIdLoop;

	CLOSE cur;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `detalle_prestamo`
--

/*!50001 DROP VIEW IF EXISTS `detalle_prestamo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `detalle_prestamo` AS select `p`.`idPrestamo` AS `idPrestamo`,`p`.`fecha_prestamo` AS `fecha_prestamo`,`p`.`fecha_devolucion` AS `fecha_devolucion`,`p`.`mora` AS `mora`,`p`.`estado` AS `estado`,`u`.`idUsuario` AS `idUsuario`,concat(`u`.`nombre`,' ',`u`.`apellido`) AS `usuario`,`u`.`correo` AS `correo`,`e`.`idEjemplar` AS `idEjemplar`,`l`.`idLibro` AS `idLibro`,`l`.`titulo` AS `titulo`,`l`.`isbn` AS `isbn` from (((`prestamo` `p` join `usuario` `u` on((`u`.`idUsuario` = `p`.`idUsuario`))) join `ejemplar` `e` on((`e`.`idEjemplar` = `p`.`idEjemplar`))) join `libro` `l` on((`l`.`idLibro` = `e`.`idLibro`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `detalle_reserva`
--

/*!50001 DROP VIEW IF EXISTS `detalle_reserva`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `detalle_reserva` AS select `r`.`idReserva` AS `idReserva`,`r`.`fecha_reserva` AS `fecha_reserva`,`r`.`fecha_vencimiento` AS `fecha_vencimiento`,`r`.`estado` AS `estado`,`u`.`idUsuario` AS `idUsuario`,concat(`u`.`nombre`,' ',`u`.`apellido`) AS `usuario`,`u`.`correo` AS `correo`,`e`.`idEjemplar` AS `idEjemplar`,`l`.`idLibro` AS `idLibro`,`l`.`titulo` AS `titulo`,`l`.`isbn` AS `isbn` from (((`reserva` `r` join `usuario` `u` on((`u`.`idUsuario` = `r`.`idUsuario`))) join `ejemplar` `e` on((`e`.`idEjemplar` = `r`.`idEjemplar`))) join `libro` `l` on((`l`.`idLibro` = `e`.`idLibro`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `historial`
--

/*!50001 DROP VIEW IF EXISTS `historial`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `historial` AS select `p`.`idPrestamo` AS `idProceso`,`p`.`fecha_prestamo` AS `inicio`,`p`.`fecha_devolucion` AS `limite`,`p`.`estado` AS `estado`,'P' AS `tipoProceso`,`u`.`idUsuario` AS `idUsuario` from (`prestamo` `p` join `usuario` `u` on((`u`.`idUsuario` = `p`.`idUsuario`))) union select `r`.`idReserva` AS `idProceso`,`r`.`fecha_reserva` AS `inicio`,`r`.`fecha_vencimiento` AS `limite`,`r`.`estado` AS `estado`,'R' AS `tipoProceso`,`u`.`idUsuario` AS `idUsuario` from (`reserva` `r` join `usuario` `u` on((`u`.`idUsuario` = `r`.`idUsuario`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

SET GLOBAL event_scheduler = ON;

DROP EVENT IF EXISTS evento_verificar_procesos;
CREATE EVENT `evento_verificar_procesos`
  ON SCHEDULE
    EVERY 5 MINUTE
    STARTS TIMESTAMP(NOW() + INTERVAL 1 MINUTE) ON COMPLETION PRESERVE ENABLE 
  DO
	CALL thot.verificar_procesos()

-- Dump completed on 2018-05-08 21:57:18
