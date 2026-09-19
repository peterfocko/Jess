package sk.jess.cli;

import sk.jess.core.Board;

public class CLI {
    
    private final Board board;

    public CLI(Board board) {
        this.board = board;
    }

    public void display() {
        System.out.println("Bulochka :*");
    }
}
