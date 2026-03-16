-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-03-2026 a las 04:17:39
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
-- Base de datos: `mydb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `idCita` int(11) NOT NULL,
  `idPaciente` int(11) NOT NULL,
  `id_Horario` int(11) NOT NULL,
  `id_Tipo_Cita` int(11) NOT NULL,
  `id_Estado_Cita` int(11) NOT NULL,
  `id_Fecha_Creacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_cita`
--

CREATE TABLE `estado_cita` (
  `idESTADO_CITA` int(11) NOT NULL,
  `nombre_estado_cita` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `estado_cita`
--

INSERT INTO `estado_cita` (`idESTADO_CITA`, `nombre_estado_cita`) VALUES
(2, 'Cancelada'),
(1, 'Programada'),
(3, 'Reagendada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_citas`
--

CREATE TABLE `historial_citas` (
  `idHISTORIAL_CITAS` int(11) NOT NULL,
  `idCita` int(11) NOT NULL,
  `idEstado` int(11) NOT NULL,
  `Fecha_Cita_Pasada` date NOT NULL,
  `Observaciones` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `idHORARIO` int(11) NOT NULL,
  `Fecha_Cita` date NOT NULL,
  `Hora_Inicio_Cita` time NOT NULL,
  `Hora_Fin_Cita` time NOT NULL,
  `Horario_Disponible` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`idHORARIO`, `Fecha_Cita`, `Hora_Inicio_Cita`, `Hora_Fin_Cita`, `Horario_Disponible`) VALUES
(1, '2026-03-14', '08:00:00', '09:00:00', 1),
(2, '2026-03-15', '09:00:00', '09:30:00', 1),
(3, '2026-03-16', '10:00:00', '10:30:00', 1),
(4, '2026-03-19', '07:00:00', '07:30:00', 1),
(5, '2026-03-20', '09:30:00', '09:50:00', 1),
(6, '2026-03-20', '09:55:00', '10:15:00', 1),
(7, '2026-03-20', '10:30:00', '11:00:00', 1),
(8, '2026-03-20', '11:05:00', '11:35:00', 1),
(9, '2026-03-21', '08:00:00', '08:30:00', 1),
(10, '2026-03-21', '08:35:00', '09:05:00', 1),
(11, '2026-03-21', '09:10:00', '09:40:00', 1),
(12, '2026-03-22', '07:30:00', '08:00:00', 1),
(13, '2026-03-22', '08:05:00', '08:35:00', 1),
(14, '2026-03-22', '08:40:00', '09:10:00', 1),
(15, '2026-03-23', '09:00:00', '09:30:00', 1),
(16, '2026-03-23', '09:35:00', '10:05:00', 1),
(17, '2026-03-24', '10:00:00', '10:30:00', 1),
(18, '2026-03-24', '10:35:00', '11:05:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `idPACIENTE` int(11) NOT NULL,
  `Nombre_paciente` varchar(45) NOT NULL,
  `Apellido_paciente` varchar(45) NOT NULL,
  `Documento` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`idPACIENTE`, `Nombre_paciente`, `Apellido_paciente`, `Documento`, `correo`, `telefono`) VALUES
(1, 'Jose ', 'Lopez', '1234567', 'test@test.com', '25574488'),
(2, 'Laura', 'Martinez', '12345678', 'laura@gmail.com', '3009876543'),
(3, 'Julian', 'Lopez', '123456789', 'julian@email.com', '3001234567'),
(9, 'Alejandra ', 'Montoya', '1234567890', 'test@testaleja.com', '3225558877'),
(10, 'Carlos', 'Moya', '987654321', 'moya@moya.com', '11447740');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`idCita`),
  ADD KEY `fk_CITA_PACIENTE_idx` (`idPaciente`),
  ADD KEY `fk_ESTADO_CITA_idx` (`id_Estado_Cita`),
  ADD KEY `fk_CITA_HORARIO1` (`id_Horario`);

--
-- Indices de la tabla `estado_cita`
--
ALTER TABLE `estado_cita`
  ADD PRIMARY KEY (`idESTADO_CITA`),
  ADD UNIQUE KEY `nombre_estado_cita_UNIQUE` (`nombre_estado_cita`);

--
-- Indices de la tabla `historial_citas`
--
ALTER TABLE `historial_citas`
  ADD PRIMARY KEY (`idHISTORIAL_CITAS`),
  ADD KEY `fk_HISTORIAL_ESTADO_idx` (`idEstado`),
  ADD KEY `fk_HISTORIAL_CITA_idx` (`idCita`);

--
-- Indices de la tabla `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`idHORARIO`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`idPACIENTE`),
  ADD UNIQUE KEY `Documento` (`Documento`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `idCita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `estado_cita`
--
ALTER TABLE `estado_cita`
  MODIFY `idESTADO_CITA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `historial_citas`
--
ALTER TABLE `historial_citas`
  MODIFY `idHISTORIAL_CITAS` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `idHORARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `idPACIENTE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `fk_CITA_HORARIO1` FOREIGN KEY (`id_Horario`) REFERENCES `horario` (`idHORARIO`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_CITA_PACIENTE` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPACIENTE`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ESTADO_CITA` FOREIGN KEY (`id_Estado_Cita`) REFERENCES `estado_cita` (`idESTADO_CITA`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `historial_citas`
--
ALTER TABLE `historial_citas`
  ADD CONSTRAINT `fk_HISTORIAL_CITA` FOREIGN KEY (`idCita`) REFERENCES `cita` (`idCita`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_HISTORIAL_ESTADO` FOREIGN KEY (`idEstado`) REFERENCES `estado_cita` (`idESTADO_CITA`) ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
