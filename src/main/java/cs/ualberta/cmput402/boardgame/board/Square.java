package cs.ualberta.cmput402.boardgame.board;

public class Square {

    
    public enum squareState { PLACEHOLDER};
    private enum Piece { PLACEHOLDER };
    private Piece pieceOnSquare;
    private boolean isShrine;
    
    public Square(){
	pieceOnSquare = null;
	isShrine = false;
    }

    public String getState(){
	return("");
    }
    
    public String toString(){
	squareState state = squareState.PLACEHOLDER;
	return state.toString();
    }

    public void placePiece(String piece){
	
    }
    
    public void setShrine(){
	
    }

    public boolean isShrine(){
	return(false);
    }
}
