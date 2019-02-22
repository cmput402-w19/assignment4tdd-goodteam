package cs.ualberta.cmput402.boardgame.board;

public class Board {

    public enum Player { RED, BLUE };
    public Square board[][];
    private int size = 5;
    private Player currentPlayer;
    private Player winner;
    
    public Board(){
	board = new Square[size][size];
	currentPlayer = Player.RED;
	winner = null;
	initBoard();
    }

    public void initBoard(){

	for (int i = 0; i < size; i++){
	    for(int j = 0; j < size; j++){
		
		Square square = new Square();
		boolean placePiece = false;
		String color = "";

		//if first row
		if (i == 0){
		    color = "RED";
		    placePiece = true;
		//or last row
		}else if(i == size-1){
		    color = "BLUE";
		    placePiece = true;
		}
		//if first or last row and middle column
		if(placePiece && (j == 2)){
		    square.setShrine();
		    boolean master = true;
		}
		    
		if(placePiece){
		    square.placePiece(color, placePiece);
		}
		
		board[i][j] = square;
		
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
