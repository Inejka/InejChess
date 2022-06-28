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
        new App(new Game());
    }

}
