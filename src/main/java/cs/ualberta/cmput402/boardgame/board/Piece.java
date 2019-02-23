package cs.ualberta.cmput402.boardgame.board;

public class Piece{

    private Board.Team team;
    private boolean isMaster;
    
    public Piece(Board.Team team, boolean master){
	this.team = team;
	isMaster = master;
    }

    public boolean isMaster(){
	return isMaster;
    }

    public Board.Team getTeam(){
	return team;
    }
}
