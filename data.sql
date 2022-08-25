INSERT INTO roles(descripcion) VALUES ('ADMIN');
INSERT INTO roles(descripcion) VALUES ('USER');

insert into vacunas (nombre) values ('Sputnik');
insert into vacunas (nombre) values ('AstraZeneca');
insert into vacunas (nombre) values ('Pfizer');
insert into vacunas (nombre) values ('Jhonson&Jhonson');

insert into usuarios (cedula, nombres, apellidos, correo, nombre_usuario, contrasena, id_rol) values (7499394367, 'Adam', 'Smith', 'asmith@yahoo.com', 'asmith1', '$2a$10$2.GOjLG0W1TZze4ShGQHF.9Ds0bW92HWWBg7RyArcPq77Nhl2lTFy', 1);
insert into usuarios (cedula, nombres, apellidos, correo, nombre_usuario, contrasena, id_rol) values (7499394367, 'Rosabelle', 'Children', 'rchildren0@yahoo.com', 'rchildren0', '$2a$10$muQvBlsjKErysgavEG3X..2d.lUtAF1lCLNE.loIhyMsLPmW7hZuu', 2);
insert into usuarios (cedula, nombres, apellidos, correo, nombre_usuario, contrasena, id_rol) values (0756084733, 'Les', 'Richardon', 'lrichardon1@4shared.com', 'lrichardon1', '$2a$10$EIDqzXm1SC9ES0nP1YZUW.IYtjMn./y6JUPW0bmBXg6NMrMoqkjCW', 2);