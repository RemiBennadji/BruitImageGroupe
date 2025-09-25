package com.remi.BruitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VoirImage {
    BufferedImage bufferedImage;

    public VoirImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public void afficher() {
        Image resize = bufferedImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);

        ImageIcon imageIcon = new ImageIcon(resize);
        JLabel label = new JLabel(imageIcon);
        JFrame jframe = new JFrame();

        jframe.setLocationRelativeTo(null);
        jframe.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
        jframe.getContentPane().add(label);
        jframe.pack();
        jframe.setVisible(true);
    }

    public static void afficher(BufferedImage... images) {
        JFrame jframe = new JFrame("Images");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);

        // calculer le nombre de colonnes (par ex : 3 images par ligne max)
        int cols = 3;
        int rows = (int) Math.ceil(images.length / (double) cols);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols, 20, 20)); // espace entre les cases

        for (BufferedImage img : images) {
            // Redimensionner l'image
            Image resized = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resized);
            JLabel label = new JLabel(icon, JLabel.CENTER);

            // Centrer et ajouter une petite bordure
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            panel.add(label);
        }

        jframe.getContentPane().add(panel);
        jframe.pack();
        jframe.setVisible(true);
    }
}