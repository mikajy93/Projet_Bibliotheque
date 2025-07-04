-- Insertion dans la table Auteur
INSERT INTO Auteur (nom, prenom) VALUES
('Dumas', 'Alexandre'),
('Rowling', 'J.K.'),
('Tolkien', 'J.R.R.'),
('Hugo', 'Victor');

-- Insertion dans la table TypeLivre
INSERT INTO TypeLivre (libelle) VALUES
('Roman'),
('Science-Fiction'),
('Fantastique'),
('Poésie');

-- Insertion dans la table Bibliothecaire
INSERT INTO Bibliothecaire (nom, mot_de_passe, email) VALUES
('Martin', 'motdepasse123', 'martin@bibliotheque.com'),
('Dupont', 'biblio456', 'dupont@bibliotheque.com');

-- Insertion dans la table TypeAdherent
INSERT INTO TypeAdherent (libelle, duree_pret, quota, nb_reservation_max, duree_penalite, nb_jour_max_prolongement) VALUES
('Etudiant', 14, 5, 3, 7, 7),
('Adulte', 21, 10, 5, 14, 14),
('Senior', 30, 8, 4, 10, 10);

-- Insertion dans la table EtatExemplaire
INSERT INTO EtatExemplaire (libelle) VALUES
('Neuf'),
('Bon état'),
('Usé'),
('Endommagé');

-- Insertion dans la table TypePret
INSERT INTO TypePret (libelle) VALUES
('Normal'),
('Express'),
('Long terme');

-- Insertion dans la table StatutReservation
INSERT INTO StatutReservation (libelle) VALUES
('En attente'),
('Confirmée'),
('Annulée'),
('Terminée');

-- Insertion dans la table JourFerie
INSERT INTO JourFerie (date_jourferie, nom) VALUES
('2025-01-01', 'Jour de l''An'),
('2025-05-01', 'Fête du Travail'),
('2025-12-25', 'Noël');

-- Insertion dans la table JourOuvrable
INSERT INTO JourOuvrable (date_jourouv, nom) VALUES
('2025-07-04', 'Vendredi ouvrable'),
('2025-07-05', 'Samedi ouvrable');

-- Insertion dans la table Adherent
INSERT INTO Adherent (nom, email, mot_de_passe, date_naissance, id_type_adherent, quota_restant) VALUES
('Leroy', 'leroy@gmail.com', 'pass123', '1995-03-15', 1, 5),
('Moreau', 'moreau@gmail.com', 'pass456', '1980-07-22', 2, 10),
('Petit', 'petit@gmail.com', 'pass789', '1965-11-30', 3, 8);

-- Insertion dans la table Abonnement
INSERT INTO Abonnement (id_adherent, date_debut, date_fin) VALUES
(1, '2025-01-01', '2025-12-31'),
(2, '2025-02-01', '2026-01-31'),
(3, '2025-03-01', '2026-02-28');

-- Insertion dans la table Livre
INSERT INTO Livre (titre, isbn, id_type, edition, id_auteur, age_minimum, annee_publication) VALUES
('Les Trois Mousquetaires', '9781234567890', 1, 'Gallimard', 1, 12, 1844),
('Harry Potter à l''école des sorciers', '9780439708180', 3, 'Bloomsbury', 2, 10, 1997),
('Le Seigneur des Anneaux', '9780261103573', 3, 'Allen & Unwin', 3, 14, 1954),
('Les Misérables', '9780140444308', 1, 'Penguin', 4, 16, 1862);

-- Insertion dans la table Exemplaire
INSERT INTO Exemplaire (id_livre) VALUES
(1), (1), -- Deux exemplaires des Trois Mousquetaires
(2), -- Un exemplaire de Harry Potter
(3), (3), -- Deux exemplaires du Seigneur des Anneaux
(4); -- Un exemplaire des Misérables

-- Insertion dans la table StatusExemplaire
INSERT INTO StatusExemplaire (id_exemplaire, date_changement, id_etat, id_biblio) VALUES
(1, '2025-01-10', 1, 1),
(2, '2025-02-15', 2, 1),
(3, '2025-03-20', 2, 2),
(4, '2025-04-01', 1, 1),
(5, '2025-04-10', 3, 2),
(6, '2025-05-05', 2, 1);

-- Insertion dans la table Pret
INSERT INTO Pret (id_exemplaire, id_adherent, id_type_pret, date_pret, date_retour_prevue, date_retour_reelle) VALUES
(1, 1, 1, '2025-06-01', '2025-06-15', NULL), -- Prêt en cours
(3, 2, 2, '2025-06-10', '2025-06-17', '2025-06-16'), -- Prêt retourné
(4, 3, 1, '2025-06-15', '2025-07-15', NULL); -- Prêt en cours

-- Insertion dans la table Prolongement
INSERT INTO Prolongement (id_pret) VALUES
(1); -- Prolongement du prêt 1

-- Insertion dans la table Reservation
INSERT INTO Reservation (id_exemplaire, id_adherent, id_statut_reservation, date_reservation) VALUES
(2, 1, 1, '2025-06-20'),
(5, 2, 2, '2025-06-22'),
(6, 3, 3, '2025-06-25');

-- Insertion dans la table Penalite
INSERT INTO Penalite (id_pret, duree_penalite) VALUES
(2, 7); -- Pénalité pour retour tardif du prêt 2

-- Insertion dans la table Retour
INSERT INTO Retour (id_pret, id_reservation, date_retour) VALUES
(2, NULL, '2025-06-16');