package cs.ualberta.cmput402.boardgame;

import java.util.ArrayList;

public class Player{

    private ArrayList<Move> moves;
    public enum Team {RED, BLUE};
    private Team team;
    
    public Player(Team team){
	this.team = team;
	moves = new ArrayList<Move>();
    }

    public Team getTeam(){
	return team;
    }
    
    public void setMove(Move move){       
	moves.add(move);	
    }
    


    public void removeMove(int i){
	moves.remove(i);
    }


    public ArrayList<Move> getMoves(){
        return moves;
    }
    
}
