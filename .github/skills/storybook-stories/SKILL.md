---
name: storybook-stories
description: "Use when creating or improving Storybook stories for React components, especially when a story is needed before writing a play function or a component test. Defines user-facing states, args, fixtures, and interactive scenarios using the project's Storybook conventions."
---

# Stories Storybook

Ce skill s'applique a la creation et a l'amelioration des stories Storybook pour les composants React de ce projet.

## Objectif

Une story represente un etat utile et observable du composant. Elle doit pouvoir servir a la documentation, a la verification visuelle et, lorsque c'est pertinent, de point de depart pour un scenario interactif avec `play`.

## Contexte du projet

- Storybook: `@storybook/react-vite`.
- Types: utiliser `Meta` et `StoryObj` depuis `@storybook/react-vite`.
- Spies et mocks d'actions: utiliser `fn` depuis `storybook/test`.
- Stories: placer le fichier a cote du composant, avec le suffixe `.stories.tsx` ou `.stories.ts` selon la nature du composant.
- Validation: utiliser `npm run build-storybook`, puis les tests Storybook/Vitest pertinents si le projet en fournit.

Toujours lire le composant, ses types et une story voisine avant d'ecrire. Reutiliser les dependances deja presentes et respecter la structure locale.

## Workflow obligatoire

1. Identifier le composant, son interface publique et le comportement utilisateur a documenter ou tester.
2. Lire le composant, ses types, ses dependances et les stories voisines.
3. Lister les etats observables importants: etat initial, variantes de contenu, etats limites, chargement, erreur, succes, desactivation et etats apres interaction.
4. Creer ou completer la story la plus proche du composant, avec des noms d'etats explicites et des `args` minimaux.
5. Ajouter un `play` seulement lorsqu'une interaction fait partie du scenario; garder les assertions de comportement dans le test adapte.
6. Valider la story avec le build Storybook et, si elle est utilisee comme test, executer le test cible.

## Conventions de stories

- Utiliser `satisfies Meta<typeof Component>` pour typer la meta.
- Utiliser `type Story = StoryObj<typeof meta>` puis des exports nommes.
- Preferer les `args` aux duplications de JSX.
- Utiliser `argTypes` uniquement pour exposer un controle utile ou documenter une prop.
- Utiliser `fn()` pour les callbacks dont l'appel est observable, par exemple `onClick`, `onSubmit` ou `onLogin`.
- Garder les fixtures deterministes, lisibles et suffisamment realistes pour l'interface.
- Ajouter `tags: ['autodocs']` lorsque cela correspond aux conventions du fichier ou du projet.
- Choisir un titre coherent avec l'arborescence Storybook existante.
- Ne pas ajouter de test ID, de prop artificielle ou de mock interne uniquement pour faciliter une story.

## Stories interactives et `play`

- Une story interactive doit partir d'un etat stable et rendre l'action reproductible.
- Utiliser les queries accessibles et les interactions de `@storybook/test`, notamment `userEvent` et `expect`, lorsque le `play` verifie directement le scenario.
- Rechercher les elements avec `getByRole`, puis `getByLabelText` ou une autre query semantique adaptee.
- Attendre les interactions asynchrones.
- Ne pas utiliser de delai arbitraire, de selecteur CSS ou de detail d'implementation.
- Garder le `play` focalise sur le scenario represente par la story; ne pas y reproduire toute la suite de tests du composant.
- Lorsque les assertions doivent vivre dans un test Vitest de composant plutot que dans Storybook, fournir une story avec l'etat et les `args` necessaires, sans y ajouter un `play` superflu.

## Delegation depuis le skill de tests

Le skill `unit-testing` doit charger ce skill lorsque:

- le composant n'a pas encore de story exploitable pour le comportement a tester;
- une story existante ne represente pas l'etat initial ou la variante necessaire;
- un scenario Storybook avec `play` est le meilleur support pour le comportement demande.

Retour attendu vers le skill appelant:

- le chemin de la story creee ou modifiee;
- les noms des stories disponibles;
- les `args`, fixtures ou decorators indispensables;
- la presence ou non d'un `play` et le comportement qu'il prepare;
- la commande de validation executee et son resultat.

## A eviter

- Stories generiques qui ne representent aucun etat utilisateur utile.
- Une story par valeur de prop sans valeur documentaire ou comportementale.
- Fixtures aleatoires, appels reseau reels ou dependances externes non necessaires.
- Mocks qui masquent le comportement du composant.
- Assertions basees sur les classes CSS, la structure DOM ou des details internes.
- Un `play` utilise comme substitut a tous les tests unitaires.
