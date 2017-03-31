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
      model.put("newClients", Client.needStylist());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String firstName = request.queryParams("firstName");
      String lastName = request.queryParams("lastName");
      String email = request.queryParams("email");
      String phoneNumber = request.queryParams("phoneNumber");
      Stylist newStylist = new Stylist(firstName, lastName, email, phoneNumber);
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
      Client newClient = new Client(firstName, lastName, email, phoneNumber);
      newClient.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
