# Atelier 3 – Dimensions du rapport, bandes et liste sur deux colonnes

**Durée indicative : 45 min.**

## Objectifs pédagogiques

- Régler les dimensions et marges du rapport.
- Comprendre le rôle de chaque bande : Title, Page Header, Column Header, Detail, Column Footer, Page Footer.
- Mettre en place un rapport à deux colonnes et observer la pagination.

## Résultat attendu

`Atelier3_ResultatAttendu.pdf` (2 pages) : l'annuaire des noms de la table `ADDRESS` sur deux colonnes. Le logo `logo-plb-footer.gif` est fourni dans ce dossier : copiez-le dans votre projet à côté du rapport.

## Cahier des charges

1. Nouveau rapport `Atelier3_DeuxColonnes.jrxml`, A4 portrait, marges 20, requête `SELECT FIRSTNAME, LASTNAME FROM ADDRESS ORDER BY LASTNAME, FIRSTNAME` sur Sample DB.
2. Le rapport est sur **deux colonnes** : dans les propriétés du rapport, *Columns* = 2, *Space* (espacement) = 15. Vérifiez la largeur de colonne calculée (270).
3. **Title** : « Annuaire des contacts » (20, gras, centré) et un sous-titre en italique « Liste des noms sur deux colonnes ». La bande Title s'étend sur toute la largeur de la page, pas sur une colonne.
4. **Page Header** : à gauche « Formation JasperStudio – base d'exemple » (9 pt), à droite la date du jour au format long (`EEEE d MMMM yyyy`), une ligne sous le tout.
5. **Column Header** : libellés Nom et Prénom en gras avec une ligne de séparation. Cette bande a la largeur d'une colonne et se répète pour chaque colonne.
6. **Detail** : hauteur 30, les deux champs alignés verticalement au milieu, une fine ligne grise (0,5 pt, couleur `#C0C0C0`) en bas de la bande.
7. **Column Footer** : une ligne noire.
8. **Page Footer** : à gauche la date et l'heure de génération (`dd/MM/yyyy 'à' HH:mm`, 8 pt), au centre le logo, à droite « Page n ».
9. Aperçu PDF : 2 pages, les 50 noms se répartissent de haut en bas dans la colonne de gauche puis dans celle de droite.

## Indications

- Les propriétés du rapport (page, colonnes) s'affichent en cliquant sur le nœud racine de l'Outline ou dans le vide du Design.
- La largeur d'une bande « colonne » (Column Header, Detail, Column Footer) est la largeur de colonne ; les autres bandes ont la largeur de page utile (555).
- La date du jour : Palette → Composite Elements → *Current Date*, puis le *Pattern* dans Properties. Le pattern accepte du texte entre apostrophes : `dd/MM/yyyy 'à' HH:mm`.
- L'image : Palette → *Image*, choisissez le fichier du projet ; l'expression devient `"logo-plb-footer.gif"`. Mode *Retain Shape*.
- Une ligne se dessine avec l'élément *Line* : height 1, épaisseur et couleur dans Properties → *Line* (Pen).

## Vérifications

- Passez *Columns* à 3 puis revenez à 2 : que devient la largeur des bandes ?
- Changez l'ordre de remplissage (*Print Order* : Vertical / Horizontal) et observez.
- Quelle bande faut-il utiliser pour un texte répété en haut de **chaque page** ? de **chaque colonne** ?

## Pour aller plus loin

- Fiche complète de l'ancien TP `TPs/3_TwoColumns/3_TwoColumns.pdf` : police Gentium, contacts de Paris en rouge, lien hypertexte sur le logo, styles externalisés. Ces points sont traités aux ateliers 7 et 8 ; ce rapport sera réutilisé.
