package classicChess;

public class Validations {
    public static int getCheckTimesForTileBlack(Board board, int y, int x) {
        int toReturn = 0;
        //king checks
        if (y - 1 > -1 && x - 1 > -1 && board.board[y - 1][x - 1] == 'k') toReturn++;
        if (y - 1 > -1 && board.board[y - 1][x] == 'k') toReturn++;
        if (y - 1 > -1 && x + 1 < 8 && board.board[y - 1][x + 1] == 'k') toReturn++;
        if (y + 1 < 8 && x - 1 > -1 && board.board[y + 1][x - 1] == 'k') toReturn++;
        if (y + 1 < 8 && board.board[y + 1][x] == 'k') toReturn++;
        if (y + 1 < 8 && x + 1 < 8 && board.board[y + 1][x + 1] == 'k') toReturn++;
        if (x - 1 > -1 && board.board[y][x - 1] == 'k') toReturn++;
        if (x + 1 < 8 && board.board[y][x + 1] == 'k') toReturn++;
        //pawn check
        if (y + 1 < 8 && x - 1 > -1 && board.board[y + 1][x - 1] == 'p') toReturn++;
        if (y + 1 < 8 && x + 1 < 8 && board.board[y + 1][x + 1] == 'p') toReturn++;
        //knights check
        if ((y - 2 > -1) && (x - 1 > -1) && board.board[y - 2][x - 1] == 'a') toReturn++;
        if ((y - 2 > -1) && (x + 1 < 8) && board.board[y - 2][x + 1] == 'a') toReturn++;
        if ((y + 2 < 8) && (x - 1 > -1) && board.board[y + 2][x - 1] == 'a') toReturn++;
        if ((y + 2 < 8) && (x + 1 < 8) && board.board[y + 2][x + 1] == 'a') toReturn++;
        if ((y - 1 > -1) && (x - 2 > -1) && board.board[y - 1][x - 2] == 'a') toReturn++;
        if ((y - 1 > -1) && (x + 2 < 8) && board.board[y - 1][x + 2] == 'a') toReturn++;
        if ((y + 1 < 8) && (x - 2 > -1) && board.board[y + 1][x - 2] == 'a') toReturn++;
        if ((y + 1 < 8) && (x + 2 < 8) && board.board[y + 1][x + 2] == 'a') toReturn++;
        //rooks && queens check
        int j = 1;
        while (x - j > -1 && Rules.emptyFieldCheck(board, y, x - j)) j++;
        if (x - j > -1 && (board.board[y][x - j] == 'r' || board.board[y][x - j] == 'q')) toReturn++;
        j = 1;
        while (x + j < 8 && Rules.emptyFieldCheck(board, y, x + j)) j++;
        if (x + j < 8 && (board.board[y][x + j] == 'r' || board.board[y][x + j] == 'q')) toReturn++;
        j = 1;
        while (y - j > -1 && Rules.emptyFieldCheck(board, y - j, x)) j++;
        if (y - j > -1 && (board.board[y - j][x] == 'r' || board.board[y - j][x] == 'q')) toReturn++;
        j = 1;
        while (y + j < 8 && Rules.emptyFieldCheck(board, y + j, x)) j++;
        if (y + j < 8 && (board.board[y + j][x] == 'r' || board.board[y + j][x] == 'q')) toReturn++;
        //bishops && queens check
        j = 1;
        while (y - j > -1 && x - j > -1 && Rules.emptyFieldCheck(board, y - j, x - j)) j++;
        if (y - j > -1 && x - j > -1 && (board.board[y - j][x - j] == 'b' || board.board[y - j][x - j] == 'q'))
            toReturn++;
        j = 1;
        while (y - j > -1 && x + j < 8 && Rules.emptyFieldCheck(board, y - j, x + j)) j++;
        if (y - j > -1 && x + j < 8 && (board.board[y - j][x + j] == 'b' || board.board[y - j][x + j] == 'q'))
            toReturn++;
        j = 1;
        while (y + j < 8 && x - j > -1 && Rules.emptyFieldCheck(board, y + j, x - j)) j++;
        if (y + j < 8 && x - j > -1 && (board.board[y + j][x - j] == 'b' || board.board[y + j][x - j] == 'q'))
            toReturn++;
        j = 1;
        while (y + j < 8 && x + j < 8 && Rules.emptyFieldCheck(board, y + j, x + j)) j++;
        if (y + j < 8 && x + j < 8 && (board.board[y + j][x + j] == 'b' || board.board[y + j][x + j] == 'q'))
            toReturn++;
        return toReturn;
    }

