import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

@Rule
public DatabaseRule database = new DatabaseRule();


@Test
public void clientClass_instantiatesCorrectly_true() {
  Client test = new Client("john", "connor", "123456", "arnold");
  assertTrue(test instanceof Client);
}

@Test
public void clientClass_instantiatesWithMembers_true() {
  Client test = new Client("john", "connor", "123456", "arnold");
  assertEquals("john", test.getFirstName());
  assertEquals("connor", test.getLastName());
  assertEquals("123456", test.getPhoneNumber());
  assertEquals("arnold", test.getEmail());
}

@Test
public void save_returnsTrueIfClientsAreTheSame() {
  Client test = new Client("john", "connor", "123456", "arnold");
  test.save();
  assertTrue(Client.all().get(0).equals(test));
}

@Test
public void all_returnsAllClients_true() {
  Client test1 = new Client("john", "connor", "123456", "arnold");
  Client test2 = new Client("sarah", "connor", "654321", "terminated");
  test1.save();
  test2.save();
  assertTrue(test1.equals(Client.all().get(0)));
  assertTrue(test2.equals(Client.all().get(1)));
}

@Test
public void client_instantiatesStylistId_0() {
  Client test1 = new Client("john", "connor", "123456", "arnold");
  assertEquals(0, test1.getStylistId());
}

@Test
public void find_returnsClientWithSameId_test2() {
  Client client1 = new Client("firstname", "lastname", "phonenumber", "email");
  client1.save();
  Client client2 = new Client("Jim", "Strong", "3609107874", "JimStronggmailcom");
  client2.save();
  assertEquals(Client.find(client2.getId()), client2);
}


}
