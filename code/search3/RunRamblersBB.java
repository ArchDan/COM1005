public class RunRamblersBB {
    public static void main ( String [] args ) {
        
        // a main to run and test Ramblers Branch and Bound from
        System.out.println("Running RamblersBB.java");
        
        // sets start point and a goal
        Coords start = new Coords(0,0), goal = new Coords(15,15);
        // Coords start = new Coords(0,0), goal = new Coords(15,0);
        // Coords start = new Coords(0,0), goal = new Coords(0,15);
        // Coords start = new Coords(7,1), goal = new Coords(8,8);
        // Coords start = new Coords(8,8), goal = new Coords(7,1);

        TerrainMap map = new TerrainMap("tmc.pgm");

        RamblersSearch searcher = new RamblersSearch(map, goal);
        SearchState initState = (SearchState) new RamblersState(start, 0);

        String res_bb = searcher.runSearch(initState, "branchAndBound");
        System.out.println(res_bb);
    }
}