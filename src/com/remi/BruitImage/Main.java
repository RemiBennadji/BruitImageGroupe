package com.remi.BruitImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedImage input = ImageIO.read(new File("LiveLoveAsapColored.jpg")); // image d'entrée
            double[][] gray = ModifImage.toGray(input);

            // Liste des filtres
            Map<String, double[]> filtres = new LinkedHashMap<>();
            filtres.put("Identite", Noyaux.Identite);
            filtres.put("Moyenneur", Noyaux.Moyenneur);
            filtres.put("Rehausseur", Noyaux.Rehausseur);
            filtres.put("Gauss3x3", Noyaux.Gauss3x3);
            filtres.put("Gauss5x5", Noyaux.Gauss5x5);
            filtres.put("Laplacien", Noyaux.Laplacien);
            //filtres.put("BoxBlur", Noyaux.BoxBlur);

            System.out.println("Application des filtres...");

            // Liste pour stocker toutes les images filtrées
            List<BufferedImage> images = new ArrayList<>();

            int idx = 1;
            for (Map.Entry<String, double[]> entry : filtres.entrySet()) {
                String nom = entry.getKey();
                double[] noyau = entry.getValue();

                double[][] resultat = ModifImage.appliquerFiltre(gray, noyau);
                BufferedImage out = ModifImage.toImage(resultat);

                // Sauvegarde sur disque
                File outputFile = new File("result_" + idx + "_" + nom + ".png");
                ImageIO.write(out, "png", outputFile);
                System.out.println("Filtre " + nom + " -> " + outputFile.getName());

                // Ajout dans la liste pour affichage
                images.add(out);

                idx++;
            }

            // Affiche toutes les images filtrées dans une grille
            VoirImage.afficher(images.toArray(new BufferedImage[0]));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
