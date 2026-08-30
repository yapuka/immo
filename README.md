# Immo Monorepo

Application immobilière organisée en monorepo avec :
- un backend Spring Boot
- un frontend React + Vite
- une base MongoDB locale ou via Docker

## Structure

```text
.
├── backend/                # API Spring Boot
├── frontend/               # SPA React/Vite
├── .env.example            # variables globales du monorepo
├── docker-compose.yml      # MongoDB + backend + frontend
├── package.json            # scripts racine
├── README.md               # documentation
├── .gitignore              # fichiers locaux non versionnés
└── scripts/
    ├── dev.sh
    └── check-ports.sh
```

## Quick start

```bash
cp .env.example .env
cp backend/.env.example backend/.env
cp frontend/.env.example frontend/.env
npm install
npm run dev
```

Accès rapides :
- Frontend : http://localhost:5173
- Backend : http://localhost:8080
- Mongo Express : http://localhost:8081

## Prérequis

- Java 25
- Node.js 22
- npm
- Docker Desktop ou Docker Engine

## Variables d’environnement

### Fichier racine
Le fichier [.env.example](.env.example) contient les variables utilisées par Docker Compose et par le monorepo :

```env
MONGODB_DATABASE=immo
MONGODB_ROOT_USERNAME=root
MONGODB_ROOT_PASSWORD=secretpassword
MONGODB_PORT=27017
BACKEND_PORT=8080
FRONTEND_PORT=80
FRONTEND_API_URL=http://localhost:8080
```

### Backend
Le fichier [backend/.env.example](backend/.env.example) contient :

```env
SPRING_DATA_MONGODB_URI=mongodb://root:secretpassword@localhost:27017/immo?authSource=admin
SERVER_PORT=8080
```

### Frontend
Le fichier [frontend/.env.example](frontend/.env.example) contient :

```env
VITE_API_URL=http://localhost:8080
```

> Les fichiers `.env` réels ne doivent pas être commités. Copiez les `.env.example` localement avant de lancer le projet.

## Démarrage local

Avant de lancer le projet, crée les fichiers d’environnement locaux :

```bash
cp .env.example .env
cp backend/.env.example backend/.env
cp frontend/.env.example frontend/.env
```

Puis installe les dépendances et lance la stack locale :

```bash
npm install
npm run dev
```

> Le script racine `npm run dev` démarre automatiquement MongoDB + Mongo Express, puis le backend et le frontend via `concurrently`.

### Authentification Mongo Express

Mongo Express est protégé par un login web dédié :

- utilisateur : `admin`
- mot de passe : `pass`

Les identifiants MongoDB de la base restent :

- utilisateur : `root`
- mot de passe : `secretpassword`

### Démarrage séparé des services

Vous pouvez aussi lancer les composants un par un :

```bash
npm run dev:mongodb
npm run dev:backend
npm run dev:frontend
```

ou directement via Docker Compose :

```bash
docker compose up -d mongodb mongo-express
```

## Scripts disponibles

```bash
npm run dev          # MongoDB + Mongo Express + backend + frontend
npm run dev:mongodb  # MongoDB + Mongo Express
npm run dev:backend  # backend seul
npm run dev:frontend # frontend seul
npm run ports:check  # vérifie les ports du projet (27017, 8080, 8081, 5173, 80)
npm run ports:free   # libère les ports du projet
npm run build        # build backend + frontend
npm run docker:up    # démarre toute la stack Docker Compose
npm run docker:down  # arrête et nettoie la stack Docker
```

## Docker Compose

```bash
cp .env.example .env
npm run docker:up
```

La stack Docker démarre :
- MongoDB sur http://localhost:27017
- Mongo Express sur http://localhost:8081
- le backend sur http://localhost:8080
- le frontend sur http://localhost:80

## Troubleshooting rapide

### Vérifier si les ports du projet sont libres

```bash
npm run ports:check
```

### Libérer les ports occupés

```bash
npm run ports:free
```

### Si Docker / Mongo ne démarre pas correctement

```bash
docker compose ps
docker logs mongo-ui
docker logs monorepo-mongodb
```

### Si Mongo Express demande un login

Utilisez :

- utilisateur : `admin`
- mot de passe : `pass`

### Si la base Mongo ne répond pas

```bash
docker compose up -d mongodb
```

Ensuite, relancez la stack locale :

```bash
npm run dev
```