    public static int getCheckTimesForTileWhite(Board board, int y, int x) {
        int toReturn = 0;
        //king checks
        if (y - 1 > -1 && x - 1 > -1 && board.board[y - 1][x - 1] == 'K') toReturn++;
        if (y - 1 > -1 && board.board[y - 1][x] == 'K') toReturn++;
        if (y - 1 > -1 && x + 1 < 8 && board.board[y - 1][x + 1] == 'K') toReturn++;
        if (y + 1 < 8 && x - 1 > -1 && board.board[y + 1][x - 1] == 'K') toReturn++;
        if (y + 1 < 8 && board.board[y + 1][x] == 'K') toReturn++;
        if (y + 1 < 8 && x + 1 < 8 && board.board[y + 1][x + 1] == 'K') toReturn++;
        if (x - 1 > -1 && board.board[y][x - 1] == 'K') toReturn++;
        if (x + 1 < 8 && board.board[y][x + 1] == 'K') toReturn++;
        //pawn check
        if (y - 1 > -1 && x - 1 > -1 && board.board[y - 1][x - 1] == 'P') toReturn++;
        if (y - 1 > -1 && x + 1 < 8 && board.board[y - 1][x + 1] == 'P') toReturn++;
        //knights check
        if ((y - 2 > -1) && (x - 1 > -1) && board.board[y - 2][x - 1] == 'A') toReturn++;
        if ((y - 2 > -1) && (x + 1 < 8) && board.board[y - 2][x + 1] == 'A') toReturn++;
        if ((y + 2 < 8) && (x - 1 > -1) && board.board[y + 2][x - 1] == 'A') toReturn++;
        if ((y + 2 < 8) && (x + 1 < 8) && board.board[y + 2][x + 1] == 'A') toReturn++;
        if ((y - 1 > -1) && (x - 2 > -1) && board.board[y - 1][x - 2] == 'A') toReturn++;
        if ((y - 1 > -1) && (x + 2 < 8) && board.board[y - 1][x + 2] == 'A') toReturn++;
        if ((y + 1 < 8) && (x - 2 > -1) && board.board[y + 1][x - 2] == 'A') toReturn++;
        if ((y + 1 < 8) && (x + 2 < 8) && board.board[y + 1][x + 2] == 'A') toReturn++;
        //rooks && queens check
        int j = 1;
        while (x - j > -1 && Rules.emptyFieldCheck(board, y, x - j)) j++;
        if (x - j > -1 && (board.board[y][x - j] == 'R' || board.board[y][x - j] == 'Q')) toReturn++;
        j = 1;
        while (x + j < 8 && Rules.emptyFieldCheck(board, y, x + j)) j++;
        if (x + j < 8 && (board.board[y][x + j] == 'R' || board.board[y][x + j] == 'Q')) toReturn++;
        j = 1;
        while (y - j > -1 && Rules.emptyFieldCheck(board, y - j, x)) j++;
        if (y - j > -1 && (board.board[y - j][x] == 'R' || board.board[y - j][x] == 'Q')) toReturn++;
        j = 1;
        while (y + j < 8 && Rules.emptyFieldCheck(board, y + j, x)) j++;
        if (y + j < 8 && (board.board[y + j][x] == 'R' || board.board[y + j][x] == 'Q')) toReturn++;
        //bishops && queens check
        j = 1;
        while (y - j > -1 && x - j > -1 && Rules.emptyFieldCheck(board, y - j, x - j)) j++;
        if (y - j > -1 && x - j > -1 && (board.board[y - j][x - j] == 'B' || board.board[y - j][x - j] == 'Q'))
            toReturn++;
        j = 1;
        while (y - j > -1 && x + j < 8 && Rules.emptyFieldCheck(board, y - j, x + j)) j++;
        if (y - j > -1 && x + j < 8 && (board.board[y - j][x + j] == 'B' || board.board[y - j][x + j] == 'Q'))
            toReturn++;
        j = 1;
        while (y + j < 8 && x - j > -1 && Rules.emptyFieldCheck(board, y + j, x - j)) j++;
        if (y + j < 8 && x - j > -1 && (board.board[y + j][x - j] == 'B' || board.board[y + j][x - j] == 'Q'))
            toReturn++;
        j = 1;
        while (y + j < 8 && x + j < 8 && Rules.emptyFieldCheck(board, y + j, x + j)) j++;
        if (y + j < 8 && x + j < 8 && (board.board[y + j][x + j] == 'B' || board.board[y + j][x + j] == 'Q'))
            toReturn++;
        return toReturn;
    }

