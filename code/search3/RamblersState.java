import java.util.ArrayList;

public class RamblersState extends SearchState {

    // Coords for this state
    private Coords coords;
    private int localCost;

    // Contructor
    public RamblersState(Coords c, int lc) {
        coords = c;
        localCost = lc;
    }

    // accessors
    public Coords getCoords() {
        return coords;
    }

    public int getLocalCost() {
        return localCost;
    }

    @Override
    boolean goalPredicate(Search searcher) {
        RamblersSearch ramblerSearcher =  (RamblersSearch) searcher;
        Coords target = ramblerSearcher.getGoal();
        
        boolean predicate = false;

        if (coords.getx() == target.getx() && coords.gety() == target.gety()) {
            predicate = true;
        }

        return predicate;
    }

    @Override
    ArrayList<SearchState> getSuccessors(Search searcher) {

        RamblersSearch ramblerSearcher =  (RamblersSearch) searcher;
        TerrainMap terrain = ramblerSearcher.getTMap();
        ArrayList<SearchState> successors = new ArrayList<>();

        int x = coords.getx(), y = coords.gety();
        // in y,x terms
        ArrayList<Coords> surroundingCoords= new ArrayList<Coords>();
        int[][] surCoords = {{y-1,x},{y+1,x},{y,x-1},{y,x+1}};
        for (int i = 0; i < surCoords.length; i++) {
            if ((surCoords[i][0] >= 0) && (surCoords[i][0] < terrain.getDepth())){
                if ((surCoords[i][1] >= 0) && (surCoords[i][1] < terrain.getWidth())) {
                    Coords newCoord = new Coords(surCoords[i][0],surCoords[i][1]);
                    surroundingCoords.add(newCoord);
                }
            }
        }

        // iterates through, finding costs, adding them to successors
        for (Coords c: surroundingCoords) {
            
            // try/catch block for incorrect inputs
            try {
            int cost;
            int x1 = c.getx(), y1 = c.gety();
            int [][] tmap = terrain.getTmap();
     
            int h = tmap[y][x], h1 = tmap[y1][x1];
            if ((h1 <= h)) { cost = 1; }
            else { cost = 1 + (Math.abs(h1 - h)); }

            RamblersState currentState = new RamblersState(c,cost);
            successors.add(currentState);
            }
            catch (Exception e) { System.out.println(e); }
        }   

        return successors;
    }


    @Override
    boolean sameState(SearchState n2) {
        RamblersState secondState = (RamblersState) n2;
        Coords coords2 = secondState.getCoords();
        
        boolean same = false;

        if (coords.getx() == coords2.getx() && coords.gety() == coords2.gety()) {
            same = true;
        }

        return same;
    }

    public String toString() {
        String s = "";
        int x = coords.getx(), y = coords.gety();
        s = s + "( "+y+" , "+x+" )";
        s = s + " Cost: " + localCost;
        return s;
    }

}
