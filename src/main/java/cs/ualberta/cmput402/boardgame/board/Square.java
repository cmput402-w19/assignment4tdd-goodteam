package cs.ualberta.cmput402.boardgame.board;

public class Square {

    
    public enum State { OCCUPIED, EMPTY };
    private State squareState;
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
    
    public void placePiece(Board.Team team, boolean master){
	pieceOnSquare = new Piece(team, master);
	squareState = State.OCCUPIED;
    }
    
    public void setShrine(){
	isShrine = true;
    }

    public boolean isShrine(){
	return isShrine;
    }
}
