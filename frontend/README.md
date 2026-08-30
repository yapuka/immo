# Frontend Immo

Application frontend React + TypeScript + Vite du projet immobilier, intégrée dans un monorepo avec un backend Spring Boot et une base MongoDB.

## Stack technique

- React 19
- TypeScript
- Vite
- Vitest + Testing Library
- ESLint + Prettier
- Storybook

## Prérequis

- Node.js 22
- npm

## Démarrage rapide

Depuis la racine du monorepo :

```bash
npm run dev:frontend
```

Ou directement dans le dossier frontend :

```bash
cd frontend
npm install
npm run dev
```

Application disponible sur :

```text
http://localhost:5173
```

## Scripts

```bash
npm run dev          # Vite en mode développement
npm run build        # TypeScript + build production
npm run preview      # prévisualisation du build
npm run test         # tests unitaires
npm run test:watch   # tests en watch
npm run lint         # ESLint
npm run format       # Prettier
npm run storybook    # Storybook local
npm run build-storybook
```

## Variables d’environnement

Le frontend consomme l’API backend via :

```env
VITE_API_URL=http://localhost:8080
```

En environnement Docker, le port exposé du frontend est `80` et l’API doit être accessible sur `http://localhost:8080`.

## Lancer via Docker

Depuis la racine du projet :

```bash
docker compose up --build frontend
```

Le frontend est servi sur :

```text
http://localhost:80
```

## Points de vigilance

- Le frontend ne doit pas dépendre directement des détails de la couche métier.
- La communication avec le backend passe par l’URL configurée dans `VITE_API_URL`.
- Les composants doivent être testés à partir du comportement utilisateur observable.
