import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void stylistClass_instantiatesCorrectly_true() {
    Stylist test = new Stylist("firstname", "lastname", "phonenumber", "email", "hiredate");
    assertTrue(test instanceof Stylist);
  }

  @Test
  public void stylistInstantiateswithMembers_true() {
    Stylist test = new Stylist("firstname", "lastname", "phonenumber", "email", "hiredate");
    assertEquals("firstname", test.getFirstName());
    assertEquals("lastname", test.getLastName());
    assertEquals("phonenumber", test.getPhoneNumber());
    assertEquals("email", test.getEmail());
    assertEquals("hiredate", test.getHireDate());


  }
}
