-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-05-2018 a las 04:33:06
-- Versión del servidor: 5.7.14
-- Versión de PHP: 7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `thot`
--

--
-- Volcado de datos para la tabla `autor`
--

INSERT INTO `autor` VALUES('A0001', 'Ricardo Eliecer Neftali', 'Reyes Basoalto', '1904-07-02', 'CHL');
INSERT INTO `autor` VALUES('A0002', 'Friedrich Wilhelm', 'Nietzsche', '1844-10-15', 'DEU');
INSERT INTO `autor` VALUES('A0003', 'Miguel de Cervantes', 'Saavedra', '1547-09-29', 'ESP');
INSERT INTO `autor` VALUES('A0004', 'Gabriel Jose', 'García Márquez', '1927-03-17', 'MEX');
INSERT INTO `autor` VALUES('A0005', 'Federico', 'Garcia Lorca', '1898-06-05', 'ESP');

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` VALUES(1, 'Filosofia', 'Filosofia universal');
INSERT INTO `categoria` VALUES(2, 'Realismo magico', 'Realidad magicas externas');
INSERT INTO `categoria` VALUES(3, 'Literatura', 'Literatura universal');
INSERT INTO `categoria` VALUES(4, 'Biografias', 'Biografias o autobiografias');
INSERT INTO `categoria` VALUES(5, 'Historia', 'Historia universal');

--
-- Volcado de datos para la tabla `config`
--

INSERT INTO `config` VALUES(1, '0.25');

--
-- Volcado de datos para la tabla `detalle_autorlibro`
--

INSERT INTO `detalle_autorlibro` VALUES(1, 'A0002', 'L0001');
INSERT INTO `detalle_autorlibro` VALUES(2, 'A0004', 'L0002');
INSERT INTO `detalle_autorlibro` VALUES(3, 'A0003', 'L0003');
INSERT INTO `detalle_autorlibro` VALUES(4, 'A0001', 'L0004');
INSERT INTO `detalle_autorlibro` VALUES(5, 'A0005', 'L0005');
INSERT INTO `detalle_autorlibro` VALUES(6, 'A0002', 'L0006');
INSERT INTO `detalle_autorlibro` VALUES(7, 'A0002', 'L0007');
INSERT INTO `detalle_autorlibro` VALUES(8, 'A0002', 'L0008');
INSERT INTO `detalle_autorlibro` VALUES(9, 'A0004', 'L0009');
INSERT INTO `detalle_autorlibro` VALUES(10, 'A0004', 'L0010');
INSERT INTO `detalle_autorlibro` VALUES(11, 'A0004', 'L0011');
INSERT INTO `detalle_autorlibro` VALUES(12, 'A0001', 'L0012');
INSERT INTO `detalle_autorlibro` VALUES(13, 'A0001', 'L0013');
INSERT INTO `detalle_autorlibro` VALUES(14, 'A0001', 'L0014');
INSERT INTO `detalle_autorlibro` VALUES(15, 'A0001', 'L0015');

--
-- Volcado de datos para la tabla `detalle_librotema`
--

INSERT INTO `detalle_librotema` VALUES(1, 'L0001', 2);
INSERT INTO `detalle_librotema` VALUES(2, 'L0002', 5);
INSERT INTO `detalle_librotema` VALUES(3, 'L0003', 3);
INSERT INTO `detalle_librotema` VALUES(4, 'L0004', 5);
INSERT INTO `detalle_librotema` VALUES(5, 'L0005', 1);
INSERT INTO `detalle_librotema` VALUES(6, 'L0006', 2);
INSERT INTO `detalle_librotema` VALUES(7, 'L0006', 1);
INSERT INTO `detalle_librotema` VALUES(8, 'L0007', 2);
INSERT INTO `detalle_librotema` VALUES(9, 'L0007', 4);
INSERT INTO `detalle_librotema` VALUES(10, 'L0007', 1);
INSERT INTO `detalle_librotema` VALUES(11, 'L0008', 2);
INSERT INTO `detalle_librotema` VALUES(12, 'L0008', 1);
INSERT INTO `detalle_librotema` VALUES(13, 'L0009', 1);
INSERT INTO `detalle_librotema` VALUES(14, 'L0010', 5);
INSERT INTO `detalle_librotema` VALUES(15, 'L0011', 5);
INSERT INTO `detalle_librotema` VALUES(16, 'L0012', 5);
INSERT INTO `detalle_librotema` VALUES(17, 'L0013', 5);
INSERT INTO `detalle_librotema` VALUES(18, 'L0013', 4);
INSERT INTO `detalle_librotema` VALUES(19, 'L0014', 5);
INSERT INTO `detalle_librotema` VALUES(20, 'L0015', 5);

--
-- Volcado de datos para la tabla `ejemplar`
--

INSERT INTO `ejemplar` VALUES('EL00010001', NULL, 'Libro nuevo.', 'R', 'L0001');
INSERT INTO `ejemplar` VALUES('EL00010002', NULL, 'Libro nuevo.', 'D', 'L0001');
INSERT INTO `ejemplar` VALUES('EL00010003', NULL, 'Libro nuevo.', 'D', 'L0001');
INSERT INTO `ejemplar` VALUES('EL00010004', NULL, 'Libro nuevo.', 'D', 'L0001');
INSERT INTO `ejemplar` VALUES('EL00010005', NULL, 'Libro nuevo.', 'D', 'L0001');
INSERT INTO `ejemplar` VALUES('EL00020001', NULL, 'Ejemplar nuevo.', 'P', 'L0002');
INSERT INTO `ejemplar` VALUES('EL00020002', NULL, 'Ejemplar nuevo.', 'R', 'L0002');
INSERT INTO `ejemplar` VALUES('EL00020003', NULL, 'Manchado en la última página.', 'P', 'L0002');
INSERT INTO `ejemplar` VALUES('EL00030001', NULL, 'Libro nuevo.', 'P', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030002', NULL, 'Libro nuevo.', 'P', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030003', NULL, 'Libro nuevo.', 'R', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030004', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030005', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030006', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030007', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030008', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030009', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030010', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030011', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030012', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030013', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030014', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030015', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030016', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030017', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030018', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030019', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00030020', NULL, 'Libro nuevo.', 'D', 'L0003');
INSERT INTO `ejemplar` VALUES('EL00040001', NULL, 'Libro nuevo.', 'P', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040002', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040003', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040004', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040005', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040006', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040007', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040008', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040009', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040010', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040011', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040012', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00040013', NULL, 'Libro nuevo.', 'D', 'L0004');
INSERT INTO `ejemplar` VALUES('EL00050001', NULL, 'Libro nuevo.', 'P', 'L0005');
INSERT INTO `ejemplar` VALUES('EL00050002', NULL, 'Libro nuevo.', 'R', 'L0005');
INSERT INTO `ejemplar` VALUES('EL00050003', NULL, 'Libro nuevo.', 'D', 'L0005');
INSERT INTO `ejemplar` VALUES('EL00050004', NULL, 'Libro nuevo.', 'D', 'L0005');
INSERT INTO `ejemplar` VALUES('EL00050005', NULL, 'Libro nuevo.', 'D', 'L0005');
INSERT INTO `ejemplar` VALUES('EL00050006', NULL, 'Libro nuevo.', 'D', 'L0005');
INSERT INTO `ejemplar` VALUES('EL00050007', NULL, 'Libro nuevo.', 'D', 'L0005');
INSERT INTO `ejemplar` VALUES('EL00060001', NULL, 'Libro nuevo.', 'R', 'L0006');
INSERT INTO `ejemplar` VALUES('EL00060002', NULL, 'Libro nuevo.', 'D', 'L0006');
INSERT INTO `ejemplar` VALUES('EL00060003', NULL, 'Libro nuevo.', 'D', 'L0006');
INSERT INTO `ejemplar` VALUES('EL00060004', NULL, 'Libro nuevo.', 'D', 'L0006');
INSERT INTO `ejemplar` VALUES('EL00070001', NULL, 'Libro nuevo.', 'R', 'L0007');
INSERT INTO `ejemplar` VALUES('EL00070002', NULL, 'Libro nuevo.', 'D', 'L0007');
INSERT INTO `ejemplar` VALUES('EL00070003', NULL, 'Libro nuevo.', 'D', 'L0007');
INSERT INTO `ejemplar` VALUES('EL00070004', NULL, 'Libro nuevo.', 'D', 'L0007');
INSERT INTO `ejemplar` VALUES('EL00070005', NULL, 'Libro nuevo.', 'D', 'L0007');
INSERT INTO `ejemplar` VALUES('EL00070006', NULL, 'Libro nuevo.', 'D', 'L0007');
INSERT INTO `ejemplar` VALUES('EL00080001', NULL, 'Libro nuevo.', 'P', 'L0008');
INSERT INTO `ejemplar` VALUES('EL00080002', NULL, 'Libro nuevo.', 'D', 'L0008');
INSERT INTO `ejemplar` VALUES('EL00080003', NULL, 'Libro nuevo.', 'D', 'L0008');
INSERT INTO `ejemplar` VALUES('EL00080004', NULL, 'Libro nuevo.', 'D', 'L0008');
INSERT INTO `ejemplar` VALUES('EL00080005', NULL, 'Libro nuevo.', 'D', 'L0008');
INSERT INTO `ejemplar` VALUES('EL00090001', NULL, 'Libro nuevo.', 'P', 'L0009');
INSERT INTO `ejemplar` VALUES('EL00090002', NULL, 'Libro nuevo.', 'R', 'L0009');
INSERT INTO `ejemplar` VALUES('EL00090003', NULL, 'Libro nuevo.', 'D', 'L0009');
INSERT INTO `ejemplar` VALUES('EL00090004', NULL, 'Libro nuevo.', 'D', 'L0009');
INSERT INTO `ejemplar` VALUES('EL00090005', NULL, 'Libro nuevo.', 'D', 'L0009');
INSERT INTO `ejemplar` VALUES('EL00100001', NULL, 'Libro nuevo.', 'R', 'L0010');
INSERT INTO `ejemplar` VALUES('EL00100002', NULL, 'Libro nuevo.', 'D', 'L0010');
INSERT INTO `ejemplar` VALUES('EL00100003', NULL, 'Libro nuevo.', 'D', 'L0010');
INSERT INTO `ejemplar` VALUES('EL00100004', NULL, 'Libro nuevo.', 'D', 'L0010');
INSERT INTO `ejemplar` VALUES('EL00110001', NULL, 'Libro nuevo.', 'P', 'L0011');
INSERT INTO `ejemplar` VALUES('EL00110002', NULL, 'Libro nuevo.', 'P', 'L0011');
INSERT INTO `ejemplar` VALUES('EL00110003', NULL, 'Libro nuevo.', 'R', 'L0011');
INSERT INTO `ejemplar` VALUES('EL00110004', NULL, 'Libro nuevo.', 'D', 'L0011');
INSERT INTO `ejemplar` VALUES('EL00110005', NULL, 'Libro nuevo.', 'D', 'L0011');
INSERT INTO `ejemplar` VALUES('EL00120001', NULL, 'Libro nuevo.', 'D', 'L0012');
INSERT INTO `ejemplar` VALUES('EL00120002', NULL, 'Libro nuevo.', 'D', 'L0012');
INSERT INTO `ejemplar` VALUES('EL00120003', NULL, 'Libro nuevo.', 'D', 'L0012');
INSERT INTO `ejemplar` VALUES('EL00120004', NULL, 'Libro nuevo.', 'D', 'L0012');
INSERT INTO `ejemplar` VALUES('EL00130001', NULL, 'Libro nuevo.', 'D', 'L0013');
INSERT INTO `ejemplar` VALUES('EL00130002', NULL, 'Libro nuevo.', 'D', 'L0013');
INSERT INTO `ejemplar` VALUES('EL00130003', NULL, 'Libro nuevo.', 'D', 'L0013');
INSERT INTO `ejemplar` VALUES('EL00130004', NULL, 'Libro nuevo.', 'D', 'L0013');
INSERT INTO `ejemplar` VALUES('EL00140001', NULL, 'Libro nuevo.', 'R', 'L0014');
INSERT INTO `ejemplar` VALUES('EL00140002', NULL, 'Libro nuevo.', 'D', 'L0014');
INSERT INTO `ejemplar` VALUES('EL00140003', NULL, 'Libro nuevo.', 'D', 'L0014');
INSERT INTO `ejemplar` VALUES('EL00140004', NULL, 'Libro nuevo.', 'D', 'L0014');
INSERT INTO `ejemplar` VALUES('EL00140005', NULL, 'Libro nuevo.', 'D', 'L0014');
INSERT INTO `ejemplar` VALUES('EL00150001', NULL, 'Libro nuevo.', 'R', 'L0015');
INSERT INTO `ejemplar` VALUES('EL00150002', NULL, 'Libro nuevo.', 'D', 'L0015');
INSERT INTO `ejemplar` VALUES('EL00150003', NULL, 'Libro nuevo.', 'D', 'L0015');
INSERT INTO `ejemplar` VALUES('EL00150004', NULL, 'Libro nuevo.', 'D', 'L0015');
INSERT INTO `ejemplar` VALUES('EL00150005', NULL, 'Libro nuevo.', 'D', 'L0015');

--
-- Volcado de datos para la tabla `imprenta`
--

INSERT INTO `imprenta` VALUES('IMTA0001', 'ALBACROME', 'Km.13. a, 5, Comalapa');
INSERT INTO `imprenta` VALUES('IMTA0002', 'Creadores', 'Calle al volcan Col. Sta. Gertrudis No. 2A Mejicanos, San Salvador');
INSERT INTO `imprenta` VALUES('IMTA0003', 'IMPRESAL', 'Calle Cuscatlan No. 532, 87 Avenida Sur, San Salvador');
INSERT INTO `imprenta` VALUES('IMTA0004', 'Tipografia comercial', '1a Calle Oriente, Santa Ana 2201');
INSERT INTO `imprenta` VALUES('IMTA0005', 'VIMTAZA', 'Carretera al puerto de la libertad, santa tecla');

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` VALUES('L0001', 'Así habló Zaratustra', 'ISBN 901-7-314-36698-3', '2', 'La obra contiene las principales ideas de Nietzsche, expresadas de forma poetica.', 'Dificil de interpretar Para mentes desarrolladas', 'L0001.jpg', 5, 'IMTA0004', 1, 1);
INSERT INTO `libro` VALUES('L0002', 'Cien años de soledad', 'ISBN 521-0-256-00528-0', '31', 'Es una novela de gabriel garcia marquez', 'Ganador del premio nobel', 'L0002.jpg', 3, 'IMTA0001', 3, 1);
INSERT INTO `libro` VALUES('L0003', 'Don quijote', 'ISBN 769-5-321-87501-9', '12', 'Es la obra mas destacada de la literatura española', 'Una buena novela', 'L0003.jpg', 20, 'IMTA0005', 2, 1);
INSERT INTO `libro` VALUES('L0004', 'Cien sonetos de amor', 'ISBN 349-6-003-74320-9', '16', 'Una coleccion de sonetos escrito por pablo neruda', 'Fue publicada en 1959', 'L0004.jpg', 13, 'IMTA0003', 3, 1);
INSERT INTO `libro` VALUES('L0005', 'Bodas de sangre', 'ISBN 349-6-003-74320-7', '4', 'es una tragedia escrita en verso y en prosa del escritor español Federico García Lorca', 'Escrita en el año 1931', 'L0005.jpg', 7, 'IMTA0004', 3, 1);
INSERT INTO `libro` VALUES('L0006', 'La Genealogia de lo moral', 'ISBN 901-7-314-37388-6', '5', 'es una obra del filósofo alemán Friedrich Nietzsche, publicada en 1887. Fue un intento de suplementar y clarificar el punto de vista de su libro anterior, Más allá del bien y del mal.', 'Posterior a mas alla del bien y el mal', 'L0006.jpg', 4, 'IMTA0003', 1, 1);
INSERT INTO `libro` VALUES('L0007', 'Mas alla del bien y el mal', 'ISBN 901-7-374-36513-4', '64', 'Es uno de los textos fundamentales de la filosofía del siglo XIX, del filósofo alemán Friedrich Nietzsche. Nietzsche atacaba en él lo que consideraba vacuidad moral de los pensadores de su siglo, falta de sentido crítico alguno de los autodenominados moralistas y su pasiva aceptación de la moral heredada judeo-cristiana.', 'Opinion critica, mente abierta', 'L0007.jpg', 6, 'IMTA0005', 1, 1);
INSERT INTO `libro` VALUES('L0008', 'Ecce Homo', 'ISBN 561-7-314-34819-3', '45', 'En el, Nietzsche, casi al borde de la crisis que le llevará a ser internado en un hospital psiquiátrico, pretende realizar un último intento por dar a conocer su filosofía, presentándose a sí mismo como autor de las obras que, según él, cambiarían la historia del pensamiento y quizá el curso de la Historia misma.', 'Mente abierta, Puede herir susceptibilidades', 'L0008.jpg', 5, 'IMTA0002', 1, 1);
INSERT INTO `libro` VALUES('L0009', 'Crónica de una muerte anunciada', 'ISBN 791-7-314-34818-3', '6', 'Es una historia basada en un hecho histórico que sucede en torno a la muerte de Santiago Nasar. Se entiende que es el libro en el que García Márquez mezcla más su faceta como periodista y literario. Lo más diferente de sus líneas es la estructura ya que comienza con la muerte de Nasar y termina cuando éste justamente es asesinado.', 'Sin notas.', 'L0009.jpg', 5, 'IMTA0004', 5, 1);
INSERT INTO `libro` VALUES('L0010', 'La hojarasca', 'ISBN 163-7-314-78498-3', '4', 'En ella, luego de la muerte de un hombre odiado por los habitantes del lugar, se cuenta sobre la perspectiva de tres generaciones distintas de una misma familia.', 'Sin notas adicionales.', 'L0010.jpg', 4, 'IMTA0002', 3, 1);
INSERT INTO `libro` VALUES('L0011', 'El Coronel no tiene quien le escriba', 'ISBN 901-7-724-34518-4', '5', 'Esta historia narra lo que le sucede a un coronel retirado que aguarda por una carta que le permita gozar de sus derechos por haber servido a la patria. El autor la escribe durante su estadía en París.', 'Sin notas', 'L0011.jpg', 5, 'IMTA0003', 3, 1);
INSERT INTO `libro` VALUES('L0012', 'Confieso que he vivido', 'ISBN 349-6-003-74370-3', '4', 'Es el libro autobiográfico de Neruda. En este texto se cuentan vivencias que dejaron una huella en la vida del autor aventuras con mujeres, conversaciones con el Che Guevara, viajes.', 'Sin notas', 'L0012.jpg', 4, 'IMTA0001', 4, 1);
INSERT INTO `libro` VALUES('L0013', 'Estravagario', 'ISBN 349-6-773-45320-9', '5', 'es un libro diferente de Neruda. Los poemas aparentan ser más livianos, fáciles y disposición lúdica. A priori, se presenta como un libro más ingenioso y despreocupado, distanciándose del clásico estilo nerudiano.', 'Un libro un poco engañoso al principio', 'L0013.jpg', 4, 'IMTA0002', 3, 1);
INSERT INTO `libro` VALUES('L0014', 'Canto General', 'ISBN 719-6-003-74715-9', '6', 'es una de las obras más ambiciosas de Neruda. Este libro es un poema ordenado en 15 cantos, 231 poemas y más de 15 mil versos que el autor compuso a lo largo de 13 años. Los principales críticos clasifican a este texto como poesía épica, ya que está dirigida a la naturaleza e historia del continente.', 'Sin notas.', 'L0014.jpg', 5, 'IMTA0004', 3, 1);
INSERT INTO `libro` VALUES('L0015', 'Odas elementales', 'ISBN 131-7-748-36698-3', '5', 'es el libro que pone punto final a la relación de Pablo Neruda con surrealismo inicial. Esta colección está compuesta por cincuenta poemas.', 'Sin notas', 'L0015.jpg', 5, 'IMTA0003', 3, 1);

