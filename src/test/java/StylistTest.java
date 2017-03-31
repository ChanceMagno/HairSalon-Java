import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void stylistClass_instantiatesCorrectly_true() {
    Stylist test = new Stylist("firstname", "lastname", "phonenumber", "email");
    assertTrue(test instanceof Stylist);
  }

  @Test
  public void stylistInstantiateswithMembers_true() {
    Stylist test = new Stylist("firstname", "lastname", "phonenumber", "email");
    assertEquals("firstname", test.getFirstName());
    assertEquals("lastname", test.getLastName());
    assertEquals("phonenumber", test.getPhoneNumber());
    assertEquals("email", test.getEmail());
  }
  @Test
  public void save_returnsTrueIfStylistsAreTheSame() {
    Stylist newStylist = new Stylist("firstname", "lastname", "phonenumber", "email");
    newStylist.save();
    assertTrue(Stylist.all().get(0).equals(newStylist));
  }

  @Test
  public void all_returnsAllStylist_true() {
    Stylist test = new Stylist("firstname", "lastname", "phonenumber", "email");
    Stylist test1 = new Stylist("Jim", "Strong", "3609107874", "JimStronggmailcom");
    test.save();
    test1.save();
    assertTrue(test.equals(Stylist.all().get(0)));
    assertTrue(test1.equals(Stylist.all().get(1)));
  }

  @Test
  public void find_returnsStylistWithSameId_test2() {
    Stylist stylist1 = new Stylist("firstname", "lastname", "phonenumber", "email");
    stylist1.save();
    Stylist stylist2 = new Stylist("Jim", "Strong", "3609107874", "JimStronggmailcom");
    stylist2.save();
    assertEquals(Stylist.find(stylist2.getId()), stylist2);
  }
}
