package cs.ualberta.cmput402.boardgame;

import java.awt.Color;

public class Player{

    public enum Team {
        RED(Color.RED),
        BLUE(Color.BLUE);

        // The team color.
        private Color color;

        Team(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    private Move[] moves;
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
    

    public void replaceMove(Move move, int idx){
	moves[idx] = move;
    }

    
    public Move[] getMoves(){
        return moves;
    }

    public Move getMove(int idx){
	return moves[idx];
    }
    
}
