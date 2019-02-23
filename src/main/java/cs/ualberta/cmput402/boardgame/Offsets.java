package cs.ualberta.cmput402.boardgame;

//idea to store these as coordinates found here since java does not have tuples
//https://stackoverflow.com/questions/48410406/java-array-of-coordinates                  
public class Offsets {

    public final int xOffset;
    public final int yOffset;

    public Offsets (int x, int y) {
	xOffset = x;
	yOffset = y;
    }

    public boolean equals(Offsets other){
	if((other.xOffset == xOffset) && (other.yOffset == yOffset)){
	    return true;
	}
	return false;
    }
}
