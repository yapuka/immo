---
name: react-components
description: 'Use when creating, reviewing, refactoring, or debugging React components in this TypeScript React project. Guides component boundaries, state ownership, accessibility, performance, stories, tests, and validation.'
---

# Composants React

Ce skill s'applique a la creation, la revue, la refactorisation et le debogage des composants React de ce projet.

## Contexte du projet

React 19, TypeScript, Vite, Tailwind CSS v4, Storybook, Vitest et Testing Library sont disponibles dans ce projet. Les conventions persistantes des composants sont definies dans `.github/instructions/react-components.instructions.md`.

Toujours lire le composant concerne, ses types, ses dependances et un exemple voisin avant de modifier l'architecture.

## Workflow obligatoire

1. Identifier le comportement utilisateur, le modele de donnees et les etats visibles a representer.
2. Decouper l'interface en composants selon les responsabilites, sans extraire chaque element JSX par principe.
3. Definir l'interface publique de chaque composant: props minimales, types explicites, contenu enfant et callbacks necessaires.
4. Construire d'abord le rendu statique avec les props et le modele de donnees.
5. Decider ou placer l'etat et expliciter le flux descendant et les callbacks remontants en respectant l'instruction persistante.
6. Representer les etats importants dans une story et ajouter un test centre sur le comportement lorsque le risque le justifie.
7. Executer la validation la plus ciblee possible, puis la validation complete si la modification touche une surface partagee.

## Stories et tests

Charger le skill `storybook-stories` lorsqu'une story manque pour l'etat visuel ou l'interaction a documenter. Il definit les conventions de meta, args, fixtures, `play` et de validation Storybook.

Charger le skill `unit-testing` lorsqu'un test de composant ou de comportement est necessaire. Il definit les queries Testing Library, les interactions `userEvent`, l'asynchronisme et les assertions a privilegier.

Une story sert a representer un etat stable et observable. Un test sert a proteger un comportement utilisateur. Ne pas utiliser l'un comme substitut automatique de l'autre.

## Validation

Utiliser la commande la plus proche du changement, puis elargir si necessaire:

- TypeScript et build: `npm run build`
- Lint: `npm run lint`
- Tests: `npm test`
- Stories: `npm run build-storybook`

Pour une modification purement visuelle, executer au minimum le build Storybook. Pour un comportement interactif, executer le test cible puis les tests associes. Ne pas ignorer une erreur de type, de lint ou d'accessibilite pour faire passer une story ou un test.

## A eviter

- Reprendre dans ce skill les conventions deja definies par `.github/instructions/react-components.instructions.md`.
- Stories qui ne representent aucun etat utilisateur utile.
- Tests bases sur les classes, les snapshots volumineux ou les details d'implementation.

## Sources

- Thinking in React: https://react.dev/learn/thinking-in-react
- React Best Practices: https://vercel.com/blog/introducing-react-best-practices

Ces sources servent de principes directeurs. Les conventions locales du projet, les contraintes du composant et les outils effectivement installes restent prioritaires.
