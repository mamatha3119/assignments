package jacoco;

import org.testng.annotations.Test;
import static org.junit.Assert.assertEquals;
public class RectangleTest {
	@Test
	public void testAdd() {
		Rectangle c = new Rectangle();
		assertEquals(5,c.area(2, 3),1.0);
	}
	@Test
	public void testPerimeter() {
		Rectangle c = new Rectangle();
		assertEquals(10,c.perimeter(3, 2),1.0);
	}
	
}














