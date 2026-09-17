# Atelier 10 – Expressions simples : concaténation, conditions, dates

**Durée indicative : 1 h.**

## Objectifs pédagogiques

- Concaténer des champs en gérant les valeurs nulles.
- Afficher un texte ou un élément sous condition.
- Utiliser les fonctions intégrées de type tableur : `IF`, `UPPER`, `DAYS`, `DATEFORMAT`, `TODAY`.
- Construire des libellés parlants.

## Résultat attendu

`Atelier10_ResultatAttendu.pdf` (6 pages, A4 paysage, pays = USA). Image fournie : `warning.gif`.

## Cahier des charges

1. Rapport `Atelier10_Expressions.jrxml`, **A4 paysage**, police 9 pt, paramètre `P_PAYS` (défaut `"USA"`), requête :

```
SELECT O.ORDERID, O.SHIPNAME, O.SHIPADDRESS, O.SHIPPOSTALCODE, O.SHIPCITY, O.SHIPREGION,
       O.ORDERDATE, O.REQUIREDDATE, O.SHIPPEDDATE, O.FREIGHT
FROM ORDERS O
WHERE O.SHIPCOUNTRY = $P{P_PAYS}
ORDER BY O.ORDERDATE
```

2. **Titre dynamique** : « Suivi des expéditions – *pays* – édité le *jj/mm/aaaa* », la date étant produite par `DATEFORMAT(TODAY(), "dd/MM/yyyy")`.
3. **Sous-titre** calculé en fin de rapport (*Evaluation Time* Report) : « *n* commandes, dont *r* expédiées en retard (en rouge) », avec le pluriel correct (`"commande" + ($V{REPORT_COUNT} > 1 ? "s" : "")`). Le nombre de retards est une variable `NB_RETARD` qui somme `1` quand la commande est en retard, `0` sinon.
4. **Colonnes** du tableau (bordures fines, en-tête gris) :
   - N° ;
   - Client en **majuscules** : `UPPER($F{SHIPNAME})` ;
   - Adresse de livraison en une seule cellule : `adresse, code postal ville (région)` ; la région (`SHIPREGION`) est souvent nulle et ne doit alors rien afficher, ni « null » ni parenthèses vides ;
   - Commandée le (`dd/MM/yyyy`) ;
   - Expédiée le : la date au format `dd/MM/yyyy`, ou un tiret « – » si la commande n'est pas expédiée (`SHIPPEDDATE` nul) ;
   - Délai (j) : nombre de jours entre la commande et l'expédition, avec `DAYS(...)`, vide si non expédiée ;
   - Statut : « En attente » si non expédiée, « En retard » si expédiée après la date demandée (`REQUIREDDATE`), « OK » sinon, avec la fonction `IF` ;
   - une petite image `warning.gif` à droite du statut, **uniquement** pour les commandes en retard ;
   - Port au format `#,##0.00 €`.
5. **Ligne en rouge** quand la commande est en retard : style conditionnel sur le style des cellules.
6. Pied de page : légende à gauche (« Retard = expédition après la date demandée… »), « Page x sur y » à droite.

## Indications

- Éditeur d'expressions (bouton `…`) : les fonctions sont listées à gauche par catégorie (*Text*, *Date/Time*, *Logical*, *Math*) avec une aide et un exemple.
- Tester le nul : `$F{SHIPREGION} == null ? "" : " (" + $F{SHIPREGION} + ")"`.
- Comparer deux dates : `$F{SHIPPEDDATE}.after($F{REQUIREDDATE})` ; toujours vérifier d'abord que la date n'est pas nulle : `$F{SHIPPEDDATE} != null && ...`.
- Le délai : `$F{SHIPPEDDATE} == null ? null : DAYS($F{ORDERDATE}, $F{SHIPPEDDATE})`, champ texte avec *Blank When Null*.
- La classe d'un champ texte doit correspondre au résultat de l'expression : texte pour une concaténation, `java.lang.Integer` pour le délai.
- La variable `NB_RETARD` : classe Integer, calcul *Sum*, expression `($F{SHIPPEDDATE} != null && $F{SHIPPEDDATE}.after($F{REQUIREDDATE})) ? 1 : 0`.
- Image conditionnelle : *Print When Expression* sur l'élément Image.

## Vérifications

- Retirez le test de nullité de la région : la cellule affiche « null ». Retirez celui de la date : erreur à l'exécution.
- Remplacez `UPPER($F{SHIPNAME})` par `$F{SHIPNAME}.toUpperCase()` : même résultat, forme Java.
- Testez avec `France` : la légende sur les régions doit s'adapter ou disparaître (conditionnez-la par une expression sur `$P{P_PAYS}`).

## Pour aller plus loin

- Ajoutez une colonne « Retard (j) » : `DAYS($F{REQUIREDDATE}, $F{SHIPPEDDATE})` seulement si positif.
- Remplacez l'imbrication de `IF` par une traduction en SQL avec `CASE WHEN` dans la requête et comparez la lisibilité.
- Affichez « aujourd'hui » en toutes lettres : `DATEFORMAT(TODAY(), "EEEE d MMMM yyyy")`.
