-- -----------------------------------------------------
-- Create table users
-- -----------------------------------------------------

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_activo` bit(1) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6km2m9i3vjuy36rnvkgj1l61s` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Create table phones
-- -----------------------------------------------------
CREATE TABLE `phones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city_code` int DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `number` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiyupsrg3ni2icd23x7fkv6v28` (`id_user`),
  CONSTRAINT `FKiyupsrg3ni2icd23x7fkv6v28` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


