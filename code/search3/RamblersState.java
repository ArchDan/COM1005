import java.util.ArrayList;

public class RamblersState extends SearchState {

    // Coords for this state
    private Coords coords;

    // Contructor
    public RamblersState(Coords c, int lc) {
        coords = c;
        localCost = lc;
    }

    // returns coords
    public Coords getCoords() {
        return coords;
    }

    @Override
    boolean goalPredicate(Search searcher) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    ArrayList<SearchState> getSuccessors(Search searcher) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    boolean sameState(SearchState n2) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
