# Atelier 6 – Images, logo et filigrane ; livraison avec les ressources

**Durée indicative : 45 min.**

## Objectifs pédagogiques

- Insérer un logo sans le déformer, le positionner dans l'en-tête, lui associer un lien.
- Mettre une image en filigrane dans la bande Background, affichée sous condition.
- Comprendre comment les images sont retrouvées à l'exécution et comment livrer un rapport avec ses ressources.

## Résultat attendu

`Atelier6_ResultatAttendu.pdf` (2 pages). Images fournies dans ce dossier : `logo.png`, `filigrane.png`, `logo-plb-footer.gif`.

## Point de départ

Copiez votre rapport de l'atelier 4.1 sous le nom `Atelier6_Images.jrxml` (clic droit → Copy / Paste dans Project Explorer). Copiez les trois images dans le projet, à côté du rapport.

## Cahier des charges

1. **Logo dans le titre** : `logo.png` à gauche de la bande Title (150 × 40), proportions conservées (*Scale Image* = Retain Shape), aligné à gauche et au milieu ; le titre et le sous-titre sont décalés à droite (x = 160, largeur 395).
2. Le logo est **cliquable** : lien hypertexte de type *Reference* vers `https://www.plb.fr`, ouverture dans une nouvelle fenêtre (*Target* = Blank), info-bulle « Site de PLB Formation ». Testez dans l'aperçu PDF.
3. **Filigrane** : un nouveau paramètre **P_BROUILLON** (`java.lang.Boolean`, défaut `Boolean.TRUE`). Dans la bande **Background** (hauteur 802), l'image `filigrane.png` occupe toute la page, centrée, proportions conservées, et n'est imprimée que si `$P{P_BROUILLON}` est vrai.
4. **Pied de page** : le petit logo `logo-plb-footer.gif` au centre (78 × 8).
5. Comportement en cas d'image manquante : le logo du titre a *On Error Type* = **Error** (on veut voir le problème), le filigrane a *On Error Type* = **Blank** (le rapport sort quand même).
6. Aperçu avec `P_BROUILLON` coché puis décoché ; en PDF, cliquez sur le logo.

## 6.3 – Livrer le rapport avec ses images (facultatif mais recommandé)

Les expressions d'image (`"logo.png"`) sont des **chemins relatifs** : Studio les résout dans le dossier du rapport. En production, l'application ou le serveur doit les retrouver de la même façon.

1. Un fichier `.jar` est simplement une archive zip renommée : il peut contenir des images, des polices, une bibliothèque de styles.
2. Créez la vôtre : sélectionnez les trois images dans l'explorateur de fichiers, compressez-les en zip, renommez en `ressources-images.jar`.
3. Dans Studio : clic droit sur le projet → *Properties* → *Java Build Path* → onglet *Libraries* → *Add JARs…* (ou *Add External JARs…*) → votre jar.
4. Supprimez (ou déplacez ailleurs) les images du dossier du rapport et relancez l'aperçu : les images sont trouvées **dans le jar**. C'est ce jar qu'il faudra livrer avec le `.jrxml`.

## Indications

- Élément *Image* de la Palette ; en glissant un fichier image du Project Explorer vers une bande, Studio crée l'élément directement.
- Propriétés d'une image : onglet *Image* (Scale Image, alignements, On Error Type, Using Cache), onglet *Hyperlink*.
- La bande Background se crée par clic droit sur le rapport dans l'Outline → *Add band* → Background si elle est absente. Sa hauteur maximale est la hauteur de page moins les marges (842 − 40 = 802).
- Le filigrane fourni est un PNG avec transparence : le texte reste lisible par-dessus.

## Vérifications

- Renommez temporairement `logo.png` : quel message obtenez-vous ? Et si *On Error Type* vaut Blank ?
- Mettez le logo en *Fill Frame* : il se déforme. Revenez à *Retain Shape*.
- Exportez en XLSX : le filigrane n'apparaît pas, c'est normal.

## Pour aller plus loin

- Remplacez `"logo.png"` par `$P{P_DOSSIER_IMAGES} + "logo.png"` avec un paramètre `P_DOSSIER_IMAGES` (défaut `""`) : l'application pourra fixer le dossier des images.
- Essayez un logo au format SVG (vectoriel) : la netteté est conservée à toutes les tailles.
