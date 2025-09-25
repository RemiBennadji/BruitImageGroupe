package com.remi.BruitImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ModifImage {

    //Je renvoie un tableau pour gagner en précision lorsque j'applique le filtre
    public static double[][] toGray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        double[][] gray = new double[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                gray[y][x] = (r + g + b) / 3.0;
            }
        }
        return gray;
    }

    // Appliquer un filtre générique (convolution)
    public static double[][] appliquerFiltre(double[][] image, double[] noyau) {
        int hauteur = image.length;
        int largeur = image[0].length;

        int tailleNoyau = (int) Math.sqrt(noyau.length); // 3x3 ou 5x5
        int offset = tailleNoyau / 2; //en 3*3 = 1 et 5*5 = 2 (décalage entre le centre et les bords du noyau

        double[][] sortie = new double[hauteur][largeur];

        // Normalisation du noyau (utile pour Gaussien, Moyenneur...)
        double somme = 0;
        for (double v : noyau) {
            somme += v;
        }
        if (somme == 0) {
            somme = 1;
        }

        // Convolution
        for (int y = offset; y < hauteur - offset; y++) {
            for (int x = offset; x < largeur - offset; x++) {
                double valeur = 0;
                int index = 0;
                for (int ky = -offset; ky <= offset; ky++) {
                    for (int kx = -offset; kx <= offset; kx++) {
                        valeur += image[y + ky][x + kx] * noyau[index++];
                    }
                }
                // On divise par la somme du noyau si nécessaire
                sortie[y][x] = valeur / somme;

                // Clamp [0,255]
                if (sortie[y][x] < 0) {
                    sortie[y][x] = 0;
                }
                if (sortie[y][x] > 255) {
                    sortie[y][x] = 255;
                }
            }
        }

        return sortie;
    }

    public static BufferedImage toImage(double[][] lesPixels) {
        int height = lesPixels.length;
        int width = lesPixels[0].length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY); //Créer une image en niveau de gris

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) { //Parcours de tous les pixels
                int val = (int) lesPixels[y][x]; //Je récupère la valeur du pixel en niveaux de gris (0–255)
                int rgb = (val << 16) | (val << 8) | val; //Met la valeur dans chaques composantes rgb
                img.setRGB(x, y, rgb); //le pixel à la position x, y a la valeur rgb
            }
        }
        return img;
    }
}
