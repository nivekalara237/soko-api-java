package com.nivekaa.soko.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nivekaa.soko.model.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nivekaa
 * Created 22/03/2020 at 17:40
 * Class com.nivekaa.soko.parser.FileParser
 */

public class FileParser implements IModelParser<File> {
    public static FileParser getInstance(){
        return new FileParser();
    }
    @Override
    public File toModel(String json) {
        if (!GsonParser.isPresents(json)){
            return null;
        }
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonObject data = jsonObject.get("presents").getAsJsonObject().get("data").getAsJsonObject();
        return (File) GsonParser.striingToModel(data.toString(), File.class);
    }

    @Override
    public List<File> toListModel(String json) {
        if (!GsonParser.isPresents(json)){
            return Collections.<File>emptyList();
        }
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonArray array = jsonObject.get("presents").getAsJsonObject().get("data").getAsJsonArray();
        List<File> files = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            File folder = (File) GsonParser.striingToModel(array.get(i).toString(), File.class);
            files.add(folder);
        }
        return files;
    }
}
