package cs.ualberta.cmput402.boardgame;

import java.util.ArrayList;

public class Player{

    private Move[] moves;
    public enum Team {RED, BLUE};
    private Team team;
    private int handSize;
    
    public Player(Team team, int handSize){
	this.team = team;
	this.handSize = handSize;
	moves = new Move[handSize];
    }

    public Team getTeam(){
	return team;
    }
    
    public void setMove(Move move, int idx){       
	moves[idx] = move;	
    }
    


    public void removeMove(int idx){
	moves[idx] = null;
    }

    public Move[] getMoves(){
        return moves;
    }

    public Move getMove(int idx){
	return moves[idx];
    }
    
}
