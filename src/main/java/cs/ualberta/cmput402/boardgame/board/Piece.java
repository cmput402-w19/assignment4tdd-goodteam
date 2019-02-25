package cs.ualberta.cmput402.boardgame.board;

public enum Piece{
    RED_STUDENT(Board.Team.RED, false),
    RED_MASTER(Board.Team.RED, true),
    BLUE_STUDENT(Board.Team.BLUE, false),
    BLUE_MASTER(Board.Team.BLUE, true);

    private Board.Team team;
    private boolean isMaster;
    
    Piece(Board.Team team, boolean master){
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
