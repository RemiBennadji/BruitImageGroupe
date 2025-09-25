package com.remi.BruitImage;

public class Noyaux {
    public static final double[] Identite = {
            0, 0, 0,
            0, 1, 0,
            0, 0, 0
    };

    public static final double[] Moyenneur = {
            1, 1, 1,
            1, 1, 1,
            1, 1, 1
    };

    public static final double[] MinV8 = {
            1, 1, 1,
            1, 1, 1,
            1, 1, 1
    };

    public static final double[] MaxV8 = {
            1, 1, 1,
            1, 1, 1,
            1, 1, 1
    };

    public static final double[] Gauss3x3 = {
            1, 2, 1,
            2, 4, 2,
            1, 2, 1
    };

    public static final double[] Gauss5x5 = {
            1, 4, 6, 4, 1,
            4, 16, 24, 16, 4,
            6, 24, 36, 24, 6,
            4, 16, 24, 16, 4,
            1, 4, 6, 4, 1
    };

    public static final double[] Rehausseur = {
            0, -1, 0,
            -1, 5, -1,
            0, -1, 0
    };

    public static final double[] Laplacien = {
            0, 1, 0,
            1, -4, 1,
            0, 1, 0
    };

//    public static final double[] BoxBlur = {
//            1, 1, 1,
//            1, 1, 1,
//            1, 1, 1
//    };
}
