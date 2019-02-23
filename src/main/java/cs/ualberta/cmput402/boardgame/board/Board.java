package cs.ualberta.cmput402.boardgame.board;

public class Board {

    public enum Team {
	RED, BLUE;

	public String toString(){
	    switch(this){
	    case RED :
            return "RED";
	    case BLUE :
            return "BLUE";
	    }
        return null;
    }
    };
    
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

    public void initBoard(){

	for (int i = 0; i < size; i++){
	    for(int j = 0; j < size; j++){
		
		Square square = new Square();
		boolean placePiece = false;
		Team team = null;

		//if first row
		if (i == 0){
		    team = Team.RED;
		    placePiece = true;
		//or last row
		}else if(i == size-1){
		    team = Team.BLUE;
		    placePiece = true;
		}
		//if first or last row and middle column
		if(placePiece && (j == 2)){
		    square.setShrine();
		    boolean master = true;
		}
		    
		if(placePiece){
		    square.placePiece(team, placePiece);
		}
		
		board[i][j] = square;
		
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
