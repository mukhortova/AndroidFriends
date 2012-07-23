package controllers;

import com.avaje.ebean.Ebean;
import models.Party;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import java.util.HashMap;

public class PartyController extends Controller{
    public static Result create(){
        HashMap<String,Boolean> response = new HashMap<String, Boolean>();

        Http.Request request = request();
        Party party = Utils.getParty(request);

        if(party == null){
            return ok(Json.toJson(null));
        }

        Ebean.save(party);

        //TODO: get party id and add to response

        return ok(Json.toJson(response));
    }
    public static Result update(){
        //TODO: create example
        HashMap<String,Boolean> response = new HashMap<String, Boolean>();

        Http.Request request = request();
        Party party = Utils.getParty(request);

        if(party == null){
            response.put("updateResponse",false);
            return ok(Json.toJson(response));
        }

        Party storedParty = Party.find.where().eq("id",party.id).findUnique();
        Ebean.delete(storedParty);
        Ebean.save(party);

        response.put("updateResponse",true);
        return ok(Json.toJson(response));
    }
    public static Result friends(){
        //TODO: create this method and example
        return null;
    }
}
