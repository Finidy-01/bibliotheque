create database bibliotheque;
\c bibliotheque;

SELECT datname, pg_encoding_to_char(encoding) FROM pg_database WHERE datname = 'bibliotheque';

create table TypeMembre(
    idTypeMembre SERIAL PRIMARY KEY,
    nomTypeMembre VARCHAR,
    sanction int,
    maxJourEmprunt int
);
INSERT INTO TypeMembre (nomTypeMembre, sanction, maxJourEmprunt) VALUES
('Etudiant', 2, 14),
('Professionnel', 1, 30),
('Professeur', 1, 30),
('Simple', 3, 7);

create table Membre(
    idMembre VARCHAR PRIMARY KEY,
    typeMembre int,
    nom VARCHAR,
    dateNaissance date,
    adresse VARCHAR,
    dateInscription date,
    FOREIGN KEY(typeMembre) REFERENCES TypeMembre(idTypeMembre)
);
INSERT INTO Membre VALUES
('DUP19980315',1, 'Alice Dupont', '1998-03-15', '123 Rue A', '2023-01-10'),
('MAR19850620',2, 'Bob Martin', '1985-06-20', '456 Avenue B', '2022-11-05'),
('LAM19700905',3, 'Charlie Lambert', '1970-09-05', '789 Boulevard C', '2023-02-28'),
('LER20021210',4, 'Diane Leroy', '2002-12-10', '321 Rue D', '2023-03-20');

create table Categorie(
    idCategorie SERIAL PRIMARY KEY,
    nomCategorie VARCHAR
);
INSERT INTO Categorie (nomCategorie) VALUES
('Science-Fiction'),
('Romance'),
('Histoire'),
('Philosophie'),
('Technologie');

create table Collections(
    idCollections SERIAL PRIMARY KEY,
    nomCollections VARCHAR
);
INSERT INTO Collections (nomCollections) VALUES
('La Bibliothèque Verte'),
('Bibliothèque Rose'),
('Fleuve Noir'),
('Le Livre de Poche'),
('Grasset Jeunesse');

create table Editions(
    idEditions SERIAL PRIMARY KEY,
    nomEditions VARCHAR    
);
INSERT INTO Editions (nomEditions) VALUES
('Première Edition'),
('Deuxième Edition'),
('Troisième Edition');

