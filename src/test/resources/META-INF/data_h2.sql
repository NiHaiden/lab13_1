DELETE FROM dozent
DELETE FROM kurstyp
DELETE FROM kurs
DELETE FROM kurs_kunde

INSERT INTO dozent (doz_zuname, doz_vorname) VALUES ('Leutner', 'Brigitte');
INSERT INTO dozent (doz_zuname, doz_vorname) VALUES ('Gernhardt', 'Wolfgang');
INSERT INTO dozent (doz_zuname, doz_vorname) VALUES ('Weizenbaum', 'Josephine');
INSERT INTO dozent (doz_zuname, doz_vorname) VALUES ('Ludwig', 'Luigi');
INSERT INTO dozent (doz_zuname, doz_vorname) VALUES ('Mergel', 'Boris');
INSERT INTO dozent (doz_zuname, doz_vorname) VALUES ('Duffing', 'Julienne');
INSERT INTO dozent (doz_zuname, doz_vorname) VALUES ('Meyer', 'Julius');


INSERT INTO kurstyp(typ_id, typ_bezeichnung) VALUES ('P', 'Programmierung');
INSERT INTO kurstyp(typ_id, typ_bezeichnung) VALUES ('S', 'Skriptsprachen');
INSERT INTO kurstyp(typ_id, typ_bezeichnung) VALUES ('W', 'Webtechnologien');

INSERT INTO kurs (kurs_typ, kurs_doz_id, kurs_bezeichnung, kurs_beginndatum) VALUES ('P', 2, 'Objektorientierte Programmierung mit Java', '2010-08-27');
INSERT INTO kurs (kurs_typ, kurs_doz_id, kurs_bezeichnung, kurs_beginndatum) VALUES ('S', 3, 'JavaScript', '2010-06-29');
INSERT INTO kurs (kurs_typ, kurs_doz_id, kurs_bezeichnung, kurs_beginndatum) VALUES ('P', 2, 'JDBC', '2010-06-30');
INSERT INTO kurs (kurs_typ, kurs_doz_id, kurs_bezeichnung, kurs_beginndatum) VALUES ('W', 4, 'HTML', '2010-07-13');
INSERT INTO kurs (kurs_typ, kurs_doz_id, kurs_bezeichnung, kurs_beginndatum) VALUES ('P', 5, 'GUI-Programmierung mit Java', '2010-10-09');
INSERT INTO kurs (kurs_typ, kurs_doz_id, kurs_bezeichnung, kurs_beginndatum) VALUES ('W', 4, 'Servlets', '2010-10-10');


INSERT INTO kunde (kunde_zuname, kunde_vorname) VALUES ('Bauer', 'Hannes');
INSERT INTO kunde (kunde_zuname, kunde_vorname) VALUES ('Khan', 'Dschingis');
INSERT INTO kunde (kunde_zuname, kunde_vorname) VALUES ('Schmidt', 'Lothar');
INSERT INTO kunde (kunde_zuname, kunde_vorname) VALUES ('Kunze', 'Sieglinde');
INSERT INTO kunde (kunde_zuname, kunde_vorname) VALUES ('Hintze', 'Franz');
INSERT INTO kunde (kunde_zuname, kunde_vorname) VALUES ('Kaiser', 'Leo');

INSERT INTO kurs_kunde VALUES (6, 1);
INSERT INTO kurs_kunde VALUES (3, 2);
INSERT INTO kurs_kunde VALUES (3, 1);
INSERT INTO kurs_kunde VALUES (4, 1);
INSERT INTO kurs_kunde VALUES (5, 3);
INSERT INTO kurs_kunde VALUES (5, 2);
INSERT INTO kurs_kunde VALUES (1, 3);
INSERT INTO kurs_kunde VALUES (4, 2);
