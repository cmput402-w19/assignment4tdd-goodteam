package cs.ualberta.cmput402.boardgame.board;

import java.util.ArrayList;
import java.util.Collections;
import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.Player;


public class Board {
    private Square board[][];
    private int size = 5;
    private int playerHandSize = 2;
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

    private void initBoard() {
        //init board of squares
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Square();
            }
        }
        //place pieces on first and last row
        for (int j = 0; j < size; j++) {
            if (j == 2) {
                //if middle of row, place shrine and master
                board[0][j].setShrine(Player.Team.RED);
                board[size-1][j].setShrine(Player.Team.BLUE);
                board[0][j].placePiece(Piece.RED_MASTER);
                board[size-1][j].placePiece(Piece.BLUE_MASTER);
            } else {
                board[0][j].placePiece(Piece.RED_STUDENT);
                board[size-1][j].placePiece(Piece.BLUE_STUDENT);
            }
        }
    }

    public void setupPlayers(){
	//set current and then deal cards random
	currentPlayer = new Player(Player.Team.RED, playerHandSize);
	idlePlayer = new Player(Player.Team.BLUE, playerHandSize);

	Collections.shuffle(deck);
	
	for (int i = 0; i < playerHandSize; i++){
	    currentPlayer.setMove(deck.get(0), i);
	    deck.remove(0);
            idlePlayer.setMove(deck.get(0), i);
            deck.remove(0);
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

		//for valid moves also check if this is end condition                        
                checkWin(newSquare);
		
		oldSquare.removePiece();
		newSquare.placePiece(oldpiece);
		return true;
	    }
	}
	return false;
    }

    public void checkWin(Square newSquare){
	//check if currentPlayer just moved onto idlePlayer's shrine
	if(newSquare.isShrine()){
	    //this is nested because belongs to may be null, so cant check all in one line
	    if(newSquare.belongsTo().equals(idlePlayer.getTeam())){
		winner = currentPlayer;
	    }
	}
	//check if currentPlayer just took out idlePlayer's Master
	if((newSquare.getState().equals(Square.State.OCCUPIED)) && (newSquare.getPiece().isMaster())){
            winner = currentPlayer;
        }
    }

    
    public void otherPlayerTurn(){
	//classic swap
	Player temp = idlePlayer;
	idlePlayer = currentPlayer;
	currentPlayer = temp;
    }
    
    public void deselectMove(int i){
	getCurrentPlayer().getMove(i).deselect();
    }
    

    public void selectMove(int i){
	getCurrentPlayer().getMove(i).select();
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

    public Player getIdlePlayer(){
	return idlePlayer;
    }
    
    public Player getWinner(){
	return winner;
    }
	
    public Square getSquareAtPos(int x, int y){
	return board[y][x];
    }
    
}
