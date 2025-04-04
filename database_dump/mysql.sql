CREATE TABLE `car_options` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) NOT NULL,
  `series` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `car_options` VALUES (1,'Mercedes-Benz','C klass','C 160'),(2,'Mercedes-Benz','C klass','C 180'),(3,'Mercedes-Benz','C klass','C 200'),(4,'Mercedes-Benz','C klass','C 220'),(5,'BMW','3 seeria','315'),(6,'BMW','3 seeria','316'),(7,'BMW','3 seeria','317'),(8,'BMW','3 seeria','318'),(9,'BMW','3 seeria','319'),(10,'BMW','4 seeria',''),(11,'BMW','5 seeria','518'),(12,'BMW','5 seeria','520'),(13,'BMW','5 seeria','523'),(14,'BMW','5 seeria','524'),(15,'BMW','5 seeria','525'),(16,'Audi','A seeria',''),(17,'Audi','e-tron',''),(18,'Audi','Q seeria','Q2'),(19,'Audi','Q seeria','Q3'),(20,'Audi','Q seeria','Q4'),(21,'Audi','Q seeria','Q5'),(22,'Audi','Q seeria','Q7'),(23,'Audi','RS seeria','RS4'),(24,'Audi','RS seeria','RS5'),(25,'Audi','RS seeria','RS6'),(26,'Audi','TT',''),(27,'Citro├½n','',''),(28,'Muu','',''),(29,'Audi','',''),(30,'Mercedes-Benz','C klass',''),(31,'BMW','3 seeria',''),(32,'BMW','5 seeria',''),(33,'Audi','Q seeria',''),(34,'Audi','RS seeria','');

CREATE TABLE `user_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `car_ids` varchar(255) NOT NULL,
  `has_license` tinyint(1) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  CONSTRAINT `user_details_ibfk_1` FOREIGN KEY (`username`) REFERENCES `login` (`username`)
);

CREATE TABLE `login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
);
