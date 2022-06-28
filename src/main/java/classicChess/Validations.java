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

    public static boolean castlingValidation(Board board, int x1) {
        if (board.isCurrentTurnWhite)
            return whiteCastlingValidation(board, x1);
        else return blackCastlingValidation(board, x1);
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

    public static boolean turnValidate(Board board, int whiteKingY, int whiteKingX, int blackKingY, int blackKingX) {
        if (board.isCurrentTurnWhite) {
            return getCheckTimesForTileWhite(board, whiteKingY, whiteKingX) == 0;
        } else {
            return getCheckTimesForTileBlack(board, blackKingY, blackKingX) == 0;
        }
    }

}
