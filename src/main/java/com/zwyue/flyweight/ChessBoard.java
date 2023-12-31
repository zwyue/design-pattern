package com.zwyue.flyweight;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private Map<Integer, ChessPiece> chessPiece = new HashMap<>();

    public ChessBoard() {
        init();
    }

    private void init() {
        chessPiece.put(1, new ChessPiece(1, "車", ChessPiece.Color.BLACK, 0, 0));
        chessPiece.put(2, new ChessPiece(2, "車", ChessPiece.Color.BLACK, 0, 1));
    }

    public void move(int chessPieceId,int toPositionX,int toPositionY) {

    }
}
