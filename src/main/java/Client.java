import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String first_name;
  private String last_name;
  private String phone_number;
  private String email;
  private int stylist_id;

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

  public void assignStylist(int stylist_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET stylist_id = :stylist_id WHERE id = :id";
      con.createQuery(sql)
      .addParameter("stylist_id", stylist_id)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateClient(String first_name, String last_name, String email, String phone_number) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET first_name = :first_name, last_name = :last_name, email = :email, phone_number = :phone_number WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("first_name", first_name)
      .addParameter("last_name", last_name)
      .addParameter("email", email)
      .addParameter("phone_number", phone_number)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void unassignStylist(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET stylist_id = 0 WHERE id =" + id + ";";
      con.createQuery(sql)
      .executeUpdate();
    }
  }

  public int getId() {
    return id;
  }

  public static List<Client> needStylist(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id = 0;";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static List<Client> assignedStylist(int id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id =" + id + ";";
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
