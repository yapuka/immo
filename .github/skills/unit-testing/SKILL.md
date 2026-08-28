---
name: unit-testing
description: "Use when creating, reviewing, debugging, or improving unit and component tests for this TypeScript React project with Vitest and Testing Library. Produces user-focused tests, applies Testing Library best practices, and validates the result."
---

# Tests unitaires et tests de composants

Ce skill s'applique aux tests unitaires, aux tests de composants React et aux corrections de tests existants dans ce projet.

## Contexte du projet

- Runner: Vitest, via `npm test`.
- Environnement DOM: `jsdom`, configure dans `vitest.config.ts`.
- Composants: React et TypeScript.
- Assertions DOM: `@testing-library/jest-dom/vitest`, deja charge dans `src/test-setup.ts`.
- Interactions: preferer `@testing-library/user-event`, deja disponible dans le projet.
- Alias: `@` pointe vers `src`.

Toujours verifier la configuration et les conventions existantes avant d'ecrire un test. Ne pas ajouter de dependance lorsqu'une dependance deja installee suffit.

## Workflow obligatoire

1. Identifier le comportement observable a proteger et le fichier qui le controle.
2. Lire le composant, ses types, ses dependances et les tests voisins avant de choisir les cas de test.
3. Decrire les scenarios utilisateur importants: etat initial, action, resultat visible, erreurs et transitions asynchrones.
4. Ecrire le test au niveau de l'interface publique du composant ou de la fonction. Ne pas tester les details d'implementation internes.
5. Executer le test cible, puis le lint et la suite complete si la modification peut affecter d'autres tests.
6. Si un test echoue, corriger le comportement ou le test selon la cause reelle. Ne pas affaiblir les assertions pour faire passer la suite.

## Principes de qualite

- Un test doit ressembler autant que possible a la facon dont un utilisateur interagit avec l'interface.
- Chaque test doit avoir un nom qui decrit un comportement, pas une implementation.
- Preferer des tests deterministes, lisibles et independants.
- Tester le resultat observable plutot que l'etat React, les hooks, les fonctions privees ou la structure interne.
- Couvrir les branches qui changent le comportement: chargement, succes, erreur, absence de donnees, validation, desactivation et disparition.
- Garder un test focalise. Plusieurs assertions sont acceptables lorsqu'elles decrivent un meme resultat observable, mais eviter les tests qui couvrent plusieurs comportements sans rapport.
- Utiliser `screen` pour les recherches dans le DOM.
- Utiliser les assertions de `@testing-library/jest-dom` quand elles expriment mieux l'intention, par exemple `toBeDisabled`, `toBeVisible`, `toHaveValue` ou `toBeInTheDocument`.
- Ne pas ajouter de `cleanup` manuel: Testing Library et Vitest gerent le nettoyage configure par le projet.

## Choix des queries

Suivre cet ordre de priorite, en partant de ce que l'utilisateur voit ou utilise:

1. `getByRole`, avec l'option `name` lorsque c'est pertinent. C'est le choix par defaut pour les elements accessibles.
2. `getByLabelText` pour les champs de formulaire.
3. `getByPlaceholderText` uniquement lorsqu'il n'existe pas de label exploitable; un placeholder ne remplace pas un label accessible.
4. `getByText` pour le contenu textuel visible non interactif.
5. `getByDisplayValue` pour la valeur courante d'un champ.
6. `getByAltText` pour les elements qui exposent un texte alternatif.
7. `getByTitle` seulement si le titre est vraiment l'interface utilisee.
8. `getByTestId` en dernier recours, notamment pour du contenu dynamique ou lorsqu'aucune interface semantique ne convient.

Ne pas utiliser `container.querySelector`, les classes CSS ou les IDs techniques pour trouver un element si une query semantique est possible. Une query manuelle peut rester justifiee pour un cas exceptionnel, mais cette intention doit etre claire et stable.

Ne pas ajouter artificiellement `aria-*` ou `role` pour rendre un test possible. Corriger le HTML semantique; n'utiliser ARIA que lorsque le composant non natif en a reellement besoin et respecter les pratiques WAI-ARIA.

Pour le texte, utiliser le texte de l'interface et la locale par defaut du projet. Les changements de libelle doivent pouvoir faire echouer les tests lorsqu'ils modifient le comportement ou le contrat visible.

