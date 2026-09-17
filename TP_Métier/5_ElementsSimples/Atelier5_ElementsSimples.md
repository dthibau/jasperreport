# Atelier 5 – Les éléments simples : mise en forme de type tableau

**Durée indicative : 1 h.**

## Objectifs pédagogiques

- Utiliser texte statique, champ texte, ligne, rectangle, ellipse et cadre.
- Régler position, taille, alignements, bordures, marges internes, patterns.
- Maîtriser les modes de redimensionnement : *Text Adjust* et *Stretch Type*.

## Résultat attendu

`Atelier5_ResultatAttendu.pdf` (2 pages) : le détail des lignes de commande des documents 1 à 5, présenté en tableau.

## Cahier des charges

1. Rapport `Atelier5_ElementsSimples.jrxml`, A4 portrait, police 9 pt, requête :

```
SELECT D.ID AS DOCUMENT, A.FIRSTNAME || ' ' || A.LASTNAME AS CLIENT,
       PO.POSITIONNO AS LIGNE, P.NAME AS PRODUIT, PO.QUANTITY AS QUANTITE,
       PO.PRICE AS PRIX, PO.QUANTITY * PO.PRICE AS TOTAL
FROM POSITIONS PO
JOIN DOCUMENT D ON D.ID = PO.DOCUMENTID
JOIN PRODUCT P ON P.ID = PO.PRODUCTID
JOIN ADDRESS A ON A.ID = D.ADDRESSID
WHERE D.ID <= 5
ORDER BY D.ID, PO.POSITIONNO
```

2. **Title** : un **rectangle** à coins arrondis (fond `#E8EEF5`, bordure `#1F3A5F`) contenant le titre « Détail des commandes clients » (18, gras, couleur `#1F3A5F`) et un sous-titre en italique ; à droite sous le rectangle, la date d'édition au format `'Édité le' dd/MM/yyyy`.
3. **Column Header** : un **cadre** (Frame) gris `#D9D9D9` avec bordure, contenant les libellés Doc., Client, Ligne, Produit, Qté, Prix unit., Total. Largeurs : 45, 120, 40, 170, 50, 60, 70. Les libellés numériques sont alignés à droite.
4. **Detail** (hauteur 18) : un cadre contenant les sept champs texte, chacun avec une bordure droite grise de 0,5 pt et une marge interne de 4 px ; Qté au format `#,##0`, Prix et Total au format `#,##0.00 €`, Total en gras ; le nom du produit peut s'étirer sur deux lignes (*Text Adjust* = Stretch Height) et les autres cellules suivent la hauteur du cadre (*Stretch Type* = Container Height).
5. Une **ellipse** verte (8 × 8, `#2E8B57`) apparaît à gauche de la quantité quand celle-ci est **supérieure ou égale à 10**.
6. **Column Footer** : la légende de la pastille verte. **Page Footer** : une ligne bleue et le numéro de page.
7. La bande Detail ne doit jamais être coupée entre deux pages (*Split Type* = Prevent).

## Indications

- Les bordures et marges internes se règlent dans Properties → onglet **Borders** : choisissez le côté, l'épaisseur, la couleur ; *Padding* pour la marge interne.
- Les outils d'alignement de la barre d'outils (aligner à gauche, même largeur, même hauteur) évitent la saisie de toutes les coordonnées.
- L'affichage conditionnel d'un élément : Properties → Appearance → **Print When Expression** : `$F{QUANTITE} >= 10`.
- Rectangle : propriété *Radius* pour les coins arrondis ; couleur de fond dans Appearance → *Backcolor* avec *Mode* = Opaque.
- Placer les éléments **dans** un cadre : glissez-les à l'intérieur du cadre ; dans l'Outline ils apparaissent sous le nœud du Frame.

## Vérifications

- Retirez *Stretch Height* du produit : que devient un nom long ?
- Mettez *Stretch Type* = No Stretch sur le champ Total et prévisualisez une ligne qui s'étire : la bordure droite ne suit plus.
- Que se passe-t-il si un élément dépasse de la bande ? (vue Problems)

## Pour aller plus loin

- Ajoutez une ligne alternée grise une ligne sur deux sans utiliser de style : expression `$V{REPORT_COUNT} % 2 == 0` en Print When Expression d'un rectangle de fond placé derrière le cadre (ordre Z : clic droit → *Send to back*).
- Remplacez la date fixe par `"Édité le " + DATEFORMAT(TODAY(), "dd/MM/yyyy")` dans un champ texte de classe String.
