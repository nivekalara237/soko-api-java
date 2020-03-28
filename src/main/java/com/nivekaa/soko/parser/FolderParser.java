package com.nivekaa.soko.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nivekaa.soko.model.Folder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nivekaa
 * Created 22/03/2020 at 17:06
 * Class com.nivekaa.soko.parser.FolderParser
 */

public class FolderParser implements IModelParser<Folder> {

    public static FolderParser getInstance(){
        return new FolderParser();
    }

    @Override
    public Folder toModel(String json) {
        if (!GsonParser.isPresents(json)){
            return null;
        }
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonObject data = jsonObject.get("presents").getAsJsonObject().get("data").getAsJsonObject();
        return (Folder) GsonParser.striingToModel(data.toString(), Folder.class);
    }

    @Override
    public List<Folder> toListModel(String json) {
        if (!GsonParser.isPresents(json)){
            return Collections.<Folder>emptyList();
        }
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonArray array = jsonObject.get("presents").getAsJsonObject().get("data").getAsJsonArray();
        List<Folder> folders = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            Folder folder = (Folder) GsonParser.striingToModel(array.get(i).toString(), Folder.class);
            folders.add(folder);
        }
        return folders;
    }

}
