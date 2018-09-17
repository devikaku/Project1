import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author devikakumar
 *
 */
public class TestFlightMap {

	FlightMap m;
	CityNode source;
	CityNode dest;
	CityEdge e;
	RouteObject r;

	@Before
	public void setUp() {
		m = new FlightMap();
		source = new CityNode('s');
		dest = new CityNode('d');
		e = new CityEdge(source, dest, 100);
		source.addEdge(e);
		r = new RouteObject();
	}
	// TESTER METHODS FOR ROUTEOBJECT

	/**
	 * Test method for {@link RouteObject#RouteObject()}.
	 */
	@Test
	public void testRouteObject() {
		RouteObject k = new RouteObject();
		assertNotNull(k);
		assertTrue(!k.isValid());
		assertEquals(k.getCost(), 0);
		assertTrue(k.getRoute().size() == 0);
	}

	/**
	 * Test method for {@link RouteObject#getRoute()}.
	 */
	@Test
	public void testGetRoute() {
		RouteObject k = new RouteObject();
		k.addToPath(source);
		k.addToPath(dest);
		assertTrue(k.getRoute().size() == 2);
	}

	/**
	 * Test method for {@link RouteObject#getRouteString()}.
	 */
	@Test
	public void testGetRouteString() {
		RouteObject k = new RouteObject();
		k.addToPath(source);
		k.addToPath(dest);
		assertTrue(k.getRouteString().equals(""));
		k.setValid(true);
		assertTrue(k.getRouteString().equals("s, d"));
	}

	/**
	 * Test method for {@link RouteObject#getCost()}.
	 */
	@Test
	public void testGetCostRoute() {
		r.setCost(100);
		assertEquals(r.getCost(), 100);
	}

	/**
	 * Test method for {@link RouteObject#setCost(int)}.
	 */
	@Test
	public void testSetCost() {
		r.setCost(100);
		assertEquals(r.getCost(), 100);
	}

	/**
	 * Test method for {@link RouteObject#addToPath(CityNode)}.
	 */
	@Test
	public void testAddToPath() {
		r.addToPath(source);
		assertEquals(r.getRoute().get(r.getRoute().size() - 1), (Character) source.getCity());

	}

	/**
	 * Test method for {@link RouteObject#isValid()}.
	 */
	@Test
	public void testIsValid() {
		r.setValid(false);
		assertTrue(!r.isValid());
	}

	/**
	 * Test method for {@link RouteObject#setValid(boolean)}.
	 */
	@Test
	public void testSetValid() {
		r.setValid(true);
		assertTrue(r.isValid());
	}

	// TESTER METHODS FOR CITYNODE

	/**
	 * Test method for {@link CityNode#CityNode(char)}.
	 */
	@Test
	public void testCityNode() {
		CityNode newCity = new CityNode('r');
		assertNotNull(newCity);
		assertNull(newCity.getParent());
		assertEquals(newCity.getCity(), 'r');
		assertTrue(newCity.getEdges().size() == 0);
	}

	/**
	 * Test method for {@link CityNode#getCity()}.
	 */
	@Test
	public void testGetCity() {
		assertEquals(source.getCity(), 's');
	}

	/**
	 * Test method for {@link CityNode#getEdges()}.
	 */
	@Test
	public void testGetEdges() {
		assertNotNull(source.getEdges());
		assertTrue(source.getEdges().size() == 1);
	}

	/**
	 * Test method for {@link CityNode#getEdgeNodes()}.
	 */
	@Test
	public void testGetEdgeNodes() {
		assertNotNull(source.getEdgeNodes());
		assertTrue(source.getEdgeNodes().size() == 1);
	}

	/**
	 * Test method for {@link CityNode#addEdge(CityEdge)}.
	 */
	@Test
	public void addEdge() {
		assertTrue(!source.addEdge(e));
		assertTrue(!source.addEdge(null));
	}

	/**
	 * Test method for {@link CityNode#getCostTo(CityNode)}.
	 */
	@Test
	public void testGetCostToValid() {
		assertEquals((int) source.getCostTo(dest), 100);
	}

	/**
	 * Test method for {@link CityNode#getCostTo(CityNode)}.
	 */
	@Test
	public void testGetCostToInvalid() {
		assertEquals((int) source.getCostTo(new CityNode('q')), -1);
		assertEquals((int) source.getCostTo(source), 0);
	}

	/**
	 * Test method for {@link CityNode#getParent()}.
	 */
	@Test
	public void testGetParent() {
		dest.setParent(source);
		assertEquals(dest.getParent(), source);
		dest.setParent(null);
	}

