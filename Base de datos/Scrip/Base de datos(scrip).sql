-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: desarrollo
-- ------------------------------------------------------
-- Server version	6.1
CREATE SCHEMA IF NOT EXISTS `desarrollo` DEFAULT CHARACTER SET utf8 ;
--
-- Tabla de Profesores
--

CREATE TABLE Profesores (
  idProfesor int NOT NULL AUTO_INCREMENT,
  nombre varchar(50) DEFAULT NULL,
  apellido varchar(50) DEFAULT NULL,
  rfc varchar(13) DEFAULT NULL,
  PRIMARY KEY (idProfesor),
  UNIQUE KEY rfc (rfc)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `unidades_de_aprendizaje`
--
CREATE TABLE usuario (
  `correo` VARCHAR(45) NOT NULL,
  `idusuario` INT(11) NOT NULL AUTO_INCREMENT,
  `contrasena` VARCHAR(45) NOT NULL,
  idadmin INT(1) NOT NULL default 0,
  idProfesor INT not null,
  CONSTRAINT profesorid1 FOREIGN KEY (idProfesor) REFERENCES Profesores (idProfesor),
  PRIMARY KEY (`idusuario`)
  )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

CREATE TABLE Unidades_Academicas (
  idUnidadAprendizaje int NOT NULL AUTO_INCREMENT,
  nombreUnidadAprendizaje varchar(50) DEFAULT NULL,
  horasClase decimal(5,2) DEFAULT NULL,
  horasTaller decimal(5,2) DEFAULT NULL,
  horasLaboratorio decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (idUnidadAprendizaje)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `profesores_unidades`
--

CREATE TABLE Unidades_Profesores (
  idUnidadp int NOT NULL auto_increment,
  idProfesor int NOT NULL,
  idUnidadAprendizaje int NOT NULL,
  PRIMARY KEY (idUnidadp),
  CONSTRAINT profesores_unidades_ibfk_1 FOREIGN KEY (idProfesor) REFERENCES Profesores (idProfesor),
  CONSTRAINT profesores_unidades_ibfk_2 FOREIGN KEY (idUnidadAprendizaje) REFERENCES Unidades_Academias(idUnidadAprendizaje)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
