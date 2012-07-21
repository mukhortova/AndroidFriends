package controllers;

import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import java.util.LinkedList;
import java.util.List;

public class FriendsController extends Controller {

    public static Result find() {

        Http.Request request = request();
        User user = Utils.getUser(request);

        if(user == null){
            return ok(Json.toJson(null));
        }

        User storedUser = User.find.where().eq("email",user.email).findUnique();

        return ok(Json.toJson(storedUser));
    }

    public static Result location() {

        Http.Request request = request();
        String[] friends = Utils.getFriends(request);

        if(friends == null){
            return ok(Json.toJson(null));
        }

        List<User> storedFriends = new LinkedList<User>();

        for(String friend : friends){
            User storedFriend = User.find.where().eq("email",friend).findUnique();
            if(storedFriend!=null) storedFriends.add(storedFriend);
        }

        return ok(Json.toJson(storedFriends));
    }


}