create table Livre(
    idLivre SERIAL PRIMARY KEY,
    idEditions INT,
    dateEdition date,
    ISBN VARCHAR,
    numeroCote int,
    titre VARCHAR,
    auteur VARCHAR,
    idCollections INT,
    resumes TEXT,
    theme VARCHAR,
    langue VARCHAR,
    nbPages INT,
    couverture VARCHAR,
    FOREIGN KEY (idEditions) REFERENCES Editions(idEditions),
    FOREIGN KEY (idCollections) REFERENCES Collections(idCollections)
);
INSERT INTO Livre (idEditions, dateEdition, ISBN, numeroCote, titre, auteur, idCollections, resumes, theme, langue, nbPages, couverture) VALUES
(1, '2020-05-15', '978-3-16-148410-0', 101, 'Le Martien', 'Andy Weir', 1, 'Un astronaute échoué sur Mars...', 'Exploration spatiale', 'Français', 300, 'Relié'),
(2, '2018-08-21', '978-1-86197-876-9', 102, 'Orgueil et Préjugés', 'Jane Austen', 2, 'Une histoire d''''amour...', 'Romance', 'Français', 350, 'Broché'),
(3, '2019-10-10', '978-0-14-312774-1', 103, 'Sapiens : Une brève histoire de l''humanité', 'Yuval Noah Harari', 3, 'L''''histoire de l''''humanité...', 'Histoire', 'Français', 400, 'Broché');

create table Exemplaire(
    idExemplaire SERIAL PRIMARY KEY,
    idLivre int,
    FOREIGN KEY (idLivre) REFERENCES Livre(idLivre)
);
INSERT INTO Exemplaire (idLivre) VALUES
(1),
(1),
(1),
(2),
(3),
(3);

create table LivreCategorie(
    idLivre int,
    idCategorie int,
    FOREIGN KEY(idLivre) REFERENCES Livre(idLivre),
    FOREIGN KEY(idCategorie) REFERENCES categorie(idCategorie)
);
INSERT INTO LivreCategorie (idLivre, idCategorie) VALUES
(1, 1),
(2, 2),
(3, 3),
(3, 4);

create table TypeEmprunt(
    idTypeEmprunt SERIAL PRIMARY KEY,
    nomTypeEmprunt VARCHAR
);
INSERT INTO TypeEmprunt (nomTypeEmprunt) VALUES
('Lecture sur place'),
('Emprunt à domicile');

create table AuthEmprunt(
    idTypeMembre int,
    idLivre int,
    idTypeEmprunt int,
    FOREIGN KEY(idTypeMembre) REFERENCES TypeMembre(idTypeMembre),
    FOREIGN KEY(idLivre) REFERENCES Livre(idLivre),
    FOREIGN KEY(idTypeEmprunt) REFERENCES TypeEmprunt(idTypeEmprunt)
);
INSERT INTO AuthEmprunt (idTypeMembre, idLivre, idTypeEmprunt) VALUES
(1, 1, 2),
(2, 2, 2),
(3, 3, 2),
(4, 3, 1);

create table Emprunt(
    idEmprunt SERIAL PRIMARY KEY,
    idExemplaire int,
    dateEmprunt date,
    idMembre VARCHAR,
    idTypeEmprunt int,
    DateDelais date,
    DateRendu date,
    FOREIGN KEY(idExemplaire) REFERENCES Exemplaire(idExemplaire),
    FOREIGN KEY(idMembre) REFERENCES Membre(idMembre),
    FOREIGN KEY(idTypeEmprunt) REFERENCES TypeEmprunt(idTypeEmprunt)
);
INSERT INTO Emprunt (idExemplaire, dateEmprunt, idMembre, idTypeEmprunt, DateDelais, DateRendu) VALUES
(1, '2024-05-01', 'DUP19980315', 2, '2024-05-15', null),
(2, '2023-05-05', 'MAR19850620', 2, '2023-06-04', null),
(3, '2023-05-10', 'LAM19700905', 2, '2023-06-24', '2023-06-20'),
(4, '2023-05-15', 'LER20021210', 1, null, null),
(3, '2023-04-21', 'LAM19700905', 2, '2023-05-21', '2023-05-23');

CREATE TABLE Sanction(
    idSanction SERIAL PRIMARY KEY,
    idMembre VARCHAR,
    DateDebut date,
    DateFin date
);

CREATE VIEW TopLivres AS 
SELECT
l.*,count(e.idExemplaire) as nb
FROM Livre l
JOIN Exemplaire ex
on l.idLivre = ex.idLivre
LEFT JOIN Emprunt e 
on ex.idExemplaire = e.idExemplaire
GROUP BY l.idLivre;

SELECT nb From TopLivres where idLivre =1;

CREATE OR REPLACE VIEW ExemplaireDispo AS
SELECT ex.*,
CASE 
    WHEN MAX(e.DateRendu) IS NULL THEN 'non disponible'
    ELSE 'disponible'
END AS disponibilite
FROM Exemplaire ex
JOIN Emprunt e 
ON ex.idExemplaire = e.idEmprunt
GROUP BY ex.idExemplaire;

CREATE VIEW LivreComplet as
SELECT
l.*,
lc.idCategorie,
c.nomCategorie,
e.nomEditions
FROM Livre l
JOIN LivreCategorie lc 
on lc.idLivre = l.idLivre
JOIN categorie c 
on lc.idCategorie = c.idCategorie
JOIN Editions e
on e.idEditions = l.idEditions
GROUP BY l.idLivre,lc.idCategorie,c.nomCategorie,e.nomEditions;