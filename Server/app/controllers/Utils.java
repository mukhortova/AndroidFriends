package controllers;

import models.Party;
import models.User;
import org.codehaus.jackson.map.ObjectMapper;
import play.mvc.Http;

import java.io.IOException;

public class Utils{
    public static User getUser(Http.Request request){
        ObjectMapper mapper = new ObjectMapper();

        if (request == null) {
            return null;
        }

        Http.RequestBody body = request.body();
        if (body == null) {
            return null;
        }

        User user;
        try {
            user = mapper.readValue(body.asJson(), User.class);
        } catch (IOException e) {
            return null;
        }
        return user;
    }
    public static String[] getFriends(Http.Request request){
        ObjectMapper mapper = new ObjectMapper();

        if (request == null) {
            return null;
        }

        Http.RequestBody body = request.body();
        if (body == null) {
            return null;
        }

        String[] friends;
        try {
            friends = mapper.readValue(body.asJson(), String[].class);
        } catch (IOException e) {
            return null;
        }
        return friends;
    }
    public static Party getParty(Http.Request request){
        ObjectMapper mapper = new ObjectMapper();

        if (request == null) {
            return null;
        }

        Http.RequestBody body = request.body();
        if (body == null) {
            return null;
        }

        Party party;
        try {
            party = mapper.readValue(body.asJson(), Party.class);
        } catch (IOException e) {
            return null;
        }
        return party;
    }
    public static Long getPartyId(Http.Request request){
        ObjectMapper mapper = new ObjectMapper();

        if (request == null) {
            return null;
        }

        Http.RequestBody body = request.body();
        if (body == null) {
            return null;
        }

        Long id;
        try {
            id = mapper.readValue(body.asJson(), Long.class);
        } catch (IOException e) {
            return null;
        }
        return id;
    }
}
