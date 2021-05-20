import java.util.ArrayList;

public class RamblersState extends SearchState {

    // Coords for this state
    private Coords coords;

    // Contructor
    public RamblersState(Coords c, int lc) {
        coords = c;
        localCost = lc;
    }

    // accessor for coords
    public Coords getCoords() {
        return coords;
    }

    @Override
    boolean goalPredicate(Search searcher) {
        RamblersSearch ramblerSearcher =  (RamblersSearch) searcher;
        Coords target = ramblerSearcher.getGoal();
        return (coords.equals(target));
    }

    @Override
    ArrayList<SearchState> getSuccessors(Search searcher) {
        RamblersSearch ramblerSearcher =  (RamblersSearch) searcher;
        TerrainMap terrain = ramblerSearcher.getTMap();
        ArrayList<SearchState> successors = new ArrayList<>();

        // get current coords, put them into terrain[][]
        // get all next coords and put them into terrain[][]
        // calculate cost
        int x = coords.getx(), y = coords.gety();
        // in y,x terms
        ArrayList<Coords> surroundingCoords= new ArrayList<Coords>();

        for (int a = -1; a < 2; a++) {
            int y2 = y + a;
            if ((y2 >= 0) && (y2 <= terrain.getHeight())) {
                for (int b = -1; b < 2; b++) {
                    int x2 = x + b;
                    if ((x2 >= 0) && (x2 <= terrain.getWidth())) {
                        Coords newCoord = new Coords(y2,x2);
                        surroundingCoords.add(newCoord);
                    }
                }
            }
        }

        // iterates through, finding costs, adding them to successors
        for (Coords c: surroundingCoords) {
            int cost = 1;
            int x1 = c.getx(), y1 = c.gety();
            int [][] tmap = new int[terrain.getHeight()][terrain.getWidth()];
            tmap = terrain.getTmap();

            int h = tmap[y][x], h1 = tmap[y1][x1];
            if (!(h1 <= h)) {
                cost = 1 + (Math.abs(h1 - h));
            }

            RamblersState currentState = new RamblersState(c,cost);

            successors.add(currentState);

        }
        return successors;
    }


    @Override
    boolean sameState(SearchState n2) {
        RamblersState secondState = (RamblersState) n2;
        return (this.equals(secondState));
    }

    public String toString() {
        // TODO
        return "";
    }

}
