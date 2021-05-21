public class RamblersSearch extends Search{
    
    private TerrainMap tMap; // map we're searching
	private Coords goal; // goal coordinates

	public TerrainMap getTMap() {
	  return tMap;
	}

	public Coords getGoal() {
	  return goal;
	}

	public RamblersSearch(TerrainMap m, Coords g) {
	  tMap = m;
	  goal = g;
	}
}
