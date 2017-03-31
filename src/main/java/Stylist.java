import org.sql2o.*;

public class Stylist {
  int id;
  String firstName;
  String lastName;
  String phoneNumber;
  String email;
  String hireDate;

  public Stylist(String firstName, String lastName, String phoneNumber, String email, String hireDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.hireDate = hireDate;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getHireDate() {
    return hireDate;
  }

}
