# Immo Monorepo

Application immobilière organisée en architecture hexagonale au sein d’un monorepo :
- backend Spring Boot avec modules de domaine, service, infrastructure et web
- frontend React + Vite
- base MongoDB pour le stockage
- orchestration locale via Docker Compose ou par scripts npm

## Structure du projet

```text
.
├── backend/                # API Spring Boot et modules hexagonaux
│   ├── common/
│   ├── domain/
│   ├── infrastructure/
│   ├── service/
│   ├── web/
│   ├── Dockerfile
│   ├── pom.xml
│   └── mvnw
├── frontend/               # application React/Vite
│   ├── Dockerfile
│   └── package.json
├── scripts/
│   ├── dev.sh
│   └── check-ports.sh
├── docker-compose.yml      # MongoDB + Mongo Express + backend + frontend
├── package.json            # scripts racine
├── .env.example            # variables globales Docker
├── README.md
├── .gitignore
└── .env                    # local override non versionné
```

## Prérequis

- Java 25
- Node.js 22
- npm
- Docker Desktop ou Docker Engine

## Quick start

1. Créez les variables locales si nécessaire :

```bash
cp .env.example .env
```

2. Installez les dépendances :

```bash
npm install
```

3. Démarrez l’environnement de développement :

```bash
npm run dev
```

Cela lance automatiquement :
- MongoDB
- Mongo Express
- le backend Spring Boot
- le frontend Vite

Accès rapides :
- Frontend : http://localhost:5173
- Backend : http://localhost:8080
- Mongo Express : http://localhost:8081

## Variables d’environnement

### Fichier racine
Le fichier [.env.example](.env.example) contient les variables de base utilisées par Docker Compose :

```env
MONGODB_DATABASE=immo
MONGODB_ROOT_USERNAME=root
MONGODB_ROOT_PASSWORD=secretpassword
MONGODB_PORT=27017
BACKEND_PORT=8080
FRONTEND_PORT=80
FRONTEND_API_URL=http://localhost:8080
```

> Les valeurs réelles peuvent être surchargées localement dans un fichier `.env` non versionné.

## Démarrage local

### Mode développement intégral

```bash
npm run dev
```

Le script `dev` démarre les services via `concurrently` :
- `docker compose up -d mongodb mongo-express`
- `./mvnw spring-boot:run` dans le module backend `web`
- `npm run dev --prefix frontend`

### Services séparés

```bash
npm run dev:mongodb
npm run dev:backend
npm run dev:frontend
```

### Docker Compose

```bash
docker compose up --build
```

La stack Docker inclut :
- MongoDB sur http://localhost:27017
- Mongo Express sur http://localhost:8081
- backend sur http://localhost:8080
- frontend sur http://localhost:80

## Scripts disponibles

```bash
npm run dev          # démarre MongoDB + Mongo Express + backend + frontend
npm run dev:mongodb  # MongoDB + Mongo Express
npm run dev:backend  # backend seul
npm run dev:frontend # frontend seul
npm run ports:check  # vérifie les ports du projet
npm run ports:free   # libère les ports du projet
npm run build        # build backend + frontend
npm run docker:up    # démarre la stack Docker Compose
npm run docker:down  # stoppe et nettoie la stack Docker
```

## Authentification Mongo Express

Mongo Express est protégé par un compte dédié :
- utilisateur : `admin`
- mot de passe : `pass`

Les identifiants MongoDB sont :
- utilisateur : `root`
- mot de passe : `secretpassword`

## Troubleshooting

### Vérifier les ports

```bash
npm run ports:check
```

### Libérer les ports occupés

```bash
npm run ports:free
```

### Si Docker ne démarre pas

```bash
docker compose ps
docker logs mongo-ui
docker logs monorepo-mongodb
docker logs monorepo-backend
```

### Si le backend Docker ne démarre pas

Le conteneur backend construit maintenant le jar Maven à partir du module `web` avec une étape multi-étapes. Si le build échoue, vérifiez que le module `backend/web` est bien présent et que Maven peut créer le jar :

```bash
cd backend
./mvnw -pl web -am package -DskipTests
```

### Si le frontend Docker ne s’affiche pas correctement

Le frontend est servi par Nginx sur le port 80. Vérifiez la build du frontend :

```bash
cd frontend
npm run build
```

## Notes d’architecture

Le backend suit une séparation claire en couches hexagonales :
- `common` : utilitaires transverses
- `domain` : modèle métier et règles
- `service` : cas d’usage
- `infrastructure` : adaptateurs techniques (MongoDB, sécurité, etc.)
- `web` : API REST et point d’entrée Spring Boot

Cette séparation vise à isoler le cœur métier des dépendances techniques et à faciliter la maintenance et l’évolution du projet.