    public static boolean blackCastlingValidation(Board board, int x1) {
        switch (x1) {
            case 2 -> {
                if (getCheckTimesForTileBlack(board, 0, 4) + getCheckTimesForTileBlack(board, 0, 3) + getCheckTimesForTileBlack(board, 0, 2) > 0)
                    return false;
            }
            case 6 -> {
                if (getCheckTimesForTileBlack(board, 0, 4) + getCheckTimesForTileBlack(board, 0, 5) + getCheckTimesForTileBlack(board, 0, 6) > 0)
                    return false;
            }
        }
        return true;
    }

    public static boolean whiteCastlingValidation(Board board, int x1) {
        switch (x1) {
            case 2 -> {
                if (getCheckTimesForTileWhite(board, 7, 4) + getCheckTimesForTileWhite(board, 7, 3) + getCheckTimesForTileWhite(board, 7, 2) > 0)
                    return false;
            }
            case 6 -> {
                if (getCheckTimesForTileWhite(board, 7, 4) + getCheckTimesForTileWhite(board, 7, 5) + getCheckTimesForTileWhite(board, 7, 6) > 0)
                    return false;
            }
        }
        return true;
    }

    public static boolean blackTurnValidate(Board board, int y, int x, int y1, int x1, int blackKingY, int blackKingX) {
        if (board.board[y][x] == 'K')
            return getCheckTimesForTileBlack(board, y1, x1) <= 0;
        int j = 1;
        //vertical || horizontal checks
        if (y == blackKingY && y != y1)
            if (x < blackKingX) {
                while (x - j > -1 && Rules.emptyFieldCheck(board, y, x - j)) j++;
                if (x - j > -1 && (board.board[y][x - j] == 'r' || board.board[y][x - j] == 'q'))
                    return false;
            } else {
                while (x + j < 8 && Rules.emptyFieldCheck(board, y, x + j)) j++;
                if (x + j < 8 && (board.board[y][x + j] == 'r' || board.board[y][x + j] == 'q'))
                    return false;
            }
        if (x == blackKingX && x != x1) {
            if (y < blackKingY) {
                while (y - j > -1 && Rules.emptyFieldCheck(board, y - j, x)) j++;
                if (y - j > -1 && (board.board[y - j][x] == 'r' || board.board[y - j][x] == 'q'))
                    return false;
            } else {
                while (y + j < 8 && Rules.emptyFieldCheck(board, y + j, x)) j++;
                if (y + j < 8 && (board.board[y + j][x] == 'r' || board.board[y + j][x] == 'q'))
                    return false;
            }
        }
        //diagonal checks
        if (blackKingY - y == blackKingX - x && y1 - y != x1 - x) {
            if (x < blackKingX) {
                while (y - j > -1 && x - j > -1 && Rules.emptyFieldCheck(board, y - j, x - j)) j++;
                if (y - j > -1 && x - j > -1 && (board.board[y - j][x - j] == 'b' || board.board[y - j][x - j] == 'q'))
                    return false;
            } else {
                while (y + j < 8 && x + j < 8 && Rules.emptyFieldCheck(board, y + j, x + j)) j++;
                if (y + j < 8 && x + j < 8 && (board.board[y + j][x + j] == 'b' || board.board[y + j][x + j] == 'q'))
                    return false;
            }
        }
        if (blackKingY - y == (blackKingX - x) * -1 && y1 - y != (x1 - x) * -1) {
            if (x < blackKingX) {
                while (y + j < 8 && x - j > -1 && Rules.emptyFieldCheck(board, y + j, x - j)) j++;
                return y + j >= 8 || x - j <= -1 || (board.board[y + j][x - j] != 'b' && board.board[y + j][x - j] != 'q');
            } else {
                while (y - j > -1 && x + j < 8 && Rules.emptyFieldCheck(board, y - j, x + j)) j++;
                return y - j <= -1 || x + j >= 8 || (board.board[y - j][x + j] != 'b' && board.board[y - j][x + j] != 'q');
            }
        }
        return true;
    }

