package classicChess;

public class Board {

    //0 - white tile
    //p - white pawn
    //r - white rook
    //a - white knight
    //b - white bishop
    //q - white queen
    //k - white king

    //# - black tile
    //P - black pawn
    //R - black rook
    //A - black knight
    //B - black bishop
    //Q - black queen
    //K - black king

    public byte[][] board = new byte[8][8];
    public boolean whiteKingMoved = false, blackKingMoved = false;
    public boolean isCurrentTurnWhite = true;
    public byte x1, y1, x2, y2;

    public Board() {
        initTiles();
        initWhiteFigures();
        initBlackFigures();
    }

    private void initBlackFigures() {
        board[0][0] = 'R';
        board[0][7] = 'R';
        board[0][1] = 'A';
        board[0][6] = 'A';
        board[0][2] = 'B';
        board[0][5] = 'B';
        board[0][3] = 'Q';
        board[0][4] = 'K';
        for (int i = 0; i < 8; i++)
            board[1][i] = 'P';
    }

    private void initWhiteFigures() {
        board[7][0] = 'r';
        board[7][7] = 'r';
        board[7][1] = 'a';
        board[7][6] = 'a';
        board[7][2] = 'b';
        board[7][5] = 'b';
        board[7][3] = 'q';
        board[7][4] = 'k';
        for (int i = 0; i < 8; i++)
            board[6][i] = 'p';
    }

    private void initTiles() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if ((i + j + 1) % 2 == 0) board[i][j] = '#';
                else board[i][j] = '0';
    }

    public void display() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print((char)board[i][j]);
            System.out.println();
        }
    }

    public String toTransfer() {
        StringBuilder toReturn = new StringBuilder(64);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                toReturn.append((char)board[i][j]);
        return toReturn.toString();
    }
}

