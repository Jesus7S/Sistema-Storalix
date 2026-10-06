-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-10-2026 a las 19:34:29
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
-- Base de datos: `sistemaleans`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_Empleado` int(11) NOT NULL,
  `Nombre` varchar(150) NOT NULL,
  `Cargo` varchar(150) NOT NULL,
  `Departamento` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_Empleado`, `Nombre`, `Cargo`, `Departamento`) VALUES
(12, 'Jesus david sanchez ', 'IT SUPPORT', 'SUPPOR IT'),
(13, 'Laura Coviz', 'OPERATIONS MANAGER', 'Operations'),
(14, 'Erica', 'OPERATIONS MANAGER', 'Operations'),
(15, 'Sofia Ancestral', 'OPERATIONS MANAGER', 'Operations'),
(16, 'German Hernadez Martines', 'OPERATIONS MANAGER', 'Operations'),
(17, 'Camila Jose ', 'OPERATIONS MANAGER', 'OPERATIONS MANAGER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `empresa_id` varchar(50) NOT NULL,
  `empresa_nombre` varchar(30) NOT NULL,
  `empresa_telefono` varchar(20) NOT NULL,
  `empresa_direccion` varchar(255) NOT NULL,
  `empresa_email` varchar(50) NOT NULL,
  `empresa_ciudad` varchar(20) NOT NULL,
  `empresa_dpto` varchar(20) NOT NULL,
  `empresa_mensaje` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`empresa_id`, `empresa_nombre`, `empresa_telefono`, `empresa_direccion`, `empresa_email`, `empresa_ciudad`, `empresa_dpto`, `empresa_mensaje`) VALUES