## Asynchronisme et disparition

- Les APIs asynchrones retournent une Promise: toujours utiliser `await`.
- Pour attendre l'apparition d'un element, preferer directement la query `findBy...` appropriee.
- Utiliser `waitFor` lorsqu'il faut attendre une assertion ou un effet asynchrone qui n'est pas une simple recherche d'element.
- Le callback de `waitFor` doit contenir une assertion qui echoue tant que l'etat attendu n'est pas atteint. Ne jamais utiliser un callback vide.
- Garder une seule assertion dans un callback `waitFor` lorsque cela permet d'echouer rapidement et de rendre l'intention precise.
- Ne placer aucun effet de bord dans `waitFor`: pas de clic, saisie, dispatch, mutation, mock ou snapshot. Les actions doivent etre faites avant l'attente.
- Pour attendre la disparition d'un element deja present, preferer `waitForElementToBeRemoved(() => screen.queryByRole(...))` ou la query adaptee.
- Pour verifier qu'un element n'existe pas, utiliser une query qui ne leve pas d'erreur, puis une assertion explicite comme `not.toBeInTheDocument()`.
- Ne pas utiliser une query d'absence pour verifier qu'un element existe; cela masque les erreurs de diagnostic.
- Ne pas ajouter de delais arbitraires (`setTimeout`, `sleep`) pour synchroniser un test.

## Interactions

- Preferer `userEvent.setup()` et les actions `user` (`click`, `type`, `clear`, `tab`, etc.) a `fireEvent`, car elles reproduisent mieux les interactions reelles.
- N'utiliser `fireEvent` que lorsqu'il faut declencher precisement un evenement qui n'a pas d'equivalent user-event.
- Ne pas entourer `render` ou les interactions standard de `act`: Testing Library les encapsule deja. Une alerte `act` doit conduire a rechercher une mise a jour non attendue et a l'attendre correctement.
- Attendre (`await`) les actions asynchrones de `userEvent`.

## Assertions et mocks

- Rendre les assertions explicites: une query seule peut echouer, mais elle ne communique pas toujours clairement l'intention du test.
- Preferer une assertion semantique ou `jest-dom` a une inspection brute de proprietes DOM lorsque c'est possible.
- Mocker uniquement les systemes externes ou les frontieres necessaires, pas les details internes du composant.
- Verifier les appels de mock seulement lorsque cet appel est un comportement important; l'interface resultante reste prioritaire.
- Ne pas utiliser de snapshots volumineux comme substitut a des assertions comportementales. Un snapshot peut completer un test, jamais remplacer les scenarios importants.
- Restaurer les mocks et timers selon les conventions Vitest du projet afin que les tests restent independants.

## A eviter

- Tests qui reimplementent la logique de production dans leurs attentes.
- Tests qui passent uniquement parce qu'ils attendent un delai arbitraire.
- Selecteurs bases sur les classes, la structure DOM ou des test IDs sans justification.
- Assertions sur `element.disabled`, `element.value` ou d'autres proprietes brutes lorsqu'une assertion `jest-dom` existe.
- Effets de bord dans `waitFor`.
- Plusieurs comportements sans rapport dans un seul test.
- `cleanup` manuel, `act` superflu et wrappers herites d'Enzyme.
- Utilisation systematique de `queryBy...` pour les assertions de presence.
- Modification du composant uniquement pour satisfaire un selecteur de test, sauf si cela corrige reellement son accessibilite.

## Format de sortie attendu

Quand une demande concerne des tests:

1. Expliquer en une phrase le comportement couvert.
2. Ajouter ou modifier le test le plus proche du code concerne, en respectant les conventions locales.
3. Signaler les cas non couverts uniquement s'ils sont pertinents.
4. Executer la validation la plus ciblee possible, puis `npm test` si necessaire.
5. Resumer les fichiers modifies et le resultat de la validation.

## Sources

- Testing Library, Queries: https://testing-library.com/docs/queries/about/
- Testing Library, Appearance and Disappearance: https://testing-library.com/docs/guide-disappearance/
- Kent C. Dodds, Common mistakes with React Testing Library: https://kentcdodds.com/blog/common-mistakes-with-react-testing-library

Les sources servent de reference pour les priorites et les anti-patterns. Le skill doit toujours privilegier les versions et conventions effectivement presentes dans le projet.
