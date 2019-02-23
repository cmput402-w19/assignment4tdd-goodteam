package cs.ualberta.cmput402.boardgame.board;

public class Piece{

    private Board.Team team;
    private boolean masterStatus;
    
    public Piece(Board.Team team, boolean master){
	this.team = team;
	masterStatus = master;
    }

    public boolean isMaster(){
	return masterStatus;
    }

    public Board.Team getTeam(){
	return team;
    }
}
