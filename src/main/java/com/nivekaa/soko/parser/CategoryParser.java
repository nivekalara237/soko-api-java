package com.nivekaa.soko.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nivekaa.soko.model.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nivekaa
 * Created 22/03/2020 at 16:31
 * Class com.nivekaa.soko.parser.CategoryParser
 */

public class CategoryParser {
    public static Category toModel(String json){
        if (!GsonParser.isPresents(json)){
            return null;
        }
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonObject data = jsonObject.get("presents").getAsJsonObject().get("data").getAsJsonObject();
        return (Category) GsonParser.striingToModel(data.toString(), Category.class);
    }
    public static List<Category> toListModel(String json){
        if (!GsonParser.isPresents(json)){
            return Collections.<Category>emptyList();
        }
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonArray array = jsonObject.get("presents").getAsJsonObject().get("data").getAsJsonArray();
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            Category category = (Category) GsonParser.striingToModel(array.get(i).toString(), Category.class);
            categories.add(category);
        }
        return categories;
    }
}
