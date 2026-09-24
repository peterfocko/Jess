package sk.jess;

import sk.jess.core.Board;
import sk.jess.cli.CLI;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        CLI cli = new CLI(board, 3);
        cli.display();
    }
}