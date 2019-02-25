package cs.ualberta.cmput402.boardgame.board;

import java.util.Random;
import java.util.ArrayList;
import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.Player;

public class Board {

    
    public Square board[][];
    private int size = 5;
    private Player currentPlayer, idlePlayer;
    private Player winner;
    private ArrayList<Move> deck =
	 new ArrayList<Move>() {{
	    add(Move.DRAGON);
	    add(Move.MONKEY);
	    add(Move.ELEPHANT);
	    add(Move.TIGER);
	    add(Move.GOOSE);
	}};
    
    public Board(){
	board = new Square[size][size];
	winner = null;
	setupPlayers();
	initBoard();
    }

    public void setupPlayers(){
	//set current and then deal cards random
	currentPlayer = new Player(Player.Team.RED);
	idlePlayer = new Player(Player.Team.BLUE);

	for (int i = 0; i < 2; i++){
	    //thank you Chris Dennett
	    //https://stackoverflow.com/questions/8065532/how-to-randomly-pick-an-element-from-an-array
	    int rnd = new Random().nextInt(deck.size());
	    currentPlayer.setMove(deck.get(rnd));
	    deck.remove(rnd);
	    int rnd2 = new Random().nextInt(deck.size());
            idlePlayer.setMove(deck.get(rnd2));
            deck.remove(rnd2);
	}
    }
    
    public void initBoard(){

	//init board of squares
	for (int i = 0; i < size; i++){
	    for(int j = 0; j < size; j++){
		board[i][j] = new Square();
	    }
	}
	//place pieces on first and last row
	for(int j = 0; j < size; j++){
	    if(j == 2){
		//if middle of row, place shrine and master
		board[0][j].setShrine();
		board[size-1][j].setShrine();
		board[0][j].placePiece(Player.Team.RED, true);
		board[size-1][j].placePiece(Player.Team.BLUE, true);
	    }else{
		board[0][j].placePiece(Player.Team.RED, false);
		board[size-1][j].placePiece(Player.Team.BLUE, false);
	    }
	}
    }

    public void nextTurn(){
	//classic swap
	Player temp = idlePlayer;
	idlePlayer = currentPlayer;
	currentPlayer = temp;
    }


    public void selectMove(int i){
	getCurrentPlayer().getMoves().get(0).select();
    }
    
    public Move getExtraMove(){
	return deck.get(0);
    }
    
    public int getSize(){
	return size;
    }

    public Player getCurrentPlayer(){
	return currentPlayer;
    }

    public Player getWinner(){
	return winner;
    }
	
    public Square getSquareAtPos(int i, int j){
	return board[i][j];
    }
    
}
