package cs.ualberta.cmput402.boardgame.board;

public class Piece{

    private String team;
    private boolean masterStatus;
    
    public Piece(String color, boolean master){
	team = color;
	masterStatus = master;
    }

    public boolean isMaster(){
	return masterStatus;
    }

    public String getTeam(){
	return team;
    }
    
}
