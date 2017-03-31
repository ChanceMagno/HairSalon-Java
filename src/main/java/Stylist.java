import org.sql2o.*;
import java.util.List;

public class Stylist {
  int id;
  String first_name;
  String last_name;
  String phone_number;
  String email;

  public Stylist(String first_name, String last_name, String phone_number, String email) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.phone_number = phone_number;
    this.email = email;
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

  public int getId() {
    return id;
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists";
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (first_name, last_name, phone_number, email) VALUES (:first_name, :last_name, :phone_number, :email);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("first_name", this.first_name)
      .addParameter("last_name", this.last_name)
      .addParameter("phone_number", phone_number)
      .addParameter("email", email)
      .executeUpdate()
      .getKey();

    }
  }

  @Override
  public boolean equals(Object otherStylist){
    if(!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getFirstName().equals(newStylist.getFirstName()) && this.getLastName().equals(newStylist.getLastName()) && this.getPhoneNumber().equals(newStylist.getPhoneNumber()) &&
      this.getEmail().equals(newStylist.getEmail());
    }
  }

  public static Stylist find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM stylists WHERE id = :id;";
    Stylist stylist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
    return stylist;
  }
}

}
