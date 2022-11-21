
--
-- Table structure for table `application_users`
--

DROP TABLE IF EXISTS `application_users`;
CREATE TABLE `application_users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL UNIQUE,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,    
  `role` varchar(255) NOT NULL,
  `user_public_key` varchar(255), 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `td` datetime NOT NULL,
  `author_id` int NOT NULL,
  `receiver_id` int NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,    
  `shared_key_by_author_pkey` varchar(255) NOT NULL,
  `shared_key_by_receiver_pkey` varchar(255) NOT NULL, 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `private_keys`;
CREATE TABLE `private_keys` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL UNIQUE,
  `private_key` varchar(255) NOT NULL,  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- CREATE USER 'mediconv'@'%' IDENTIFIED BY 'mysecretpassword';
-- GRANT ALL PRIVILEGES ON *.* TO 'mediconv'@'%' WITH GRANT OPTION;