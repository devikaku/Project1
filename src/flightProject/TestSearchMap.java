package flightProject;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author devikakumar
 *
 */
public class TestSearchMap {

	SearchMap search;

	@Before
	public void setUp() {
		search = new SearchMap();
	}

	/**
	 * Test method for {@link SearchMap#parseAndCreateEdge(String)}.
	 */
	@Test
	public void testParseAndCreateEdgeValid() {
		String s = "        P          W              300 ";
		search.parseAndCreateEdge(s);
		CityNode source = search.map.getCities().get('P');
		CityNode dest = search.map.getCities().get('W');
		assertNotNull(source);
		assertNotNull(dest);
		assertEquals((int) source.getCostTo(dest), 300);

	}

	/**
	 * Test method for {@link SearchMap#parseAndCreateEdge(String)}.
	 */
	@Test
	public void testParseAndCreateEdgeInvalid() {
		String s = "        P     I     W              300 ";
		search.parseAndCreateEdge(s);
		assertNull(search.map.getCities().get('P'));
		assertNull(search.map.getCities().get('W'));
	}

}