('Leanso123', 'Leansolution', '32345321211', 'CCIAL UNICO cra 17 # 24-55 Plaza Mayor', 'joljeansunico.med001@gmail.com', 'Cartagena', 'Antioquia', '¡Bienvenido a nuestra empresa! Esperamos que disfrute su estadía con nosotros.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `listaarticulo`
--

CREATE TABLE `listaarticulo` (
  `id_Articulo` int(4) NOT NULL,
  `Marca_Pantalla` varchar(50) NOT NULL,
  `Tec_Pantalla` varchar(50) NOT NULL,
  `Tec_Pantalla2` varchar(50) NOT NULL,
  `Tec_Pantalla3` varchar(50) NOT NULL,
  `Tec_Torre` varchar(50) NOT NULL,
  `Marca_Torre` varchar(50) NOT NULL,
  `Marca_Raton` varchar(50) NOT NULL,
  `Marca_Teclado` varchar(50) NOT NULL,
  `Marca_Diademas` varchar(50) NOT NULL,
  `Marca_Camara` varchar(50) NOT NULL,
  `Adaptador` varchar(50) NOT NULL,
  `Fecha` date NOT NULL,
  `Acta` varchar(20) NOT NULL,
  `empresa_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `listaarticulo`
--

INSERT INTO `listaarticulo` (`id_Articulo`, `Marca_Pantalla`, `Tec_Pantalla`, `Tec_Pantalla2`, `Tec_Pantalla3`, `Tec_Torre`, `Marca_Torre`, `Marca_Raton`, `Marca_Teclado`, `Marca_Diademas`, `Marca_Camara`, `Adaptador`, `Fecha`, `Acta`, `empresa_id`) VALUES
(1, 'Hp ', '225687', '65589', '', '223548', 'Lenovo', 'HP', 'Lenovo', 'Casco', 'Digital', '', '2026-10-04', '12368', 'Leanso123'),
(2, 'Lenovo', '3124547', '', '', '3245715', 'Lenovo', 'HP', 'Lenovo', '', 'Digital', '', '2026-10-06', '321457', 'Leanso123'),
(3, 'Lenovo', '1234567', '', '', '124521', 'Lenovo', 'LENOVO', 'Lenovo', '', 'Digital', '', '2026-10-06', '1241122', 'Leanso123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(4) NOT NULL,
  `cli_tipo_id` varchar(20) NOT NULL,
  `cli_nombre` varchar(50) NOT NULL,
  `cli_apellido` varchar(20) NOT NULL,
  `cli_piso` varchar(20) NOT NULL,
  `cli_cuenta` varchar(20) NOT NULL,
  `cli_celular` varchar(20) NOT NULL,
  `cli_correo` varchar(50) NOT NULL,
  `cli_direccion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `cli_tipo_id`, `cli_nombre`, `cli_apellido`, `cli_piso`, `cli_cuenta`, `cli_celular`, `cli_correo`, `cli_direccion`) VALUES
(1, '1007256581', 'Jesus', 'Sanchez Gomez', '16', 'Stord', '313308546752', 'J7@gmail.com', 'chile'),
(2, '3138565841', 'Erica', 'Gomez', '14', 'Netuno', '3137568140', 'erica7@gmail.com', 'barrio chile'),
(4, '1007256581', 'Sofia', 'Ancestral', '11', 'Eshipping', '3135864718', 'sofi7@gmail.com', 'chile'),
(5, '1007258894', 'Juliana', 'Bargas ', '14', 'FreesPack', '313568974', 'Juli7@gmail.com', 'Nuevo Bosque '),
(8, '100725685', 'Luis', 'Martines', '14', 'Netuno', '31258547564', 'Luis7@gmail.com', 'barrio Nuvo bosque '),
(9, '1007256584', 'Claudia', 'Gonzales', '11', 'Netuno', '32156487251', 'Claudii7@gmail.com', 'Bosque'),
(10, '10045472215', 'Camila ', 'Torres', '11', 'Netuno', '313204587', 'Cami7@gmail.com', 'Bosque'),
(11, '1007256581', 'Jorge ', 'Salgado', '11', 'Netuno', '321457151', 'Sal7@gmail.com', 'Bosque'),
(12, '1234567110', 'Rodrigo', 'Alberto', '11', 'Netuno', '321457115', 'R7@gmail.com', 'peras');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transportista`
--

CREATE TABLE `transportista` (
  `id_Transportista` int(4) NOT NULL,
  `Tra_Nombre` varchar(150) NOT NULL,
  `Tra_NoCaja` varchar(20) NOT NULL,
  `Tra_Transportadora` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `transportista`
--

INSERT INTO `transportista` (`id_Transportista`, `Tra_Nombre`, `Tra_NoCaja`, `Tra_Transportadora`) VALUES
(11, 'Ernesto Melendes ', '4', 'ENVIPACK'),
(12, 'Jose rodrigres ', '5', 'ENVIPACK'),
(13, 'Sofia Melendez ', '2', 'ENVIPACK'),
(14, 'Camila Perez', '3', 'ENVIPACK');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `rol` varchar(20) NOT NULL,
  `estado` varchar(10) NOT NULL DEFAULT 'Activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `correo`, `pass`, `rol`, `estado`) VALUES
(1, 'Jesus David ', 'jesusdavid6@gmail.com', 'PBKDF2$600000$N1Wjjdz21fjMBmxrfD7vtQ==$mOcZK9Y3prwSmzbIUG7NkzXD0JDLibtqFbuuEDCbfH4=', 'ADMI', 'Activo'),
(2, 'Jairo Martinez', 'JairoMartin@gmail.com', 'PBKDF2$600000$y33cSeyvA4WsMRinGpfRww==$Am13b3cjeMhlrGKUxOXQaFy7/P/uY9bxXBIih9LQt+M=', 'USER', 'Activo'),
(3, 'German Romero', 'GermanS45@gmail.com', 'PBKDF2$600000$hnrJU/3kWVp3DeUFlXZ7pg==$BBRCa9DdiUkF2stup96UHwldqpvAEiG/UGT/BdnUcBs=', 'USER', 'Activo'),
(7, 'Erica', 'erica7@gmail.com', 'PBKDF2$600000$U3cKs28m8/HMgpN1Y772YQ==$yoAaveY83ub8TYYYwgFyZkdcuI6VEvjpD4hdB6e3W8A=', 'USER', 'Activo'),
(11, 'Petra Fernanda', 'Petra7@gmail.com', 'PBKDF2$600000$g4SMD/BsvHk2j9Z9mBNqCw==$nXLwXCh/bH8sBmv8r4N1fIsLPmXfkfNri/NaADAp2Y8=', 'USER', 'Inactivo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_Empleado`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`empresa_id`);

--
-- Indices de la tabla `listaarticulo`
--
ALTER TABLE `listaarticulo`
  ADD PRIMARY KEY (`id_Articulo`),
  ADD KEY `FK_empres_id` (`empresa_id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `transportista`
--
ALTER TABLE `transportista`
  ADD PRIMARY KEY (`id_Transportista`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_Empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `listaarticulo`
--
ALTER TABLE `listaarticulo`
  MODIFY `id_Articulo` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `transportista`
--
ALTER TABLE `transportista`
  MODIFY `id_Transportista` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `listaarticulo`
--
ALTER TABLE `listaarticulo`
  ADD CONSTRAINT `FK_empres_id` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`empresa_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
