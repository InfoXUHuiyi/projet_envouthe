README
  Sur le test des algorithmes pour le projet Envouthé, tous les tests sont réalisés en local. 
  La nouvelle table de base contient les données froides et les données chaudes, tous les étapes sont les mêmes comme les tests sur l'ancienne table de base.


Prétraitement
  Sur les champs de datetime, j’ai extrait leurs années et mois comme les nouvelles champs;  
  Ensuite, sur les champs catégoriques, j’ai supprimé les champs lesquels ont trop de catégoriques et n’affectent pas sur la prédiction de churn, par exemple, les champs “client_name”, “email”, etc. Et j’ai supprimé les valeurs nulles du champ “churn” qui sont les incertitudes totales, pour simplifier la vie, on considère seulement les cas “churn” et “non churn” dans le test. Et enfin, j’ai converti les valeurs catégoriques en valeurs numériques.
  Et sur les champs numériques, les champs "âge","action_open" et "action_click" contiennent nombreux valeurs nulles, si remplir les valeurs nulles avec la valeur moyenne ou médian, ce qui va prendre beaucoup de bruit; 
j’ai donc filtré l’âge entre 20 et 59 ans, puis je considère le champ “âge” en 2 catégories: les valeurs nulles comme la catégorie “0”, qui représente “pas d’âge”; et les autres valeurs comme la catégorie “1” représentant “avoir l’âge”.
et pour les champs "action_open" et "action_click", qui sont les données chaudes, j'ai converti leurs valeurs nulles comme une valeur spécifique: -1.
  Puis, sur les champs booléens, je les ai convertis aussi en valeurs numériques.
  Après, j’ai visualisé un heatmap sur la matrice de corrélation sur tous les champs.
  Enfin, j’ai normalisé tous les champs sauf les ids et le champ “churn”.


Machine Learning
  J’ai d’abord défini le churn comme le label, et tous les autres champs comme les features.
  Ensuite, j’ai échantillonné 80% des données comme le training set, et 20% comme le test set. Et j’ai divisé le training set en deux sous-ensembles: train_X et train_Y, ainsi, test_X et test_Y pour le test set. Le train_X set contient tous les features pour l’entraînement, et le train_Y set contient le label pour l’entraînement; et test_X et test_Y sets sont pour la phase test.
  Puis, je trouve qu’il existe les déséquilibres de la distribution sur les valeurs de label, donc, j’ai essayé SMOTE, RandomUnderSampler et SMOTEENN sur le training set pour résoudre ce problème.
  Après, j’ai testé 5 algos: Régression Logistique, Arbre de Decision, Forêt Aléatoir, DNN et SVM. Pour réduire la grosse perte de SVM, j’ai essayé BaggingClassifier et AdaBoostClassifier, ce qui donnent les résultats optimals différents.
  Ainsi, j’ai évalué ces 5 modèles en comparant leurs score_accuracy, matrix_confusion et classification_repport.
  Et enfin, j’ai sauvegardé les prédictions dans les fichiers différents. Les fichiers comprennent l’id d’abonnement, le label réel, le label prédit, le label prédit avec probabilité, les features utilisés comme input.



