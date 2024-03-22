
--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` enum('Nam','Nữ') NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `salary` decimal(15,2) NOT NULL DEFAULT 0.00,
  `join_date` date NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `position` VARCHAR(255) DEFAULT NULL,
  `commission` decimal(15,2) DEFAULT 0.00,
  `allowance` decimal(15,2) DEFAULT 0.00,
  `work_duration` DATE NOT NULL,
  `create_time` timestamp NULL DEFAULT current_timestamp(),
  `update_time` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
