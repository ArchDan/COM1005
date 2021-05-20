public class RunRamblersBB {
    public static void main ( String [] args ) {
        
        // a main to run and test Ramblers Branch and Bound from
        System.out.println("Running RamblersBB.java");
        
        // sets start point and a goal
        Coords start = new Coords(3,6), goal = new Coords(3,7);
        TerrainMap map = new TerrainMap("tmc.pgm");

        RamblersSearch searcher = new RamblersSearch(map, goal);
        SearchState initState = (SearchState) new RamblersState(start, 0);

        String res_bb = searcher.runSearch(initState, "branchAndBound");
        System.out.println(res_bb);
    }
}