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

	//init mid board
	for (int i = 1; i < size-1; i++){
	    for(int j = 0; j < size; j++){
		board[i][j] = new Square();
	    }
	}
	//init first and last row
	for(int j = 0; j < size; j++){
	    Square squareFirstRow = new Square();
	    Square squareLastRow = new Square();
	    if(j == 2){
		//if middle of row, place shrine and master
		squareFirstRow.setShrine();
		squareLastRow.setShrine();
		squareFirstRow.placePiece(Team.RED, true);
		squareLastRow.placePiece(Team.BLUE, true);
	    }else{
		squareFirstRow.placePiece(Team.RED, false);
		squareLastRow.placePiece(Team.BLUE, false);
	    }
	    
	    board[0][j] = squareFirstRow;
	    board[size-1][j] = squareLastRow;
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
