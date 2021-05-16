DROP TABLE IF EXISTS dozent;
DROP TABLE IF EXISTS kurs;
DROP TABLE IF EXISTS kunde;
DROP TABLE IF EXISTS kurs_kunde;

CREATE TABLE dozent
(
    doz_id      IDENTITY NOT NULL,
    doz_zuname  varchar(25),
    doz_vorname varchar(25),
    CONSTRAINT dozent_pkey PRIMARY KEY (doz_id)
);


CREATE TABLE kurstyp
(
    ktID           CHARACTER,
    ktBeschreibung VARCHAR(128),
    CONSTRAINT kurs_typ_pk PRIMARY KEY (ktID)
);

CREATE TABLE kurs
(
    kurs_id          IDENTITY NOT NULL,
    kurs_typ         character(1),
    kurs_doz_id      integer,
    kurs_bezeichnung varchar(100),
    kurs_beginndatum date,
    CONSTRAINT kurs_pkey PRIMARY KEY (kurs_id),
    CONSTRAINT kurs_fk_doz FOREIGN KEY (kurs_doz_id)
        REFERENCES dozent (doz_id),
    CONSTRAINT kurs_typ_fk FOREIGN KEY (kurs_typ) REFERENCES kurstyp (ktID)
);

CREATE TABLE kunde
(
    kunde_id      IDENTITY NOT NULL,
    kunde_zuname  varchar(25),
    kunde_vorname varchar(25),
    CONSTRAINT kunde_pkey PRIMARY KEY (kunde_id)
);

CREATE TABLE kurs_kunde
(
    kunde_id integer NOT NULL,
    kurs_id  integer NOT NULL,
    CONSTRAINT kurs_kunde_pkey PRIMARY KEY (kunde_id, kurs_id),
    CONSTRAINT kurs_kunde_fk1 FOREIGN KEY (kunde_id)
        REFERENCES kunde (kunde_id),
    CONSTRAINT kurs_kunde_fk2 FOREIGN KEY (kurs_id)
        REFERENCES kurs (kurs_id)
);