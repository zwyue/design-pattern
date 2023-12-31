package com.zwyue.flyweight;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChessPiece {

    private int id;

    private String text;

    private Color color;

    private int positionX;

    private int positionY;

    public static enum Color {
        RED, BLACK
    }
}
