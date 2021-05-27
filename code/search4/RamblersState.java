import java.util.ArrayList;

public class RamblersState extends SearchState{
    
    // coords in this state
    private Coords coords;

    // constructor
    public RamblersState(Coords c, int lc, int remc) {
        coords = c;
        localCost = lc;
        estRemCost = remc;
    }

    // accessor
    public Coords getCoords(){
        return coords;
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

    private int getRemCost(RamblersSearch s, Coords c){
        int result;

        int x1 = coords.getx(), y1 = coords.gety();
        TerrainMap terrain = s.getTMap();
        int[][] tmap = terrain.getTmap();
        
        Coords goal = s.getGoal();
        int x2 = goal.getx(), y2 = goal.gety();

        // ManhattanDistance
        // sum of absolute differences between two points
        // int ydiff = y2 - y1, xdiff = x2 - x1;
        // int manDist = Math.abs(ydiff + xdiff);

        // Euclidean Distance
        // pythagorian difference between two points
        // int ydSquare = ydiff * ydiff, xdSquare = xdiff * xdiff;
        // double unrounded = Math.sqrt(ydSquare + xdSquare);
        // int EucDist = (int) Math.floor(unrounded);

        // Height Difference
        // change in height
        int h1 = tmap[y1][x1], h2 = tmap[y2][x2];
        int hDiff = Math.abs(h2 - h1);

        // used to test for best heurisitic
        // less than as A star should be underestimate
        result = hDiff;

        return result;
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
            
            // try/catch for incorrect inputs
            try {
                int cost;
                int x1 = c.getx(), y1 = c.gety();

                int [][] tmap = terrain.getTmap();

                int h = tmap[y][x], h1 = tmap[y1][x1];
                if ((h1 <= h)) { cost = 1; }
                else { cost = 1 + (Math.abs(h1 - h)); }

                estRemCost = getRemCost(ramblerSearcher, coords);

                RamblersState currentState = new RamblersState(c,cost,estRemCost);
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
        s = s + " RemCost: " + estRemCost;
        return s;
    }
    
}
