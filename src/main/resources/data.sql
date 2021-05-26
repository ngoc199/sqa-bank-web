-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: banking
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accrual_savings_accounts`
--

DROP TABLE IF EXISTS `accrual_savings_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accrual_savings_accounts` (
  `monthly_amount` decimal(19,2) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKtl4clwk4c8vyugyk7ew19j7rx` FOREIGN KEY (`id`) REFERENCES `savings_accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accrual_savings_accounts`
--

LOCK TABLES `accrual_savings_accounts` WRITE;
/*!40000 ALTER TABLE `accrual_savings_accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `accrual_savings_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account` (
  `id` varchar(255) NOT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt7tjwn84dd2buad0e448cbmbj` (`customer_id`),
  CONSTRAINT `FKt7tjwn84dd2buad0e448cbmbj` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES ('BANK_1',1200000.00,'2001-12-12 00:00:00.000000','VND','2001-12-12 00:00:00.000000','1'),('BANK_2',1000000.00,'2003-11-12 00:00:00.000000','VND','2003-10-10 00:00:00.000000','1');
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corroborate_savings_accounts`
--

DROP TABLE IF EXISTS `corroborate_savings_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corroborate_savings_accounts` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKdn4qrtxwhyl4ek83u56g6xks1` FOREIGN KEY (`id`) REFERENCES `savings_accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corroborate_savings_accounts`
--

LOCK TABLES `corroborate_savings_accounts` WRITE;
/*!40000 ALTER TABLE `corroborate_savings_accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `corroborate_savings_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `sub_district` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_login_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb0dvnt2qxwgo0w8suwjwhayxi` (`user_login_id`),
  CONSTRAINT `FKb0dvnt2qxwgo0w8suwjwhayxi` FOREIGN KEY (`user_login_id`) REFERENCES `user_logins` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('1','hanoi','vietnam',NULL,'hanoi','hanoi','100000',NULL,'Ngoc','Nguyen','Duy Minh',NULL,NULL,1),('5','hanoi','vietnam',NULL,'hanoi','hanoi','100000',NULL,'Nam','Nguyen','Van',NULL,NULL,5);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `sub_district` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_login_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9cr6oaf0507y1bd8q71hfw6bw` (`user_login_id`),
  CONSTRAINT `FK9cr6oaf0507y1bd8q71hfw6bw` FOREIGN KEY (`user_login_id`) REFERENCES `user_logins` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Nghia',NULL,NULL,NULL,NULL,2),('3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Nam',NULL,NULL,NULL,NULL,3),('4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Nguyen',NULL,NULL,NULL,NULL,4);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed_loan_accounts`
--

DROP TABLE IF EXISTS `fixed_loan_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixed_loan_accounts` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKrt3lv6p64d74nnmxwqpvu4d8l` FOREIGN KEY (`id`) REFERENCES `loan_accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_loan_accounts`
--

LOCK TABLES `fixed_loan_accounts` WRITE;
/*!40000 ALTER TABLE `fixed_loan_accounts` DISABLE KEYS */;
INSERT INTO `fixed_loan_accounts` VALUES ('BANK_2');
/*!40000 ALTER TABLE `fixed_loan_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed_savings_accounts`
--

DROP TABLE IF EXISTS `fixed_savings_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixed_savings_accounts` (
  `id` varchar(255) NOT NULL,
  `fixed_rate` float NOT NULL,
  `lowest_rate` float NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKf1pk860r08t2a5dishj0j10xg` FOREIGN KEY (`id`) REFERENCES `savings_accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_savings_accounts`
--

LOCK TABLES `fixed_savings_accounts` WRITE;
/*!40000 ALTER TABLE `fixed_savings_accounts` DISABLE KEYS */;
INSERT INTO `fixed_savings_accounts` VALUES ('BANK_1',0,0);
/*!40000 ALTER TABLE `fixed_savings_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `float_loan_accounts`
--

DROP TABLE IF EXISTS `float_loan_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `float_loan_accounts` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKp02ubqqfcf1dm8he32qpl7tb3` FOREIGN KEY (`id`) REFERENCES `loan_accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `float_loan_accounts`
--

LOCK TABLES `float_loan_accounts` WRITE;
/*!40000 ALTER TABLE `float_loan_accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `float_loan_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interest`
--

DROP TABLE IF EXISTS `interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `period` int NOT NULL,
  `rate` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest`
--

LOCK TABLES `interest` WRITE;
/*!40000 ALTER TABLE `interest` DISABLE KEYS */;
INSERT INTO `interest` VALUES (7,30,0.02);
/*!40000 ALTER TABLE `interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ladder_savings_accounts`
--

DROP TABLE IF EXISTS `ladder_savings_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ladder_savings_accounts` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKrsxjp68wl4iho9hn446atfubn` FOREIGN KEY (`id`) REFERENCES `savings_accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ladder_savings_accounts`
--

LOCK TABLES `ladder_savings_accounts` WRITE;
/*!40000 ALTER TABLE `ladder_savings_accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `ladder_savings_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_accounts`
--

DROP TABLE IF EXISTS `loan_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan_accounts` (
  `real_pay_date` date DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `rate` float NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKhsjyoodjk0akdbyewu0w53ewu` FOREIGN KEY (`id`) REFERENCES `bank_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_accounts`
--

LOCK TABLES `loan_accounts` WRITE;
/*!40000 ALTER TABLE `loan_accounts` DISABLE KEYS */;
INSERT INTO `loan_accounts` VALUES (NULL,'BANK_2',0.1);
/*!40000 ALTER TABLE `loan_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progressive_savings_accounts`
--

DROP TABLE IF EXISTS `progressive_savings_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progressive_savings_accounts` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKrj4s39wcpuq79xkombo3h9j57` FOREIGN KEY (`id`) REFERENCES `savings_accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progressive_savings_accounts`
--

LOCK TABLES `progressive_savings_accounts` WRITE;
/*!40000 ALTER TABLE `progressive_savings_accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `progressive_savings_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savings_accounts`
--

DROP TABLE IF EXISTS `savings_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savings_accounts` (
  `money_receive` decimal(19,2) DEFAULT NULL,
  `period` int NOT NULL,
  `rate` float NOT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKh4tk338d4mo5slc7j3jlnhydo` FOREIGN KEY (`id`) REFERENCES `bank_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savings_accounts`
--

LOCK TABLES `savings_accounts` WRITE;
/*!40000 ALTER TABLE `savings_accounts` DISABLE KEYS */;
INSERT INTO `savings_accounts` VALUES (NULL,365,0.12,'BANK_1');
/*!40000 ALTER TABLE `savings_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_types`
--

DROP TABLE IF EXISTS `transaction_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fee` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_types`
--

LOCK TABLES `transaction_types` WRITE;
/*!40000 ALTER TABLE `transaction_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `money_transfer` varchar(255) DEFAULT NULL,
  `transaction_time` datetime(6) DEFAULT NULL,
  `receive_account_id` varchar(255) DEFAULT NULL,
  `transfer_account_id` varchar(255) DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyfq1l7qvta6rsgje7yn6vxud` (`receive_account_id`),
  KEY `FK7jtinp5hk6tyaoank6uheetix` (`transfer_account_id`),
  KEY `FK8rugypr4phjn6n2i4eb3ud2ox` (`type_id`),
  CONSTRAINT `FK7jtinp5hk6tyaoank6uheetix` FOREIGN KEY (`transfer_account_id`) REFERENCES `bank_account` (`id`),
  CONSTRAINT `FK8rugypr4phjn6n2i4eb3ud2ox` FOREIGN KEY (`type_id`) REFERENCES `transaction_types` (`id`),
  CONSTRAINT `FKjyfq1l7qvta6rsgje7yn6vxud` FOREIGN KEY (`receive_account_id`) REFERENCES `bank_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_logins`
--

DROP TABLE IF EXISTS `user_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_logins` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `is_locked` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_efhwwpkp78kgtcsh3gvf59f48` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_logins`
--

LOCK TABLES `user_logins` WRITE;
/*!40000 ALTER TABLE `user_logins` DISABLE KEYS */;
INSERT INTO `user_logins` VALUES (1,NULL,_binary '\0','$2a$12$/EJpPN446K2JUvrBJgqe/OHla.SiMlW65RkrYnhDgx0fs7qV0XtY2','CUSTOMER',NULL,'customer'),(2,NULL,_binary '\0','$2a$12$/EJpPN446K2JUvrBJgqe/OHla.SiMlW65RkrYnhDgx0fs7qV0XtY2','EMPLOYEE_CUSTOMER_CARE',NULL,'employee1'),(3,NULL,_binary '\0','$2a$12$/EJpPN446K2JUvrBJgqe/OHla.SiMlW65RkrYnhDgx0fs7qV0XtY2','EMPLOYEE_ACCOUNTANT',NULL,'employee2'),(4,NULL,_binary '\0','$2a$12$/EJpPN446K2JUvrBJgqe/OHla.SiMlW65RkrYnhDgx0fs7qV0XtY2','EMPLOYEE_MANAGER',NULL,'employee3'),(5,NULL,_binary '\0','$2a$12$/EJpPN446K2JUvrBJgqe/OHla.SiMlW65RkrYnhDgx0fs7qV0XtY2','CUSTOMER',NULL,'customerNone');
/*!40000 ALTER TABLE `user_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'banking'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-26 23:13:42
