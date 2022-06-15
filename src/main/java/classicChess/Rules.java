package classicChess;

import java.util.LinkedList;
import java.util.List;

public class Rules {

    public static List<Integer> generateMovesForTile(Board board, int y, int x) {
        List<Integer> toReturn = new LinkedList<>();
        switch (board.board[y][x]) {
            case 'P':
                blackPawnCheck(board, y, x, toReturn);
                break;
            case 'p':
                whitePawnCheck(board, y, x, toReturn);
                break;
        }
        return toReturn;
    }

    private static void whitePawnCheck(Board board, int y, int x, List<Integer> toReturn) {
        if (emptyFieldCheck(board, y - 1, x)) {
            toReturn.add(y - 1);
            toReturn.add(x);
            if (y == 7 && emptyFieldCheck(board, 5, x)) {
                toReturn.add(5);
                toReturn.add(x);
            }
        }
        if (x - 1 > -1 && blackFigureCheck(board, y - 1, x - 1)) {
            toReturn.add(y - 1);
            toReturn.add(x - 1);
        }
        if (x + 1 < 8 && blackFigureCheck(board, y - 1, x + 1)) {
            toReturn.add(y - 1);
            toReturn.add(x + 1);
        }
        if (board.board[board.y2][board.x1] == 'P' && board.x1 == board.x2 && board.y2 - board.y1 == 2 && board.y2 == y &&
                (board.x2 - x == -1 || board.x2 - x == 1)) {
            toReturn.add(board.y2 - 1);
            toReturn.add((int) board.x2);
        }
    }

    private static void blackPawnCheck(Board board, int y, int x, List<Integer> toReturn) {
        if (emptyFieldCheck(board, y + 1, x)) {
            toReturn.add(y + 1);
            toReturn.add(x);
            if (y == 1 && emptyFieldCheck(board, 3, x)) {
                toReturn.add(3);
                toReturn.add(x);
            }
        }
        if (x - 1 > -1 && whiteFigureCheck(board, y + 1, x - 1)) {
            toReturn.add(y + 1);
            toReturn.add(x - 1);
        }
        if (x + 1 < 8 && whiteFigureCheck(board, y + 1, x + 1)) {
            toReturn.add(y + 1);
            toReturn.add(x + 1);
        }
        if (board.board[board.y2][board.x1] == 'p' && board.x1 == board.x2 && board.y2 - board.y1 == -2 && board.y2 == y &&
                (board.x2 - x == -1 || board.x2 - x == 1)) {
            toReturn.add(board.y2 + 1);
            toReturn.add((int) board.x2);
        }
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
