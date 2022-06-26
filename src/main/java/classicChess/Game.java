package classicChess;

public class Game {

    private byte currentWhiteKingY = 7, currentWhiteKingX = 4;
    private byte currentBlackKingY = 0, currentBlackKingX = 4;

    private byte currentAttackingFigureY = -1, currentAttackingFigureX = -1;


    public Game() {
        board = new Board();
    }

    private Board board;

    public Board getBoard() {
        return board;
    }

    public byte[] generateMovesForTile(int y, int x) {
        return Rules.generateMovesForTile(board, y, x);
    }

    public void makeTurn(int y, int x, int y1, int x1) {
        /*if (board.isCurrentTurnWhite) {
            Validations.whiteTurnValidate(board, y, x, y1, x1, currentWhiteKingY, currentWhiteKingX);
        } else {
            Validations.blackTurnValidate(board, y, x, y1, x1, currentBlackKingY, currentBlackKingX);
        }*/
        if (currentAttackingFigureY != -1) {
            if (Rules.whiteFigureCheck(board, currentAttackingFigureY, currentAttackingFigureX) && Rules.blackFigureCheck(board, y, x)) {
                if (board.board[y][x] != 'K' && (y1 != currentAttackingFigureY && x1 != currentAttackingFigureX))
                    throw new RuntimeException("Invalid move");
            }
            if (Rules.blackFigureCheck(board, currentAttackingFigureY, currentAttackingFigureX) && Rules.whiteFigureCheck(board, y, x)) {
                if (board.board[y][x] != 'k' && (y1 != currentAttackingFigureY && x1 != currentAttackingFigureX))
                    throw new RuntimeException("Invalid move");
            }
        }
        if (Rules.whiteFigureCheck(board, y, x)) {
            if(!Validations.whiteTurnValidate(board, y, x, y1, x1, currentWhiteKingY, currentWhiteKingX))
                throw new RuntimeException("Invalid move");
        }
        if (Rules.blackFigureCheck(board, y, x))
            if(!Validations.blackTurnValidate(board, y, x, y1, x1, currentBlackKingY, currentBlackKingX))
                throw new RuntimeException("Invalid move");
        specialMovesCheckAndExtraChangesToBoard(y, x, x1, y1);
        defaultMove(y, x, y1, x1);
        syncMoveWithBooleans(y, x);
        checkAndMateCheck(y1, x1);
    }

    private void checkAndMateCheck(int y1, int x1) {
        byte[] moves = Rules.generateMovesForTile(board, y1, x1);
        if (Rules.blackFigureCheck(board, y1, x1)) {
            for (int i = 1; i < moves[0]; i += 2)
                if (board.board[moves[i]][moves[i + 1]] == 'k') {
                    currentAttackingFigureX = (byte) x1;
                    currentAttackingFigureY = (byte) y1;
                    break;
                }
            if (currentAttackingFigureY != -1 && !Validations.whiteKingCanMove(board, currentWhiteKingY, currentWhiteKingX) &&
                    !Validations.blackFigureCanBeEaten(board, currentAttackingFigureY, currentAttackingFigureX, currentWhiteKingY, currentWhiteKingX))
                System.out.println("Black wins");
        } else {
            for (int i = 1; i < moves[0]; i += 2)
                if (board.board[moves[i]][moves[i + 1]] == 'K') {
                    currentAttackingFigureX = (byte) x1;
                    currentAttackingFigureY = (byte) y1;
                    break;
                }
            if (currentAttackingFigureY != -1 && !Validations.blackKingCanMove(board, currentBlackKingY, currentBlackKingX) &&
                    !Validations.whiteFigureCanBeEaten(board, currentAttackingFigureY, currentAttackingFigureX, currentBlackKingY, currentBlackKingX))
                System.out.println("White wins");
        }
    }

    private void specialMovesCheckAndExtraChangesToBoard(int y, int x, int x1, int y1) {
        switch (board.board[y][x]) {
            case 'K' -> {
                if (Math.abs(x1 - x) == 2) {
                    if(!Validations.blackCastlingValidation(board, x1))
                        throw new RuntimeException("Invalid move");
                    if (x1 == 2) {
                        board.board[0][3] = 'R';
                        board.board[0][0] = '0';
                    } else {
                        board.board[0][5] = 'R';
                        board.board[0][7] = '#';
                    }
                }
                currentBlackKingX = (byte) x1;
                currentBlackKingY = (byte) y1;
            }
            case 'k' -> {
                if (Math.abs(x1 - x) == 2) {
                    if(!Validations.whiteCastlingValidation(board, x1))
                        throw new RuntimeException("Invalid move");
                    if (x1 == 2) {
                        board.board[7][3] = 'r';
                        board.board[7][0] = '0';
                    } else {
                        board.board[7][5] = 'r';
                        board.board[7][7] = '#';
                    }
                }
                currentWhiteKingX = (byte) x1;
                currentWhiteKingY = (byte) y1;
            }
            case 'P' -> {
                if (board.board[board.y2][board.x1] == 'p' && board.x1 == board.x2 && board.y2 - board.y1 == -2 && board.y2 == y &&
                        (board.x2 - x == -1 || board.x2 - x == 1))
                    board.board[board.y2][board.x1] = (byte) (((board.y2 + board.x2 + 1) % 2 == 0) ? '#' : '0');

            }
            case 'p' -> {
                if (board.board[board.y2][board.x1] == 'P' && board.x1 == board.x2 && board.y2 - board.y1 == 2 && board.y2 == y &&
                        (board.x2 - x == -1 || board.x2 - x == 1))
                    board.board[board.y2][board.x1] = (byte) (((board.y2 + board.x2 + 1) % 2 == 0) ? '#' : '0');
            }
        }
    }

    private void syncMoveWithBooleans(int y, int x) {
        switch (y) {
            case 0 -> {
                switch (x) {
                    case 0 -> board.leftBlackRockMoved = true;
                    case 4 -> board.blackKingMoved = true;
                    case 7 -> board.rightBlackRockMoved = true;
                }
            }
            case 7 -> {
                switch (x) {
                    case 0 -> board.leftWhiteRockMoved = true;
                    case 4 -> board.whiteKingMoved = true;
                    case 7 -> board.rightWhiteRockMoved = true;
                }
            }
        }
    }

    private void defaultMove(int y, int x, int y1, int x1) {
        board.board[y1][x1] = board.board[y][x];
        board.isCurrentTurnWhite = !board.isCurrentTurnWhite;
        board.board[y][x] = (byte) (((y + x + 1) % 2 == 0) ? '#' : '0');
        board.x1 = (byte) x;
        board.y1 = (byte) y;
        board.x2 = (byte) x1;
        board.y2 = (byte) y1;
        currentAttackingFigureY = -1;
        currentAttackingFigureX = -1;
    }
}
