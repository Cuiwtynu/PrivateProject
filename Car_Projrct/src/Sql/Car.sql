CREATE DATABASE CarSpecials
USE CarSpecials
CREATE TABLE CarType (typeId INT PRIMARY KEY,typeName VARCHAR(20),carNum INT DEFAULT 0) cartype
CREATE table Cars(carId int PRIMARY KEY,
		carName varchar(20),
		typeId int,
		passenger_size int DEFAULT 0,
		carry_size int DEFAULT 0,
		piece DOUBLE default 0,
		CONSTRAINT Cars_typeId_fk foreign key(typeId) REFERENCES carType(typeId) on update cascade on delete cascade)