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
    public boolean leftBlackRockMoved = false, rightBlackRockMoved = false;
    public boolean leftWhiteRockMoved = false, rightWhiteRockMoved = false;
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
                board[i][j] = (byte) (((i + j + 1) % 2 == 0) ? '#' : '0');
    }

    public void display() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print((char) board[i][j]);
            System.out.println();
        }
    }

    public String toTransfer() {
        StringBuilder toReturn = new StringBuilder(64);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                toReturn.append((char) board[i][j]);
        return toReturn.toString();
    }

    public void makeTurn(int y, int x, int y1, int x1) {
        switch (y) {
            case 0 -> {
                switch (x) {
                    case 0 -> leftBlackRockMoved = true;
                    case 4 -> blackKingMoved = true;
                    case 7 -> rightBlackRockMoved = true;
                }
            }
            case 7 -> {
                switch (x) {
                    case 0 -> leftWhiteRockMoved = true;
                    case 4 -> whiteKingMoved = true;
                    case 7 -> rightWhiteRockMoved = true;
                }

            }
        }
        switch (board[y][x]) {
            case 'K' -> {
                if (Math.abs(x1 - x) == 2) {
                    if (x1 == 2) {
                        board[0][3] = 'R';
                        board[0][0] = '0';
                    } else {
                        board[0][5] = 'R';
                        board[0][7] = '#';
                    }
                }
            }
            case 'k' -> {
                if (Math.abs(x1 - x) == 2) {
                    if (x1 == 2) {
                        board[7][3] = 'r';
                        board[7][0] = '0';
                    } else {
                        board[7][5] = 'r';
                        board[7][7] = '#';
                    }
                }
            }
            case 'P' -> {
                if (this.board[this.y2][this.x1] == 'p' && this.x1 == this.x2 && this.y2 - this.y1 == -2 && this.y2 == y &&
                        (this.x2 - x == -1 || this.x2 - x == 1))
                    board[this.y2][this.x1] = (byte) (((this.y2 + this.x2 + 1) % 2 == 0) ? '#' : '0');

            }
            case 'p' -> {
                if (this.board[this.y2][this.x1] == 'P' && this.x1 == this.x2 && this.y2 - this.y1 == 2 && this.y2 == y &&
                        (this.x2 - x == -1 || this.x2 - x == 1))
                    board[this.y2][this.x1] = (byte) (((this.y2 + this.x2 + 1) % 2 == 0) ? '#' : '0');
            }
        }
        defaultMove(y, x, y1, x1);
    }

    private void defaultMove(int y, int x, int y1, int x1) {
        board[y1][x1] = board[y][x];
        isCurrentTurnWhite = !isCurrentTurnWhite;
        board[y][x] = (byte) (((y + x + 1) % 2 == 0) ? '#' : '0');
        this.x1 = (byte) x;
        this.y1 = (byte) y;
        this.x2 = (byte) x1;
        this.y2 = (byte) y1;
    }
}

