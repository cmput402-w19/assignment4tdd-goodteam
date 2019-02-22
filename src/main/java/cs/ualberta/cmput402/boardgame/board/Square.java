package cs.ualberta.cmput402.boardgame.board;

public class Square {

    
    public enum State { OCCUPIED, EMPTY };
    private State squareState;
    public enum Piece { RED, BLUE };
    private Piece pieceOnSquare;
    private boolean isShrine;
    
    public Square(){
	squareState = State.EMPTY;
	pieceOnSquare = null;
	isShrine = false;
    }

    public String getState(){
	switch(squareState){
	    case EMPTY:
		return "EMPTY";
	    case OCCUPIED:
		return "OCCUPIED";
	    default:
		return "";
	}
    }
    
    public void placePiece(String piece){
	if(piece == "RED"){
	    pieceOnSquare = Piece.RED;
	}else{
	    pieceOnSquare = Piece.BLUE;
	}
	squareState = State.OCCUPIED;
    }
    
    public void setShrine(){
	isShrine = true;
    }

    public boolean isShrine(){
	return isShrine;
    }
}
