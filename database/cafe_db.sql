-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 17, 2023 at 08:35 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cafe_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `ACCOUNT_ID` varchar(10) NOT NULL,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWD` varchar(100) DEFAULT NULL,
  `STAFF_ID` varchar(10) DEFAULT NULL,
  `DELETED` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`ACCOUNT_ID`, `USERNAME`, `PASSWD`, `STAFF_ID`, `DELETED`) VALUES
('AC000', 'admin', '$2a$12$M5/5k6V35X4mMpJGsQt.iedCMPFUoYjg3Zc6EieI3Vk1jCzmP7DIK', 'ST00', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `BILL_ID` varchar(10) NOT NULL,
  `STAFF_ID` varchar(10) DEFAULT NULL,
  `DOPURCHASE` date DEFAULT NULL,
  `TOTAL` double DEFAULT 0,
  `RECEIVED` double DEFAULT 0,
  `EXCESS` double DEFAULT 0,
  `DELETED` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`BILL_ID`, `STAFF_ID`, `DOPURCHASE`, `TOTAL`, `RECEIVED`, `EXCESS`, `DELETED`) VALUES
('BI0001', 'ST00', '2025-04-22', 45000, 50000, -5000, b'0'),
('BI0002', 'ST00', '2025-04-22', 180000, 300000, -120000, b'0'),
('BI0003', 'ST00', '2025-04-24', 29000, 300000, -271000, b'0'),
('BI0004', 'ST00', '2025-05-06', 295000, 300000, -500, b'0'),
('BI0005', 'ST00', '2025-05-08', 29000, 30000, -1000, b'0'),
('BI0006', 'ST00', '2025-05-08', 154000, 155000, -1000, b'0'),
('BI0007', 'ST00', '2025-05-08', 87000, 90000, -3000, b'0'),
('BI0008', 'ST00', '2025-05-08', 222000, 250000, -28000, b'0'),
('BI0009', 'ST00', '2025-05-08', 45000, 50000, -5000, b'0'),
('BI0010', 'ST00', '2025-05-08', 29000, 30000, -1000, b'0'),
('BI0011', 'ST00', '2025-05-08', 58000, 60000, -2000, b'0'),
('BI0012', 'ST00', '2025-05-08', 195000, 200000, -5000, b'0');



-- --------------------------------------------------------

--
-- Table structure for table `bill_details`
--

CREATE TABLE `bill_details` (
  `BILL_ID` varchar(10) NOT NULL,
  `PRODUCT_ID` varchar(10) NOT NULL,
  `QUANTITY` int(11) DEFAULT 0,
  `NOTE` varchar(100) NOT NULL,
  `TOTAL` double DEFAULT 0,
  `PERCENT` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bill_details`
--

INSERT INTO `bill_details` (`BILL_ID`, `PRODUCT_ID`, `QUANTITY`, `NOTE`, `TOTAL`, `PERCENT`) VALUES
('BI0001', 'PR016', 1, '', 45000, 0),
('BI0002', 'PR010', 4, '', 45000, 0),
('BI0003', 'PR004', 1, '', 29000, 0),
('BI0004', 'PR017', 3, '', 49000, 0),
('BI0004', 'PR021', 2, '', 45000, 0),
('BI0004', 'PR036', 1, '', 29000, 0),
('BI0004', 'PR037', 1, '', 29000, 0),
('BI0005', 'PR001', 1, '', 29000, 0),
('BI0006', 'PR001', 1, '', 29000, 0),
('BI0006', 'PR016', 1, '', 45000, 0),
('BI0006', 'PR021', 1, '', 45000, 0),
('BI0006', 'PR041', 1, '', 35000, 0),
('BI0007', 'PR001', 3, '', 29000, 0),
('BI0008', 'PR001', 3, '', 29000, 0),
('BI0009', 'PR010', 1, '', 45000, 0),
('BI0010', 'PR001', 1, '', 29000, 0),
('BI0011', 'PR002', 1, 'Ít đá', 39000, 0),
('BI0011', 'PR019', 1, 'Nhiều tương ớt', 19000, 0),
('BI0012', 'PR033', 2, '', 45000, 0),
('BI0012', 'PR038', 2, '', 35000, 0),
('BI0012', 'PR039', 1, '', 35000, 0),
('BI0012', 'PR041', 1, '', 35000, 0);


--
-- Triggers `bill_details`
--

