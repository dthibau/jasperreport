# Solutions des ateliers (formateur)

`SolutionsAteliers/` est un projet Jaspersoft Studio : **File → Import… → General → Existing Projects into Workspace**, dossier racine `TP_Métier_Solutions`, cocher `SolutionsAteliers`. Chaque sous-dossier `AtelierN/` contient le rapport `.jrxml` (syntaxe JasperReports 7), ses ressources (images, `charte.jrtx`, jars) et le PDF de résultat attendu distribué aux stagiaires.

Les rapports s'exécutent dans Studio avec le data adapter **Sample DB**. L'atelier 7 requiert l'extension de police `Atelier7/gentium-font.jar` (déjà dans le `.classpath` du projet) ou la déclaration de la police Gentium dans les préférences de Studio.

## Régénérer les PDF hors de Studio

`../outils-tp/run.sh` compile un `.jrxml`, le remplit avec la base d'exemple extraite de Studio et exporte le PDF (à lancer depuis le dossier du rapport pour que les ressources relatives soient trouvées) :

```
cd TP_Métier_Solutions/SolutionsAteliers/Atelier4
../../../outils-tp/run.sh Atelier4_Parametres.jrxml Atelier4_Parametres.pdf P_PAYS=Germany
cd ../Atelier7
EXTRA_CP=$PWD/gentium-font.jar ../../../outils-tp/run.sh Atelier7_Police.jrxml Atelier7_Police.pdf
```

Les énoncés se régénèrent à partir des `.md` de `TP_Métier` avec `outils-tp/md2doc.py`.
