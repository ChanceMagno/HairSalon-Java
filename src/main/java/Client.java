import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String first_name;
  private String last_name;
  private String phone_number;
  private String email;
  int stylist_id;

  public Client(String first_name, String last_name, String phone_number, String email) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.phone_number = phone_number;
    this.email = email;
    this.stylist_id = 0;
  }

  public String getFirstName() {
    return first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public String getPhoneNumber() {
    return phone_number;
  }

  public String getEmail() {
    return email;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public int getId() {
    return id;
  }

  public List<Client> needStylist(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id > 0;";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (first_name, last_name, phone_number, email, stylist_id) VALUES (:first_name, :last_name, :phone_number, :email, :stylist_id);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("first_name", this.first_name)
      .addParameter("last_name", this.last_name)
      .addParameter("phone_number", this.phone_number)
      .addParameter("email", this.email)
      .addParameter("stylist_id", this.stylist_id)
      .executeUpdate()
      .getKey();
    }
  }

  @Override
  public boolean equals(Object otherClient){
    if(!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return
      this.getFirstName().equals(newClient.getFirstName()) && this.getLastName().equals(newClient.getLastName()) && this.getPhoneNumber().equals(newClient.getPhoneNumber()) &&
      this.getEmail().equals(newClient.getEmail()) &&
      this.stylist_id == newClient.getStylistId();
    }
  }

  public static Client find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients WHERE id = :id;";
    Client client = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
    return client;
  }
}



}
