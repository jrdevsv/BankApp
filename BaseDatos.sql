-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 26, 2023 at 10:02 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_number` varchar(255) NOT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  `balance` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `id_customer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_number`, `account_type`, `balance`, `state`, `id_customer`) VALUES
('225487', 'Corriente', '100', 'True', 3),
('478758', 'ahorro', '2000', 'True', 2),
('495878', 'Ahorro', '0', 'True', 4),
('496825', 'Ahorro', '540', 'True', 3),
('585545', 'Corriente', '1000', 'True', 2);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id_customer` int(11) NOT NULL,
  `id_person` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id_customer`, `id_person`, `password`, `state`) VALUES
(2, 2, '1234', 'True'),
(3, 3, '5678', 'True'),
(4, 4, '1245', 'True');

-- --------------------------------------------------------

--
-- Table structure for table `customer_seq`
--

CREATE TABLE `customer_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_seq`
--

INSERT INTO `customer_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `movement`
--

CREATE TABLE `movement` (
  `id_movement` int(11) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `movement_type` varchar(255) DEFAULT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  `balance` decimal(38,2) DEFAULT NULL,
  `account_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movement`
--

INSERT INTO `movement` (`id_movement`, `date`, `movement_type`, `amount`, `balance`, `account_number`) VALUES
(1, '2023-07-25 00:00:00.000000', 'DEBITO', '-575.00', '1425.00', '478758'),
(2, '2023-07-25 00:00:00.000000', 'CREDITO', '600.00', '700.00', '225487'),
(3, '2023-07-25 00:00:00.000000', 'CREDITO', '150.00', '150.00', '495878'),
(4, '2023-07-25 00:00:00.000000', 'DEBITO', '-540.00', '0.00', '496825');

-- --------------------------------------------------------

--
-- Table structure for table `movement_seq`
--

CREATE TABLE `movement_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movement_seq`
--

INSERT INTO `movement_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id_person` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `identification` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id_person`, `name`, `genre`, `age`, `identification`, `address`, `phone`) VALUES
(2, 'Jose Lema', 'M', '32', '123456', 'Otavalo sn y principal', '098254785'),
(3, 'Marianela Montalvo', 'F', '25', '321654', 'Amazonas y NNUU', '097548965'),
(4, 'Juan Osorio', 'M', '20', '985324', '13 junio y Equinoccial', '098874587');

-- --------------------------------------------------------

--
-- Table structure for table `person_seq`
--

CREATE TABLE `person_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person_seq`
--

INSERT INTO `person_seq` (`next_val`) VALUES
(101);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_number`),
  ADD KEY `account_FK` (`id_customer`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id_customer`),
  ADD KEY `customer_FK` (`id_person`);

--
-- Indexes for table `movement`
--
ALTER TABLE `movement`
  ADD PRIMARY KEY (`id_movement`),
  ADD KEY `movement_FK` (`account_number`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id_person`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id_customer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=353;

--
-- AUTO_INCREMENT for table `movement`
--
ALTER TABLE `movement`
  MODIFY `id_movement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=754;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id_person` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=304;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_FK` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id_customer`);

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_FK` FOREIGN KEY (`id_person`) REFERENCES `person` (`id_person`);

--
-- Constraints for table `movement`
--
ALTER TABLE `movement`
  ADD CONSTRAINT `movement_FK` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