--
-- Volcado de datos para la tabla `pais`
--

INSERT INTO `pais` VALUES('AFG', 'Afganistán');
INSERT INTO `pais` VALUES('ALB', 'Albania');
INSERT INTO `pais` VALUES('DEU', 'Alemania');
INSERT INTO `pais` VALUES('DZA', 'Algeria');
INSERT INTO `pais` VALUES('AND', 'Andorra');
INSERT INTO `pais` VALUES('AGO', 'Angola');
INSERT INTO `pais` VALUES('AIA', 'Anguila');
INSERT INTO `pais` VALUES('ATA', 'Antártida');
INSERT INTO `pais` VALUES('ATG', 'Antigua y Barbuda');
INSERT INTO `pais` VALUES('ANT', 'Antillas Neerlandesa');
INSERT INTO `pais` VALUES('SAU', 'Arabia Saudita');
INSERT INTO `pais` VALUES('ARG', 'Argentina');
INSERT INTO `pais` VALUES('ARM', 'Armenia');
INSERT INTO `pais` VALUES('ABW', 'Aruba');
INSERT INTO `pais` VALUES('AUS', 'Australia');
INSERT INTO `pais` VALUES('AUT', 'Austria');
INSERT INTO `pais` VALUES('AZE', 'Azerbayán');
INSERT INTO `pais` VALUES('BHS', 'Bahamas');
INSERT INTO `pais` VALUES('BHR', 'Bahrein');
INSERT INTO `pais` VALUES('BGD', 'Bangladesh');
INSERT INTO `pais` VALUES('BRB', 'Barbados');
INSERT INTO `pais` VALUES('BEL', 'Bélgica');
INSERT INTO `pais` VALUES('BLZ', 'Belice');
INSERT INTO `pais` VALUES('BEN', 'Benín');
INSERT INTO `pais` VALUES('BTN', 'Bhután');
INSERT INTO `pais` VALUES('BLR', 'Bielorrusia');
INSERT INTO `pais` VALUES('MMR', 'Birmania');
INSERT INTO `pais` VALUES('BOL', 'Bolivia');
INSERT INTO `pais` VALUES('BIH', 'Bosnia y Herzegovina');
INSERT INTO `pais` VALUES('BWA', 'Botsuana');
INSERT INTO `pais` VALUES('BRA', 'Brasil');
INSERT INTO `pais` VALUES('BRN', 'Brunéi');
INSERT INTO `pais` VALUES('BGR', 'Bulgaria');
INSERT INTO `pais` VALUES('BFA', 'Burkina Faso');
INSERT INTO `pais` VALUES('BDI', 'Burundi');
INSERT INTO `pais` VALUES('CPV', 'Cabo Verde');
INSERT INTO `pais` VALUES('KHM', 'Camboya');
INSERT INTO `pais` VALUES('CMR', 'Camerún');
INSERT INTO `pais` VALUES('CAN', 'Canadá');
INSERT INTO `pais` VALUES('VAT', 'Ciudad del Vaticano');
INSERT INTO `pais` VALUES('COL', 'Colombia');
INSERT INTO `pais` VALUES('COM', 'Comoras');
INSERT INTO `pais` VALUES('COG', 'Congo');
INSERT INTO `pais` VALUES('PRK', 'Corea del Norte');
INSERT INTO `pais` VALUES('KOR', 'Corea del Sur');
INSERT INTO `pais` VALUES('CIV', 'Costa de Marfil');
INSERT INTO `pais` VALUES('CRI', 'Costa Rica');
INSERT INTO `pais` VALUES('HRV', 'Croacia');
INSERT INTO `pais` VALUES('CUB', 'Cuba');
INSERT INTO `pais` VALUES('TCD', 'Chad');
INSERT INTO `pais` VALUES('CHL', 'Chile');
INSERT INTO `pais` VALUES('CHN', 'China');
INSERT INTO `pais` VALUES('CYP', 'Chipre');
INSERT INTO `pais` VALUES('DNK', 'Dinamarca');
INSERT INTO `pais` VALUES('DMA', 'Dominica');
INSERT INTO `pais` VALUES('ECU', 'Ecuador');
INSERT INTO `pais` VALUES('EGY', 'Egipto');
INSERT INTO `pais` VALUES('SLV', 'El Salvador');
INSERT INTO `pais` VALUES('ARE', 'Emiratos Árabes Unid');
INSERT INTO `pais` VALUES('ERI', 'Eritrea');
INSERT INTO `pais` VALUES('SVK', 'Eslovaquia');
INSERT INTO `pais` VALUES('SVN', 'Eslovenia');
INSERT INTO `pais` VALUES('ESP', 'España');
INSERT INTO `pais` VALUES('USA', 'Estados Unidos de Am');
INSERT INTO `pais` VALUES('EST', 'Estonia');
INSERT INTO `pais` VALUES('ETH', 'Etiopía');
INSERT INTO `pais` VALUES('PHL', 'Filipinas');
INSERT INTO `pais` VALUES('FIN', 'Finlandia');
INSERT INTO `pais` VALUES('FJI', 'Fiyi');
INSERT INTO `pais` VALUES('FRA', 'Francia');
INSERT INTO `pais` VALUES('GAB', 'Gabón');
INSERT INTO `pais` VALUES('GMB', 'Gambia');
INSERT INTO `pais` VALUES('GEO', 'Georgia');
INSERT INTO `pais` VALUES('GHA', 'Ghana');
INSERT INTO `pais` VALUES('GIB', 'Gibraltar');
INSERT INTO `pais` VALUES('GRD', 'Granada');
INSERT INTO `pais` VALUES('GRC', 'Grecia');
INSERT INTO `pais` VALUES('GRL', 'Groenlandia');
INSERT INTO `pais` VALUES('GLP', 'Guadalupe');
INSERT INTO `pais` VALUES('GUM', 'Guam');
INSERT INTO `pais` VALUES('GTM', 'Guatemala');
INSERT INTO `pais` VALUES('GUF', 'Guayana Francesa');
INSERT INTO `pais` VALUES('GGY', 'Guernsey');
INSERT INTO `pais` VALUES('GIN', 'Guinea');
INSERT INTO `pais` VALUES('GNQ', 'Guinea Ecuatorial');
INSERT INTO `pais` VALUES('GNB', 'Guinea-Bissau');
INSERT INTO `pais` VALUES('GUY', 'Guyana');
INSERT INTO `pais` VALUES('HTI', 'Haití');
INSERT INTO `pais` VALUES('HND', 'Honduras');
INSERT INTO `pais` VALUES('HKG', 'Hong kong');
INSERT INTO `pais` VALUES('HUN', 'Hungría');
INSERT INTO `pais` VALUES('IND', 'India');
INSERT INTO `pais` VALUES('IDN', 'Indonesia');
INSERT INTO `pais` VALUES('IRQ', 'Irak');
INSERT INTO `pais` VALUES('IRN', 'Irán');
INSERT INTO `pais` VALUES('IRL', 'Irlanda');
INSERT INTO `pais` VALUES('BVT', 'Isla Bouvet');
INSERT INTO `pais` VALUES('IMN', 'Isla de Man');
INSERT INTO `pais` VALUES('CXR', 'Isla de Navidad');
INSERT INTO `pais` VALUES('NFK', 'Isla Norfolk');
INSERT INTO `pais` VALUES('ISL', 'Islandia');
INSERT INTO `pais` VALUES('BMU', 'Islas Bermudas');
INSERT INTO `pais` VALUES('CYM', 'Islas Caimán');
INSERT INTO `pais` VALUES('CCK', 'Islas Cocos (Keeling');
INSERT INTO `pais` VALUES('COK', 'Islas Cook');
INSERT INTO `pais` VALUES('ALA', 'Islas de Åland');
INSERT INTO `pais` VALUES('FRO', 'Islas Feroe');
INSERT INTO `pais` VALUES('SGS', 'Islas Georgias del S');
INSERT INTO `pais` VALUES('HMD', 'Islas Heard y McDona');
INSERT INTO `pais` VALUES('MDV', 'Islas Maldivas');
INSERT INTO `pais` VALUES('FLK', 'Islas Malvinas');
INSERT INTO `pais` VALUES('MNP', 'Islas Marianas del N');
INSERT INTO `pais` VALUES('MHL', 'Islas Marshall');
INSERT INTO `pais` VALUES('PCN', 'Islas Pitcairn');
INSERT INTO `pais` VALUES('SLB', 'Islas Salomón');
INSERT INTO `pais` VALUES('TCA', 'Islas Turcas y Caico');
INSERT INTO `pais` VALUES('UMI', 'Islas Ultramarinas M');
INSERT INTO `pais` VALUES('VG', 'Islas Vírgenes Britá');
INSERT INTO `pais` VALUES('VIR', 'Islas Vírgenes de lo');
INSERT INTO `pais` VALUES('ISR', 'Israel');
INSERT INTO `pais` VALUES('ITA', 'Italia');
INSERT INTO `pais` VALUES('JAM', 'Jamaica');
INSERT INTO `pais` VALUES('JPN', 'Japón');
INSERT INTO `pais` VALUES('JEY', 'Jersey');
INSERT INTO `pais` VALUES('JOR', 'Jordania');
INSERT INTO `pais` VALUES('KAZ', 'Kazajistán');
INSERT INTO `pais` VALUES('KEN', 'Kenia');
INSERT INTO `pais` VALUES('KGZ', 'Kirgizstán');
INSERT INTO `pais` VALUES('KIR', 'Kiribati');
INSERT INTO `pais` VALUES('KWT', 'Kuwait');
INSERT INTO `pais` VALUES('LAO', 'Laos');
INSERT INTO `pais` VALUES('LSO', 'Lesoto');
INSERT INTO `pais` VALUES('LVA', 'Letonia');
INSERT INTO `pais` VALUES('LBN', 'Líbano');
INSERT INTO `pais` VALUES('LBR', 'Liberia');
INSERT INTO `pais` VALUES('LBY', 'Libia');
INSERT INTO `pais` VALUES('LIE', 'Liechtenstein');
INSERT INTO `pais` VALUES('LTU', 'Lituania');
INSERT INTO `pais` VALUES('LUX', 'Luxemburgo');
INSERT INTO `pais` VALUES('MAC', 'Macao');
INSERT INTO `pais` VALUES('MKD', 'Macedônia');
INSERT INTO `pais` VALUES('MDG', 'Madagascar');
INSERT INTO `pais` VALUES('MYS', 'Malasia');
INSERT INTO `pais` VALUES('MWI', 'Malawi');
INSERT INTO `pais` VALUES('MLI', 'Mali');
INSERT INTO `pais` VALUES('MLT', 'Malta');
INSERT INTO `pais` VALUES('MAR', 'Marruecos');
INSERT INTO `pais` VALUES('MTQ', 'Martinica');
INSERT INTO `pais` VALUES('MUS', 'Mauricio');
INSERT INTO `pais` VALUES('MRT', 'Mauritania');
INSERT INTO `pais` VALUES('MYT', 'Mayotte');
INSERT INTO `pais` VALUES('MEX', 'México');
INSERT INTO `pais` VALUES('FSM', 'Micronesia');
INSERT INTO `pais` VALUES('MDA', 'Moldavia');
INSERT INTO `pais` VALUES('MCO', 'Mónaco');
INSERT INTO `pais` VALUES('MNG', 'Mongolia');
INSERT INTO `pais` VALUES('MNE', 'Montenegro');
INSERT INTO `pais` VALUES('MSR', 'Montserrat');
INSERT INTO `pais` VALUES('MOZ', 'Mozambique');
INSERT INTO `pais` VALUES('NAM', 'Namibia');
INSERT INTO `pais` VALUES('NRU', 'Nauru');
INSERT INTO `pais` VALUES('NPL', 'Nepal');
INSERT INTO `pais` VALUES('NIC', 'Nicaragua');
INSERT INTO `pais` VALUES('NER', 'Niger');
INSERT INTO `pais` VALUES('NGA', 'Nigeria');
INSERT INTO `pais` VALUES('NIU', 'Niue');
INSERT INTO `pais` VALUES('NOR', 'Noruega');
INSERT INTO `pais` VALUES('NCL', 'Nueva Caledonia');
INSERT INTO `pais` VALUES('NZL', 'Nueva Zelanda');
INSERT INTO `pais` VALUES('OMN', 'Omán');
INSERT INTO `pais` VALUES('NLD', 'Países Bajos');
INSERT INTO `pais` VALUES('PAK', 'Pakistán');
INSERT INTO `pais` VALUES('PLW', 'Palau');
INSERT INTO `pais` VALUES('PSE', 'Palestina');
INSERT INTO `pais` VALUES('PAN', 'Panamá');
INSERT INTO `pais` VALUES('PNG', 'Papúa Nueva Guinea');
INSERT INTO `pais` VALUES('PRY', 'Paraguay');
INSERT INTO `pais` VALUES('PER', 'Perú');
INSERT INTO `pais` VALUES('PYF', 'Polinesia Francesa');
INSERT INTO `pais` VALUES('POL', 'Polonia');
INSERT INTO `pais` VALUES('PRT', 'Portugal');
INSERT INTO `pais` VALUES('PRI', 'Puerto Rico');
INSERT INTO `pais` VALUES('QAT', 'Qatar');
INSERT INTO `pais` VALUES('GBR', 'Reino Unido');
INSERT INTO `pais` VALUES('CAF', 'República Centroafri');
INSERT INTO `pais` VALUES('CZE', 'República Checa');
INSERT INTO `pais` VALUES('DOM', 'República Dominicana');
INSERT INTO `pais` VALUES('REU', 'Reunión');
INSERT INTO `pais` VALUES('RWA', 'Ruanda');
INSERT INTO `pais` VALUES('ROU', 'Rumanía');
INSERT INTO `pais` VALUES('RUS', 'Rusia');
INSERT INTO `pais` VALUES('ESH', 'Sahara Occidental');
INSERT INTO `pais` VALUES('WSM', 'Samoa');
INSERT INTO `pais` VALUES('ASM', 'Samoa Americana');
INSERT INTO `pais` VALUES('BLM', 'San Bartolomé');
INSERT INTO `pais` VALUES('KNA', 'San Cristóbal y Niev');
INSERT INTO `pais` VALUES('SMR', 'San Marino');
INSERT INTO `pais` VALUES('MAF', 'San Martín (Francia)');
INSERT INTO `pais` VALUES('SPM', 'San Pedro y Miquelón');
INSERT INTO `pais` VALUES('VCT', 'San Vicente y las Gr');
INSERT INTO `pais` VALUES('SHN', 'Santa Elena');
INSERT INTO `pais` VALUES('LCA', 'Santa Lucía');
INSERT INTO `pais` VALUES('STP', 'Santo Tomé y Príncip');
INSERT INTO `pais` VALUES('SEN', 'Senegal');
INSERT INTO `pais` VALUES('SRB', 'Serbia');
INSERT INTO `pais` VALUES('SYC', 'Seychelles');
INSERT INTO `pais` VALUES('SLE', 'Sierra Leona');
INSERT INTO `pais` VALUES('SGP', 'Singapur');
INSERT INTO `pais` VALUES('SYR', 'Siria');
INSERT INTO `pais` VALUES('SOM', 'Somalia');
INSERT INTO `pais` VALUES('LKA', 'Sri lanka');
INSERT INTO `pais` VALUES('ZAF', 'Sudáfrica');
INSERT INTO `pais` VALUES('SDN', 'Sudán');
INSERT INTO `pais` VALUES('SWE', 'Suecia');
INSERT INTO `pais` VALUES('CHE', 'Suiza');
INSERT INTO `pais` VALUES('SUR', 'Surinám');
INSERT INTO `pais` VALUES('SJM', 'Svalbard y Jan Mayen');
INSERT INTO `pais` VALUES('SWZ', 'Swazilandia');
INSERT INTO `pais` VALUES('TJK', 'Tadjikistán');
INSERT INTO `pais` VALUES('THA', 'Tailandia');
INSERT INTO `pais` VALUES('TWN', 'Taiwán');
INSERT INTO `pais` VALUES('TZA', 'Tanzania');
INSERT INTO `pais` VALUES('IOT', 'Territorio Británico');
INSERT INTO `pais` VALUES('ATF', 'Territorios Australe');
INSERT INTO `pais` VALUES('TLS', 'Timor Oriental');
INSERT INTO `pais` VALUES('TGO', 'Togo');
INSERT INTO `pais` VALUES('TKL', 'Tokelau');
INSERT INTO `pais` VALUES('TON', 'Tonga');
INSERT INTO `pais` VALUES('TTO', 'Trinidad y Tobago');
INSERT INTO `pais` VALUES('TUN', 'Tunez');
INSERT INTO `pais` VALUES('TKM', 'Turkmenistán');
INSERT INTO `pais` VALUES('TUR', 'Turquía');
INSERT INTO `pais` VALUES('TUV', 'Tuvalu');
INSERT INTO `pais` VALUES('UKR', 'Ucrania');
INSERT INTO `pais` VALUES('UGA', 'Uganda');
INSERT INTO `pais` VALUES('URY', 'Uruguay');
INSERT INTO `pais` VALUES('UZB', 'Uzbekistán');
INSERT INTO `pais` VALUES('VUT', 'Vanuatu');
INSERT INTO `pais` VALUES('VEN', 'Venezuela');
INSERT INTO `pais` VALUES('VNM', 'Vietnam');
INSERT INTO `pais` VALUES('WLF', 'Wallis y Futuna');
INSERT INTO `pais` VALUES('YEM', 'Yemen');
INSERT INTO `pais` VALUES('DJI', 'Yibuti');
INSERT INTO `pais` VALUES('ZMB', 'Zambia');
INSERT INTO `pais` VALUES('ZWE', 'Zimbabue');

