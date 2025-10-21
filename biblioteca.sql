-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-10-2025 a las 06:56:01
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
-- Estructura de tabla para la tabla `autores`
--

CREATE TABLE `autores` (
  `Id_autor` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `id_pais` int(50) NOT NULL,
  `estado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `autores`
--

INSERT INTO `autores` (`Id_autor`, `Nombre`, `Apellido`, `id_pais`, `estado`) VALUES
(1, 'Luis', 'Joyanes Aguilar', 13, 1),
(2, 'Alberto', 'Rodriguez ', 1, 1),
(3, 'Raúl', 'Medina Rodriguez', 15, 1),
(5, 'sffsfs', 'dsdfsdf', 6, 0),
(6, 'Luis', 'Joyanes Aguilar', 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargo`
--

CREATE TABLE `cargo` (
  `id_cargo` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cargo`
--

INSERT INTO `cargo` (`id_cargo`, `nombre`) VALUES
(1, 'Administrativo'),
(2, 'Docente'),
(3, 'Estudiante');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

CREATE TABLE `carrera` (
  `id_carrera` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carrera`
--

INSERT INTO `carrera` (`id_carrera`, `nombre`) VALUES
(1, 'Ingeniería Informática'),
(2, 'Ingeniería de Sistemas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `Id_categoria` int(11) NOT NULL,
  `Categoria` varchar(50) NOT NULL,
  `estado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`Id_categoria`, `Categoria`, `estado`) VALUES
(1, 'Tesis', 1),
(2, 'Proyecto de Grado', 1),
(3, 'Trabajo de Investigacion', 1),
(4, 'Articulo Cientifico', 1),
(5, 'Informe laboratorio', 1),
(6, 'Trabajo de Curso', 1),
(7, 'Proyecto de Curso', 1),
(8, 'Revista Cientifica', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `editoriales`
--

CREATE TABLE `editoriales` (
  `Id_editorial` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Direccion` varchar(150) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `id_pais` int(11) NOT NULL,
  `estado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `editoriales`
--

INSERT INTO `editoriales` (`Id_editorial`, `Nombre`, `Direccion`, `Telefono`, `id_pais`, `estado`) VALUES
(3, 'Latinas', 'Potosi y Caro', '52-49652', 1, 1),
(7, 'OddKings', 'Boulevard Jonks', '351-45150', 4, 1),
(10, 'Kodlif', 'St. Jhonson', '659-94652', 4, 1),
(11, 'Potosina Editoriales', '6 de agosto y Tarija', '', 1, 1),
(12, 'Jabushins', '', '', 9, 1),
(21, 'Jhonimsl', '', '', 10, 1),
(22, 'Oxford edits', 'Oxford', '', 2, 1),
(23, 'Maduro Editores', '', '', 7, 1),
(24, 'Nuevas Generaciones Editoriales', '', '', 6, 1),
(25, 'Los andes ', 'La paz y Bolivia', '52-49353', 1, 1),
(26, 'Potosina Editorial', '6 de agosto y Tarija', '', 5, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadolibro`
--

CREATE TABLE `estadolibro` (
  `id_estado` int(11) NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estadolibro`
--

INSERT INTO `estadolibro` (`id_estado`, `estado`) VALUES
(1, 'Castigado'),
(2, 'En prestamo'),
(5, 'Disponible');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_devolucion`
--

CREATE TABLE `estado_devolucion` (
  `id_estado_devolucion` int(11) NOT NULL,
  `estado_dev` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_devolucion`
--

INSERT INTO `estado_devolucion` (`id_estado_devolucion`, `estado_dev`) VALUES
(1, 'Vigente'),
(2, 'Devuelto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_usuario`
--

CREATE TABLE `estado_usuario` (
  `id_estado_usuario` int(11) NOT NULL,
  `estado_usuario` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_usuario`
--

INSERT INTO `estado_usuario` (`id_estado_usuario`, `estado_usuario`) VALUES
(1, 'Activo'),
(2, 'Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_libros`
--

CREATE TABLE `historial_libros` (
  `Id_historial` int(11) NOT NULL,
  `Id_libro` int(11) NOT NULL,
  `Accion` varchar(100) NOT NULL,
  `Fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `id_estado` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`Id_libro`, `Titulo`, `Id_categoria`, `Id_editorial`, `Id_autor`, `Id_materia`, `Edicion`, `Estado`, `codigo`, `fechaRegistro`, `stock`, `anio`, `Descripcion`, `id_estado`) VALUES
(4, 'Fundamentos de C++', 1, 25, 1, 8, '9na.', '1', 'P-1101', '2025-10-12', 1, 2000, 'KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK', 5),
(6, 'El mundo de los Objetos', 1, 12, 3, 6, '10ma.', '1', 'P-1102', '2025-10-12', 4, 2003, '', 5),
(11, 'Java', NULL, NULL, NULL, NULL, '', '1', 'P-1010', '2025-10-12', 2, 0, '', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `login`
--

INSERT INTO `login` (`id`, `nombre`, `correo`, `password`) VALUES
(1, 'fernando', 'fernando@gmail.com', '12345');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

CREATE TABLE `materia` (
  `Id_materia` int(11) NOT NULL,
  `sigla` varchar(15) NOT NULL,
  `Nombre` varchar(80) NOT NULL,
  `estado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `materia`
--

INSERT INTO `materia` (`Id_materia`, `sigla`, `Nombre`, `estado`) VALUES
(1, 'INF-3510', 'Redes Informáticas I', 1),
(3, 'SIS-2520', 'Simulacion de Sistemas', 1),
(4, 'INF-1210', 'Análisis Discreto', 1),
(6, 'SIS-2210', 'Metodología de la Programación II', 1),
(7, 'SIS-2430', 'Programación Gráfica', 1),
(8, 'SIS-1110', 'Metodología de la Programación I', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multa`
--

CREATE TABLE `multa` (
  `Id_multa` int(11) NOT NULL,
  `Id_prestamo` int(11) NOT NULL,
  `Id_usuario` int(11) NOT NULL,
  `Dias_retraso` int(11) NOT NULL,
  `Monto` decimal(8,2) NOT NULL,
  `Estado` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paises`
--

CREATE TABLE `paises` (
  `id_pais` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `estado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paises`
--

INSERT INTO `paises` (`id_pais`, `nombre`, `estado`) VALUES
(1, 'Bolivia', 1),
(2, 'Estados Unidos', 1),
(4, 'Nueva Zelanda', 1),
(5, 'Rusia', 1),
(6, 'Chile', 1),
(7, 'Venezuela', 1),
(8, 'Ecuador', 1),
(9, 'Reino Unido', 1),
(10, 'México', 1),
(11, 'Cuba', 0),
(12, 'China', 1),
(13, 'España', 1),
(14, 'Colombia', 1),
(15, 'Puerto Rico', 1),
(16, 'Noruega', 1),
(17, 'Venezuela', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `Id_prestamo` int(11) NOT NULL,
  `Id_usuario` int(11) NOT NULL,
  `Id_libro` int(11) NOT NULL,
  `Fecha_prestamo` datetime NOT NULL DEFAULT current_timestamp(),
  `Fecha_devolucion` datetime NOT NULL,
  `Estado` varchar(80) NOT NULL DEFAULT '1',
  `Id_estado_devolucion` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`Id_prestamo`, `Id_usuario`, `Id_libro`, `Fecha_prestamo`, `Fecha_devolucion`, `Estado`, `Id_estado_devolucion`) VALUES
(7, 7, 6, '2025-10-19 14:00:28', '2025-10-19 14:30:28', '1', 2),
(8, 5, 4, '2025-10-19 16:28:46', '2025-10-21 16:28:45', '1', 1),
(9, 6, 11, '2025-10-19 16:33:46', '2025-10-24 16:33:45', '1', 1),
(10, 7, 6, '2025-10-19 16:36:05', '2025-10-25 16:36:04', '0', 1),
(11, 7, 6, '2025-10-19 16:36:11', '2025-10-25 16:36:10', '1', 1),
(12, 7, 6, '2025-10-19 16:36:35', '2025-10-25 16:36:34', '0', 1),
(13, 6, 6, '2025-10-19 17:16:16', '2025-10-24 17:16:14', '1', 2),
(14, 7, 4, '2025-10-19 17:17:08', '2025-10-31 17:17:07', '1', 1),
(15, 7, 6, '2025-10-20 19:16:25', '2025-10-30 19:16:24', '0', 1),
(16, 7, 6, '2025-10-20 19:18:40', '2025-10-29 19:18:39', '0', 1),
(17, 7, 6, '2025-10-20 19:23:22', '2025-10-29 19:23:21', '1', 1),
(18, 7, 6, '2025-10-20 19:25:25', '2025-10-28 19:25:24', '1', 1),
(19, 7, 6, '2025-10-20 19:30:13', '2025-10-27 19:30:12', '1', 1),
(20, 7, 6, '2025-10-20 19:33:24', '2025-10-26 19:33:23', '1', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sancion`
--

CREATE TABLE `sancion` (
  `Id_sancion` int(11) NOT NULL,
  `Id_usuario` int(11) NOT NULL,
  `Motivo` text NOT NULL,
  `Fecha_unicio` date NOT NULL,
  `Fecha_fin` date NOT NULL,
  `Estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `Id_tipo_usuario` int(11) NOT NULL,
  `Tipo_usuario` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`Id_tipo_usuario`, `Tipo_usuario`) VALUES
(1, 'administrador'),
(2, 'normal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `Id_usuario` int(11) NOT NULL,
  `Carnet` varchar(20) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `Domicilo` varchar(100) NOT NULL,
  `Id_tipo_usuario` int(11) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `id_cargo` int(11) NOT NULL,
  `id_carrera` int(11) NOT NULL,
  `Estado` int(11) NOT NULL,
  `id_estado_usuario` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Id_usuario`, `Carnet`, `Nombre`, `Apellido`, `Domicilo`, `Id_tipo_usuario`, `Telefono`, `id_cargo`, `id_carrera`, `Estado`, `id_estado_usuario`) VALUES
(5, '7278371', 'Juan Marco', 'Mercedes Canaviri', 'Av. al valle entre junin y ayacucho Nro578', 2, '54821147', 3, 2, 1, 2),
(6, '54986321', 'Maria', 'Camopero Rodriguez', 'La plata entre villaroel y 6 de agosto Nro 67', 2, '54871125', 3, 1, 1, 1),
(7, '48658454', 'Carla', 'Rocha Quispe', 'Pagado entre soria galvarro y ayacucho Nro 40', 2, '58421154', 3, 1, 0, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autores`
--
ALTER TABLE `autores`
  ADD PRIMARY KEY (`Id_autor`),
  ADD KEY `autores_ibfk_1` (`id_pais`);

--
-- Indices de la tabla `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`id_cargo`);

--
-- Indices de la tabla `carrera`
--
ALTER TABLE `carrera`
  ADD PRIMARY KEY (`id_carrera`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`Id_categoria`);

--
-- Indices de la tabla `editoriales`
--
ALTER TABLE `editoriales`
  ADD PRIMARY KEY (`Id_editorial`),
  ADD KEY `editoriales_ibfk_1` (`id_pais`);

--
-- Indices de la tabla `estadolibro`
--
ALTER TABLE `estadolibro`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `estado_devolucion`
--
ALTER TABLE `estado_devolucion`
  ADD PRIMARY KEY (`id_estado_devolucion`);

--
-- Indices de la tabla `estado_usuario`
--
ALTER TABLE `estado_usuario`
  ADD PRIMARY KEY (`id_estado_usuario`);

--
-- Indices de la tabla `historial_libros`
--
ALTER TABLE `historial_libros`
  ADD PRIMARY KEY (`Id_historial`),
  ADD KEY `FkLibro` (`Id_libro`);

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
-- Indices de la tabla `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `materia`
--
ALTER TABLE `materia`
  ADD PRIMARY KEY (`Id_materia`);

--
-- Indices de la tabla `multa`
--
ALTER TABLE `multa`
  ADD PRIMARY KEY (`Id_multa`),
  ADD KEY `Fkprestamo` (`Id_prestamo`),
  ADD KEY `Id_usuario` (`Id_usuario`);

--
-- Indices de la tabla `paises`
--
ALTER TABLE `paises`
  ADD PRIMARY KEY (`id_pais`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`Id_prestamo`),
  ADD KEY `FKusuario` (`Id_usuario`),
  ADD KEY `Fklibro` (`Id_libro`),
  ADD KEY `FKdevolucion` (`Id_estado_devolucion`);

--
-- Indices de la tabla `sancion`
--
ALTER TABLE `sancion`
  ADD PRIMARY KEY (`Id_sancion`),
  ADD KEY `FK` (`Id_usuario`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`Id_tipo_usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`Id_usuario`),
  ADD KEY `Tipo_usuario` (`Id_tipo_usuario`),
  ADD KEY `id_cargo` (`id_cargo`),
  ADD KEY `id_carrera` (`id_carrera`),
  ADD KEY `id_estado_usuario` (`id_estado_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `autores`
--
ALTER TABLE `autores`
  MODIFY `Id_autor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `cargo`
--
ALTER TABLE `cargo`
  MODIFY `id_cargo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `carrera`
--
ALTER TABLE `carrera`
  MODIFY `id_carrera` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `Id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `editoriales`
--
ALTER TABLE `editoriales`
  MODIFY `Id_editorial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `estadolibro`
--
ALTER TABLE `estadolibro`
  MODIFY `id_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `estado_devolucion`
--
ALTER TABLE `estado_devolucion`
  MODIFY `id_estado_devolucion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `estado_usuario`
--
ALTER TABLE `estado_usuario`
  MODIFY `id_estado_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `historial_libros`
--
ALTER TABLE `historial_libros`
  MODIFY `Id_historial` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `Id_libro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `materia`
--
ALTER TABLE `materia`
  MODIFY `Id_materia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `multa`
--
ALTER TABLE `multa`
  MODIFY `Id_multa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `paises`
--
ALTER TABLE `paises`
  MODIFY `id_pais` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `Id_prestamo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `sancion`
--
ALTER TABLE `sancion`
  MODIFY `Id_sancion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `Id_tipo_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `Id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `autores`
--
ALTER TABLE `autores`
  ADD CONSTRAINT `autores_ibfk_1` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id_pais`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `editoriales`
--
ALTER TABLE `editoriales`
  ADD CONSTRAINT `editoriales_ibfk_1` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id_pais`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `historial_libros`
--
ALTER TABLE `historial_libros`
  ADD CONSTRAINT `historial_libros_ibfk_1` FOREIGN KEY (`Id_libro`) REFERENCES `libro` (`Id_libro`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`Id_materia`) REFERENCES `materia` (`Id_materia`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `libro_ibfk_2` FOREIGN KEY (`Id_editorial`) REFERENCES `editoriales` (`Id_editorial`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `libro_ibfk_3` FOREIGN KEY (`Id_categoria`) REFERENCES `categoria` (`Id_categoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `libro_ibfk_4` FOREIGN KEY (`Id_autor`) REFERENCES `autores` (`Id_autor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `libro_ibfk_5` FOREIGN KEY (`id_estado`) REFERENCES `estadolibro` (`id_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `multa`
--
ALTER TABLE `multa`
  ADD CONSTRAINT `multa_ibfk_1` FOREIGN KEY (`Id_prestamo`) REFERENCES `prestamos` (`Id_prestamo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `multa_ibfk_2` FOREIGN KEY (`Id_usuario`) REFERENCES `usuario` (`Id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`Id_usuario`) REFERENCES `usuario` (`Id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`Id_libro`) REFERENCES `libro` (`Id_libro`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prestamos_ibfk_3` FOREIGN KEY (`Id_estado_devolucion`) REFERENCES `estado_devolucion` (`id_estado_devolucion`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `sancion`
--
ALTER TABLE `sancion`
  ADD CONSTRAINT `sancion_ibfk_1` FOREIGN KEY (`Id_usuario`) REFERENCES `usuario` (`Id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`Id_tipo_usuario`) REFERENCES `tipo_usuario` (`Id_tipo_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`id_cargo`) REFERENCES `cargo` (`id_cargo`),
  ADD CONSTRAINT `usuario_ibfk_3` FOREIGN KEY (`id_carrera`) REFERENCES `carrera` (`id_carrera`),
  ADD CONSTRAINT `usuario_ibfk_4` FOREIGN KEY (`id_estado_usuario`) REFERENCES `estado_usuario` (`id_estado_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
