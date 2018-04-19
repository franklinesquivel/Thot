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
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES ('A0001','Ricardo Eliecer Neftali','Reyes Basoalto','1904-07-02','CHL'),('A0002','Friedrich Wilhelm','Nietzsche','1844-10-15','DEU'),('A0003','Miguel de Cervantes','Saavedra','1547-09-29','ESP'),('A0004','Gabriel Jose','García Márquez','1927-03-17','MEX'),('A0005','Federico','Garcia Lorca','1898-06-05','ESP');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Filosofia','Filosofia universal'),(2,'Realismo magico','Realidad magicas externas'),(3,'Literatura','Literatura universal'),(4,'Biografias','Biografias o autobiografias'),(5,'Historia','Historia universal');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,0.25);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `detalle_autorlibro`
--

LOCK TABLES `detalle_autorlibro` WRITE;
/*!40000 ALTER TABLE `detalle_autorlibro` DISABLE KEYS */;
INSERT INTO `detalle_autorlibro` VALUES (1,'A0002','L0001'),(2,'A0004','L0002'),(3,'A0003','L0003'),(4,'A0001','L0004'),(5,'A0005','L0005');
/*!40000 ALTER TABLE `detalle_autorlibro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `detalle_librotema`
--

LOCK TABLES `detalle_librotema` WRITE;
/*!40000 ALTER TABLE `detalle_librotema` DISABLE KEYS */;
INSERT INTO `detalle_librotema` VALUES (1,'L0001',2),(2,'L0002',5),(3,'L0003',3),(4,'L0004',5),(5,'L0005',1);
/*!40000 ALTER TABLE `detalle_librotema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ejemplar`
--

LOCK TABLES `ejemplar` WRITE;
/*!40000 ALTER TABLE `ejemplar` DISABLE KEYS */;
INSERT INTO `ejemplar` VALUES ('EL00010001','EL00010001','PENDIENTE','PM','L0001'),('EL00010002','EL00010002','PENDIENTE','PM','L0001'),('EL00010003','EL00010003','PENDIENTE','PM','L0001'),('EL00010004','EL00010004','PENDIENTE','PM','L0001'),('EL00010005','EL00010005','PENDIENTE','PM','L0001'),('EL00020001','EL00020001','PENDIENTE','PM','L0002'),('EL00020002','EL00020002','PENDIENTE','PM','L0002'),('EL00020003','EL00020003','PENDIENTE','PM','L0002'),('EL00030001','EL00030001','PENDIENTE','PM','L0003'),('EL00030002','EL00030002','PENDIENTE','PM','L0003'),('EL00030003','EL00030003','PENDIENTE','PM','L0003'),('EL00030004','EL00030004','PENDIENTE','PM','L0003'),('EL00030005','EL00030005','PENDIENTE','PM','L0003'),('EL00030006','EL00030006','PENDIENTE','PM','L0003'),('EL00030007','EL00030007','PENDIENTE','PM','L0003'),('EL00030008','EL00030008','PENDIENTE','PM','L0003'),('EL00030009','EL00030009','PENDIENTE','PM','L0003'),('EL00030010','EL00030010','PENDIENTE','PM','L0003'),('EL00030011','EL00030011','PENDIENTE','PM','L0003'),('EL00030012','EL00030012','PENDIENTE','PM','L0003'),('EL00030013','EL00030013','PENDIENTE','PM','L0003'),('EL00030014','EL00030014','PENDIENTE','PM','L0003'),('EL00030015','EL00030015','PENDIENTE','PM','L0003'),('EL00030016','EL00030016','PENDIENTE','PM','L0003'),('EL00030017','EL00030017','PENDIENTE','PM','L0003'),('EL00030018','EL00030018','PENDIENTE','PM','L0003'),('EL00030019','EL00030019','PENDIENTE','PM','L0003'),('EL00030020','EL00030020','PENDIENTE','PM','L0003'),('EL00040001','EL00040001','PENDIENTE','PM','L0004'),('EL00040002','EL00040002','PENDIENTE','PM','L0004'),('EL00040003','EL00040003','PENDIENTE','PM','L0004'),('EL00040004','EL00040004','PENDIENTE','PM','L0004'),('EL00040005','EL00040005','PENDIENTE','PM','L0004'),('EL00040006','EL00040006','PENDIENTE','PM','L0004'),('EL00040007','EL00040007','PENDIENTE','PM','L0004'),('EL00040008','EL00040008','PENDIENTE','PM','L0004'),('EL00040009','EL00040009','PENDIENTE','PM','L0004'),('EL00040010','EL00040010','PENDIENTE','PM','L0004'),('EL00040011','EL00040011','PENDIENTE','PM','L0004'),('EL00040012','EL00040012','PENDIENTE','PM','L0004'),('EL00040013','EL00040013','PENDIENTE','PM','L0004'),('EL00050001','EL00050001','PENDIENTE','PM','L0005'),('EL00050002','EL00050002','PENDIENTE','PM','L0005'),('EL00050003','EL00050003','PENDIENTE','PM','L0005'),('EL00050004','EL00050004','PENDIENTE','PM','L0005'),('EL00050005','EL00050005','PENDIENTE','PM','L0005'),('EL00050006','EL00050006','PENDIENTE','PM','L0005'),('EL00050007','EL00050007','PENDIENTE','PM','L0005');
/*!40000 ALTER TABLE `ejemplar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `imprenta`
--

LOCK TABLES `imprenta` WRITE;
/*!40000 ALTER TABLE `imprenta` DISABLE KEYS */;
INSERT INTO `imprenta` VALUES ('IMTA0001','ALBACROME','Km.13. a, 5, Comalapa'),('IMTA0002','Creadores','Calle al volcan Col. Sta. Gertrudis No. 2A Mejicanos, San Salvador'),('IMTA0003','IMPRESAL','Calle Cuscatlan No. 532, 87 Avenida Sur, San Salvador'),('IMTA0004','Tipografia comercial','1a Calle Oriente, Santa Ana 2201'),('IMTA0005','VIMTAZA','Carretera al puerto de la libertad, santa tecla');
/*!40000 ALTER TABLE `imprenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES ('L0001','Asi hablo zaratustra','ISBN 901-7-314-36698-3','2','La obra contiene las principales ideas de Nietzsche, expresadas de forma poetica.','Dificil de interpretar Para mentes desarrolladas','L0001.jpg',5,'IMTA0004',1,1),('L0002','Cien años de soledad','ISBN 521-0-256-00528-0','31','Es una novela de gabriel garcia marquez','Ganador del premio nobel','L0002.jpg',3,'IMTA0001',3,1),('L0003','Don quijote','ISBN 769-5-321-87501-9','12','Es la obra mas destacada de la literatura española','Una buena novela','L0003.jpg',20,'IMTA0005',2,1),('L0004','Cien sonetos de amor','ISBN 349-6-003-74320-9','16','Una coleccion de sonetos escrito por pablo neruda','Fue publicada en 1959','L0004.jpg',13,'IMTA0003',3,1),('L0005','Bodas de sangre','ISBN 349-6-003-74320-7','4','es una tragedia escrita en verso y en prosa del escritor español Federico García Lorca','Escrita en el año 1931','L0005.jpg',7,'IMTA0004',3,1);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES ('AFG','Afganistán'),('ALB','Albania'),('DEU','Alemania'),('DZA','Algeria'),('AND','Andorra'),('AGO','Angola'),('AIA','Anguila'),('ATA','Antártida'),('ATG','Antigua y Barbuda'),('ANT','Antillas Neerlandesa'),('SAU','Arabia Saudita'),('ARG','Argentina'),('ARM','Armenia'),('ABW','Aruba'),('AUS','Australia'),('AUT','Austria'),('AZE','Azerbayán'),('BHS','Bahamas'),('BHR','Bahrein'),('BGD','Bangladesh'),('BRB','Barbados'),('BEL','Bélgica'),('BLZ','Belice'),('BEN','Benín'),('BTN','Bhután'),('BLR','Bielorrusia'),('MMR','Birmania'),('BOL','Bolivia'),('BIH','Bosnia y Herzegovina'),('BWA','Botsuana'),('BRA','Brasil'),('BRN','Brunéi'),('BGR','Bulgaria'),('BFA','Burkina Faso'),('BDI','Burundi'),('CPV','Cabo Verde'),('KHM','Camboya'),('CMR','Camerún'),('CAN','Canadá'),('VAT','Ciudad del Vaticano'),('COL','Colombia'),('COM','Comoras'),('COG','Congo'),('PRK','Corea del Norte'),('KOR','Corea del Sur'),('CIV','Costa de Marfil'),('CRI','Costa Rica'),('HRV','Croacia'),('CUB','Cuba'),('TCD','Chad'),('CHL','Chile'),('CHN','China'),('CYP','Chipre'),('DNK','Dinamarca'),('DMA','Dominica'),('ECU','Ecuador'),('EGY','Egipto'),('SLV','El Salvador'),('ARE','Emiratos Árabes Unid'),('ERI','Eritrea'),('SVK','Eslovaquia'),('SVN','Eslovenia'),('ESP','España'),('USA','Estados Unidos de Am'),('EST','Estonia'),('ETH','Etiopía'),('PHL','Filipinas'),('FIN','Finlandia'),('FJI','Fiyi'),('FRA','Francia'),('GAB','Gabón'),('GMB','Gambia'),('GEO','Georgia'),('GHA','Ghana'),('GIB','Gibraltar'),('GRD','Granada'),('GRC','Grecia'),('GRL','Groenlandia'),('GLP','Guadalupe'),('GUM','Guam'),('GTM','Guatemala'),('GUF','Guayana Francesa'),('GGY','Guernsey'),('GIN','Guinea'),('GNQ','Guinea Ecuatorial'),('GNB','Guinea-Bissau'),('GUY','Guyana'),('HTI','Haití'),('HND','Honduras'),('HKG','Hong kong'),('HUN','Hungría'),('IND','India'),('IDN','Indonesia'),('IRQ','Irak'),('IRN','Irán'),('IRL','Irlanda'),('BVT','Isla Bouvet'),('IMN','Isla de Man'),('CXR','Isla de Navidad'),('NFK','Isla Norfolk'),('ISL','Islandia'),('BMU','Islas Bermudas'),('CYM','Islas Caimán'),('CCK','Islas Cocos (Keeling'),('COK','Islas Cook'),('ALA','Islas de Åland'),('FRO','Islas Feroe'),('SGS','Islas Georgias del S'),('HMD','Islas Heard y McDona'),('MDV','Islas Maldivas'),('FLK','Islas Malvinas'),('MNP','Islas Marianas del N'),('MHL','Islas Marshall'),('PCN','Islas Pitcairn'),('SLB','Islas Salomón'),('TCA','Islas Turcas y Caico'),('UMI','Islas Ultramarinas M'),('VG','Islas Vírgenes Britá'),('VIR','Islas Vírgenes de lo'),('ISR','Israel'),('ITA','Italia'),('JAM','Jamaica'),('JPN','Japón'),('JEY','Jersey'),('JOR','Jordania'),('KAZ','Kazajistán'),('KEN','Kenia'),('KGZ','Kirgizstán'),('KIR','Kiribati'),('KWT','Kuwait'),('LAO','Laos'),('LSO','Lesoto'),('LVA','Letonia'),('LBN','Líbano'),('LBR','Liberia'),('LBY','Libia'),('LIE','Liechtenstein'),('LTU','Lituania'),('LUX','Luxemburgo'),('MAC','Macao'),('MKD','Macedônia'),('MDG','Madagascar'),('MYS','Malasia'),('MWI','Malawi'),('MLI','Mali'),('MLT','Malta'),('MAR','Marruecos'),('MTQ','Martinica'),('MUS','Mauricio'),('MRT','Mauritania'),('MYT','Mayotte'),('MEX','México'),('FSM','Micronesia'),('MDA','Moldavia'),('MCO','Mónaco'),('MNG','Mongolia'),('MNE','Montenegro'),('MSR','Montserrat'),('MOZ','Mozambique'),('NAM','Namibia'),('NRU','Nauru'),('NPL','Nepal'),('NIC','Nicaragua'),('NER','Niger'),('NGA','Nigeria'),('NIU','Niue'),('NOR','Noruega'),('NCL','Nueva Caledonia'),('NZL','Nueva Zelanda'),('OMN','Omán'),('NLD','Países Bajos'),('PAK','Pakistán'),('PLW','Palau'),('PSE','Palestina'),('PAN','Panamá'),('PNG','Papúa Nueva Guinea'),('PRY','Paraguay'),('PER','Perú'),('PYF','Polinesia Francesa'),('POL','Polonia'),('PRT','Portugal'),('PRI','Puerto Rico'),('QAT','Qatar'),('GBR','Reino Unido'),('CAF','República Centroafri'),('CZE','República Checa'),('DOM','República Dominicana'),('REU','Reunión'),('RWA','Ruanda'),('ROU','Rumanía'),('RUS','Rusia'),('ESH','Sahara Occidental'),('WSM','Samoa'),('ASM','Samoa Americana'),('BLM','San Bartolomé'),('KNA','San Cristóbal y Niev'),('SMR','San Marino'),('MAF','San Martín (Francia)'),('SPM','San Pedro y Miquelón'),('VCT','San Vicente y las Gr'),('SHN','Santa Elena'),('LCA','Santa Lucía'),('STP','Santo Tomé y Príncip'),('SEN','Senegal'),('SRB','Serbia'),('SYC','Seychelles'),('SLE','Sierra Leona'),('SGP','Singapur'),('SYR','Siria'),('SOM','Somalia'),('LKA','Sri lanka'),('ZAF','Sudáfrica'),('SDN','Sudán'),('SWE','Suecia'),('CHE','Suiza'),('SUR','Surinám'),('SJM','Svalbard y Jan Mayen'),('SWZ','Swazilandia'),('TJK','Tadjikistán'),('THA','Tailandia'),('TWN','Taiwán'),('TZA','Tanzania'),('IOT','Territorio Británico'),('ATF','Territorios Australe'),('TLS','Timor Oriental'),('TGO','Togo'),('TKL','Tokelau'),('TON','Tonga'),('TTO','Trinidad y Tobago'),('TUN','Tunez'),('TKM','Turkmenistán'),('TUR','Turquía'),('TUV','Tuvalu'),('UKR','Ucrania'),('UGA','Uganda'),('URY','Uruguay'),('UZB','Uzbekistán'),('VUT','Vanuatu'),('VEN','Venezuela'),('VNM','Vietnam'),('WLF','Wallis y Futuna'),('YEM','Yemen'),('DJI','Yibuti'),('ZMB','Zambia'),('ZWE','Zimbabue');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tema`
--

LOCK TABLES `tema` WRITE;
/*!40000 ALTER TABLE `tema` DISABLE KEYS */;
INSERT INTO `tema` VALUES (1,'Realidad social'),(2,'Creencias religiosas'),(3,'Realismo magico'),(4,'Formas de vida'),(5,'Clasicos latinoamerica');
/*!40000 ALTER TABLE `tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipos_usuario`
--

LOCK TABLES `tipos_usuario` WRITE;
/*!40000 ALTER TABLE `tipos_usuario` DISABLE KEYS */;
INSERT INTO `tipos_usuario` VALUES ('B','Bibliotecario','Posee acceso total a la plataforma'),('U','Usuario','Puede registrar y visaulizar libros');
/*!40000 ALTER TABLE `tipos_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('B0001','Jasson Alexander','Lopez Soriano','jasson_alex99@hotmail.com','1999-07-10','B0001','84472/74362/10143*109123-112163-66282!80432/72342*',1,'B'),('B0002','Franklin Armando','Esquivel Guevara','franklin.esquivel@outlook.com','1998-10-09','B0002','112163-113173/52A2/107103!116203*107103/88512/120243-',1,'B'),('U0003','Diego Alberto','Lemus Torres','ciegolem7@gmail.es','1998-09-18','U0001','57p2!121253-10473/66282/76382*51y2*88512-9702/',1,'U'),('U0004','Marta Carolina','Rosado Pérez','marta.rope@gmail.com','1998-08-12','U0002','10693-51y2*56o2/65272-68302-115193!111153!10363!',1,'U'),('U0005','Leonarlo Elenilson','Lopez Cañas','lopezleonardo44@gmail.com','1999-04-09','U0003','75372-10693-66282/116203-84472!49l2/70322-56o2!',1,'U');
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

-- Dump completed on 2018-04-18 19:13:33