DELIMITER $$
 CREATE TRIGGER InsertBill_Details AFTER INSERT ON bill_details FOR EACH ROW BEGIN
 UPDATE bill
	SET bill.TOTAL = bill.TOTAL + (SELECT product.COST FROM product WHERE product.PRODUCT_ID = NEW.PRODUCT_ID) * NEW.QUANTITY
	WHERE bill.BILL_ID = NEW.BILL_ID;
END$$
DELIMITER ;
-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `CATEGORY_ID` varchar(10) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `DELETED` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CATEGORY_ID`, `NAME`, `QUANTITY`, `DELETED`) VALUES
('CA01', 'CÀ PHÊ PHIN', 9, b'0'),
('CA02', 'PHINDI', 9, b'0'),
('CA03', 'BÁNH MÌ QUE', 2, b'0'),
('CA04', 'TRÀ', 15, b'0'),
('CA05', 'BÁNH', 7, b'0'),
('CA06', 'FREEZE', 15, b'0'),
('CA07', 'TRÀ SỮA', 8, b'0');


-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `PRODUCT_ID` varchar(10) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `CATEGORY_ID` varchar(10) NOT NULL,
  `SIZED` varchar(4) DEFAULT 'NULL',
  `COST` double DEFAULT NULL,
  `IMAGE` mediumtext NOT NULL,
  `DELETED` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`PRODUCT_ID`, `NAME`, `CATEGORY_ID`, `SIZED`, `COST`, `IMAGE`, `DELETED`) VALUES
('PR001', 'PHIN SỮA ĐÁ', 'CA01', 'S', 29000, 'img/products/PR001.jpg', b'0'),
('PR002', 'PHIN SỮA ĐÁ', 'CA01', 'M', 39000, 'img/products/PR002.jpg', b'0'),
('PR003', 'PHIN SỮA ĐÁ', 'CA01', 'L', 45000, 'img/products/PR003.jpg', b'0'),
('PR004', 'PHIN ĐEN ĐÁ', 'CA01', 'S', 29000, 'img/products/PR004.jpg', b'0'),
('PR005', 'PHIN ĐEN ĐÁ', 'CA01', 'M', 35000, 'img/products/PR005.jpg', b'0'),
('PR006', 'PHIN ĐEN ĐÁ', 'CA01', 'L', 39000, 'img/products/PR006.jpg', b'0'),
('PR007', 'BẠC XỈU', 'CA01', 'S', 29000, 'img/products/PR007.jpg', b'0'),
('PR008', 'BẠC XỈU', 'CA01', 'M', 39000, 'img/products/PR008.jpg', b'0'),
('PR009', 'BẠC XỈU', 'CA01', 'L', 45000, 'img/products/PR009.jpg', b'0'),
('PR010', 'SỮA TƯƠI MACCHIATO', 'CA02', 'S', 45000, 'img/products/PR010.jpg', b'0'),
('PR011', 'SỮA TƯƠI MACCHIATO', 'CA02', 'M', 49000, 'img/products/PR011.jpg', b'0'),
('PR012', 'SỮA TƯƠI MACCHIATO', 'CA02', 'L', 55000, 'img/products/PR012.jpg', b'0'),
('PR013', 'SỮA MẮC CA', 'CA02', 'S', 45000, 'img/products/PR013.jpg', b'0'),
('PR014', 'SỮA MẮC CA', 'CA02', 'M', 49000, 'img/products/PR014.jpg', b'0'),
('PR015', 'SỮA MẮC CA', 'CA02', 'L', 55000, 'img/products/PR015.jpg', b'0'),
('PR016', 'LATTE', 'CA02', 'S', 45000, 'img/products/PR016.jpg', b'0'),
('PR017', 'LATTE', 'CA02', 'M', 49000, 'img/products/PR017.jpg', b'0'),
('PR018', 'LATTE', 'CA02', 'L', 55000, 'img/products/PR018.jpg', b'0'),
('PR019', 'BÁNH MÌ PATE', 'CA03', 'null', 19000, 'img/products/PR019.jpg', b'0'),
('PR020', 'BÁNH MÌ GÀ PHÔ MAI', 'CA03', 'null', 19000, 'img/products/PR020.jpg', b'0'),
('PR021', 'TRÀ OOLONG PHÚC BỒN TỬ', 'CA04', 'S', 45000, 'img/products/PR021.jpg', b'0'),
('PR022', 'TRÀ OOLONG PHÚC BỒN TỬ', 'CA04', 'M', 55000, 'img/products/PR022.jpg', b'0'),
('PR023', 'TRÀ OOLONG PHÚC BỒN TỬ', 'CA04', 'L', 65000, 'img/products/PR023.jpg', b'0'),
('PR024', 'TRÀ ĐÀO CAM SẢ', 'CA04', 'S', 45000, 'img/products/PR024.jpg', b'0'),
('PR025', 'TRÀ ĐÀO CAM SẢ', 'CA04', 'M', 55000, 'img/products/PR025.jpg', b'0'),
('PR026', 'TRÀ ĐÀO CAM SẢ', 'CA04', 'L', 65000, 'img/products/PR026.jpg', b'0'),
('PR027', 'TRÀ OOLONG HẠT SEN', 'CA04', 'S', 45000, 'img/products/PR027.jpg', b'0'),
('PR028', 'TRÀ OOLONG HẠT SEN', 'CA04', 'M', 55000, 'img/products/PR028.jpg', b'0'),
('PR029', 'TRÀ OOLONG HẠT SEN', 'CA04', 'L', 65000, 'img/products/PR029.jpg', b'0'),
('PR030', 'TRÀ OOLONG VẢI', 'CA04', 'S', 45000, 'img/products/PR030.jpg', b'0'),
('PR031', 'TRÀ OOLONG VẢI', 'CA04', 'M', 55000, 'img/products/PR031.jpg', b'0'),
('PR032', 'TRÀ OOLONG VẢI', 'CA04', 'L', 65000, 'img/products/PR032.jpg', b'0'),
('PR033', 'TRÀ XANH ĐẬU ĐỎ', 'CA04', 'S', 45000, 'img/products/PR033.jpg', b'0'),
('PR034', 'TRÀ XANH ĐẬU ĐỎ', 'CA04', 'M', 55000, 'img/products/PR034.jpg', b'0'),
('PR035', 'TRÀ XANH ĐẬU ĐỎ', 'CA04', 'L', 65000, 'img/products/PR035.jpg', b'0'),
('PR036', 'BÁNH TIRAMISU', 'CA05', 'null', 29000, 'img/products/PR036.jpg', b'0'),
('PR037', 'BÁNH PASSION CHEESE', 'CA05', 'null', 29000, 'img/products/PR037.jpg', b'0'),
('PR038', 'BÁNH MATCHA', 'CA05', 'null', 35000, 'img/products/PR038.jpg', b'0'),
('PR039', 'BÁNH GẤU CHOCOLATE', 'CA05', 'null', 35000, 'img/products/PR039.jpg', b'0'),
('PR040', 'BÁNH CHOCOLATE', 'CA05', 'null', 35000, 'img/products/PR040.jpg', b'0'),
('PR041', 'BÁNH BÔNG LAN TRỨNG MUỐI', 'CA05', 'null', 35000, 'img/products/PR041.jpg', b'0'),
('PR042', 'PHÔ MAI CACAO', 'CA05', 'null', 35000, 'img/products/PR042.jpg', b'0'),
('PR043', 'MATCHA ĐÁ XAY', 'CA06', 'S', 55000, 'img/products/PR043.jpg', b'0'),
('PR044', 'MATCHA ĐÁ XAY', 'CA06', 'M', 65000, 'img/products/PR044.jpg', b'0'),
('PR045', 'MATCHA ĐÁ XAY', 'CA06', 'L', 69000, 'img/products/PR045.jpg', b'0'),
('PR046', 'CHOCOLATE ĐÁ XAY', 'CA06', 'S', 55000, 'img/products/PR046.jpg', b'0'),
('PR047', 'CHOCOLATE ĐÁ XAY', 'CA06', 'M', 65000, 'img/products/PR047.jpg', b'0'),
('PR048', 'CHOCOLATE ĐÁ XAY', 'CA06', 'L', 69000, 'img/products/PR048.jpg', b'0'),
('PR049', 'CÀ PHÊ ĐÁ XAY', 'CA06', 'S', 55000, 'img/products/PR049.jpg', b'0'),
('PR050', 'CÀ PHÊ ĐÁ XAY', 'CA06', 'M', 65000, 'img/products/PR050.jpg', b'0'),
('PR051', 'CÀ PHÊ ĐÁ XAY', 'CA06', 'L', 69000, 'img/products/PR051.jpg', b'0'),
('PR052', 'TRÀ CÀ PHÊ ĐÁ XAY', 'CA06', 'S', 55000, 'img/products/PR052.jpg', b'0'),
('PR053', 'TRÀ CÀ PHÊ ĐÁ XAY', 'CA06', 'M', 65000, 'img/products/PR053.jpg', b'0'),
('PR054', 'TRÀ CÀ PHÊ ĐÁ XAY', 'CA06', 'L', 69000, 'img/products/PR054.jpg', b'0'),
('PR055', 'ANH ĐÀO ĐÁ XAY', 'CA06', 'S', 55000, 'img/products/PR055.jpg', b'0'),
('PR056', 'ANH ĐÀO ĐÁ XAY', 'CA06', 'M', 65000, 'img/products/PR056.jpg', b'0'),
('PR057', 'ANH ĐÀO ĐÁ XAY', 'CA06', 'L', 69000, 'img/products/PR057.jpg', b'0'),
('PR058', 'TRÀ SỮA BẠC HÀ', 'CA07', 'S', 54000, 'img/products/PR058.jpg', b'0'),
('PR059', 'TRÀ SỮA BẠC HÀ', 'CA07', 'M', 59000, 'img/products/PR059.jpg', b'0'),
('PR060', 'TRÀ SỮA KHOAI MÔN', 'CA07', 'S', 54000, 'img/products/PR060.jpg', b'0'),
('PR061', 'TRÀ SỮA KHOAI MÔN', 'CA07', 'M', 59000, 'img/products/PR061.jpg', b'0'),
('PR062', 'TRÀ SỮA SƯƠNG SÁO', 'CA07', 'S', 54000, 'img/products/PR062.jpg', b'0'),
('PR063', 'TRÀ SỮA SƯƠNG SÁO', 'CA07', 'M', 59000, 'img/products/PR063.jpg', b'0'),
('PR064', 'TRÀ SỮA TRÂN CHÂU ĐƯỜNG ĐEN', 'CA07', 'S', 54000, 'img/products/PR064.jpg', b'0'),
('PR065', 'TRÀ SỮA TRÂN CHÂU ĐƯỜNG ĐEN', 'CA07', 'M', 59000, 'img/products/PR065.jpg', b'0');

--
-- Triggers `product`
--
DELIMITER $$
CREATE TRIGGER `InsertProduct` AFTER INSERT ON `product` FOR EACH ROW UPDATE category SET category.QUANTITY = category.QUANTITY + 1
WHERE category.CATEGORY_ID = NEW.CATEGORY_ID
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `UpdateProduct` AFTER UPDATE ON `product` FOR EACH ROW BEGIN
UPDATE category
SET QUANTITY = ( SELECT COUNT(PRO.PRODUCT_ID) FROM product PRO WHERE PRO.CATEGORY_ID = category.CATEGORY_ID AND PRO.DELETED = b'0' );
END
$$
DELIMITER ;
-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `STAFF_ID` varchar(10) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `GENDER` bit(1) DEFAULT b'0',
  `DOB` date DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `PHONE` varchar(12) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  `DOENTRY` date DEFAULT NULL,
  `DELETED` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`STAFF_ID`, `NAME`, `GENDER`, `DOB`, `ADDRESS`, `PHONE`, `EMAIL`, `SALARY`, `DOENTRY`, `DELETED`) VALUES
('ST00', 'ADMIN', b'0', '1000-01-01', '', '', 'admin@gmail.com', 0, '1000-01-01', b'0');


-- --------------------------------------------------------



--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`ACCOUNT_ID`),
  ADD KEY `FK_STAFF` (`STAFF_ID`) USING BTREE;

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`BILL_ID`),
  ADD KEY `FK_STAFF` (`STAFF_ID`);

--
-- Indexes for table `bill_details`
--
ALTER TABLE `bill_details`
  ADD PRIMARY KEY (`BILL_ID`,`PRODUCT_ID`),
  ADD KEY `FK_PRODU` (`PRODUCT_ID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CATEGORY_ID`);



--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`PRODUCT_ID`),
  ADD KEY `FK_CATEGORY` (`CATEGORY_ID`);


--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`STAFF_ID`);



--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `PK_STAFF` FOREIGN KEY (`STAFF_ID`) REFERENCES `staff` (`STAFF_ID`) ON UPDATE CASCADE;

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `FK_STAFF` FOREIGN KEY (`STAFF_ID`) REFERENCES `staff` (`STAFF_ID`) ON UPDATE CASCADE;

--
-- Constraints for table `bill_details`
--
ALTER TABLE `bill_details`
  ADD CONSTRAINT `FK_BILL` FOREIGN KEY (`BILL_ID`) REFERENCES `bill` (`BILL_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PRODU` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON UPDATE CASCADE;


--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_CATEGORY` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
