public class RunRamblersAstart {
    public static void main (String[] arg) {

        // a main to run the test cases from
        System.out.println("Running RamblersAstart.java");
        
        // sets start point and a goal
        Coords start = new Coords(0,0), goal = new Coords(15,15);
        // Coords start = new Coords(0,0), goal = new Coords(15,0);
        // Coords start = new Coords(0,0), goal = new Coords(0,15);
        // Coords start = new Coords(7,1), goal = new Coords(8,8);
        // Coords start = new Coords(8,8), goal = new Coords(7,1);

        TerrainMap map = new TerrainMap("tmc.pgm");
        RamblersSearch searcher = new RamblersSearch(map, goal);
        SearchState initState = (SearchState) new RamblersState(start, 0, 0);

        String res_astar = searcher.runSearch(initState, "AStar");
        System.out.println(res_astar);
    }
}
