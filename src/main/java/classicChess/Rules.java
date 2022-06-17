package classicChess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Rules {

    public static byte[] generateMovesForTile(Board board, int y, int x) {
        byte[] toReturn = null;
        switch (board.board[y][x]) {
            case 'p' -> {
                toReturn = new byte[9];
                whitePawnCheck(board, y, x, toReturn);
            }
            case 'P' -> {
                toReturn = new byte[9];
                blackPawnCheck(board, y, x, toReturn);
            }
            case 'r' -> {
                toReturn = new byte[29];
                whiteRookCheck(board, y, x, toReturn);
            }
            case 'R' -> {
                toReturn = new byte[29];
                blackRookCheck(board, y, x, toReturn);
            }
            case 'a' -> {
                toReturn = new byte[17];
                whiteKnightCheck(board, y, x, toReturn);
            }
            case 'A' -> {
                toReturn = new byte[17];
                blackKnightCheck(board, y, x, toReturn);
            }
            case 'b' -> {
                toReturn = new byte[29];
                whiteBishopCheck(board, y, x, toReturn);
            }
            case 'B' -> {
                toReturn = new byte[29];
                blackBishopCheck(board, y, x, toReturn);
            }
            case 'q' -> {
                toReturn = new byte[57];
                whiteQueenCheck(board, y, x, toReturn);
            }
            case 'Q' -> {
                toReturn = new byte[57];
                blackQueenCheck(board, y, x, toReturn);
            }
        }
        return toReturn;
    }

    private static void blackQueenCheck(Board board, int y, int x, byte[] toReturn) {
        blackRookCheck(board, y, x, toReturn);
        byte i = toReturn[0], j = 1;
        while (y - j > -1 && x - j > -1 && emptyFieldCheck(board, y - j, x - j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
            j += 1;
        }
        if (y - j > -1 && x - j > -1 && whiteFigureCheck(board, y - j, x - j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
        }
        j = 1;
        while (y - j > -1 && x + j < 8 && emptyFieldCheck(board, y - j, x + j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
            j += 1;
        }
        if (y - j > -1 && x + j < 8 && whiteFigureCheck(board, y - j, x + j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
        }
        j = 1;
        while (y + j < 8 && x - j > -1 && emptyFieldCheck(board, y + j, x - j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
            j += 1;
        }
        if (y + j < 8 && x - j > -1 && whiteFigureCheck(board, y + j, x - j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
        }
        j = 1;
        while (y + j < 8 && x + j < 8 && emptyFieldCheck(board, y + j, x + j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
            j += 1;
        }
        if (y + j < 8 && x + j < 8 && whiteFigureCheck(board, y + j, x + j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
        }
        toReturn[0] = i;
    }

    private static void whiteQueenCheck(Board board, int y, int x, byte[] toReturn) {
        whiteRookCheck(board, y, x, toReturn);
        byte i = toReturn[0], j = 1;
        while (y - j > -1 && x - j > -1 && emptyFieldCheck(board, y - j, x - j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
            j += 1;
        }
        if (y - j > -1 && x - j > -1 && blackFigureCheck(board, y - j, x - j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
        }
        j = 1;
        while (y - j > -1 && x + j < 8 && emptyFieldCheck(board, y - j, x + j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
            j += 1;
        }
        if (y - j > -1 && x + j < 8 && blackFigureCheck(board, y - j, x + j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
        }
        j = 1;
        while (y + j < 8 && x - j > -1 && emptyFieldCheck(board, y + j, x - j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
            j += 1;
        }
        if (y + j < 8 && x - j > -1 && blackFigureCheck(board, y + j, x - j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
        }
        j = 1;
        while (y + j < 8 && x + j < 8 && emptyFieldCheck(board, y + j, x + j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
            j += 1;
        }
        if (y + j < 8 && x + j < 8 && blackFigureCheck(board, y + j, x + j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
        }
        toReturn[0] = i;
    }

    private static void blackBishopCheck(Board board, int y, int x, byte[] toReturn) {
        byte i = 1, j = 1;
        while (y - j > -1 && x - j > -1 && emptyFieldCheck(board, y - j, x - j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
            j += 1;
        }
        if (y - j > -1 && x - j > -1 && whiteFigureCheck(board, y - j, x - j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
        }
        j = 1;
        while (y - j > -1 && x + j < 8 && emptyFieldCheck(board, y - j, x + j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
            j += 1;
        }
        if (y - j > -1 && x + j < 8 && whiteFigureCheck(board, y - j, x + j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
        }
        j = 1;
        while (y + j < 8 && x - j > -1 && emptyFieldCheck(board, y + j, x - j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
            j += 1;
        }
        if (y + j < 8 && x - j > -1 && whiteFigureCheck(board, y + j, x - j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
        }
        j = 1;
        while (y + j < 8 && x + j < 8 && emptyFieldCheck(board, y + j, x + j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
            j += 1;
        }
        if (y + j < 8 && x + j < 8 && whiteFigureCheck(board, y + j, x + j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
        }
        toReturn[0] = i;
    }

    private static void whiteBishopCheck(Board board, int y, int x, byte[] toReturn) {
        byte i = 1, j = 1;
        while (y - j > -1 && x - j > -1 && emptyFieldCheck(board, y - j, x - j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
            j += 1;
        }
        if (y - j > -1 && x - j > -1 && blackFigureCheck(board, y - j, x - j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
        }
        j = 1;
        while (y - j > -1 && x + j < 8 && emptyFieldCheck(board, y - j, x + j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
            j += 1;
        }
        if (y - j > -1 && x + j < 8 && blackFigureCheck(board, y - j, x + j)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
        }
        j = 1;
        while (y + j < 8 && x - j > -1 && emptyFieldCheck(board, y + j, x - j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
            j += 1;
        }
        if (y + j < 8 && x - j > -1 && blackFigureCheck(board, y + j, x - j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
        }
        j = 1;
        while (y + j < 8 && x + j < 8 && emptyFieldCheck(board, y + j, x + j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
            j += 1;
        }
        if (y + j < 8 && x + j < 8 && blackFigureCheck(board, y + j, x + j)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
        }
        toReturn[0] = i;
    }

    private static void blackKnightCheck(Board board, int y, int x, byte[] toReturn) {
        byte i = 1;
        if ((y - 2 > -1) && (x - 1 > -1) &&
                ((whiteFigureCheck(board, y - 2, x - 1)) || (emptyFieldCheck(board, y - 2, x - 1)))) {
            toReturn[i] = (byte) (y - 2);
            toReturn[i + 1] = (byte) (x - 1);
            i += 2;
        }
        if ((y - 2 > -1) && (x + 1 < 8) &&
                ((whiteFigureCheck(board, y - 2, x + 1)) || (emptyFieldCheck(board, y - 2, x + 1)))) {
            toReturn[i] = (byte) (y - 2);
            toReturn[i + 1] = (byte) (x + 1);
            i += 2;
        }
        if ((y + 2 < 8) && (x - 1 > -1) &&
                ((whiteFigureCheck(board, y + 2, x - 1)) || (emptyFieldCheck(board, y + 2, x - 1)))) {
            toReturn[i] = (byte) (y + 2);
            toReturn[i + 1] = (byte) (x - 1);
            i += 2;
        }
        if ((y + 2 < 8) && (x + 1 < 8) &&
                ((whiteFigureCheck(board, y + 2, x + 1)) || (emptyFieldCheck(board, y + 2, x + 1)))) {
            toReturn[i] = (byte) (y + 2);
            toReturn[i + 1] = (byte) (x + 1);
            i += 2;
        }
        if ((y - 1 > -1) && (x - 2 > -1) &&
                ((whiteFigureCheck(board, y - 1, x - 2)) || (emptyFieldCheck(board, y - 1, x - 2)))) {
            toReturn[i] = (byte) (y - 1);
            toReturn[i + 1] = (byte) (x - 2);
            i += 2;
        }
        if ((y - 1 > -1) && (x + 2 < 8) &&
                ((whiteFigureCheck(board, y - 1, x + 2)) || (emptyFieldCheck(board, y - 1, x + 2)))) {
            toReturn[i] = (byte) (y - 1);
            toReturn[i + 1] = (byte) (x + 2);
            i += 2;
        }
        if ((y + 1 < 8) && (x - 2 > -1) &&
                ((whiteFigureCheck(board, y + 1, x - 2)) || (emptyFieldCheck(board, y + 1, x - 2)))) {
            toReturn[i] = (byte) (y + 1);
            toReturn[i + 1] = (byte) (x - 2);
            i += 2;
        }
        if ((y + 1 < 8) && (x + 2 < 8) &&
                ((whiteFigureCheck(board, y + 1, x + 2)) || (emptyFieldCheck(board, y + 1, x + 2)))) {
            toReturn[i] = (byte) (y + 1);
            toReturn[i + 1] = (byte) (x + 2);
            i += 2;
        }
        toReturn[0] = i;
    }

    private static void whiteKnightCheck(Board board, int y, int x, byte[] toReturn) {
        byte i = 1;
        if ((y - 2 > -1) && (x - 1 > -1) &&
                ((blackFigureCheck(board, y - 2, x - 1)) || (emptyFieldCheck(board, y - 2, x - 1)))) {
            toReturn[i] = (byte) (y - 2);
            toReturn[i + 1] = (byte) (x - 1);
            i += 2;
        }
        if ((y - 2 > -1) && (x + 1 < 8) &&
                ((blackFigureCheck(board, y - 2, x + 1)) || (emptyFieldCheck(board, y - 2, x + 1)))) {
            toReturn[i] = (byte) (y - 2);
            toReturn[i + 1] = (byte) (x + 1);
            i += 2;
        }
        if ((y + 2 < 8) && (x - 1 > -1) &&
                ((blackFigureCheck(board, y + 2, x - 1)) || (emptyFieldCheck(board, y + 2, x - 1)))) {
            toReturn[i] = (byte) (y + 2);
            toReturn[i + 1] = (byte) (x - 1);
            i += 2;
        }
        if ((y + 2 < 8) && (x + 1 < 8) &&
                ((blackFigureCheck(board, y + 2, x + 1)) || (emptyFieldCheck(board, y + 2, x + 1)))) {
            toReturn[i] = (byte) (y + 2);
            toReturn[i + 1] = (byte) (x + 1);
            i += 2;
        }
        if ((y - 1 > -1) && (x - 2 > -1) &&
                ((blackFigureCheck(board, y - 1, x - 2)) || (emptyFieldCheck(board, y - 1, x - 2)))) {
            toReturn[i] = (byte) (y - 1);
            toReturn[i + 1] = (byte) (x - 2);
            i += 2;
        }
        if ((y - 1 > -1) && (x + 2 < 8) &&
                ((blackFigureCheck(board, y - 1, x + 2)) || (emptyFieldCheck(board, y - 1, x + 2)))) {
            toReturn[i] = (byte) (y - 1);
            toReturn[i + 1] = (byte) (x + 2);
            i += 2;
        }
        if ((y + 1 < 8) && (x - 2 > -1) &&
                ((blackFigureCheck(board, y + 1, x - 2)) || (emptyFieldCheck(board, y + 1, x - 2)))) {
            toReturn[i] = (byte) (y + 1);
            toReturn[i + 1] = (byte) (x - 2);
            i += 2;
        }
        if ((y + 1 < 8) && (x + 2 < 8) &&
                ((blackFigureCheck(board, y + 1, x + 2)) || (emptyFieldCheck(board, y + 1, x + 2)))) {
            toReturn[i] = (byte) (y + 1);
            toReturn[i + 1] = (byte) (x + 2);
            i += 2;
        }
        toReturn[0] = i;
    }

    private static void blackRookCheck(Board board, int y, int x, byte[] toReturn) {
        byte i = 1, j = 1;
        while (x - j > -1 && emptyFieldCheck(board, y, x - j)) {
            toReturn[i] = (byte) y;
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
            j += 1;
        }
        if (x - j > -1 && whiteFigureCheck(board, y, x - j)) {
            toReturn[i] = (byte) y;
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
        }
        j = 1;
        while (x + j < 8 && emptyFieldCheck(board, y, x + j)) {
            toReturn[i] = (byte) y;
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
            j += 1;
        }
        if (x + j < 8 && whiteFigureCheck(board, y, x + j)) {
            toReturn[i] = (byte) y;
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
        }
        j = 1;
        while (y - j > -1 && emptyFieldCheck(board, y - j, x)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) x;
            i += 2;
            j += 1;
        }
        if (x - j > -1 && whiteFigureCheck(board, y - j, x)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) x;
            i += 2;
        }
        j = 1;
        while (y + j < 8 && emptyFieldCheck(board, y + j, x)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) x;
            i += 2;
            j += 1;
        }
        if (y + j < 8 && whiteFigureCheck(board, y + j, x)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) x;
            i += 2;
        }
        toReturn[0] = i;
    }

    private static void whiteRookCheck(Board board, int y, int x, byte[] toReturn) {
        byte i = 1, j = 1;
        while (x - j > -1 && emptyFieldCheck(board, y, x - j)) {
            toReturn[i] = (byte) y;
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
            j += 1;
        }
        if (x - j > -1 && blackFigureCheck(board, y, x - j)) {
            toReturn[i] = (byte) y;
            toReturn[i + 1] = (byte) (x - j);
            i += 2;
        }
        j = 1;
        while (x + j < 8 && emptyFieldCheck(board, y, x + j)) {
            toReturn[i] = (byte) y;
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
            j += 1;
        }
        if (x + j < 8 && blackFigureCheck(board, y, x + j)) {
            toReturn[i] = (byte) y;
            toReturn[i + 1] = (byte) (x + j);
            i += 2;
        }
        j = 1;
        while (y - j > -1 && emptyFieldCheck(board, y - j, x)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) x;
            i += 2;
            j += 1;
        }
        if (x - j > -1 && blackFigureCheck(board, y - j, x)) {
            toReturn[i] = (byte) (y - j);
            toReturn[i + 1] = (byte) x;
            i += 2;
        }
        j = 1;
        while (y + j < 8 && emptyFieldCheck(board, y + j, x)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) x;
            i += 2;
            j += 1;
        }
        if (y + j < 8 && blackFigureCheck(board, y + j, x)) {
            toReturn[i] = (byte) (y + j);
            toReturn[i + 1] = (byte) x;
            i += 2;
        }
        toReturn[0] = i;
    }

    private static void whitePawnCheck(Board board, int y, int x, byte[] toReturn) {
        byte i = 1;
        if (emptyFieldCheck(board, y - 1, x)) {
            toReturn[1] = ((byte) (y - 1));
            toReturn[2] = ((byte) x);
            i += 2;
            if (y == 7 && emptyFieldCheck(board, 5, x)) {
                toReturn[3] = ((byte) 5);
                toReturn[4] = ((byte) x);
                i += 2;
            }
        }
        if (x - 1 > -1 && blackFigureCheck(board, y - 1, x - 1)) {
            toReturn[i] = ((byte) (y - 1));
            toReturn[i + 1] = ((byte) (x - 1));
            i += 2;
        }
        if (x + 1 < 8 && blackFigureCheck(board, y - 1, x + 1)) {
            toReturn[i] = ((byte) (y - 1));
            toReturn[i + 1] = ((byte) (x + 1));
            i += 2;
        }
        if (board.board[board.y2][board.x1] == 'P' && board.x1 == board.x2 && board.y2 - board.y1 == 2 && board.y2 == y &&
                (board.x2 - x == -1 || board.x2 - x == 1)) {
            toReturn[i] = ((byte) (board.y2 - 1));
            toReturn[i + 1] = (board.x2);
            i += 2;
        }
        toReturn[0] = i;
    }

    private static void blackPawnCheck(Board board, int y, int x, byte[] toReturn) {
        byte i = 1;
        if (emptyFieldCheck(board, y + 1, x)) {
            toReturn[1] = ((byte) (y + 1));
            toReturn[2] = ((byte) x);
            i += 2;
            if (y == 1 && emptyFieldCheck(board, 3, x)) {
                toReturn[3] = ((byte) 3);
                toReturn[4] = ((byte) x);
                i += 2;
            }
        }
        if (x - 1 > -1 && whiteFigureCheck(board, y + 1, x - 1)) {
            toReturn[i] = ((byte) (y + 1));
            toReturn[i + 1] = ((byte) (x - 1));
            i += 2;
        }
        if (x + 1 < 8 && whiteFigureCheck(board, y + 1, x + 1)) {
            toReturn[i] = ((byte) (y + 1));
            toReturn[i + 1] = ((byte) (x + 1));
            i += 2;
        }
        if (board.board[board.y2][board.x1] == 'p' && board.x1 == board.x2 && board.y2 - board.y1 == -2 && board.y2 == y &&
                (board.x2 - x == -1 || board.x2 - x == 1)) {
            toReturn[i] = ((byte) (board.y2 + 1));
            toReturn[i + 1] = (board.x2);
            i += 2;
        }
        toReturn[0] = i;
    }

    private static boolean blackFigureCheck(Board board, int y, int x) {
        return board.board[y][x] >= 'A' && board.board[y][x] <= 'Z';
    }

    private static boolean emptyFieldCheck(Board board, int y, int x) {
        return board.board[y][x] == '0' || board.board[y][x] == '#';
    }

    private static boolean whiteFigureCheck(Board board, int y, int x) {
        return board.board[y][x] >= 'a' && board.board[y][x] <= 'z';
    }
}
