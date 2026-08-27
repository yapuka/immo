# Immo

Starter frontend basé sur React 19, TypeScript et Vite.

## Stack

- React 19 et React DOM
- TypeScript
- Vite
- Tailwind CSS v4
- Storybook 10 avec addons accessibility, docs et tests
- Vitest, Testing Library et jsdom
- ESLint et Prettier

## Prérequis

- Node.js 22 ou une version compatible avec `import.meta.dirname`
- npm

## Installation

```bash
npm install
```

## Développement

Lancer l'application :

```bash
npm run dev
```

L'application est disponible sur `http://localhost:5173`.

Lancer Storybook :

```bash
npm run storybook
```

Storybook est disponible sur `http://localhost:6006`.

## Scripts

| Commande                  | Description                                          |
| ------------------------- | ---------------------------------------------------- |
| `npm run dev`             | Lance le serveur Vite en mode développement          |
| `npm run build`           | Vérifie TypeScript et produit le build de production |
| `npm run preview`         | Sert localement le build de production               |
| `npm run storybook`       | Lance Storybook                                      |
| `npm run build-storybook` | Produit le build statique de Storybook               |
| `npm run test`            | Exécute les tests une fois                           |
| `npm run test:watch`      | Lance Vitest en mode watch                           |
| `npm run lint`            | Vérifie le code avec ESLint                          |
| `npm run format`          | Formate les fichiers avec Prettier                   |
| `npm run format:check`    | Vérifie le formatage sans modifier les fichiers      |

## Structure

```text
.
├── .storybook/          # Configuration Storybook
├── src/
│   ├── App.tsx          # Écran de départ
│   ├── App.stories.tsx  # Story de l'écran de départ
│   ├── App.test.tsx     # Test de fumée
│   ├── index.css        # Imports Tailwind et styles globaux
│   └── stories/         # Exemples de composants Storybook
├── vite.config.ts       # Vite, Tailwind et intégration Storybook/Vitest
└── vitest.config.ts     # Configuration des tests unitaires
```

## Conventions

- Les composants et leurs stories vivent près les uns des autres.
- L'alias `@` pointe vers `src`.
- Les classes utilitaires Tailwind sont utilisées directement dans les composants.
- Toute modification peut être vérifiée avec `npm run lint`, `npm run format:check`, `npm run test` et `npm run build`.
