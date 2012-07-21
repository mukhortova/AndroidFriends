package controllers;

import com.avaje.ebean.Ebean;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import java.util.HashMap;

public class UserController extends Controller {

    public static Result register() {
        HashMap<String,Boolean> response = new HashMap<String, Boolean>();

        Http.Request request = request();
        User user = Utils.getUser(request);

        if(user == null){
            response.put("registrationResponse",false);
            return ok(Json.toJson(response));
        }

        //TODO: validate token

        Ebean.save(user);

        response.put("registrationResponse",true);
        return ok(Json.toJson(response));
    }

    public static Result location() {
        HashMap<String,Boolean> response = new HashMap<String, Boolean>();

        Http.Request request = request();
        User user = Utils.getUser(request);

        if(user == null){
            response.put("userLocationResponse",false);
            return ok(Json.toJson(response));
        }

        User storedUser = User.find.where().eq("email",user.email).findUnique();
        storedUser.latitude = user.latitude;
        storedUser.longitude = user.longitude;
        storedUser.tags = user.tags;

        Ebean.update(storedUser);

        response.put("userLocationResponse",true);
        return ok(Json.toJson(response));
    }
}
