package classicChess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Rules {

    public static byte[] generateMovesForTile(Board board, int y, int x) {
        byte[] toReturn = null;
        switch (board.board[y][x]) {
            case 'p':
                toReturn = new byte[10];
                whitePawnCheck(board, y, x, toReturn);
                break;
            case 'P':
                toReturn = new byte[10];
                blackPawnCheck(board, y, x, toReturn);
                break;
        }
        return toReturn;
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
