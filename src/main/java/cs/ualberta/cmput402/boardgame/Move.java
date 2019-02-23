package cs.ualberta.cmput402.boardgame;

import cs.ualberta.cmput402.boardgame.Offsets;

public enum Move {
    DRAGON(new Offsets[] {new Offsets(0,0), new Offsets(1,1), new Offsets(1,1 ),
			  new Offsets(1,1)}),
    GOOSE(new Offsets[] {new Offsets(0,0), new Offsets(1,1), new Offsets(1,1 ),
			 new Offsets(1,1)}),
    ELEPHANT(new Offsets[] {new Offsets(0,0), new Offsets(1,1), new Offsets(1,1 ),
			    new Offsets(1,1)}),
    MONKEY(new Offsets[] {new Offsets(0,0), new Offsets(1,1), new Offsets(1,1 ),
			  new Offsets(1,1)});

    private boolean selected;
    private Offsets[] offsets;
    
    Move(Offsets[] offsets){
	selected = false;
	this.offsets = offsets;
    }

    public void select(){
    }
    
    public boolean isChosen(){
	return false;
    }

    public Offsets[] getOffsets(){
	return new Offsets[]{new Offsets(0,0)};
    }
}
