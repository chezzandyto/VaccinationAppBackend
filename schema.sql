-- -----------------------------------------------------
-- Table "REGISTRO VACUNAS"
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS public.registro_vacunas
(
    id_usuario integer NOT NULL,
    id_vacuna integer NOT NULL,
    fecha_vacuna date NOT NULL,
    id integer NOT NULL DEFAULT nextval('registro_vacunas_id_seq'::regclass),
    CONSTRAINT pk_registro_vacunas PRIMARY KEY (id),
    CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario)
        REFERENCES public.usuarios (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_id_vacuna FOREIGN KEY (id_vacuna)
        REFERENCES public.vacunas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

-- -----------------------------------------------------
-- Table "ROLES"
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS public.roles
(
    descripcion character varying(20) COLLATE pg_catalog."default",
    id integer NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
    CONSTRAINT pkey_rol PRIMARY KEY (id)
)

-- -----------------------------------------------------
-- Table "USUARIOS"
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS public.usuarios
(
    cedula numeric(10,0) NOT NULL,
    nombres character varying(50) COLLATE pg_catalog."default" NOT NULL,
    apellidos character varying(50) COLLATE pg_catalog."default" NOT NULL,
    correo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    nombre_usuario character varying(50) COLLATE pg_catalog."default" NOT NULL,
    contrasena character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fecha_nacimiento date,
    direccion character varying(60) COLLATE pg_catalog."default",
    telefono integer,
    estado_vacunacion boolean,
    id_rol integer NOT NULL,
    id integer NOT NULL DEFAULT nextval('usuario_id_seq'::regclass),
    CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT fk_id_rol FOREIGN KEY (id_rol)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

-- -----------------------------------------------------
-- Table "VACUNAS"
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS public.vacunas
(
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    id integer NOT NULL DEFAULT nextval('nombre_vacunas_id_seq'::regclass),
    CONSTRAINT nombre_vacunas_pkey PRIMARY KEY (id)
)

