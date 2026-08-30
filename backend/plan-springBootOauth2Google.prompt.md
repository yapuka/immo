## Plan : API Spring Boot

Créer un **monolithe modulaire Spring Boot** en Java 21 avec Maven et MongoDB. La première version se concentrera sur l’API métier et sa sécurité de base. OAuth2 Google sera ajouté dans une itération ultérieure et devra être explicitement mentionné dans le README pour ne pas être oublié.

**Étapes**

1. Initialiser le projet avec Spring Initializr :
   - Spring Web
   - Spring Security
   - Spring Data MongoDB
   - Validation
   - Spring Boot Test

2. Configurer les environnements avec `application.yml` et une variable d’environnement pour la connexion MongoDB.

3. Organiser le code par fonctionnalité :
   - `security` : configuration de sécurité de base
   - `user` : utilisateurs MongoDB et endpoint `/me`
   - `property` : futur domaine immobilier

4. Implémenter les premiers endpoints métier et leur validation, avec des DTO séparés des documents MongoDB.

5. Configurer une sécurité de base pour l’API et CORS explicitement, en laissant l’architecture prête à accueillir OAuth2 plus tard.

6. Tester :
   - validation des entrées
   - routes publiques et routes nécessitant une authentification clairement séparées
   - CORS correctement restreint

7. Documenter dans `README.md` le démarrage, la configuration MongoDB, le contrat consommé par React et une section **À prévoir : OAuth2 Google** rappelant que l’authentification Google, la gestion des utilisateurs et l’émission éventuelle de JWT seront traitées dans une itération ultérieure.

**Architecture recommandée**

OAuth2 Google est volontairement hors périmètre de la première version. Il sera étudié et intégré ultérieurement dans cette API, après stabilisation du socle métier.

**Lombok et Spring Initializr**

Oui, Spring Initializr est parfaitement adapté pour générer le squelette et importer les dépendances. Lombok est optionnel : tu peux l’ajouter, mais Java 21 permet aussi d’utiliser des `record` pour les DTO et de garder un code plus explicite. Je limiterais Lombok aux classes où il apporte réellement quelque chose.

**Fichiers principaux à prévoir**

- `pom.xml`
- `src/main/java/.../security/SecurityConfig.java`
- `src/main/java/.../user/`
- `src/main/resources/application.yml`
- `README.md`

**Hors périmètre initial**

OAuth2 Google, JWT applicatif, microservices, serveur d’autorisation dédié, gestion multi-fournisseurs, refresh tokens complexes et rôles métier avancés.

OAuth2 Google devra être ajouté ultérieurement et rester visible dans le README comme évolution prévue.