	/**
	 * Test method for {@link CityNode#setParent(CityNode)}.
	 */
	@Test
	public void testSetParent() {
		source.setParent(dest);
		assertEquals(source.getParent(), dest);
		source.setParent(null);
	}

	// TESTER METHODS FOR CITYEDGE

	/**
	 * Test method for {@link CityEdge#CityEdge(CityNode, CityNode, double)}.
	 */
	@Test
	public void testCityEdge() {
		assertNotNull(e = new CityEdge(source, dest, 100));
	}

	/**
	 * Test method for {@link CityEdge#getSource()}.
	 */
	@Test
	public void testGetSource() {
		assertEquals(e.getSource(), source);
	}

	/**
	 * Test method for {@link CityEdge#getDestination()}.
	 */
	@Test
	public void testGetDestination() {
		assertEquals(e.getDestination(), dest);
	}

	/**
	 * Test method for {@link CityEdge#getCost()}.
	 */
	@Test
	public void testGetCost() {
		assertEquals((int) e.getCost(), (int) 100);

	}

	// TESTER METHODS FOR FLIGHTMAP

	/**
	 * Test method for {@link FlightMap#FlightMap()}.
	 */
	@Test
	public void testFlightMap() {
		m = new FlightMap();
		assertNotNull(m);
	}

	/**
	 * Test method for {@link FlightMap#getCities()}.
	 */
	@Test
	public void testGetCities() {
		m = new FlightMap();
		m.addCity('a');
		m.addCity('b');
		assertNotNull(m.getCities());
		assertEquals(m.getCities().size(), 2);
	}

	/**
	 * Test method for {@link FlightMap#addCity(char)}.
	 */
	@Test
	public void testAddCity() {
		m = new FlightMap();
		assertTrue(m.addCity('a'));
		assertTrue(m.addCity('b'));
		assertNotNull(m.getCities());
		assertEquals(m.getCities().size(), 2);

	}

	/**
	 * Test method for {@link FlightMap#addEdge(char, char, double)}.
	 */
	@Test
	public void testAddEdge() {
		m = new FlightMap();
		m.addCity('a');
		m.addCity('b');
		CityEdge c = m.addEdge('a', 'b', 100);
		assertNotNull(c);
		assertTrue(m.getCities().get('a').getEdges().contains(c));
	}

	/**
	 * Test method for {@link FlightMap#getAllPathInfo(char)}.
	 */
	@Test
	public void testGetAllPathInfoValid() {
		m = new FlightMap();
		m.addEdge('T', 'P', 200);
		m.addEdge('Q', 'S', 250);
		m.addEdge('S', 'T', 300);
		m.addEdge('T', 'W', 350);
		List<List<String>> info = m.getAllPathInfo('T');
		assertNotNull(info);
		assertTrue(info.size() > 1);

	}

	/**
	 * Test method for {@link FlightMap#getAllPathInfo(char)}.
	 */
	@Test
	public void testGetAllPathInfoInvalid() {
		m = new FlightMap();
		m.addEdge('T', 'P', 200);
		m.addEdge('Q', 'S', 250);
		m.addEdge('S', 'T', 300);
		m.addEdge('T', 'W', 350);
		List<List<String>> info = m.getAllPathInfo('L');
		assertNull(info);

	}

	/**
	 * Test method for {@link FlightMap#getPathInformation(char, char)}.
	 */
	@Test
	public void testGetPathInformationValidPath() {
		m = new FlightMap();
		m.addCity('a');
		m.addCity('b');
		m.addCity('c');
		m.addCity('d');
		m.addEdge('a', 'b', 100);
		m.addEdge('b', 'c', 100);
		RouteObject r = m.getPathInformation('a', 'c');
		assertNotNull(r);
		assertTrue(r.isValid());
		assertEquals(r.getCost(), 200);
		assertTrue(r.getRouteString().equals("a, b, c"));
	}

	/**
	 * Test method for {@link FlightMap#getPathInformation(char, char)}.
	 */
	@Test
	public void testGetPathInformationInvalidPath() {
		m = new FlightMap();
		m.addCity('a');
		m.addCity('b');
		m.addCity('c');
		m.addCity('d');
		m.addEdge('a', 'b', 100);
		m.addEdge('d', 'c', 100);
		m.addEdge('a', 'c', 100);
		RouteObject r = m.getPathInformation('a', 'd');
		assertNotNull(r);
		assertTrue(!r.isValid());
		assertTrue(r.getRouteString().equals(""));
	}

}
