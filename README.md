# Zen Factory
## Application exemple ZenBox-Demo

Application de démonstration backend :

Pans verticaux fonctionnels :

- Données d'une UF

- Géolocalisation d'une UF (unité fonctionnelle)

- Tableaux de bord

- Gestion de la flotte des utilisateurs

- (...)


Technologies utilisées :

- API RESTful avec JAX-RS

- Sécurité avec OAuth

- Crud DB avec JPA

- Consommation WS avec Jax-WS

- Tests avec Junit4

    
## Pour démarrer l'application localement :

D'abord la construire :

    $ mvn clean install

Puis la lancer avec :

    $ java -cp target/classes:target/dependency/* fr.zen.MainGlassfish

Enjoy ;)
