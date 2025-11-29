-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-11-2025 a las 02:52:09
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `Id_libro` int(11) NOT NULL,
  `Titulo` varchar(150) NOT NULL,
  `Id_categoria` int(11) DEFAULT NULL,
  `Id_editorial` int(11) DEFAULT NULL,
  `Id_autor` int(11) DEFAULT NULL,
  `Id_materia` int(11) DEFAULT NULL,
  `Edicion` varchar(30) NOT NULL,
  `Estado` varchar(80) NOT NULL,
  `codigo` varchar(20) NOT NULL,
  `fechaRegistro` date NOT NULL DEFAULT current_timestamp(),
  `stock` int(11) NOT NULL,
  `anio` int(11) NOT NULL,
  `Descripcion` varchar(200) NOT NULL,
  `id_estado` int(50) NOT NULL,
  `tipo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`Id_libro`, `Titulo`, `Id_categoria`, `Id_editorial`, `Id_autor`, `Id_materia`, `Edicion`, `Estado`, `codigo`, `fechaRegistro`, `stock`, `anio`, `Descripcion`, `id_estado`, `tipo`) VALUES
(4, 'Fundamentos de C++', 1, 25, 1, 8, '9na.', '1', 'P-1101', '2025-10-12', 1, 2000, 'KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK', 5, 'Copia'),
(6, 'El mundo de los Objetos', 1, 12, 3, 6, '10ma.', '1', 'P-1102', '2025-10-12', 4, 2003, '', 5, 'Original'),
(11, 'Java', 5, NULL, NULL, NULL, '', '1', 'P-1010', '2025-10-12', 1, 2020, '', 5, 'Copia'),
(12, 'Programacion en C', 2, 10, 1, 6, '10m', '1', 'P-1020', '2025-10-26', 1, 2020, 'Programacion', 5, 'Copia'),
(13, 'Python', NULL, 10, 1, NULL, '1ra.', '1', 'P-1022', '2025-10-26', 2, 1999, 'PYTHON', 5, 'Original'),
(14, 'Chanchito Feliz', 5, NULL, NULL, NULL, '', '1', 'CH-1012', '2025-11-01', 4, 1998, '', 5, 'Copia'),
(15, 'Chanchito Feliciano', NULL, NULL, NULL, 6, '9na', '1', 'P-10103', '2025-11-01', 1, 2000, 'aas@asf~4', 1, 'Copia'),
(16, 'Puerquito', NULL, NULL, NULL, NULL, '4ta.', '1', 'P-202', '2025-11-01', 1, 2005, '', 5, 'Copia'),
(17, 'Pruebas de Escritorio', 11, 23, 2, 6, '2da.', '1', 'P-1666', '2025-11-06', 0, 2005, 'Para probar la base datos :v', 2, 'Copia'),
(18, 'Prueba', NULL, NULL, NULL, NULL, '9na.', '1', 'P-1023', '2025-11-28', 1, 2002, '', 5, 'Copia');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`Id_libro`),
  ADD KEY `Fkcategoria` (`Id_categoria`),
  ADD KEY `Fkeditorial` (`Id_editorial`),
  ADD KEY `Fkautor` (`Id_autor`),
  ADD KEY `Fkmateria` (`Id_materia`),
  ADD KEY `libro_ibfk_5` (`id_estado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `Id_libro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`Id_materia`) REFERENCES `materia` (`Id_materia`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `libro_ibfk_2` FOREIGN KEY (`Id_editorial`) REFERENCES `editoriales` (`Id_editorial`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `libro_ibfk_3` FOREIGN KEY (`Id_categoria`) REFERENCES `categoria` (`Id_categoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `libro_ibfk_4` FOREIGN KEY (`Id_autor`) REFERENCES `autores` (`Id_autor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `libro_ibfk_5` FOREIGN KEY (`id_estado`) REFERENCES `estadolibro` (`id_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
