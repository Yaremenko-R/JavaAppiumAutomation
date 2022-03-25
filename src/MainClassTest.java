import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

  @Test
  public void testGetLocalNumber() {
    int actual = getLocalNumber();
    int expected = 14;
    Assert.assertEquals("Expected number is not equal to 14", expected, actual);
  }
}
