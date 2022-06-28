package classicChess;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private byte currentWhiteKingY = 7, currentWhiteKingX = 4;
    private byte currentBlackKingY = 0, currentBlackKingX = 4;

    int currentState = 0;


    public Game() {
        board = new Board();
    }

    public Game(Game game) {
        this.board = new Board(game.getBoard());
        this.currentBlackKingY = game.currentBlackKingY;
        this.currentBlackKingX = game.currentBlackKingX;
        this.currentWhiteKingY = game.currentWhiteKingY;
        this.currentWhiteKingX = game.currentWhiteKingX;
        this.currentState = game.currentState;
    }

    private final Board board;

    public Board getBoard() {
        return board;
    }

    public byte[] generateMovesForTile(int y, int x) {
        return Rules.generateMovesForTile(board, y, x);
    }

    public void makeTurn(int y, int x, int y1, int x1) {
        if (currentState > 0) {
            return;
        }
        if (!validateTurn(y, x, y1, x1))
            throw new RuntimeException("Invalid move");
        int moveType = getMoveType(y, x, y1, x1);
        makeTempTurn(y, x, y1, x1);
        if (moveType > 0)
            specialTurnDoChanges(moveType, y, x, y1, x1);
        syncMoveWithBooleans(y, x);
        logTurnToBoard(y, x, y1, x1);
        transformPawnsToQueens(y1, x1);
        board.isCurrentTurnWhite = !board.isCurrentTurnWhite;
        currentState = checkAndMateCheck();
        if (currentState > 0) {
            switch (currentState) {
                case 1 -> System.out.println("Draw");
                case 2 -> System.out.println("White wins");
                case 3 -> System.out.println("Black wins");
            }
        }
    }

    private void transformPawnsToQueens(int y1, int x1) {
        if (board.board[y1][x1] == 'P' && y1 == 7) {
            board.board[y1][x1] = 'Q';
        }
        if (board.board[y1][x1] == 'p' && y1 == 0) {
            board.board[y1][x1] = 'q';
        }
    }

    public void makeTurn(int turn) {
        int x1 = turn / 8 / 8 / 8;
        int y1 = (turn - x1 * 8 * 8 * 8) / 8 / 8;
        int x = (turn - x1 * 8 * 8 * 8 - y1 * 8 * 8) / 8;
        int y = (turn - x1 * 8 * 8 * 8 - y1 * 8 * 8 - x * 8);
        if (!board.isCurrentTurnWhite) {
            y = rotateMove(y);
            y1 = rotateMove(y1);
        }
        makeTurn(y, x, y1, x1);
    }

    private boolean validateTurn(int y, int x, int y1, int x1) {
        boolean toReturn;
        byte tempToRemember = board.board[y1][x1];
        int moveType = getMoveType(y, x, y1, x1);
        makeTempTurn(y, x, y1, x1);
        if (moveType > 0)
            specialTurnDoChanges(moveType, y, x, y1, x1);
        if (moveType == 1 || moveType == 2)
            toReturn = Validations.castlingValidation(board, x1);
        else
            toReturn = Validations.turnValidate(board, currentWhiteKingY, currentWhiteKingX, currentBlackKingY, currentBlackKingX);
        undoMove(tempToRemember, y, x, y1, x1);
        if (moveType > 0)
            specialTurnUndoChanges(moveType, y, x, y1, x1);
        return toReturn;
    }

    //0 - game is aliva
    //1 - draw
    //2 - white wins
    //3 - black wins
    private int checkAndMateCheck() {
        int toReturn = 0;
        if (board.isCurrentTurnWhite) {
            if (!whiteHaveTurns())
                toReturn = (Validations.getCheckTimesForTileWhite(board, currentWhiteKingY, currentWhiteKingX) > 0) ? 3 : 1;
        } else {
            if (!blackHaveTurns())
                toReturn = (Validations.getCheckTimesForTileBlack(board, currentBlackKingY, currentBlackKingX) > 0) ? 3 : 1;
        }
        return toReturn;
    }

    private boolean whiteHaveTurns() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (Rules.whiteFigureCheck(board, i, j)) {
                    byte[] moves = Rules.generateMovesForTile(board, i, j);
                    for (int c = 1; c < moves[0]; c += 2)
                        if (validateTurn(i, j, moves[c], moves[c + 1]))
                            return true;
                }
        return false;
    }

    private boolean blackHaveTurns() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (Rules.blackFigureCheck(board, i, j)) {
                    byte[] moves = Rules.generateMovesForTile(board, i, j);
                    for (int c = 1; c < moves[0]; c += 2)
                        if (validateTurn(i, j, moves[c], moves[c + 1]))
                            return true;
                }
        return false;
    }

    private void makeTempTurn(int y, int x, int y1, int x1) {
        board.board[y1][x1] = board.board[y][x];
        board.board[y][x] = (byte) (((y + x + 1) % 2 == 0) ? '#' : '0');
        syncMoveWithKingsPos(y1, x1);
    }

    private void undoMove(byte tempToRemember, int y, int x, int y1, int x1) {
        board.board[y][x] = board.board[y1][x1];
        board.board[y1][x1] = tempToRemember;
        syncMoveWithKingsPos(y, x);
    }

    private void specialTurnUndoChanges(int type, int y, int x, int y1, int x1) {
        switch (type) {
            case 1 -> {
                if (x1 == 2) {
                    board.board[0][3] = '#';
                    board.board[0][0] = 'R';
                } else {
                    board.board[0][5] = '#';
                    board.board[0][7] = 'R';
                }
            }
            case 2 -> {
                if (x1 == 2) {
                    board.board[7][3] = '0';
                    board.board[7][0] = 'R';
                } else {
                    board.board[7][5] = '0';
                    board.board[7][7] = 'R';
                }

            }
            case 3 -> board.board[board.y2][board.x1] = 'p';
            case 4 -> board.board[board.y2][board.x1] = 'P';
        }
    }

    private void specialTurnDoChanges(int type, int y, int x, int y1, int x1) {
        switch (type) {
            case 1 -> {
                if (x1 == 2) {
                    board.board[0][3] = 'R';
                    board.board[0][0] = '0';
                } else {
                    board.board[0][5] = 'R';
                    board.board[0][7] = '#';
                }
            }
            case 2 -> {
                if (x1 == 2) {
                    board.board[7][3] = 'r';
                    board.board[7][0] = '0';
                } else {
                    board.board[7][5] = 'r';
                    board.board[7][7] = '#';
                }

            }
            case 3, 4 -> board.board[board.y2][board.x1] = (byte) (((board.y2 + board.x2 + 1) % 2 == 0) ? '#' : '0');
        }
    }

    //0 - default move
    //1 - black king castling
    //2 - white king castling
    //3 - black pawn take on pass
    //4 - white pawn take on pass
    private int getMoveType(int y, int x, int y1, int x1) {
        switch (board.board[y][x]) {
            case 'K' -> {
                if (Math.abs(x1 - x) == 2) {
                    return 1;
                }
            }
            case 'k' -> {
                if (Math.abs(x1 - x) == 2) {
                    return 2;
                }
            }
            case 'P' -> {
                if (board.board[board.y2][board.x1] == 'p' && board.x1 == board.x2 && board.y2 - board.y1 == -2 && board.y2 == y &&
                        (board.x2 - x == -1 || board.x2 - x == 1) && board.x1 == x1)
                    return 3;

            }
            case 'p' -> {
                if (board.board[board.y2][board.x1] == 'P' && board.x1 == board.x2 && board.y2 - board.y1 == 2 && board.y2 == y &&
                        (board.x2 - x == -1 || board.x2 - x == 1) && board.x1 == x1)
                    return 4;
            }
        }
        return 0;
    }

    private void syncMoveWithKingsPos(int y1, int x1) {
        if (board.board[y1][x1] == 'K') {
            currentBlackKingX = (byte) x1;
            currentBlackKingY = (byte) y1;
        }
        if (board.board[y1][x1] == 'k') {
            currentWhiteKingX = (byte) x1;
            currentWhiteKingY = (byte) y1;
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

    private void logTurnToBoard(int y, int x, int y1, int x1) {
        board.x1 = (byte) x;
        board.y1 = (byte) y;
        board.x2 = (byte) x1;
        board.y2 = (byte) y1;
    }

    public byte[] getAllValidTurns() {
        byte[] toReturn = new byte[64 * 64];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (!Rules.emptyFieldCheck(board, i, j)) {
                    byte[] moves = Rules.generateMovesForTile(board, i, j);
                    for (int c = 1; c < moves[0]; c += 2) {
                        if (validateTurn(i, j, moves[c], moves[c + 1]))
                            if (board.isCurrentTurnWhite)
                                toReturn[i + j * 8 + moves[c] * 8 * 8 + moves[c + 1] * 8 * 8 * 8] = 1;
                            else
                                toReturn[rotateMove(i) + j * 8 + rotateMove(moves[c] * 8 * 8) + moves[c + 1] * 8 * 8 * 8] = 1;
                    }
                }
        return toReturn;
    }

    private int rotateMove(int y) {
        return (8 - y);
    }
}
