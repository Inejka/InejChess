package gui;

import classicChess.Board;
import classicChess.Game;
import classicChess.Rules;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App {

    private JFrame jFrame;
    JButtonWithData buttons[][];

    int currentY = -1, currentX = -1;
    byte[] currentPossibleTurns;

    Map<Byte, String> dict;

    Game currentGame;

    public App(Game currentGame) {
        this.currentGame = currentGame;
        jFrame = new JFrame("example");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initDict();
        initButtons();
        redrawAll();
        jFrame.setSize(550, 550);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    private void initDict() {
        dict = new HashMap<>();
        dict.put((byte) 'p', "pieces/plt.png");
        dict.put((byte) 'P', "pieces/pdt.png");
        dict.put((byte) 'k', "pieces/klt.png");
        dict.put((byte) 'K', "pieces/kdt.png");
        dict.put((byte) 'r', "pieces/rlt.png");
        dict.put((byte) 'R', "pieces/rdt.png");
        dict.put((byte) 'b', "pieces/blt.png");
        dict.put((byte) 'B', "pieces/bdt.png");
        dict.put((byte) 'a', "pieces/nlt.png");
        dict.put((byte) 'A', "pieces/ndt.png");
        dict.put((byte) 'q', "pieces/qlt.png");
        dict.put((byte) 'Q', "pieces/qdt.png");
    }

    private void initButtons() {
        buttons = new JButtonWithData[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                //buttons[i][j] = new JButton(String.valueOf(i)+String.valueOf(j));
                buttons[i][j] = new JButtonWithData(i, j, this::buttonReact);
                buttons[i][j].setBounds(j * 60, i * 60, 60, 60);
                buttons[i][j].setBorder(BorderFactory.createEmptyBorder());
                jFrame.add(buttons[i][j]);
            }
    }

    private void redrawAll() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                buttons[i][j].setIcon(generateImageIcon(i, j));
            }
    }

    private void buttonReact(int y, int x) {
        //for testing purposes
        //System.out.print(y);
        //System.out.print(' ');
        //System.out.println(x);
        if (currentX != -1 && checkTurnInTurns(y, x)) {
            currentGame.makeTurn(currentY, currentX, y, x);
            currentPossibleTurns = null;
            currentX = -1;
            currentY = -1;
        } else if (currentGame.getBoard().board[y][x] != '0' && currentGame.getBoard().board[y][x] != '#') {
            currentPossibleTurns = currentGame.generateMovesForTile(y, x);
            currentY = y;
            currentX = x;
        } else {
            currentX = -1;
            currentY = -1;
            currentPossibleTurns = null;
        }
        //for testing purposes
        //if (currentPossibleTurns != null) {
        //    System.out.println(currentPossibleTurns[0]);
        //    for (int i = 1; i < currentPossibleTurns[0]; i += 2) {
        //        System.out.print(currentPossibleTurns[i]);
        //        System.out.print(' ');
        //        System.out.println(currentPossibleTurns[i + 1]);
        //    }
        //}
        redrawAll();
    }

    private boolean checkTurnInTurns(int y, int x) {
        for (int i = 1; i < currentPossibleTurns[0]; i += 2) {
            if (currentPossibleTurns[i] == y && currentPossibleTurns[i + 1] == x)
                return true;
        }
        return false;
    }

    private ImageIcon generateImageIcon(int y, int x) {
        try {
            BufferedImage combined = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
            BufferedImage tile = ImageIO.read(getClass().getClassLoader().getResource(((y + x + 1) % 2 == 0) ? "pieces/light_grey.png" : "pieces/white.png"));
            if (currentX == x && currentY == y) {
                tile = ImageIO.read(getClass().getClassLoader().getResource("pieces/brown.png"));
            }
            Graphics2D g = combined.createGraphics();
            g.drawImage(tile, 0, 0, null);
            if (currentGame.getBoard().board[y][x] != '0' && currentGame.getBoard().board[y][x] != '#') {
                BufferedImage toAdd = ImageIO.read(getClass().getClassLoader().getResource(dict.get(currentGame.getBoard().board[y][x])));
                g.drawImage(toAdd, 0, 0, null);
            }
            if (currentX != -1 && checkTurnInTurns(y, x)) {
                BufferedImage toAdd = ImageIO.read(getClass().getClassLoader().getResource("pieces/blue_circle.png"));
                g.drawImage(toAdd, 20, 20, null);
            }
            g.dispose();
            return new ImageIcon(combined);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
