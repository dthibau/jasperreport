# Atelier 9 – Page de garde et contrôle de la pagination

**Durée indicative : 45 min.**

## Objectifs pédagogiques

- Construire une page de garde avec la bande Title.
- Comprendre l'espace disponible par page et la répétition des en-têtes.
- Éviter les coupures d'enregistrement, placer le récapitulatif sur une nouvelle page, gérer le dernier pied de page.

## Résultat attendu

`Atelier9_ResultatAttendu.pdf` (6 pages, pays = Germany) : page de garde, 4 pages de commandes à 40 lignes, une page de récapitulatif. Image fournie : `logo.png`.

## Cahier des charges

1. Rapport `Atelier9_Pagination.jrxml`, A4 portrait, police 9 pt, paramètre `P_PAYS` (défaut `"Germany"`), requête :

```
SELECT O.ORDERID, O.ORDERDATE, O.SHIPNAME, O.SHIPADDRESS, O.SHIPCITY, O.FREIGHT
FROM ORDERS O
WHERE O.SHIPCOUNTRY = $P{P_PAYS}
ORDER BY O.SHIPCITY, O.ORDERDATE
```

2. **Page de garde** : la bande Title occupe une page entière (hauteur 700, propriété du rapport **Title on a new page**). Elle contient : le logo centré, le titre « Registre des commandes » (28, gras), « Pays de livraison : *pays* » (16), une ligne, la date et l'heure de génération en toutes lettres, puis une ligne « *n* commandes – *p* pages » (les deux nombres viennent de variables prédéfinies et exigent *Evaluation Time* = Report), et une mention en bas de page en italique 8 pt.
3. **Page Header** (30) : « Registre des commandes – *pays* » en 12 gras à gauche, le logo réduit à droite, une ligne.
4. **Column Header** (22) : N°, Date, Ville, Client, Adresse, Port (€), style d'en-tête gris avec bordure. Il se répète automatiquement sur chaque page.
5. **Detail** (18) : les six champs avec bordures 0,5 pt ; Client et Adresse en *Text Adjust* = Stretch Height ; les autres cellules suivent (*Stretch Type* = Element Group Height) ; **Split Type = Prevent** pour qu'une commande ne soit jamais coupée entre deux pages.
6. **Page Footer** (24) : date d'édition à gauche, « Page x sur y » à droite.
7. **Last Page Footer** (40) : remplace le pied de page sur la dernière page, avec une mention « Fin du document… » et la numérotation.
8. **Summary** sur une **nouvelle page** (propriété *Summary on a new page*, avec en-tête et pied de page : *Summary with page header and footer*) : titre « Récapitulatif », nombre de commandes (`$V{REPORT_COUNT}`) et total des frais de port (variable `TOTAL_PORT`, somme de `FREIGHT`, pattern `'Total des frais de port : ' #,##0.00 '€'`).
9. **No Data** : « Aucune commande pour ce pays », avec *When No Data Type* = No Data Section.
10. Vérifiez le nombre de lignes par page (40) et le nombre de pages (6). Testez avec `USA` (122 commandes) et `Suisse` (aucune).

## Indications

- Hauteur disponible pour le détail = 842 − 20 − 20 (marges) − 30 (Page Header) − 22 (Column Header) − 24 (Page Footer) = 726 px ; à 18 px par ligne, 40 lignes par page.
- Les propriétés *Title on a new page*, *Summary on a new page*, *Summary with page header and footer*, *When No Data Type* sont dans Properties du rapport (nœud racine de l'Outline).
- Les bandes Last Page Footer et No Data s'ajoutent par clic droit sur le rapport dans l'Outline → *Add band*.
- Un champ avec *Evaluation Time* = Report est calculé à la fin : c'est le cas du total de pages et du nombre total de commandes sur la page de garde.

## Vérifications

- Passez *Split Type* du Detail à Stretch et cherchez, avec `USA`, une adresse coupée entre deux pages.
- Retirez *Title on a new page* : la première page de données commence sous le titre. Placez alors un élément **Break** (Palette) en bas de la bande Title : même effet qu'avant.
- Propriété du rapport *Ignore pagination* : tout le rapport tient sur une seule page très haute, utile pour l'export Excel. Remettez la valeur par défaut.

## Pour aller plus loin

- Ajoutez un paramètre `P_LIGNES_PAR_PAGE` et un élément Break conditionnel `$V{REPORT_COUNT} % $P{P_LIGNES_PAR_PAGE} == 0` en bas du Detail.
- Sur la page de garde, affichez la période couverte : `MIN` et `MAX` de `ORDERDATE` via deux variables (calcul Lowest / Highest, *Evaluation Time* Report).
