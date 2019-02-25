package cs.ualberta.cmput402.boardgame.board;

public class Board {

    public enum Team { RED, BLUE };
    
    public Square board[][];
    private int size = 5;
    private Team currentPlayer;
    private Team winner;

    public Board(){
	board = new Square[size][size];
	currentPlayer = Team.RED;
	winner = null;
	initBoard();
    }

    public void initBoard() {
        //init board of squares
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Square();
            }
        }
        //place pieces on first and last row
        for (int j = 0; j < size; j++) {
            if (j == 2) {
                //if middle of row, place shrine and master
                board[0][j].setShrine();
                board[size-1][j].setShrine();
                board[0][j].placePiece(Piece.RED_MASTER);
                board[size-1][j].placePiece(Piece.BLUE_MASTER);
            } else {
                board[0][j].placePiece(Piece.RED_STUDENT);
                board[size-1][j].placePiece(Piece.BLUE_STUDENT);
            }
        }
    }

    public int getSize(){
	return size;
    }

    public Team getCurrentPlayer(){
	return currentPlayer;
    }

    public Team getWinner(){
	return winner;
    }

    public Square getSquareAtPos(int i, int j){
	return board[i][j];
    }
    
}
