# Immo

Application frontend de démarrage pour un projet immobilier, construite sur React 19, TypeScript et Vite. Le but de cette base est de fournir un socle rapide, propre et maintenable pour concevoir une interface de gestion immobilière avec un workflow de qualité dès le départ.

## Vision du projet

Ce projet est conçu pour accélérer le développement d'un produit front de type immobilier en combinant :

- une base applicative moderne et légère ;
- une architecture orientée composants ;
- une documentation visuelle avec Storybook ;
- une couverture de tests automatisés avec Vitest ;
- des conventions de qualité partagées via des skills Copilot.

L'objectif est de permettre à l'équipe de livrer des interfaces fiables, lisibles et évolutives sans casser la cohérence produit au fil des itérations.

## Stack technique

- React 19
- TypeScript
- Vite
- Tailwind CSS v4
- Storybook 10
- Vitest + Testing Library + jsdom
- ESLint + Prettier

## Prérequis

- Node.js 22 ou version compatible avec `import.meta.dirname`
- npm

## Installation

```bash
npm install
```

Pour publier Storybook avec Chromatic, créez un fichier `.env.local` à partir de `.env.example` et ajoutez votre token de projet.

## Démarrage rapide

### Lancer l'application

```bash
npm run dev
```

L'application est ensuite disponible sur :

```text
http://localhost:5173
```

### Lancer Storybook

```bash
npm run storybook
```

Storybook est accessible sur :

```text
http://localhost:6006
```

## Scripts

| Commande | Description |
| --- | --- |
| `npm run dev` | Lance le serveur Vite en mode développement |
| `npm run build` | Vérifie TypeScript et produit le build de production |
| `npm run preview` | Sert le build de production localement |
| `npm run storybook` | Ouvre Storybook |
| `npm run build-storybook` | Génère le build statique Storybook |
| `npm run chromatic` | Publie Storybook sur Chromatic |
| `npm run test` | Exécute les tests une fois |
| `npm run test:watch` | Lance Vitest en mode watch |
| `npm run lint` | Vérifie le code avec ESLint |
| `npm run format` | Formate les fichiers avec Prettier |
| `npm run format:check` | Vérifie le formatage sans modifier les fichiers |

## Structure du projet

```text
.
├── .storybook/              # Configuration Storybook
├── .github/
│   ├── instructions/        # Règles persistantes applicables aux fichiers TS/TSX
│   └── skills/              # Skills Copilot projetés sur les bonnes pratiques produit
├── src/
│   ├── App.tsx              # Page de départ
│   ├── App.stories.tsx      # Story de démarrage
│   ├── App.test.tsx         # Test de base
│   ├── index.css            # Styles globaux
│   └── stories/             # Exemples de composants et de scénarios UI
├── public/                  # Assets publics
├── vite.config.ts           # Configuration Vite
├── vitest.config.ts         # Configuration des tests
├── eslint.config.js         # Règles de linting
├── package.json             # Scripts et dépendances
├── tsconfig.json            # Configuration TypeScript
└── README.md                # Documentation du projet
```

## Standards de qualité produit

La base du projet impose des conventions qui vont au-delà du simple fonctionnement technique :

- les composants sont pensés pour être compréhensibles et réutilisables ;
- les états utilisateur sont explicitement représentés ;
- les interactions sont testées comme des comportements réels ;
- la documentation visuelle est utilisée pour valider les états before/after ;
- l'accessibilité est traitée comme un critère de produit, pas comme une option.

## Skills GitHub Copilot

Le projet met en place des skills dans le dossier `.github/skills` pour standardiser les bonnes pratiques dans les domaines les plus sensibles du développement frontend : composants, stories et tests.

### 1. `react-components`

Le skill React définit les règles de conception des composants :

- interface publique explicite et minimale ;
- état correctement hiérarchisé ;
- composition avant complexification ;
- accessibilité native en priorité ;
- gestion claire des états de chargement, succès, erreur et vide ;
- performance mesurée avant d'ajouter des optimisations.

Ce skill est complété par l'instruction persistante dans `.github/instructions/react-components.instructions.md`.

### 2. `storybook-stories`

Le skill Storybook standardise la manière de documenter les états du produit :

- stories centrées sur des états utiles et observables ;
- usage de `Meta` et `StoryObj` ;
- arguments et fixtures lisibles ;
- `play` uniquement pour les scénarios réellement utiles ;
- sélecteurs accessibles pour des validations visuelles fiables.

L’enjeu est d’avoir une documentation vivante du comportement interface, utile aussi bien au design qu’au développement.

### 3. `unit-testing`

Le skill tests impose une méthode de validation orientée utilisateur :

- tests basés sur des comportements observables ;
- préférences pour `getByRole`, `getByLabelText` et `userEvent` ;
- gestion explicite de l’asynchronisme ;
- assertions sémantiques et faciles à lire ;
- validation centrée sur le résultat plutôt que sur l’implémentation technique.

## Workflow recommandé

Pour toute évolution d’interface :

1. concevoir le comportement et les états utilisateur ;
2. implémenter le composant avec les conventions React ;
3. documenter les états clés dans Storybook ;
4. couvrir les scénarios importants avec des tests ;
5. valider via la commande la plus ciblée, puis élargir si nécessaire.

Cette boucle permet de garder un équilibre entre vitesse de livraison et qualité produit.

## Conventions de contribution

- les composants et leurs stories sont proches les uns des autres ;
- l’alias `@` pointe vers `src` ;
- les classes Tailwind sont utilisées directement dans les composants ;
- les changements sont validés par lint, tests et build selon le contexte ;
- la qualité ne doit pas être sacrifiée au profit d’une livraison rapide.

## CI et déploiement

En CI, configurez `CHROMATIC_PROJECT_TOKEN` comme secret du fournisseur de CI et lancez :

```bash
npm run chromatic
```

Cela permet de publier automatiquement la documentation visuelle du produit via Storybook.
