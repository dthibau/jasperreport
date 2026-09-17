# Atelier 7 – Police spécifique

**Durée indicative : 30 min.**

## Objectifs pédagogiques

- Comprendre pourquoi une police visible dans l'éditeur peut manquer dans le PDF.
- Déclarer une extension de police dans Studio et l'exporter en jar.
- Appliquer la police par le style par défaut du rapport.

## Résultat attendu

`Atelier7_ResultatAttendu.pdf` : l'annuaire de l'atelier 3 en police **Gentium** (sous-titre en italique). Polices fournies dans ce dossier : `GenR102.TTF` (normal) et `GenI102.TTF` (italique).

## Point de départ

Copiez le rapport de l'atelier 3 sous le nom `Atelier7_Police.jrxml`.

## Cahier des charges

1. **Reproduire le problème** : dans le style par défaut du rapport (ou sur le titre), tapez à la main le nom de police `Gentium` et lancez l'aperçu PDF. Notez le message obtenu (`Font "Gentium" is not available to the JVM`). C'est le cas « ça marche dans l'éditeur mais pas dans le PDF » quand la police est seulement installée sur le poste.
2. **Déclarer la police** : Window → Preferences → Jaspersoft Studio → **Fonts** → *Add*. Famille `Gentium`, fichier *Normal* = `GenR102.TTF`, *Italic* = `GenI102.TTF` (laissez Bold et Bold Italic vides). *PDF Encoding* = `Identity-H`, cochez **Embed this font in PDF document**. *Finish*, puis *Apply and Close*.
3. **Appliquer** : créez un style `Base`, coché *Default Style*, police `Gentium`, taille 12 ; vérifiez que tout le rapport l'utilise (Outline → Styles). Le sous-titre est en italique : il doit utiliser la variante `GenI102`.
4. Aperçu **PDF** : la police est correcte. Dans le lecteur PDF, *Propriétés du document → Polices* : `Gentium` et `Gentium-Italic` sont **incorporées** (embedded).
5. **Exporter** l'extension : Preferences → Fonts → sélectionnez `Gentium` → **Export** → `gentium-font.jar`. Ouvrez le jar avec un outil zip : il contient `jasperreports_extension.properties`, `fonts/gentium.xml` et les deux TTF.
6. Ce jar est à livrer avec le rapport : sans lui, la production utilisera une police de substitution.

## Indications

- Le style par défaut : Outline → Styles → clic droit → *Create Style*, puis dans Properties cochez *Default Style* et réglez *Font Name*.
- La liste des polices d'un élément (Properties → Text → Font) contient les polices déclarées dans Studio **et** celles du système ; seules les premières sont fiables pour le PDF.
- Un texte en gras avec une police qui n'a pas de variante Bold est « épaissi » artificiellement par le PDF : évitez.

## Vérifications

- Exportez en HTML : la police n'est pas embarquée, le navigateur utilise la sienne si Gentium n'est pas installée.
- Dans l'onglet Source de votre rapport, lisez le style par défaut : `fontName="Gentium" pdfEncoding="Identity-H" pdfEmbedded="true"`.

## Pour aller plus loin

- Ajoutez une variante Bold en réutilisant `GenR102.TTF` et observez la différence avec un vrai fichier gras.
- Déclarez la police d'entreprise de votre organisation de la même manière.
