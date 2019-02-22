package cs.ualberta.cmput402.boardgame.board;

public class Board {

    public enum Player { RED, BLUE };
    //just for now, will refactor to be real object list 
    public enum Square { OCCUPIED, EMPTY};
    public Square board[][];
    private int size = 5;
    private Player currentPlayer;
    private Player winner;
    
    public Board(){
	board = new Square[size][size];
	initBoard();
    }

    public void initBoard(){
	currentPlayer = Player.RED;
	winner = null;

	for (int i = 0; i < size; i++){
	    for(int j = 0; j < size; j++){
		if (i == 0 || i == size-1){
		    board[i][j] = Square.OCCUPIED;
		}else{
		    board[i][j] = Square.EMPTY;
		}
	    }
	}
    }
    
    public int getSize(){
	return size;
    }

    public Player getCurrentPlayer(){
	return currentPlayer;
    }

    public Player getWinner(){
	return winner;
    }
	
    public Square getSquareAtPos(int i, int j){
	return board[i][j];
    }
    
}
