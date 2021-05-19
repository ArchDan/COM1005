public class RunRamblersBB {
    public static void main ( String [] args ) {
        
        // a main to run and test Ramblers Branch and Bound from
        System.out.println("Running RamblersBB.java");

        TerrainMap map1 = new TerrainMap("tmc.pgm");
        
        RamblersSearch searcher = new RamblersSearch(map1, COORDS);
        SearchState initState = (SearchState) new RamblersState("Start", 0);

        
        String res_bb = searcher.runSearch(initState, "branchAndBound");
        System.out.println(res_bb);
    }
}
