package sk.jess.cli;

import java.util.HashMap;
import java.util.Map;
import sk.jess.core.*;

public class CLI {

    private static final String CORNER_DELIMITER = "+";
    private static final String ROW_DELIMITER = "-";
    private static final String COLUMN_DELIMITER = "|";
    private static final String EMPTY_DELIMITER = " ";
    private static final int BOARD_SIZE = Column.values().length;

    private static final String ANSI_RESET = "\u001B[0m\u001B[1m";
    private static final String ANSI_LIGHT_BG = "\u001B[46m";
    private static final String ANSI_DARK_BG = "\u001B[45m";
    private static final String ANSI_LIGHT_FG = "\u001B[37m";
    private static final String ANSI_DARK_FG = "\u001B[30m";

    private static final Map<Boolean, String> BG_COLOR = new HashMap<>();
    private static final Map<PieceColor, String> FG_COLOR = new HashMap<>();
    static {
        BG_COLOR.put(true, ANSI_LIGHT_BG);
        BG_COLOR.put(false, ANSI_DARK_BG);

        FG_COLOR.put(PieceColor.White, ANSI_LIGHT_FG);
        FG_COLOR.put(PieceColor.Black, ANSI_DARK_FG);
    }

    private final String emptyLocation;
    private final String emptyLocationHalf;
    private final String emptyLocationEvenHalfShorter;
    private final String rowLocation;

    private final Board board;

    public CLI(Board board, int columnWidth) {
        this.board = board;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columnWidth; i++) {
            builder.append(EMPTY_DELIMITER);
        }
        this.emptyLocation = builder.toString();
        this.rowLocation = this.emptyLocation.replace(EMPTY_DELIMITER, ROW_DELIMITER);
        this.emptyLocationHalf = this.emptyLocation.substring(0, columnWidth / 2);
        this.emptyLocationEvenHalfShorter = this.emptyLocation.substring(0, columnWidth / 2 - 1);
    }

    public void display() {
        System.err.print(ANSI_RESET);
        for (int i = 0; i < BOARD_SIZE * 2 + 1; i++) {
            System.out.println(i % 2 == 0 ? this.getBorderRowString() : this.getPieceRowString(BOARD_SIZE - i / 2 - 1));
        }
    }

    private String getBorderRowString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE * 2 + 1; i++) {
            builder.append(i % 2 == 0 ? CORNER_DELIMITER : this.rowLocation);
        }
        return builder.toString();
    }

    private String getPieceRowString(int row) {
        StringBuilder builder = new StringBuilder();
        boolean isLightSquare = row % 2 != 0;
        for (Column column : Column.values()) {
            builder.append(COLUMN_DELIMITER + BG_COLOR.get(isLightSquare) + this.getPieceString(row, column) + ANSI_RESET);
            isLightSquare = !isLightSquare;
        }
        return builder.append(COLUMN_DELIMITER).toString();
    }

    private String getPieceString(int row, Column column) {
        Piece piece = this.board.getPieceAt(row, column);
        if (piece == null) {
            return this.emptyLocation;
        }

        if (this.emptyLocation.length() % 2 == 1) {
            return this.emptyLocationHalf + FG_COLOR.get(piece.getColor()) + "P" + this.emptyLocationHalf;
        }

        if (piece.getColor() == PieceColor.White) {
            return this.emptyLocationEvenHalfShorter + "P" + this.emptyLocationHalf;
        }

        return this.emptyLocationHalf + "P" + this.emptyLocationEvenHalfShorter;
    }
}
