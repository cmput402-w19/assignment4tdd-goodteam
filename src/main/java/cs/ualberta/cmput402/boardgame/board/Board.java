package cs.ualberta.cmput402.boardgame.board;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.Player;

public class Board {

    
    private Square board[][];
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

	Collections.shuffle(deck);
	
	for (int i = 0; i < 2; i++){
	    currentPlayer.setMove(deck.get(i), i);
	    deck.remove(i);
            idlePlayer.setMove(deck.get(i), i);
            deck.remove(i);
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

    public boolean onBoard(int xCoord, int yCoord){
	if(xCoord > size-1 || yCoord > size-1 || xCoord < 0 || yCoord < 0){
	    return false;
	}
	return true;
    }
    

    public boolean playPiece(int oldx, int oldy, int x, int y){
	//if its on the board
	if(onBoard(x, y)){
	    //check if that square is empty, or occupied by enemy
	    Square newSquare = getSquareAtPos(x, y);
	    if(((newSquare.getState().equals(Square.State.EMPTY)) || newSquare.getPiece().getTeam().equals(idlePlayer.getTeam()))){
		Square oldSquare = getSquareAtPos(oldx, oldy);
		Piece oldpiece = oldSquare.getPiece();
		oldSquare.removePiece();
		newSquare.placePiece(oldpiece.getTeam(), oldpiece.isMaster()); 
		return true;
	    }
	}
	return false;
    }
    
    public void otherPlayerTurn(){
	//classic swap
	Player temp = idlePlayer;
	idlePlayer = currentPlayer;
	currentPlayer = temp;
    }

    public void deselectMove(int i){
	getCurrentPlayer().getMove(0).deselect();
    }
    

    public void selectMove(int i){
	getCurrentPlayer().getMove(0).select();
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
	
    public Square getSquareAtPos(int x, int y){
	return board[y][x];
    }
    
}
