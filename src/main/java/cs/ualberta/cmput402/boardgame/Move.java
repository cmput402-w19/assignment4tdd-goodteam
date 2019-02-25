package cs.ualberta.cmput402.boardgame;

import cs.ualberta.cmput402.boardgame.Offsets;

public enum Move {
    //formatted L -> R appearance of possible squares
    DRAGON(new Offsets[] {new Offsets(-2,1), new Offsets(-1,-1), new Offsets(2,1),
			  new Offsets(1,-1)}),
    
    GOOSE(new Offsets[] {new Offsets(-1,1), new Offsets(-1,0), new Offsets(1,0),
			 new Offsets(1,-1)}),

    ELEPHANT(new Offsets[] {new Offsets(-1,1), new Offsets(-1,0), new Offsets(1,0),
			    new Offsets(1,1)}),

    MONKEY(new Offsets[] {new Offsets(-1,1), new Offsets(-1,-1), new Offsets(1,1),
			  new Offsets(1,-1)}),

    TIGER(new Offsets[] {new Offsets(0, 2), new Offsets(0, -1)});
    
    private boolean selected;
    private Offsets[] offsets;
    
    Move(Offsets[] offsets){
	selected = false;
	this.offsets = offsets;
    }

    public void select(){
	selected = true;
    }

    public void deselect(){
	selected = false;
    }
    
    public boolean isChosen(){
	return selected;
    }

    public Offsets[] getOffsets(){
	return offsets;
    }
}
