/**
 * 
 */
package edu.brandeis.vogueable;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Yulia
 *
 */
public class ItemTest extends TestCase {
	Item item =  new Item("SHOES!!");
	
	

	/**
	 * Test method for {@link edu.brandeis.vogueable.Item#getName()}.
	 */
	@Test
	public void testGetName() {
		Item item =  new Item("SHOES!!");
		assertEquals("Shoes", item.getName());
	}

	/**
	 * Test method for {@link edu.brandeis.vogueable.Item#getImageFileString()}.
	 */
	@Test
	public void testGetImageFileString() {
		item.setImageFileString("www.lookatsomeshoes.com");
		assertEquals("www.lookatsomeshoes.com", item.getImageFileString());
		}

}
