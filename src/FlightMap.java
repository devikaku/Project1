import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightMap {
	List<CityNode> cities;
	public class CityNode{
		char city;
		Map<CityNode, Integer> edges;
		
		public CityNode(char city) {
			this.city = city;
			edges = new HashMap<CityNode, Integer>();
		}
	}
	
	public FlightMap() {
		cities = new ArrayList<CityNode>();
	}

}
