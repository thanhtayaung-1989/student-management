USE `student-db`;
CREATE TABLE `student` (
  `studentid` int NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phoneno` varchar(20) DEFAULT NULL,
  `studentname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;