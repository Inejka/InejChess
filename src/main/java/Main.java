import classicChess.Board;
import classicChess.Game;
import gui.App;
import server.Server;

import javax.swing.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //new App(new Game());
        //rec(new Game().generateAllPossiblePositions(),5,0);
        new Server();
    }

    public static void rec(List<Game> toIterate, int size, int current) {
        System.out.println(toIterate.size());
        if (size <= current) return;
        List<Game> next = new LinkedList<>();
        for (Game i : toIterate)
            next.addAll(i.generateAllPossiblePositions());
        rec(next, size, current + 1);
    }
}
