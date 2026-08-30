---
name: react-components
applyTo: 'src/**/*.ts,src/**/*.tsx'
description: 'Conventions persistantes pour les composants React TypeScript du projet.'
---

# Conventions React

- Utiliser TypeScript et définir une interface publique explicite avec des props minimales.
- Donner a chaque composant une responsabilite identifiable et testable; ne pas extraire chaque element JSX par principe.
- Ne conserver que l'etat qui change dans le temps. Calculer les donnees derivees depuis les props et l'etat existant.
- Ne pas utiliser `useEffect` pour calculer une valeur derivee ou synchroniser un etat qui peut etre exprime directement pendant le rendu.
- Placer l'etat dans le plus proche parent commun de tous les composants qui le lisent ou le modifient.
- Faire circuler les donnees vers le bas par les props et les changements vers le haut par des callbacks explicites.
- Preferer la composition ou un parent commun avant d'introduire un contexte pour eviter le prop drilling.
- Utiliser des cles stables liees aux donnees; eviter l'index lorsqu'une identite metier existe.
- Utiliser les elements HTML natifs avant d'ajouter ARIA ou des roles personnalises. Tout controle interactif doit avoir un nom accessible et un comportement clavier coherent.
- Representer explicitement les etats observables: chargement, vide, erreur, succes et desactivation.
- Ne pas ajouter de `data-testid`, d'ID technique ou de role artificiel uniquement pour faciliter un test.
- Ne pas ajouter `memo`, `useMemo` ou `useCallback` sans probleme couteux identifie et mesure.
- Demarrer les requetes independantes en parallele et eviter les imports lourds dans le chemin initial lorsqu'un chargement differe est acceptable.
- Ne pas supposer la presence du SSR, de Next.js ou des Server Components: le projet utilise actuellement Vite et un rendu cote client.
- Preserver les APIs publiques et les dependances existantes sauf si le comportement demande exige leur evolution.
