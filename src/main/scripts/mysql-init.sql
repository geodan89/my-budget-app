DROP DATABASE IF EXISTS budgetappservice;
DROP USER IF EXISTS `budgetapp_service`@`localhost`;
CREATE DATABASE IF NOT EXISTS budgetappservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `budgetapp_service`@`localhost` IDENTIFIED BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `budgetappservice`.* TO `budgetapp_service`@`localhost`;
FLUSH PRIVILEGES;