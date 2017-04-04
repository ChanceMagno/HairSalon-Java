import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String first_name;
  private String last_name;
  private String phone_number;
  private String email;

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

  public void updateName(String first_name, String last_name, String email, String phone_number) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET first_name = :first_name, last_name = :last_name, email = :email, phone_number = :phone_number WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("first_name", first_name)
      .addParameter("last_name", last_name)
      .addParameter("email", email)
      .addParameter("phone_number", phone_number)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void removeClientsAssigned() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET stylist_id = 0 WHERE stylist_id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }


  public void removeStylist() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
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
      .addParameter("phone_number", this.phone_number)
      .addParameter("email", this.email)
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
      return
      this.getFirstName().equals(newStylist.getFirstName()) && this.getLastName().equals(newStylist.getLastName()) && this.getPhoneNumber().equals(newStylist.getPhoneNumber()) &&
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
