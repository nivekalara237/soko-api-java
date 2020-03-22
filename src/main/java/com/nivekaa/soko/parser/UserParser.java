package com.nivekaa.soko.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nivekaa.soko.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nivekaa
 * Created 22/03/2020 at 17:35
 * Class com.nivekaa.soko.parser.UserParser
 */

public class UserParser implements IModelParser<User> {
    public static UserParser getInstance(){
        return new UserParser();
    }
    @Override
    public User toModel(String json) {
        if (!GsonParser.isPresents(json)){
            return null;
        }
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonObject data = jsonObject.get("presents").getAsJsonObject().get("data").getAsJsonObject();
        return (User) GsonParser.striingToModel(data.toString(), User.class);
    }

    @Override
    public List<User> toListModel(String json) {
        if (!GsonParser.isPresents(json)){
            return Collections.<User>emptyList();
        }
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonArray array = jsonObject.get("presents").getAsJsonObject().get("data").getAsJsonArray();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            User user = (User) GsonParser.striingToModel(array.get(i).toString(), User.class);
            users.add(user);
        }
        return users;
    }
}
