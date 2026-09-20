package sk.jess.core;

public class Board {

    private Piece[][] pieces;

    public Board() {
        Column[] columns = Column.values();
        this.pieces = new Piece[columns.length][columns.length];

        for (Column column : columns) {
            pieces[0][column.getValue()] = new Piece(PieceColor.White);
            pieces[1][column.getValue()] = new Piece(PieceColor.White);
            pieces[6][column.getValue()] = new Piece(PieceColor.Black);
            pieces[7][column.getValue()] = new Piece(PieceColor.Black);
        }
    }

    public Piece getPieceAt(int row, Column column) {
        return pieces[row][column.getValue()];
    }
}
