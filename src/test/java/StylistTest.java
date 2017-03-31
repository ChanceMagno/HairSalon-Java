import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void stylistClass_instantiatesCorrectly_true() {
    Stylist test = new Stylist();
    assertTrue(test instanceof Stylist);
  }
}