    public static boolean whiteTurnValidate(Board board, int y, int x, int y1, int x1, int whiteKingY, int whiteKingX) {
        if (board.board[y][x] == 'k')
            return getCheckTimesForTileWhite(board, y1, x1) <= 0;
        int j = 1;
        //vertical || horizontal checks
        if (y == whiteKingY && y != y1)
            if (x < whiteKingX) {
                while (x - j > -1 && Rules.emptyFieldCheck(board, y, x - j)) j++;
                if (x - j > -1 && (board.board[y][x - j] == 'R' || board.board[y][x - j] == 'Q'))
                    return false;
            } else {
                while (x + j < 8 && Rules.emptyFieldCheck(board, y, x + j)) j++;
                if (x + j < 8 && (board.board[y][x + j] == 'R' || board.board[y][x + j] == 'Q'))
                    return false;
            }
        if (x == whiteKingX && x != x1) {
            if (y < whiteKingY) {
                while (y - j > -1 && Rules.emptyFieldCheck(board, y - j, x)) j++;
                if (y - j > -1 && (board.board[y - j][x] == 'R' || board.board[y - j][x] == 'Q'))
                    return false;
            } else {
                while (y + j < 8 && Rules.emptyFieldCheck(board, y + j, x)) j++;
                if (y + j < 8 && (board.board[y + j][x] == 'R' || board.board[y + j][x] == 'Q'))
                    return false;
            }
        }
        //diagonal checks
        if (whiteKingY - y == whiteKingX - x && y1 - y != x1 - x) {
            if (x < whiteKingX) {
                while (y - j > -1 && x - j > -1 && Rules.emptyFieldCheck(board, y - j, x - j)) j++;
                if (y - j > -1 && x - j > -1 && (board.board[y - j][x - j] == 'B' || board.board[y - j][x - j] == 'Q'))
                    return false;
            } else {
                while (y + j < 8 && x + j < 8 && Rules.emptyFieldCheck(board, y + j, x + j)) j++;
                if (y + j < 8 && x + j < 8 && (board.board[y + j][x + j] == 'B' || board.board[y + j][x + j] == 'Q'))
                    return false;
            }
        }
        if (whiteKingY - y == (whiteKingX - x) * -1 && y1 - y != (x1 - x) * -1) {
            if (x < whiteKingX) {
                while (y + j < 8 && x - j > -1 && Rules.emptyFieldCheck(board, y + j, x - j)) j++;
                return y + j >= 8 || x - j <= -1 || (board.board[y + j][x - j] != 'B' && board.board[y + j][x - j] != 'Q');
            } else {
                while (y - j > -1 && x + j < 8 && Rules.emptyFieldCheck(board, y - j, x + j)) j++;
                return y - j <= -1 || x + j >= 8 || (board.board[y - j][x + j] != 'B' && board.board[y - j][x + j] != 'Q');
            }
        }
        return true;
    }

    public static boolean whiteKingCanMove(Board board, int whiteKingY, int whiteKingX) {
        byte[] moves = Rules.generateMovesForTile(board, whiteKingY, whiteKingX);
        if (moves[0] < 2)
            return false;
        for (int i = 1; i < moves[0]; i += 2)
            if (whiteTurnValidate(board, whiteKingY, whiteKingX, moves[i], moves[i + 1], whiteKingY, whiteKingX))
                return true;
        return false;
    }

    public static boolean blackKingCanMove(Board board, int blackKingY, int blackKingX) {
        byte[] moves = Rules.generateMovesForTile(board, blackKingY, blackKingX);
        if (moves[0] < 2)
            return false;
        for (int i = 1; i < moves[0]; i += 2)
            if (blackTurnValidate(board, blackKingY, blackKingX, moves[i], moves[i + 1], blackKingY, blackKingX))
                return true;
        return false;
    }

    public static boolean blackFigureCanBeEaten(Board board, int y, int x, int whiteKingY, int whiteKingX) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (Rules.whiteFigureCheck(board, i, j)) {
                    byte[] moves = Rules.generateMovesForTile(board, i, j);
                    for (int c = 1; c < moves[0]; c += 2)
                        if (moves[c] == y && moves[c + 1] == x)
                            if (whiteTurnValidate(board, i, j, y, x, whiteKingY, whiteKingX))
                                return true;
                }
        return false;
    }

    public static boolean whiteFigureCanBeEaten(Board board, int y, int x, int blackKingY, int blackKingX) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (Rules.blackFigureCheck(board, i, j)) {
                    byte[] moves = Rules.generateMovesForTile(board, i, j);
                    for (int c = 1; c < moves[0]; c += 2)
                        if (moves[c] == y && moves[c + 1] == x)
                            if (blackTurnValidate(board, i, j, y, x, blackKingY, blackKingX))
                                return true;
                }
        return false;
    }
}
