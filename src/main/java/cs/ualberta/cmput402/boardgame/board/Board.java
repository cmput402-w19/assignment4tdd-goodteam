package cs.ualberta.cmput402.boardgame.board;

public class Board {

    public enum Square { PLACEHOLDER };
    public enum Player { PLACEHOLDER };
    public Square board[][];
    
    public Board(){

    }

    public void initBoard(){
       
    }
    
    public int getSize(){
	return 0;
    }

    public Player getCurrentPlayer(){
	Player player = Player.PLACEHOLDER;
	return player;
    }

    public Player getWinner(){

    Player player = Player.PLACEHOLDER;
        return player;
    }
	
    public Square getSquareAtPos(int i, int j){
	Square square = Square.PLACEHOLDER;
	return square;
    }
    
}
