# immo-api

API backend de l'application immobiliere, construite avec Spring Boot, Java 21, Maven et MongoDB.

## Prerequis

- Java 21
- MongoDB local ou MongoDB Atlas
- Maven Wrapper inclus dans le projet

## Demarrage

Configurer la connexion MongoDB si necessaire :

```bash
cp .env.example .env
set -a
source .env
set +a
./mvnw spring-boot:run
```

L'API demarre par defaut sur `http://localhost:8080`.

## Endpoints actuels

- `GET /api/health` : verifie que l'API est disponible
- `GET /api/users` : liste les utilisateurs
- `POST /api/users` : cree un utilisateur

Exemple de creation :

```bash
curl -X POST http://localhost:8080/api/users \
  -H 'Content-Type: application/json' \
  -d '{"email":"alice@example.com","displayName":"Alice"}'
```

La validation des donnees est activee et les documents MongoDB ne sont pas exposes directement : l'API utilise des DTO de requete et de reponse.

## Frontend React

Les origines locales `http://localhost:3000` et `http://localhost:5173` sont autorisees par CORS. Cette liste devra etre adaptee aux domaines reels lors du deploiement.

## A prevoir : OAuth2 Google

L'authentification OAuth2 avec Google est volontairement reportee. Elle sera integree dans une iteration ulterieure, apres stabilisation du socle metier. Il faudra alors definir le parcours de connexion, le stockage de l'utilisateur et le mecanisme d'authentification des appels React, sans placer de token dans une query string.

## Verification

```bash
./mvnw test
```

## Qualite et hooks Git

Les contrôles de formatage, Checkstyle et compilation des tests s'exécutent avant chaque commit.
La suite complète de tests s'exécute avant chaque push.

Activer les hooks versionnés pour votre clone :

```bash
git config core.hooksPath .githooks
```

La CI GitHub exécute `./mvnw clean verify`, génère le rapport JaCoCo et lance un scan CVE des dépendances.
Pour accélérer la première synchronisation NVD, ajouter le secret GitHub `NVD_API_KEY` aux paramètres du dépôt.
En local, renseigner `NVD_API_KEY` dans `.env`, puis charger ce fichier avant le scan Maven :

```bash
./mvnw org.owasp:dependency-check-maven:check \
  -DskipTests \
  -DnvdApiKey="$NVD_API_KEY"
```
