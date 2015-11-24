-	Spécifications fonctionnelles :
o	Cas d'utilisation : poser un congé, modifier un congé et lister les congés
o	Détection du profil manager. Un employé est un manager si son identifiant figure dans la colonne ID\_MANAGER de la table EMPLOYE pour d'autres employés que lui-même
o	Une demande de congé a une caractéristique "commentaire" destinée uniquement au manager
o	Cinématique :
-	La page d'accueil contient les liens pour lister et poser un congé. Si l'employé est un manager il aura un paragraphe supplémentaire "Gérer les congés de l'équipe" avec la liste des ses collaborateurs. Chaque élément de la liste est un lien vers la liste des congés de cet employé.
-	La page listant les congés comporte pour chaque congé 3 liens : un pour modifier la demande de congés, un lien pour accepter la demande et un autre pour refuser la demande.
-	La page "Modifier une demande de congés" affiche une demande de congés pré-remplie avec les données : "type, date de début, date de fin" et ne concerne que les demandes dont le statut est "en attente".
-	Le refus d'un congé passe par une page d'affichage de la demande avec possibilité donnée au manager de motiver sa demande par un commentaire.
-	Sur chaque page est affiché le nom de l'employé connecté.
-	La page de retour après avoir poser, accepter ou refuser un congé est la page de présentation de la liste des congés de l'employé en question.