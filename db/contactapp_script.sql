-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema contactapp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema contactapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `contactapp` DEFAULT CHARACTER SET ascii ;
USE `contactapp` ;

-- -----------------------------------------------------
-- Table `contactapp`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `contactapp`.`admin` (
  `id_admin` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NULL DEFAULT NULL,
  `password_hash` VARCHAR(255) NULL DEFAULT NULL,
  `full_name` VARCHAR(100) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_admin`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = ascii;


-- -----------------------------------------------------
-- Table `contactapp`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `contactapp`.`contact` (
  `id_contact` INT NOT NULL AUTO_INCREMENT,
  `name_contact` VARCHAR(45) NULL DEFAULT NULL,
  `phone_contact` VARCHAR(20) NULL DEFAULT NULL,
  `email_contact` VARCHAR(100) NULL DEFAULT NULL,
  `photo_path` VARCHAR(450) NULL DEFAULT NULL,
  PRIMARY KEY (`id_contact`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = ascii;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
