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
  `nombres` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidos` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `fechaNac` date NOT NULL,
  `idPais` varchar(3) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idAutor`),
  KEY `FK_Autor_Pais` (`idPais`),
  CONSTRAINT `FK_Autor_Pais` FOREIGN KEY (`idPais`) REFERENCES `pais` (`idPais`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_autorlibro`
--

LOCK TABLES `detalle_autorlibro` WRITE;
/*!40000 ALTER TABLE `detalle_autorlibro` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_autorlibro` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_librotema`
--

LOCK TABLES `detalle_librotema` WRITE;
/*!40000 ALTER TABLE `detalle_librotema` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_librotema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imprenta`
--

DROP TABLE IF EXISTS `imprenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imprenta` (
  `idImprenta` varchar(8) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idImprenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imprenta`
--

LOCK TABLES `imprenta` WRITE;
/*!40000 ALTER TABLE `imprenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `imprenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libro` (
  `idLibro` varchar(5) COLLATE utf8_spanish2_ci NOT NULL,
  `titulo` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `isbn` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `edicion` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `notas` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `imagen` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `idImprenta` varchar(8) COLLATE utf8_spanish2_ci NOT NULL,
  `idCategoria` int(11) NOT NULL,
  PRIMARY KEY (`idLibro`),
  UNIQUE KEY `U_Libro_Isbn` (`isbn`),
  KEY `FK_Libro_Imprenta` (`idImprenta`),
  KEY `FK_Libro_Categoria` (`idCategoria`),
  CONSTRAINT `FK_Libro_Categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`),
  CONSTRAINT `FK_Libro_Imprenta` FOREIGN KEY (`idImprenta`) REFERENCES `imprenta` (`idImprenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `idPais` varchar(3) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idPais`),
  UNIQUE KEY `U_Pais_Nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES ('AFG','Afganistán'),('ALB','Albania'),('DEU','Alemania'),('DZA','Algeria'),('AND','Andorra'),('AGO','Angola'),('AIA','Anguila'),('ATA','Antártida'),('ATG','Antigua y Barbuda'),('ANT','Antillas Neerlandesa'),('SAU','Arabia Saudita'),('ARG','Argentina'),('ARM','Armenia'),('ABW','Aruba'),('AUS','Australia'),('AUT','Austria'),('AZE','Azerbayán'),('BHS','Bahamas'),('BHR','Bahrein'),('BGD','Bangladesh'),('BRB','Barbados'),('BEL','Bélgica'),('BLZ','Belice'),('BEN','Benín'),('BTN','Bhután'),('BLR','Bielorrusia'),('MMR','Birmania'),('BOL','Bolivia'),('BIH','Bosnia y Herzegovina'),('BWA','Botsuana'),('BRA','Brasil'),('BRN','Brunéi'),('BGR','Bulgaria'),('BFA','Burkina Faso'),('BDI','Burundi'),('CPV','Cabo Verde'),('KHM','Camboya'),('CMR','Camerún'),('CAN','Canadá'),('VAT','Ciudad del Vaticano'),('COL','Colombia'),('COM','Comoras'),('COG','Congo'),('PRK','Corea del Norte'),('KOR','Corea del Sur'),('CIV','Costa de Marfil'),('CRI','Costa Rica'),('HRV','Croacia'),('CUB','Cuba'),('TCD','Chad'),('CHL','Chile'),('CHN','China'),('CYP','Chipre'),('DNK','Dinamarca'),('DMA','Dominica'),('ECU','Ecuador'),('EGY','Egipto'),('SLV','El Salvador'),('ARE','Emiratos Árabes Unid'),('ERI','Eritrea'),('SVK','Eslovaquia'),('SVN','Eslovenia'),('ESP','España'),('USA','Estados Unidos de Am'),('EST','Estonia'),('ETH','Etiopía'),('PHL','Filipinas'),('FIN','Finlandia'),('FJI','Fiyi'),('FRA','Francia'),('GAB','Gabón'),('GMB','Gambia'),('GEO','Georgia'),('GHA','Ghana'),('GIB','Gibraltar'),('GRD','Granada'),('GRC','Grecia'),('GRL','Groenlandia'),('GLP','Guadalupe'),('GUM','Guam'),('GTM','Guatemala'),('GUF','Guayana Francesa'),('GGY','Guernsey'),('GIN','Guinea'),('GNQ','Guinea Ecuatorial'),('GNB','Guinea-Bissau'),('GUY','Guyana'),('HTI','Haití'),('HND','Honduras'),('HKG','Hong kong'),('HUN','Hungría'),('IND','India'),('IDN','Indonesia'),('IRQ','Irak'),('IRN','Irán'),('IRL','Irlanda'),('BVT','Isla Bouvet'),('IMN','Isla de Man'),('CXR','Isla de Navidad'),('NFK','Isla Norfolk'),('ISL','Islandia'),('BMU','Islas Bermudas'),('CYM','Islas Caimán'),('CCK','Islas Cocos (Keeling'),('COK','Islas Cook'),('ALA','Islas de Åland'),('FRO','Islas Feroe'),('SGS','Islas Georgias del S'),('HMD','Islas Heard y McDona'),('MDV','Islas Maldivas'),('FLK','Islas Malvinas'),('MNP','Islas Marianas del N'),('MHL','Islas Marshall'),('PCN','Islas Pitcairn'),('SLB','Islas Salomón'),('TCA','Islas Turcas y Caico'),('UMI','Islas Ultramarinas M'),('VG','Islas Vírgenes Britá'),('VIR','Islas Vírgenes de lo'),('ISR','Israel'),('ITA','Italia'),('JAM','Jamaica'),('JPN','Japón'),('JEY','Jersey'),('JOR','Jordania'),('KAZ','Kazajistán'),('KEN','Kenia'),('KGZ','Kirgizstán'),('KIR','Kiribati'),('KWT','Kuwait'),('LAO','Laos'),('LSO','Lesoto'),('LVA','Letonia'),('LBN','Líbano'),('LBR','Liberia'),('LBY','Libia'),('LIE','Liechtenstein'),('LTU','Lituania'),('LUX','Luxemburgo'),('MAC','Macao'),('MKD','Macedônia'),('MDG','Madagascar'),('MYS','Malasia'),('MWI','Malawi'),('MLI','Mali'),('MLT','Malta'),('MAR','Marruecos'),('MTQ','Martinica'),('MUS','Mauricio'),('MRT','Mauritania'),('MYT','Mayotte'),('MEX','México'),('FSM','Micronesia'),('MDA','Moldavia'),('MCO','Mónaco'),('MNG','Mongolia'),('MNE','Montenegro'),('MSR','Montserrat'),('MOZ','Mozambique'),('NAM','Namibia'),('NRU','Nauru'),('NPL','Nepal'),('NIC','Nicaragua'),('NER','Niger'),('NGA','Nigeria'),('NIU','Niue'),('NOR','Noruega'),('NCL','Nueva Caledonia'),('NZL','Nueva Zelanda'),('OMN','Omán'),('NLD','Países Bajos'),('PAK','Pakistán'),('PLW','Palau'),('PSE','Palestina'),('PAN','Panamá'),('PNG','Papúa Nueva Guinea'),('PRY','Paraguay'),('PER','Perú'),('PYF','Polinesia Francesa'),('POL','Polonia'),('PRT','Portugal'),('PRI','Puerto Rico'),('QAT','Qatar'),('GBR','Reino Unido'),('CAF','República Centroafri'),('CZE','República Checa'),('DOM','República Dominicana'),('REU','Reunión'),('RWA','Ruanda'),('ROU','Rumanía'),('RUS','Rusia'),('ESH','Sahara Occidental'),('WSM','Samoa'),('ASM','Samoa Americana'),('BLM','San Bartolomé'),('KNA','San Cristóbal y Niev'),('SMR','San Marino'),('MAF','San Martín (Francia)'),('SPM','San Pedro y Miquelón'),('VCT','San Vicente y las Gr'),('SHN','Santa Elena'),('LCA','Santa Lucía'),('STP','Santo Tomé y Príncip'),('SEN','Senegal'),('SRB','Serbia'),('SYC','Seychelles'),('SLE','Sierra Leona'),('SGP','Singapur'),('SYR','Siria'),('SOM','Somalia'),('LKA','Sri lanka'),('ZAF','Sudáfrica'),('SDN','Sudán'),('SWE','Suecia'),('CHE','Suiza'),('SUR','Surinám'),('SJM','Svalbard y Jan Mayen'),('SWZ','Swazilandia'),('TJK','Tadjikistán'),('THA','Tailandia'),('TWN','Taiwán'),('TZA','Tanzania'),('IOT','Territorio Británico'),('ATF','Territorios Australe'),('TLS','Timor Oriental'),('TGO','Togo'),('TKL','Tokelau'),('TON','Tonga'),('TTO','Trinidad y Tobago'),('TUN','Tunez'),('TKM','Turkmenistán'),('TUR','Turquía'),('TUV','Tuvalu'),('UKR','Ucrania'),('UGA','Uganda'),('URY','Uruguay'),('UZB','Uzbekistán'),('VUT','Vanuatu'),('VEN','Venezuela'),('VNM','Vietnam'),('WLF','Wallis y Futuna'),('YEM','Yemen'),('DJI','Yibuti'),('ZMB','Zambia'),('ZWE','Zimbabue');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tema`
--

DROP TABLE IF EXISTS `tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tema` (
  `idTema` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idTema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tema`
--

LOCK TABLES `tema` WRITE;
/*!40000 ALTER TABLE `tema` DISABLE KEYS */;
/*!40000 ALTER TABLE `tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_usuario`
--

DROP TABLE IF EXISTS `tipos_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_usuario` (
  `idTipoUsuario` varchar(1) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idTipoUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_usuario`
--

LOCK TABLES `tipos_usuario` WRITE;
/*!40000 ALTER TABLE `tipos_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipos_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `correo` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `username` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `password` varchar(300) COLLATE utf8_spanish2_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `tipoUsuario` varchar(1) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `usuarios_ibfk_1` (`tipoUsuario`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`tipoUsuario`) REFERENCES `tipos_usuario` (`idTipoUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-09 17:08:05

--Aqui dejo los campos por que no se donde ponerlos xd
--Autores
INSERT INTO `autor` (`idAutor`, `nombres`, `apellidos`, `fechaNac`, `idPais`) VALUES
('A0001', 'Ricardo Eliecer Neftali', 'Reyes Basoalto', '1904-07-02', 'CHL'),
('A0002', 'Friedrich Wilhelm', 'Nietzsche', '1844-10-15', 'DEU'),
('A0003', 'Miguel de Cervantes', 'Saavedra', '1547-09-29', 'ESP'),
('A0004', 'Gabriel Jose', 'García Márquez', '1927-03-17', 'MEX'),
('A0005', 'Federico', 'Garcia Lorca', '1898-06-05', 'ESP');

--categoria
INSERT INTO `categoria` (`idCategoria`, `nombre`, `descripcion`) VALUES
(1, 'Filosofia', 'Filosofia universal'),
(2, 'Realismo magico', 'Realidad magicas externas'),
(3, 'Literatura', 'Literatura universal'),
(4, 'Biografias', 'Biografias o autobiografias'),
(5, 'Historia', 'Historia universal');

--Detalle Autor libro
INSERT INTO `detalle_autorlibro` (`idDetalle_AutorLibro`, `idAutor`, `idLibro`) VALUES
(1, 'A0002', 'L0001'),
(2, 'A0004', 'L0002'),
(3, 'A0003', 'L0003'),
(4, 'A0001', 'L0004'),
(5, 'A0005', 'L0005');

--DetalleLibroTema
INSERT INTO `detalle_librotema` (`idDetalle_LibroTema`, `idLibro`, `idTema`) VALUES
(1, 'L0001', 2),
(2, 'L0002', 5),
(3, 'L0003', 3),
(4, 'L0004', 5),
(5, 'L0005', 1);

--Imprenta
INSERT INTO `imprenta` (`idImprenta`, `nombre`, `direccion`) VALUES
('IMTA0001', 'ALBACROME', 'Km.13. a, 5, Comalapa'),
('IMTA0002', 'Creadores', 'Calle al volcan Col. Sta. Gertrudis No. 2A Mejicanos, San Salvador'),
('IMTA0003', 'IMPRESAL', 'Calle Cuscatlan No. 532, 87 Avenida Sur, San Salvador'),
('IMTA0004', 'Tipografia comercial', '1a Calle Oriente, Santa Ana 2201'),
('IMTA0005', 'VIMTAZA', 'Carretera al puerto de la libertad, santa tecla');

--Libros
INSERT INTO `libro` (`idLibro`, `titulo`, `isbn`, `edicion`, `descripcion`, `notas`, `imagen`, `idImprenta`, `idCategoria`) VALUES
('L0001', 'Asi hablo zaratustra', 'ISBN 901-7-314-36698-3', '2', 'La obra contiene las principales ideas de Nietzsche, expresadas de forma poetica.', 'Dificil de interpretar Para mentes desarrolladas', 'L0001.jpg', 'IMTA0004', 1),
('L0002', 'Cien años de soledad', 'ISBN 521-0-256-00528-0', '31', 'Es una novela de gabriel garcia marquez', 'Ganador del premio nobel', 'L0002.jpg', 'IMTA0001', 3),
('L0003', 'Don quijote', 'ISBN 769-5-321-87501-9', '12', 'Es la obra mas destacada de la literatura española', 'Una buena novela', 'L0003.jpg', 'IMTA0005', 2),
('L0004', 'Cien sonetos de amor', 'ISBN 349-6-003-74320-9', '16', 'Una coleccion de sonetos escrito por pablo neruda', 'Fue publicada en 1959', 'L0004.jpg', 'IMTA0003', 3),
('L0005', 'Bodas de sangre', 'ISBN 349-6-003-74320-7', '4', 'es una tragedia escrita en verso y en prosa del escritor español Federico García Lorca', 'Escrita en el año 1931', 'L0005.jpg', 'IMTA0004', 3);

--Temas
INSERT INTO `tema` (`idTema`, `descripcion`) VALUES
(1, 'Realidad social'),
(2, 'Creencias religiosas'),
(3, 'Realismo magico'),
(4, 'Formas de vida'),
(5, 'Clasicos latinoamerica');

--Tipousuarios
INSERT INTO `tipos_usuario` (`idTipoUsuario`, `nombre`, `descripcion`) VALUES
('B', 'Bibliotecario', ''),
('U', 'Usuario', '');

--Usuarios
INSERT INTO `usuario` (`idUsuario`, `nombre`, `apellido`, `correo`, `fechaNacimiento`, `username`, `password`, `estado`, `tipoUsuario`) VALUES
(1, 'Jasson Alexander', 'Lopez Soriano', 'jasson_alex99@hotmail.com', '1999-07-10', 'B0001', '84472/74362/10143*109123-112163-66282!80432/72342*', 1, 'B'),
(2, 'Franklin Armando', 'Esquivel Guevara', 'franklin@yahoo.com', '1998-10-09', 'B0002', '112163-113173/52A2/107103!116203*107103/88512/120243-', 1, 'B'),
(3, 'Diego Alejandro', 'Lemus Canjura', 'diego@hotmail.es', '1998-09-18', 'U0001', '57p2!121253-10473/66282/76382*51y2*88512-9702/', 1, 'U'),
(4, 'Carlos Armando', 'Lemus Torres', 'carlos@edu.sv', '1998-08-12', 'U0002', '10693-51y2*56o2/65272-68302-115193!111153!10363!', 1, 'U'),
(5, 'Leonarlo Elenilson', 'Lopez Cañas', 'leonardo@educa.me', '1999-04-09', 'U0003', '75372-10693-66282/116203-84472!49l2/70322-56o2!', 1, 'U');
