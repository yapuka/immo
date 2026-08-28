---
name: react-components
description: 'Use when creating, reviewing, refactoring, or debugging React components in this TypeScript React project. Guides component boundaries, state ownership, accessibility, performance, stories, tests, and validation.'
---

# Composants React

Ce skill s'applique a la creation, la revue, la refactorisation et le debogage des composants React de ce projet.

## Objectif

Construire des composants lisibles, accessibles, testables et adaptes au besoin utilisateur. Partir du modele de donnees et des etats visibles, puis rendre explicites les responsabilites, le flux de donnees et le contrat de chaque composant.

## Contexte du projet

- React 19 avec TypeScript.
- Vite pour le build et le rendu cote client; ne pas supposer la presence du SSR, de Next.js ou des Server Components.
- Tailwind CSS v4 est disponible pour les styles.
- Storybook est utilise pour documenter les etats visuels.
- Vitest et Testing Library sont utilises pour les tests de comportement.
- Alias `@` pointe vers `src`.

Toujours lire le composant concerne, ses types, ses dependances et un exemple voisin avant de modifier l'architecture. Reutiliser les conventions et les dependances deja presentes.

## Workflow obligatoire

1. Identifier le comportement utilisateur, le modele de donnees et les etats visibles a representer.
2. Decouper l'interface en composants selon les responsabilites, sans extraire chaque element JSX par principe.
3. Definir l'interface publique de chaque composant: props minimales, types explicites, contenu enfant et callbacks necessaires.
4. Construire d'abord le rendu statique avec les props et le modele de donnees.
5. Distinguer les props, l'etat local, les donnees externes et les donnees derivees.
6. Conserver uniquement l'etat minimal qui change dans le temps; calculer le reste a partir des props et de l'etat existant.
7. Placer l'etat dans le plus proche parent commun de tous les composants qui le lisent ou le modifient.
8. Faire circuler les donnees vers le bas par les props et les changements vers le haut par des callbacks explicites.
9. Representer les etats importants dans une story et ajouter un test centre sur le comportement lorsque le risque le justifie.
10. Executer la validation la plus ciblee possible, puis la validation complete si la modification touche une surface partagee.

## Conception des composants

- Une responsabilite de composant doit etre identifiable et testable.
- Ne pas stocker une valeur qui peut etre calculee de facon fiable depuis des props ou un autre etat.
- Ne pas utiliser `useEffect` pour calculer une valeur derivee ou synchroniser un etat qui peut etre exprime directement pendant le rendu.
- Preferer des composants controles lorsque l'etat doit etre possede par un parent; documenter les callbacks correspondants.
- Garder les composants presentational independants des appels reseau et des details d'infrastructure lorsque cela est possible.
- Eviter le prop drilling uniquement lorsqu'il rend le contrat illisible; avant d'introduire un contexte ou une abstraction, verifier qu'un parent commun ou une composition ne suffit pas.
- Ne pas extraire une abstraction generique avant d'avoir au moins un besoin clair de reutilisation.
- Utiliser des cles stables et liees aux donnees, jamais l'index lorsqu'une identite metier existe.
- Preserver les APIs publiques existantes sauf si leur evolution est necessaire au comportement demande.

## Accessibilite et interface

- Utiliser les elements HTML natifs avant d'ajouter ARIA ou des roles personnalises.
- Les boutons, liens et champs doivent avoir un nom accessible et un comportement clavier coherent.
- Associer les labels aux champs et utiliser des messages d'erreur lisibles par les technologies d'assistance.
- Representer explicitement les etats de chargement, vide, erreur, succes et desactivation lorsqu'ils sont observables.
- Ne pas ajouter de `data-testid`, d'ID technique ou de role artificiel uniquement pour faciliter un test.
- Tester l'interface par ses roles, labels et textes visibles plutot que par ses classes CSS ou sa structure DOM.

## Performance

Appliquer les regles dans cet ordre: supprimer les waterfalls, reduire le JavaScript envoye au client, puis optimiser les re-renders et le travail de rendu.

- Demarrer les requetes independantes en parallele au lieu de les enchainer.
- Eviter d'importer un module lourd dans le chemin initial lorsqu'un chargement differe est acceptable.
- Garder les imports precis et deplacer les dependances cote serveur uniquement si une architecture SSR est introduite explicitement.
- Eviter les re-renders provoques par un etat ou un contexte trop haut dans l'arbre.
- Ne pas ajouter `useMemo`, `useCallback` ou `memo` par reflexe; les introduire seulement apres avoir identifie un calcul ou un rendu couteux et mesurable.
- Utiliser une initialisation paresseuse pour un calcul couteux qui ne doit etre fait qu'a la creation de l'etat, par exemple `useState(() => initialValue())`.
- Ne pas optimiser une boucle ou un hook avant d'avoir verifie le bundle, les waterfalls et le chemin critique.

Les recommandations SSR, streaming, Server Components et cache serveur sont conditionnelles: elles ne s'appliquent que si la pile du projet evolue vers une architecture qui les supporte.

## Stories et tests

Charger le skill `storybook-stories` lorsqu'une story manque pour l'etat visuel ou l'interaction a documenter. Il definit les conventions de meta, args, fixtures, `play` et de validation Storybook.

Charger le skill `unit-testing` lorsqu'un test de composant ou de comportement est necessaire. Il definit les queries Testing Library, les interactions `userEvent`, l'asynchronisme et les assertions a privilegier.

Pour chaque nouveau composant, evaluer au minimum:

- son etat initial et son contenu principal;
- ses variantes de contenu et ses limites;
- ses etats chargement, vide, erreur, succes ou desactive lorsqu'ils existent;
- les actions utilisateur qui modifient un resultat visible;
- son comportement clavier et son nom accessible lorsqu'il est interactif.

Une story sert a representer un etat stable et observable. Un test sert a proteger un comportement utilisateur. Ne pas utiliser l'un comme substitut automatique de l'autre.

## Validation

Utiliser la commande la plus proche du changement, puis elargir si necessaire:

- TypeScript et build: `npm run build`
- Lint: `npm run lint`
- Tests: `npm test`
- Stories: `npm run build-storybook`

Pour une modification purement visuelle, executer au minimum le build Storybook. Pour un comportement interactif, executer le test cible puis les tests associes. Ne pas ignorer une erreur de type, de lint ou d'accessibilite pour faire passer une story ou un test.

## A eviter

- Composants monolithiques qui melangent donnees, orchestration, rendu et effets sans raison.
- Etat duplique ou donnees derivees synchronisees par plusieurs `useEffect`.
- Abstractions generiques, contexte global ou gestionnaire d'etat ajoute sans besoin concret.
- Optimisations prematurees et multiplication de `memo`, `useMemo` ou `useCallback`.
- Requetes sequentielles alors qu'elles sont independantes.
- Stories qui ne representent aucun etat utilisateur utile.
- Tests bases sur les classes, les snapshots volumineux ou les details d'implementation.
- Supposition qu'une fonctionnalite SSR existe dans le projet actuel.

## Sources

- Thinking in React: https://react.dev/learn/thinking-in-react
- React Best Practices: https://vercel.com/blog/introducing-react-best-practices

Ces sources servent de principes directeurs. Les conventions locales du projet, les contraintes du composant et les outils effectivement installes restent prioritaires.
