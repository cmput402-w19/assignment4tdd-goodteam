package cs.ualberta.cmput402.boardgame.board;

import cs.ualberta.cmput402.boardgame.Player;

public class Square {

    
    public enum State { OCCUPIED, EMPTY };
    private State squareState;
    private Piece pieceOnSquare;
    private boolean isShrine;
    //this is only set for two squares, but its just
    //as wasteful as an enum with mostly none value...
    private Player.Team shrineTeam;
    
    public Square(){
	squareState = State.EMPTY;
	pieceOnSquare = null;
	isShrine = false;
    }

    public State getState(){
	return squareState;
    }

    public Piece getPiece(){
	return pieceOnSquare;
    }

    public void removePiece(){
	pieceOnSquare = null;
	squareState = State.EMPTY;
    }
    
    public void placePiece(Player.Team team, boolean master){
	pieceOnSquare = new Piece(team, master);
	squareState = State.OCCUPIED;
    }
    
    public void setShrine(Player.Team team){
	isShrine = true;
	shrineTeam = team;
    }

    public boolean isShrine(){
	return isShrine;
    }

    public Player.Team belongsTo(){
	if(isShrine){
	    return shrineTeam;
	}
	return null;
    }
}
