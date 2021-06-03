-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2021 a las 05:41:50
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectofinal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datoscliente`
--

CREATE TABLE `datoscliente` (
  `idcliente` int(10) NOT NULL,
  `nombres_cliente` varchar(50) NOT NULL,
  `apellidos_cliente` varchar(50) NOT NULL,
  `dpi_cliente` int(18) NOT NULL,
  `telefono_cliente` int(11) NOT NULL,
  `direccion_cliente` varchar(50) NOT NULL,
  `nit_cliente` int(11) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `datoscliente`
--

INSERT INTO `datoscliente` (`idcliente`, `nombres_cliente`, `apellidos_cliente`, `dpi_cliente`, `telefono_cliente`, `direccion_cliente`, `nit_cliente`, `estado`) VALUES
(4, 'Alexandro', 'Rodas', 77565656, 123456555, 'Zona 10', 195545415, 1),
(6, 'Erix', 'Solares', 152645646, 123154546, 'Zona 21', 156145646, 1),
(10, 'Christian', 'Gomez', 444513352, 5216521, 'Zona 7', 66624154, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datosempresa`
--

CREATE TABLE `datosempresa` (
  `idempresa` int(10) NOT NULL,
  `Nombre_Empresa` varchar(50) NOT NULL,
  `NIT_Empresa` int(15) NOT NULL,
  `Direccion_Empresa` varchar(50) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `datosempresa`
--

INSERT INTO `datosempresa` (`idempresa`, `Nombre_Empresa`, `NIT_Empresa`, `Direccion_Empresa`, `estado`) VALUES
(10, 'Avantia', 123123, 'Zona 7', 1),
(16, 'Ricoh', 1521654, 'Zona 1', 1),
(25, 'Prueba', 66614451, 'Zona 10', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datosfactura`
--

CREATE TABLE `datosfactura` (
  `idfactura` int(11) NOT NULL,
  `correlativo` int(11) NOT NULL,
  `articulo` varchar(50) NOT NULL,
  `preciounitario` int(11) NOT NULL,
  `preciototal` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `iva` int(11) NOT NULL,
  `descuento` int(11) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `datosfactura`
--

INSERT INTO `datosfactura` (`idfactura`, `correlativo`, `articulo`, `preciounitario`, `preciototal`, `fecha`, `iva`, `descuento`, `estado`) VALUES
(1, 1234, 'Cosmeticos', 400, 11000, '2020-06-09', 1100, 2750, 1),
(2, 456, 'Comida', 600, 27000, '2020-06-09', 2700, 6750, 1),
(7, 111234, 'Telefonos', 4000, 16000, '2020-06-17', 1600, 4000, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `datoscliente`
--
ALTER TABLE `datoscliente`
  ADD PRIMARY KEY (`idcliente`);

--
-- Indices de la tabla `datosempresa`
--
ALTER TABLE `datosempresa`
  ADD PRIMARY KEY (`idempresa`);

--
-- Indices de la tabla `datosfactura`
--
ALTER TABLE `datosfactura`
  ADD PRIMARY KEY (`idfactura`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `datoscliente`
--
ALTER TABLE `datoscliente`
  MODIFY `idcliente` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `datosempresa`
--
ALTER TABLE `datosempresa`
  MODIFY `idempresa` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `datosfactura`
--
ALTER TABLE `datosfactura`
  MODIFY `idfactura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
