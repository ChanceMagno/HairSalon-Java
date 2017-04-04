import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("noStylists", Client.needStylist());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String firstName = request.queryParams("firstName");
      String lastName = request.queryParams("lastName");
      String email = request.queryParams("email");
      String phoneNumber = request.queryParams("phoneNumber");
      Stylist newStylist = new Stylist(firstName, lastName, phoneNumber, email);
      newStylist.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String firstName = request.queryParams("firstName");
      String lastName = request.queryParams("lastName");
      String email = request.queryParams("email");
      String phoneNumber = request.queryParams("phoneNumber");
      Client newClient = new Client(firstName, lastName, phoneNumber, email);
      newClient.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/all/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/allstylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/info", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylist")));
      model.put("stylistClients", Client.assignedStylist(stylist.getId()));
      model.put("stylist", stylist);
      model.put("firstName", stylist.getFirstName());
      model.put("lastName", stylist.getLastName());
      model.put("email", stylist.getEmail());
      model.put("phoneNumber", stylist.getPhoneNumber());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients/assign-stylist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylists")));
      Client client = Client.find(Integer.parseInt(request.queryParams("noStylist")));
      client.assignStylist(stylist.getId());
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/remove/client", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.queryParams("clients")));
      client.unassignStylist(client.getId());
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("all/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("clients", Client.all());
      model.put("template", "templates/allclients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/info", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.queryParams("client")));
      Stylist stylist = Stylist.find(client.getStylistId());
      model.put("stylist", stylist);
      model.put("client", client);
      model.put("firstName", client.getFirstName());
      model.put("lastName", client.getLastName());
      model.put("email", client.getEmail());
      model.put("phoneNumber", client.getPhoneNumber());
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete/stylist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      stylist.removeClientsAssigned();
      stylist.removeStylist();
      response.redirect("/all/stylists");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/update-stylist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      model.put("stylist", stylist);
      model.put("template", "templates/update-stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/updated-stylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String first_name = request.queryParams("firstName");
      String last_name = request.queryParams("lastName");
      String email = request.queryParams("email");
      String phone_number = request.queryParams("phoneNumber");
      stylist.updateStylist(first_name, last_name, phone_number, email);
      response.redirect("/all/stylists");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/update-client", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.queryParams("clientId")));
      model.put("client", client);
      model.put("template", "templates/update-client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/updated-client/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      String first_name = request.queryParams("firstName");
      String last_name = request.queryParams("lastName");
      String email = request.queryParams("email");
      String phone_number = request.queryParams("phoneNumber");
      client.updateClient(first_name, last_name, phone_number, email);
      response.redirect("/all/clients");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
