# Atelier 8 – Charte graphique : styles et bibliothèque de styles

**Durée indicative : 1 h.**

## Objectifs pédagogiques

- Définir des styles, l'héritage et le style par défaut.
- Créer des styles conditionnels et formater nombres et dates avec des patterns.
- Externaliser la charte dans un fichier `.jrtx` réutilisable, et la livrer avec les rapports.

## Résultat attendu

`Atelier8_ResultatAttendu.pdf` (2 pages) : le tableau de l'atelier 5 entièrement piloté par une bibliothèque de styles `charte.jrtx` que vous allez créer.

## Point de départ

Copiez le rapport de l'atelier 5 sous le nom `Atelier8_Charte.jrxml`.

## Cahier des charges

1. **Styles** (Outline → Styles → *Create Style*) :

| Style | Rôle | Propriétés |
|---|---|---|
| Base | style par défaut du rapport | DejaVu Sans 9 pt, noir |
| Titre | titre du rapport | 18 pt, gras, `#1F3A5F`, centré verticalement |
| SousTitre | sous-titre et date | 10 pt, italique, `#444444` |
| Entete | libellés de colonnes | gras, fond `#D9D9D9` opaque, bordure 0,75 pt, padding 2 / 4 |
| Donnees | cellules | bordure 0,5 pt `#808080`, padding 2 / 4 |
| DonneesNombre | cellules numériques | hérite de Donnees, aligné à droite |
| Ligne | fond de la ligne | conditionnel : une ligne sur deux fond `#F2F2F2` |
| MontantTotal | colonne Total | hérite de DonneesNombre, gras ; conditionnel : rouge `#C00000` si le total ≥ 500 |
| Total | total général | gras, aligné à droite, filet haut 1 pt |
| PiedDePage | pied de page | 8 pt, `#555555` |

2. **Appliquer** les styles à tous les éléments (sélection multiple → propriété *Style*) et supprimer les mises en forme directes devenues inutiles : le rectangle du titre disparaît, remplacé par une ligne bleue de 1,5 pt sous le titre.
3. **Formats** : quantités `#,##0`, prix et totaux `#,##0.00` avec l'unité « € » portée par l'en-tête de colonne.
4. **Total général** dans la bande Summary : une variable `TOTAL_GENERAL` (somme de `$F{TOTAL}`) affichée avec le style Total.
5. **Externaliser** : créez un *Style Template* `charte.jrtx` (File → New → Other… → Jaspersoft Studio → Style Template, ou clic droit sur un style → *Export to a style template*), déplacez-y tous les styles **sauf Base**, puis référencez la bibliothèque dans le rapport (Outline → clic droit sur *Styles* → *Add Style Template* / *Template Reference*). Le rapport doit se prévisualiser à l'identique.
6. Créez un second rapport vide, référencez la même bibliothèque et vérifiez que ses styles sont disponibles.

## Indications

- Le style par défaut reste **dans le rapport** : c'est lui qui porte la police de base ; les styles de la bibliothèque n'ont pas besoin de parent explicite.
- Les **conditions des styles d'une bibliothèque** doivent être une simple référence (`$V{...}`, `$F{...}`), pas un calcul. Créez donc dans le rapport deux variables booléennes : `LIGNE_PAIRE` = `$V{REPORT_COUNT} % 2 == 0` et `MONTANT_ELEVE` = `$F{TOTAL}.doubleValue() >= 500`, et utilisez-les comme conditions.
- Un style conditionnel se crée par clic droit sur le style → *Create Conditional Style* ; la condition est une expression booléenne.
- L'onglet *Inheritance* de Properties montre d'où vient chaque propriété d'un élément.
- Le fichier `.jrtx` a son propre éditeur dans Studio.

## Vérifications

- Changez la police de `Base` : tout le rapport change.
- Modifiez la couleur d'`Entete` dans le `.jrtx` : le rapport suit sans être modifié.
- Onglet Source du rapport : la référence est la ligne `<template><![CDATA["charte.jrtx"]]></template>`.

## Pour aller plus loin (livraison)

- Ajoutez `charte.jrtx` au jar de ressources de l'atelier 6 : un seul jar livre images, filigrane et charte pour tous les rapports.
- Créez un style `Alerte` (rouge, gras) et appliquez-le par condition aux quantités supérieures à 20.
