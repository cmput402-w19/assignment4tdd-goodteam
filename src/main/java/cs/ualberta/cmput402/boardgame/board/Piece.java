package cs.ualberta.cmput402.boardgame.board;

import cs.ualberta.cmput402.boardgame.Player;

public class Piece{

    private Player.Team team;
    private boolean isMaster;
    
    public Piece(Player.Team team, boolean master){
	this.team = team;
	isMaster = master;
    }

    public boolean isMaster(){
	return isMaster;
    }

    public Player.Team getTeam(){
	return team;
    }
}
