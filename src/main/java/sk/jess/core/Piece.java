package sk.jess.core;

public class Piece {
    
    private final PieceColor color;

    public Piece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return this.color;
    }
}
