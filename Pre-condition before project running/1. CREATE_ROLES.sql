use `student-db`;
CREATE TABLE `roles` (
  `rolesid` int NOT NULL,
  `rolesname` enum('ROLE_ADMIN','ROLE_USER') DEFAULT NULL,
  PRIMARY KEY (`rolesid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;