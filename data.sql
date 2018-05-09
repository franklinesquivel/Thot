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
INSERT INTO `detalle_autorlibro` VALUES (1,'A0002','L0001'),(2,'A0004','L0002'),(3,'A0003','L0003'),(4,'A0001','L0004'),(5,'A0005','L0005'),(6,'A0002','L0006'),(7,'A0002','L0007'),(8,'A0002','L0008'),(9,'A0004','L0009'),(10,'A0004','L0010'),(11,'A0004','L0011'),(12,'A0001','L0012'),(13,'A0001','L0013'),(14,'A0001','L0014'),(15,'A0001','L0015');
/*!40000 ALTER TABLE `detalle_autorlibro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `detalle_librotema`
--

LOCK TABLES `detalle_librotema` WRITE;
/*!40000 ALTER TABLE `detalle_librotema` DISABLE KEYS */;
INSERT INTO `detalle_librotema` VALUES (1,'L0001',2),(2,'L0002',5),(3,'L0003',3),(4,'L0004',5),(5,'L0005',1),(6,'L0006',2),(7,'L0006',1),(8,'L0007',2),(9,'L0007',4),(10,'L0007',1),(11,'L0008',2),(12,'L0008',1),(13,'L0009',1),(14,'L0010',5),(15,'L0011',5),(16,'L0012',5),(17,'L0013',5),(18,'L0013',4),(19,'L0014',5),(20,'L0015',5);
/*!40000 ALTER TABLE `detalle_librotema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ejemplar`
--

LOCK TABLES `ejemplar` WRITE;
/*!40000 ALTER TABLE `ejemplar` DISABLE KEYS */;
INSERT INTO `ejemplar` VALUES ('EL00010001',NULL,'Libro nuevo.','R','L0001'),('EL00010002',NULL,'Libro nuevo.','D','L0001'),('EL00010003',NULL,'Libro nuevo.','D','L0001'),('EL00010004',NULL,'Libro nuevo.','D','L0001'),('EL00010005',NULL,'Libro nuevo.','D','L0001'),('EL00020001',NULL,'Ejemplar nuevo.','P','L0002'),('EL00020002',NULL,'Ejemplar nuevo.','R','L0002'),('EL00020003',NULL,'Manchado en la última página.','P','L0002'),('EL00030001',NULL,'Libro nuevo.','P','L0003'),('EL00030002',NULL,'Libro nuevo.','P','L0003'),('EL00030003',NULL,'Libro nuevo.','R','L0003'),('EL00030004',NULL,'Libro nuevo.','D','L0003'),('EL00030005',NULL,'Libro nuevo.','D','L0003'),('EL00030006',NULL,'Libro nuevo.','D','L0003'),('EL00030007',NULL,'Libro nuevo.','D','L0003'),('EL00030008',NULL,'Libro nuevo.','D','L0003'),('EL00030009',NULL,'Libro nuevo.','D','L0003'),('EL00030010',NULL,'Libro nuevo.','D','L0003'),('EL00030011',NULL,'Libro nuevo.','D','L0003'),('EL00030012',NULL,'Libro nuevo.','D','L0003'),('EL00030013',NULL,'Libro nuevo.','D','L0003'),('EL00030014',NULL,'Libro nuevo.','D','L0003'),('EL00030015',NULL,'Libro nuevo.','D','L0003'),('EL00030016',NULL,'Libro nuevo.','D','L0003'),('EL00030017',NULL,'Libro nuevo.','D','L0003'),('EL00030018',NULL,'Libro nuevo.','D','L0003'),('EL00030019',NULL,'Libro nuevo.','D','L0003'),('EL00030020',NULL,'Libro nuevo.','D','L0003'),('EL00040001',NULL,'Libro nuevo.','P','L0004'),('EL00040002',NULL,'Libro nuevo.','D','L0004'),('EL00040003',NULL,'Libro nuevo.','D','L0004'),('EL00040004',NULL,'Libro nuevo.','D','L0004'),('EL00040005',NULL,'Libro nuevo.','D','L0004'),('EL00040006',NULL,'Libro nuevo.','D','L0004'),('EL00040007',NULL,'Libro nuevo.','D','L0004'),('EL00040008',NULL,'Libro nuevo.','D','L0004'),('EL00040009',NULL,'Libro nuevo.','D','L0004'),('EL00040010',NULL,'Libro nuevo.','D','L0004'),('EL00040011',NULL,'Libro nuevo.','D','L0004'),('EL00040012',NULL,'Libro nuevo.','D','L0004'),('EL00040013',NULL,'Libro nuevo.','D','L0004'),('EL00050001',NULL,'Libro nuevo.','P','L0005'),('EL00050002',NULL,'Libro nuevo.','R','L0005'),('EL00050003',NULL,'Libro nuevo.','D','L0005'),('EL00050004',NULL,'Libro nuevo.','D','L0005'),('EL00050005',NULL,'Libro nuevo.','D','L0005'),('EL00050006',NULL,'Libro nuevo.','D','L0005'),('EL00050007',NULL,'Libro nuevo.','D','L0005'),('EL00060001',NULL,'Libro nuevo.','R','L0006'),('EL00060002',NULL,'Libro nuevo.','D','L0006'),('EL00060003',NULL,'Libro nuevo.','D','L0006'),('EL00060004',NULL,'Libro nuevo.','D','L0006'),('EL00070001',NULL,'Libro nuevo.','R','L0007'),('EL00070002',NULL,'Libro nuevo.','D','L0007'),('EL00070003',NULL,'Libro nuevo.','D','L0007'),('EL00070004',NULL,'Libro nuevo.','D','L0007'),('EL00070005',NULL,'Libro nuevo.','D','L0007'),('EL00070006',NULL,'Libro nuevo.','D','L0007'),('EL00080001',NULL,'Libro nuevo.','P','L0008'),('EL00080002',NULL,'Libro nuevo.','D','L0008'),('EL00080003',NULL,'Libro nuevo.','D','L0008'),('EL00080004',NULL,'Libro nuevo.','D','L0008'),('EL00080005',NULL,'Libro nuevo.','D','L0008'),('EL00090001',NULL,'Libro nuevo.','P','L0009'),('EL00090002',NULL,'Libro nuevo.','R','L0009'),('EL00090003',NULL,'Libro nuevo.','D','L0009'),('EL00090004',NULL,'Libro nuevo.','D','L0009'),('EL00090005',NULL,'Libro nuevo.','D','L0009'),('EL00100001',NULL,'Libro nuevo.','R','L0010'),('EL00100002',NULL,'Libro nuevo.','D','L0010'),('EL00100003',NULL,'Libro nuevo.','D','L0010'),('EL00100004',NULL,'Libro nuevo.','D','L0010'),('EL00110001',NULL,'Libro nuevo.','P','L0011'),('EL00110002',NULL,'Libro nuevo.','P','L0011'),('EL00110003',NULL,'Libro nuevo.','R','L0011'),('EL00110004',NULL,'Libro nuevo.','D','L0011'),('EL00110005',NULL,'Libro nuevo.','D','L0011'),('EL00120001',NULL,'Libro nuevo.','D','L0012'),('EL00120002',NULL,'Libro nuevo.','D','L0012'),('EL00120003',NULL,'Libro nuevo.','D','L0012'),('EL00120004',NULL,'Libro nuevo.','D','L0012'),('EL00130001',NULL,'Libro nuevo.','D','L0013'),('EL00130002',NULL,'Libro nuevo.','D','L0013'),('EL00130003',NULL,'Libro nuevo.','D','L0013'),('EL00130004',NULL,'Libro nuevo.','D','L0013'),('EL00140001',NULL,'Libro nuevo.','R','L0014'),('EL00140002',NULL,'Libro nuevo.','D','L0014'),('EL00140003',NULL,'Libro nuevo.','D','L0014'),('EL00140004',NULL,'Libro nuevo.','D','L0014'),('EL00140005',NULL,'Libro nuevo.','D','L0014'),('EL00150001',NULL,'Libro nuevo.','R','L0015'),('EL00150002',NULL,'Libro nuevo.','D','L0015'),('EL00150003',NULL,'Libro nuevo.','D','L0015'),('EL00150004',NULL,'Libro nuevo.','D','L0015'),('EL00150005',NULL,'Libro nuevo.','D','L0015');
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
INSERT INTO `libro` VALUES ('L0001','Así habló Zaratustra','ISBN 901-7-314-36698-3','2','La obra contiene las principales ideas de Nietzsche, expresadas de forma poetica.','Dificil de interpretar Para mentes desarrolladas','L0001.jpg',5,'IMTA0004',1,1),('L0002','Cien años de soledad','ISBN 521-0-256-00528-0','31','Es una novela de gabriel garcia marquez','Ganador del premio nobel','L0002.jpg',3,'IMTA0001',3,1),('L0003','Don quijote','ISBN 769-5-321-87501-9','12','Es la obra mas destacada de la literatura española','Una buena novela','L0003.jpg',20,'IMTA0005',2,1),('L0004','Cien sonetos de amor','ISBN 349-6-003-74320-9','16','Una coleccion de sonetos escrito por pablo neruda','Fue publicada en 1959','L0004.jpg',13,'IMTA0003',3,1),('L0005','Bodas de sangre','ISBN 349-6-003-74320-7','4','es una tragedia escrita en verso y en prosa del escritor español Federico García Lorca','Escrita en el año 1931','L0005.jpg',7,'IMTA0004',3,1),('L0006','La Genealogia de lo moral','ISBN 901-7-314-37388-6','5','es una obra del filósofo alemán Friedrich Nietzsche, publicada en 1887. Fue un intento de suplementar y clarificar el punto de vista de su libro anterior, Más allá del bien y del mal.','Posterior a mas alla del bien y el mal','L0006.jpg',4,'IMTA0003',1,1),('L0007','Mas alla del bien y el mal','ISBN 901-7-374-36513-4','64','Es uno de los textos fundamentales de la filosofía del siglo XIX, del filósofo alemán Friedrich Nietzsche. Nietzsche atacaba en él lo que consideraba vacuidad moral de los pensadores de su siglo, falta de sentido crítico alguno de los autodenominados moralistas y su pasiva aceptación de la moral heredada judeo-cristiana.','Opinion critica, mente abierta','L0007.jpg',6,'IMTA0005',1,1),('L0008','Ecce Homo','ISBN 561-7-314-34819-3','45','En el, Nietzsche, casi al borde de la crisis que le llevará a ser internado en un hospital psiquiátrico, pretende realizar un último intento por dar a conocer su filosofía, presentándose a sí mismo como autor de las obras que, según él, cambiarían la historia del pensamiento y quizá el curso de la Historia misma.','Mente abierta, Puede herir susceptibilidades','L0008.jpg',5,'IMTA0002',1,1),('L0009','Crónica de una muerte anunciada','ISBN 791-7-314-34818-3','6','Es una historia basada en un hecho histórico que sucede en torno a la muerte de Santiago Nasar. Se entiende que es el libro en el que García Márquez mezcla más su faceta como periodista y literario. Lo más diferente de sus líneas es la estructura ya que comienza con la muerte de Nasar y termina cuando éste justamente es asesinado.','Sin notas.','L0009.jpg',5,'IMTA0004',5,1),('L0010','La hojarasca','ISBN 163-7-314-78498-3','4','En ella, luego de la muerte de un hombre odiado por los habitantes del lugar, se cuenta sobre la perspectiva de tres generaciones distintas de una misma familia.','Sin notas adicionales.','L0010.jpg',4,'IMTA0002',3,1),('L0011','El Coronel no tiene quien le escriba','ISBN 901-7-724-34518-4','5','Esta historia narra lo que le sucede a un coronel retirado que aguarda por una carta que le permita gozar de sus derechos por haber servido a la patria. El autor la escribe durante su estadía en París.','Sin notas','L0011.jpg',5,'IMTA0003',3,1),('L0012','Confieso que he vivido','ISBN 349-6-003-74370-3','4','Es el libro autobiográfico de Neruda. En este texto se cuentan vivencias que dejaron una huella en la vida del autor aventuras con mujeres, conversaciones con el Che Guevara, viajes.','Sin notas','L0012.jpg',4,'IMTA0001',4,1),('L0013','Estravagario','ISBN 349-6-773-45320-9','5','es un libro diferente de Neruda. Los poemas aparentan ser más livianos, fáciles y disposición lúdica. A priori, se presenta como un libro más ingenioso y despreocupado, distanciándose del clásico estilo nerudiano.','Un libro un poco engañoso al principio','L0013.jpg',4,'IMTA0002',3,1),('L0014','Canto General','ISBN 719-6-003-74715-9','6','es una de las obras más ambiciosas de Neruda. Este libro es un poema ordenado en 15 cantos, 231 poemas y más de 15 mil versos que el autor compuso a lo largo de 13 años. Los principales críticos clasifican a este texto como poesía épica, ya que está dirigida a la naturaleza e historia del continente.','Sin notas.','L0014.jpg',5,'IMTA0004',3,1),('L0015','Odas elementales','ISBN 131-7-748-36698-3','5','es el libro que pone punto final a la relación de Pablo Neruda con surrealismo inicial. Esta colección está compuesta por cincuenta poemas.','Sin notas','L0015.jpg',5,'IMTA0003',3,1);
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
INSERT INTO `prestamo` VALUES ('PU0003EL0002000320180001','2018-01-04 23:32:15','2018-01-08 01:32:15',0.00,'FO','EL00020003','U0003'),('PU0003EL0008000120180002','2018-05-04 02:38:48','2018-05-08 02:38:48',0.25,'VO','EL00080001','U0003'),('PU0004EL0005000120180001','2018-05-06 02:41:04','2018-05-11 02:41:04',0.00,'EP','EL00050001','U0004'),('PU0005EL0002000120180001','2018-04-05 21:54:35','2018-04-09 21:54:35',7.50,'VO','EL00020001','U0005'),('PU0005EL0011000120180002','2018-05-05 02:39:47','2018-05-10 02:39:47',0.00,'EP','EL00110001','U0005'),('PU0006EL0003000120180001','2018-05-02 02:40:08','2018-05-12 02:40:08',0.00,'FO','EL00030001','U0006'),('PU0007EL0009000120180001','2018-02-15 02:41:22','2018-02-20 02:41:22',0.00,'FO','EL00090001','U0007'),('PU0008EL0003000220180002','2018-05-05 02:41:40','2018-05-08 02:41:40',0.00,'FO','EL00030002','U0008'),('PU0008EL0004000120180001','2018-04-26 02:40:29','2018-05-01 02:40:29',0.00,'FO','EL00040001','U0008'),('PU0010EL0011000220180001','2018-04-11 02:40:41','2018-04-14 02:40:41',0.00,'FO','EL00110002','U0010');
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES ('RU0003EL0001000120180002','2018-03-09 03:30:51','2018-03-11 03:30:51','VO','EL00010001','U0003'),('RU0003EL0002000220180001','2018-05-02 21:56:16','2018-05-02 21:56:16','VO','EL00020002','U0003'),('RU0006EL0003000320180001','2018-05-02 02:53:31','2018-05-05 02:53:31','VO','EL00030003','U0006'),('RU0006EL0005000220180002','2018-05-09 02:53:52','2018-05-11 02:53:52','VE','EL00050002','U0006'),('RU0007EL0006000120180001','2018-05-05 02:56:20','2018-05-10 02:56:20','EO','EL00060001','U0007'),('RU0007EL0007000120180002','2018-01-04 02:56:25','2018-01-06 02:56:25','VO','EL00070001','U0007'),('RU0007EL0010000120180003','2018-02-03 02:56:37','2018-02-06 02:56:37','VO','EL00100001','U0007'),('RU0008EL0009000220180001','2018-05-09 02:52:50','2018-05-10 02:52:50','VE','EL00090002','U0008'),('RU0008EL0014000120180002','2018-04-04 02:52:58','2018-04-07 02:52:58','VO','EL00140001','U0008'),('RU0009EL0011000320180002','2018-05-06 02:58:27','2018-05-11 02:58:27','VE','EL00110003','U0009'),('RU0009EL0015000120180001','2017-12-08 02:58:07','2017-12-12 02:58:07','VO','EL00150001','U0009');
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
INSERT INTO `usuario` VALUES ('B0001','Jasson Alexander','Lopez Soriano','jasson_alex99@hotmail.com','1999-07-10','B0001','84472/74362/10143*109123-112163-66282!80432/72342*',1,'B'),('B0002','Franklin Armando','Esquivel Guevara','franklin.esquivel@outlook.com','1998-10-09','B0002','112163-113173/52A2/107103!116203*107103/88512/120243-',1,'B'),('U0003','Diego Alberto','Lemus Torres','ciegolem7@gmail.es','1998-09-18','U0003','57p2!121253-10473/66282/76382*51y2*88512-9702/',1,'U'),('U0004','Marta Carolina','Rosado Pérez','marta.rope@gmail.com','1998-08-12','U0004','10693-51y2*56o2/65272-68302-115193!111153!10363!',1,'U'),('U0005','Leonarlo Elenilson','Lopez Cañas','lopezleonardo282@gmail.com','1999-04-09','U0005','75372-10693-66282/116203-84472!49l2/70322-56o2!',1,'U'),('U0006','Jeferson Alberto','Gutierrez Hernandez','gutierrezjefferson@gmail.com','1992-04-16','U0006','66282/10253!68302*120243!71332-74362!65272!51y2/',1,'U'),('U0007','Oscar Armando','Santin Caceres','santin.armando@gmail.com','1988-03-09','U0007','111153!10583/88512-69312*78402!115193/72342!10693-',1,'U'),('U0008','Francisco Ernesto','Campos Mejia','francisco.campos@gmail.com','1997-07-10','U0008','67292!10693/117213-49l2!57p2/78402-120243*107103/',1,'U'),('U0009','Ursula Alejandra','Rodriguez Mejia','ursula.mejia@gmail.com','1991-11-02','U0009','78402*120243*9812-82452*10143/66282!74362*49l2-',1,'U'),('U0010','Francisca Javiera','Hernandez Mejia','francisca.hndez@gmail.com','1994-06-18','U0010','107103-9812/73352!57p2*67292-118223!88512-90532!',1,'U');
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

-- Dump completed on 2018-05-08 23:38:18
