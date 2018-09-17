package flightProject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

//used this link for inspiration for the graph/node structure: https://codereview.stackexchange.com/questions/141616/directed-weighted-graph-data-structure-in-java
/**
 * 
 * 
 * @author devikakumar
 *
 */

/**
 * Object containing all the information about a route between two nodes
 */
class RouteObject {
	private List<Character> route;
	private int cost;
	private CityNode lastAdded = null;
	private boolean valid = false;

	/**
	 * constructor for Route Object
	 */
	public RouteObject() {
		route = new ArrayList<Character>();
		cost = 0;
		lastAdded = null;
	}

	/**
	 * Returns the Route
	 * 
	 * @return The current route
	 */
	public List<Character> getRoute() {
		return route;
	}

	/**
	 * Return the current route as a character string
	 * 
	 * @return The current route as character string
	 */
	public String getRouteString() {
		if (!valid) {
			return "";
		}
		if (route.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < route.size() - 1; i++) {
			sb.append(route.get(i));
			sb.append(", ");
		}
		sb.append(route.get(route.size() - 1));
		return sb.toString();
	}

	/**
	 * Return the current cost of route
	 * 
	 * @return current cost of route
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Sets the cost of the route
	 * 
	 * @param cost
	 *            int representing new cost
	 */
	public void setCost(int c) {
		cost = c;
	}

	/**
	 * Adds a new node to the route
	 * 
	 * @param CityNode
	 *            The citynode to accept
	 */
	public void addToPath(CityNode c) {
		route.add(c.getCity());

	}

	/**
	 * Returns if the route leads to the desired destination node
	 * 
	 * @return if the route is valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Sets the valid value for this route if we found destination
	 * 
	 * @param valid
	 *            the option to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

}

/**
 * Node representing city and its routes to other cities and cost
 */
class CityNode {
	private char city;
	private Set<CityEdge> edges;
	private CityNode parent = null;

	/**
	 * Creates new citynode
	 * 
	 * @param city
	 *            The city to create
	 */
	public CityNode(char city) {
		this.city = city;
		edges = new HashSet<CityEdge>();
	}

	/**
	 * Returns the object
	 * 
	 * @return citynode
	 */
	public char getCity() {
		return city;
	}

	/**
	 * Returns the city's edges
	 * 
	 * @return citynode's edges
	 */
	public Set<CityEdge> getEdges() {
		return edges;
	}

	/**
	 * Returns the city's edges as nodes
	 * 
	 * @return citynode's edges as nodes
	 */
	public Set<CityNode> getEdgeNodes() {
		Iterator<CityEdge> iter = edges.iterator();
		Set<CityNode> nodes = new HashSet<CityNode>();
		while (iter.hasNext()) {
			nodes.add(iter.next().getDestination());
		}
		return nodes;
	}

	/**
	 * Adds edge between cities
	 * 
	 * @param edge
	 *            The edge to add
	 */
	public boolean addEdge(CityEdge e) {
		if (e == null) {
			return false;
		}
		if (edges.contains(e)) {
			return false;
		}
		edges.add(e);
		return true;
	}

	/**
	 * Returns the direct cost between two cities, -1 if no direct path
	 * 
	 * @return direct cost
	 */
	public double getCostTo(CityNode c) {
		if (this.equals(c)) {
			return 0;
		}
		Iterator<CityEdge> iter = edges.iterator();
		Set<CityNode> nodes = new HashSet<CityNode>();
		while (iter.hasNext()) {
			CityEdge x = iter.next();
			if (x.getDestination().equals(c)) {
				return x.getCost();
			}
		}
		return -1;
	}

	/**
	 * Returns the city's parent when finding path
	 * 
	 * @return citynode's parent
	 */
	public CityNode getParent() {
		return parent;
	}

	/**
	 * Sets the city's temporary parent when finding a path
	 * 
	 * @param CityNode
	 *            citynode of parent
	 */
	public void setParent(CityNode parent) {
		this.parent = parent;
	}

}

/**
 * Represents edge between two cities and the cost associated with the flight
 */
class CityEdge {

	private CityNode source = null;
	private CityNode destination = null;
	private double cost = 0;

	/**
	 * Constructor
	 * 
	 * @param CityNode
	 *            the source node, destination node, cost
	 */
	public CityEdge(CityNode source, CityNode destination, double cost) {
		this.source = source;
		this.destination = destination;
		this.cost = cost;
	}

