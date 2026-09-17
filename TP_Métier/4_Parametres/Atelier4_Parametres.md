# Atelier 4 – Paramètres de requête

**Durée indicative : 45 min.**

## Objectifs pédagogiques

- Déclarer un paramètre : nom, classe, valeur par défaut, invite (*Is For Prompting*).
- L'utiliser dans la clause `WHERE` de la requête et dans le rapport.
- Saisir les paramètres dans l'aperçu ; prévoir le cas « aucune donnée ».

## Résultat attendu

`Atelier4_ResultatAttendu.pdf` (2 pages, pays = France). Version facultative avec le paramètre année : `Atelier4_bis_ResultatAttendu.pdf` (pays = France, année 2017).

## Cahier des charges (4.1)

1. Rapport `Atelier4_Parametres.jrxml`, A4 portrait, police 9 pt.
2. Un paramètre **P_PAYS** de classe `java.lang.String`, valeur par défaut `"France"`, invite activée, description « Pays de livraison (valeur exacte de la colonne SHIPCOUNTRY) ».
3. La requête liste les commandes **expédiées** du pays choisi, avec le nom de l'employé (jointure sur la table `ADDRESS` par `EMPLOYEEID`) :

```
SELECT O.ORDERID, O.SHIPCITY, O.SHIPNAME, O.SHIPPEDDATE,
       A.FIRSTNAME || ' ' || A.LASTNAME AS EMPLOYE
FROM ORDERS O
JOIN ADDRESS A ON A.ID = O.EMPLOYEEID
WHERE O.SHIPCOUNTRY = $P{P_PAYS}
  AND O.SHIPPEDDATE IS NOT NULL
ORDER BY O.SHIPCITY, EMPLOYE, O.SHIPPEDDATE
```

4. Le titre est dynamique : « Commandes expédiées – *pays* », sous-titre « Liste des commandes par ville et par employé ».
5. Colonnes : Ville, Employé, N° cmde (aligné à droite), Client, Expédiée le (`dd/MM/yyyy`, à droite). En-têtes en gras avec une ligne.
6. Pied de page : date et heure d'édition à gauche, « Page x sur y » à droite.
7. Si la requête ne renvoie rien (pays inexistant), le rapport affiche une page avec la mention « Aucune commande expédiée pour ces critères » au lieu d'être vide.
8. Testez dans l'aperçu avec `France`, `Germany`, `USA`, puis avec `Suisse` (aucune donnée).

## Cahier des charges (4.2, facultatif)

Ajoutez un paramètre **P_ANNEE** (`java.lang.Integer`, défaut `2017`) et la condition `AND YEAR(O.SHIPPEDDATE) = $P{P_ANNEE}`. Affichez l'année dans le titre.

## Indications

- Le paramètre se crée dans l'Outline (Parameters → Create Parameter) ; ses propriétés dans Properties. La valeur par défaut est une **expression** : un texte se met entre guillemets, un entier s'écrit tel quel.
- Dans la requête, `$P{P_PAYS}` est remplacé par la valeur : pas de guillemets autour.
- Dans une expression du rapport, la concaténation se fait avec `+` : `"Commandes expédiées – " + $P{P_PAYS}`.
- « Page x sur y » : Palette → Composite Elements → *Page X of Y*. Regardez la propriété *Evaluation Time* du second champ.
- Le comportement sans données : propriété du rapport **When No Data Type** (`No Data Section` ou `All Sections No Detail`) et bande *No Data* (clic droit sur le rapport dans l'Outline → *Add band*).
- Le champ Client peut être long : *Text Adjust* = Stretch Height.

## Vérifications

- Que se passe-t-il si vous tapez `france` en minuscules ? Pourquoi ?
- Décochez *Is For Prompting* : l'aperçu ne demande plus la valeur et utilise la valeur par défaut.
- Onglet Source : lisez la déclaration `<parameter …>` et son `<defaultValueExpression>`.

## Pour aller plus loin

- Un paramètre liste : `P_PAYS_LISTE` de classe `java.util.Collection`, défaut `java.util.Arrays.asList("France","Germany")`, et la clause `$X{IN, O.SHIPCOUNTRY, P_PAYS_LISTE}`.
- Un tri paramétrable avec `ORDER BY $P!{P_TRI}` (le contenu est inséré tel quel : à réserver à des valeurs contrôlées).