--
-- Volcado de datos para la tabla `prestamo`
--

INSERT INTO `prestamo` VALUES('PU0003EL0002000320180001', '2018-01-04 23:32:15', '2018-01-08 01:32:15', '0.00', 'FO', 'EL00020003', 'U0003');
INSERT INTO `prestamo` VALUES('PU0003EL0008000120180002', '2018-05-04 02:38:48', '2018-05-08 02:38:48', '0.25', 'VO', 'EL00080001', 'U0003');
INSERT INTO `prestamo` VALUES('PU0004EL0005000120180001', '2018-05-06 02:41:04', '2018-05-11 02:41:04', '0.00', 'EP', 'EL00050001', 'U0004');
INSERT INTO `prestamo` VALUES('PU0005EL0002000120180001', '2018-04-05 21:54:35', '2018-04-09 21:54:35', '7.25', 'VO', 'EL00020001', 'U0005');
INSERT INTO `prestamo` VALUES('PU0005EL0011000120180002', '2018-05-05 02:39:47', '2018-05-10 02:39:47', '0.00', 'EP', 'EL00110001', 'U0005');
INSERT INTO `prestamo` VALUES('PU0006EL0003000120180001', '2018-05-02 02:40:08', '2018-05-12 02:40:08', '0.00', 'FO', 'EL00030001', 'U0006');
INSERT INTO `prestamo` VALUES('PU0007EL0009000120180001', '2018-02-15 02:41:22', '2018-02-20 02:41:22', '0.00', 'FO', 'EL00090001', 'U0007');
INSERT INTO `prestamo` VALUES('PU0008EL0003000220180002', '2018-05-05 02:41:40', '2018-05-08 02:41:40', '0.00', 'FO', 'EL00030002', 'U0008');
INSERT INTO `prestamo` VALUES('PU0008EL0004000120180001', '2018-04-26 02:40:29', '2018-05-01 02:40:29', '0.00', 'FO', 'EL00040001', 'U0008');
INSERT INTO `prestamo` VALUES('PU0010EL0011000220180001', '2018-04-11 02:40:41', '2018-04-14 02:40:41', '0.00', 'FO', 'EL00110002', 'U0010');

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` VALUES('RU0003EL0001000120180002', '2018-03-09 03:30:51', '2018-03-11 03:30:51', 'VO', 'EL00010001', 'U0003');
INSERT INTO `reserva` VALUES('RU0003EL0002000220180001', '2018-05-02 21:56:16', '2018-05-02 21:56:16', 'VO', 'EL00020002', 'U0003');
INSERT INTO `reserva` VALUES('RU0006EL0003000320180001', '2018-05-02 02:53:31', '2018-05-05 02:53:31', 'VO', 'EL00030003', 'U0006');
INSERT INTO `reserva` VALUES('RU0006EL0005000220180002', '2018-05-09 02:53:52', '2018-05-11 02:53:52', 'VE', 'EL00050002', 'U0006');
INSERT INTO `reserva` VALUES('RU0007EL0006000120180001', '2018-05-05 02:56:20', '2018-05-10 02:56:20', 'EO', 'EL00060001', 'U0007');
INSERT INTO `reserva` VALUES('RU0007EL0007000120180002', '2018-01-04 02:56:25', '2018-01-06 02:56:25', 'VO', 'EL00070001', 'U0007');
INSERT INTO `reserva` VALUES('RU0007EL0010000120180003', '2018-02-03 02:56:37', '2018-02-06 02:56:37', 'VO', 'EL00100001', 'U0007');
INSERT INTO `reserva` VALUES('RU0008EL0009000220180001', '2018-05-09 02:52:50', '2018-05-10 02:52:50', 'VE', 'EL00090002', 'U0008');
INSERT INTO `reserva` VALUES('RU0008EL0014000120180002', '2018-04-04 02:52:58', '2018-04-07 02:52:58', 'VO', 'EL00140001', 'U0008');
INSERT INTO `reserva` VALUES('RU0009EL0011000320180002', '2018-05-06 02:58:27', '2018-05-11 02:58:27', 'VE', 'EL00110003', 'U0009');
INSERT INTO `reserva` VALUES('RU0009EL0015000120180001', '2017-12-08 02:58:07', '2017-12-12 02:58:07', 'VO', 'EL00150001', 'U0009');

--
-- Volcado de datos para la tabla `tema`
--

INSERT INTO `tema` VALUES(1, 'Realidad social');
INSERT INTO `tema` VALUES(2, 'Creencias religiosas');
INSERT INTO `tema` VALUES(3, 'Realismo magico');
INSERT INTO `tema` VALUES(4, 'Formas de vida');
INSERT INTO `tema` VALUES(5, 'Clasicos latinoamerica');

--
-- Volcado de datos para la tabla `tipos_usuario`
--

INSERT INTO `tipos_usuario` VALUES('B', 'Bibliotecario', 'Posee acceso total a la plataforma');
INSERT INTO `tipos_usuario` VALUES('U', 'Usuario', 'Puede registrar y visaulizar libros');

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` VALUES('B0001', 'Jasson Alexander', 'Lopez Soriano', 'jasson_alex99@hotmail.com', '1999-07-10', 'B0001', '84472/74362/10143*109123-112163-66282!80432/72342*', 1, 'B');
INSERT INTO `usuario` VALUES('B0002', 'Franklin Armando', 'Esquivel Guevara', 'franklin.esquivel@outlook.com', '1998-10-09', 'B0002', '112163-113173/52A2/107103!116203*107103/88512/120243-', 1, 'B');
INSERT INTO `usuario` VALUES('U0003', 'Diego Alberto', 'Lemus Torres', 'ciegolem7@gmail.es', '1998-09-18', 'U0003', '57p2!121253-10473/66282/76382*51y2*88512-9702/', 1, 'U');
INSERT INTO `usuario` VALUES('U0004', 'Marta Carolina', 'Rosado Pérez', 'marta.rope@gmail.com', '1998-08-12', 'U0004', '10693-51y2*56o2/65272-68302-115193!111153!10363!', 1, 'U');
INSERT INTO `usuario` VALUES('U0005', 'Leonarlo Elenilson', 'Lopez Cañas', 'lopezleonardo282@gmail.com', '1999-04-09', 'U0005', '75372-10693-66282/116203-84472!49l2/70322-56o2!', 1, 'U');
INSERT INTO `usuario` VALUES('U0006', 'Jeferson Alberto', 'Gutierrez Hernandez', 'gutierrezjefferson@gmail.com', '1992-04-16', 'U0006', '66282/10253!68302*120243!71332-74362!65272!51y2/', 1, 'U');
INSERT INTO `usuario` VALUES('U0007', 'Oscar Armando', 'Santin Caceres', 'santin.armando@gmail.com', '1988-03-09', 'U0007', '111153!10583/88512-69312*78402!115193/72342!10693-', 1, 'U');
INSERT INTO `usuario` VALUES('U0008', 'Francisco Ernesto', 'Campos Mejia', 'francisco.campos@gmail.com', '1997-07-10', 'U0008', '67292!10693/117213-49l2!57p2/78402-120243*107103/', 1, 'U');
INSERT INTO `usuario` VALUES('U0009', 'Ursula Alejandra', 'Rodriguez Mejia', 'ursula.mejia@gmail.com', '1991-11-02', 'U0009', '78402*120243*9812-82452*10143/66282!74362*49l2-', 1, 'U');
INSERT INTO `usuario` VALUES('U0010', 'Francisca Javiera', 'Hernandez Mejia', 'francisca.hndez@gmail.com', '1994-06-18', 'U0010', '107103-9812/73352!57p2*67292-118223!88512-90532!', 1, 'U');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