	/**
	 * Returns the source city
	 * 
	 * @return citynode of source
	 */
	public CityNode getSource() {
		return source;
	}

	/**
	 * Returns the destination city
	 * 
	 * @return citynode of destination
	 */
	public CityNode getDestination() {
		return destination;
	}

	/**
	 * Returns the cost of the flight
	 * 
	 * @return cost of flight
	 */
	public double getCost() {
		return cost;
	}

}

/**
 * FlightMap represents a graph of all the cities and their paths
 */
public class FlightMap {
	Map<Character, CityNode> cities;

	/**
	 * Constructor
	 */
	public FlightMap() {
		cities = new HashMap<Character, CityNode>();
	}

	/**
	 * Returns all cities in graph
	 * 
	 * @return cities in graph
	 */
	public Map<Character, CityNode> getCities() {
		return this.cities;
	}

	/**
	 * Adds a city to the graph
	 * 
	 * @return boolean indicating success
	 * @param city
	 *            City to add
	 */
	public boolean addCity(char city) {
		if (cities.get(city) != null) {
			return false;
		}
		cities.put(city, new CityNode(city));
		return true;

	}

	/**
	 * Adds an edge between two cities, creating them if they havent been created
	 * 
	 * @return boolean indicating success
	 * @param a
	 *            source
	 * @param b
	 *            dest
	 * @param cost
	 *            cost of edge
	 */
	public CityEdge addEdge(char a, char b, double cost) {
		if (cities.get(a) == null) {
			addCity(a);
		}
		if (cities.get(b) == null) {
			addCity(b);
		}

		CityEdge c = new CityEdge(cities.get(a), cities.get(b), cost);
		cities.get(a).addEdge(c);

		return c;

	}

	/**
	 * Returns the list of information about all paths from one node to all other
	 * nodes
	 * 
	 * @return a list of list of strings containing info
	 * @param source
	 *            the character of the source
	 */
	public List<List<String>> getAllPathInfo(char source) {
		List<List<String>> results = new ArrayList<List<String>>();
		if (cities.get(source) == null) {
			return null;
		}
		List<String> headers = new ArrayList<String>(
				Arrays.asList("Destination", "Flight Route from " + source, "Total Cost"));
		results.add(headers);
		for (Character key : cities.keySet()) {
			if (key == source) {
				continue;
			}
			RouteObject res = getPathInformation(source, key);
			if (res == null || !res.isValid()) {
				continue;
			}
			String path = res.getRouteString();
			int cost = res.getCost();
			List<String> curr = new ArrayList<String>();

			curr.add(Character.toString(key));
			curr.add(path);
			curr.add(String.valueOf(cost));
			results.add(curr);
		}
		return results;
	}

	/**
	 * Returns routeobject of information about the shortest path from one node to
	 * another
	 * 
	 * @return a routeobject containing info
	 * @param source
	 *            the character of the source
	 * @param dest
	 *            character of the dest
	 */
	public RouteObject getPathInformation(char source, char dest) {
		RouteObject route = new RouteObject();
		CityNode s = cities.get(source);
		CityNode d = cities.get(dest);
		if (s == null || s == null || s == d) {
			return route;
		}
		Queue<CityNode> q = new LinkedList<CityNode>();
		Set<CityNode> visited = new HashSet<CityNode>();
		q.add(s);

		while (!q.isEmpty()) {
			CityNode node = q.poll();
			visited.add(node);
			Set<CityNode> neighboring = node.getEdgeNodes();
			for (CityNode c : neighboring) {
				if (c.equals(d)) {
					c.setParent(node);
					route.setValid(true);
					setRoute(c, route);
					return route;
				}
				if (!visited.contains(c)) {
					q.add(c);
					visited.add(c);
					c.setParent(node);
				}
			}
		}

		return route;
	}

	/**
	 * Private helper function used to find the route of shortest path
	 * 
	 * @param the
	 *            destination node and route object
	 */
	private void setRoute(CityNode dest, RouteObject route) {
		if (dest == null) {
			return;
		}
		int cost = 0;
		Stack<CityNode> path = new Stack<CityNode>();
		CityNode curr = dest;
		while (curr != null) {
			CityNode parent = curr.getParent();
			path.push(curr);
			double currCost = 0;
			if (parent != null) {
				currCost = parent.getCostTo(curr);
				cost += currCost;
			}
			curr.setParent(null);
			curr = parent;
		}

		while (!path.isEmpty()) {
			route.addToPath(path.peek());
			path.pop();
		}
		route.setCost(cost);

	}

}
