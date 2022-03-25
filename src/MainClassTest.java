import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

  @Test
  //HW Test1
  public void testGetLocalNumber() {
    int actual = getLocalNumber();
    int expected = 14;
    Assert.assertEquals("Expected local number is not equal to 14", expected, actual);
  }

  @Test
  //HW Test2
  public void testGetClassNumber() {
    int actual = getClassNumber();
    Assert.assertTrue("Expected class number is less than 45", actual > 45);
  }
}
