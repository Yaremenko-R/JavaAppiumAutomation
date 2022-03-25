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

  @Test
  //HW Test3
  public void testGetClassString() {
    String test_string = getClassString();
    String sub_string1 = "Hello";
    String sub_string2 = "hello";

    if (!(test_string.contains(sub_string1) || (test_string.contains(sub_string2)))) {
      Assert.fail("Expected string '" + test_string + "' doesn't contain 'Hello' or 'hello'");
    }
  }

  @Test
  //HW Test3 another variant
  public void testGetClassString2() {
    String test_string = getClassString();
    String sub_string = "Hello";

    Assert.assertTrue("Expected string '" + test_string + "' doesn't contain 'Hello' or 'hello'",
            test_string.toLowerCase().contains(sub_string.toLowerCase()));
  }
}
