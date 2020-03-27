package com.nivekaa.soko.parser;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.nivekaa.soko.model.Pagination;

import java.lang.reflect.Field;

/**
 * @author nivekaa
 * Created 22/03/2020 at 16:12
 * Class com.nivekaa.soko.parser.GsonParser
 */

public class GsonParser {
    public static GsonBuilder builder = new GsonBuilder();
    private static FieldNamingStrategy namingStrategy = new FieldNamingStrategy() {
        @Override
        public String translateName(Field f) {
            return f.getName().replace("_", "");
        }
    };

    public static Object striingToModel(String json, Class clazz){
        builder.setFieldNamingStrategy(namingStrategy);
        return builder.serializeNulls()
                .create()
                .fromJson(json, clazz);
    }

    public static boolean isOk(String response){
        return response != null && response.contains("\"success\":true");
    }

    public static boolean isPresents(String response){
        if (!isOk(response)){
            return false;
        }else{
            return response.contains("\"presents\":{\"data\":");
        }
    }

    public static boolean isSuccess(String response){
        if (response==null)
            return false;
        else {
            JsonObject jsonObject = builder.create().fromJson(response, JsonObject.class);
            if (!jsonObject.has("success"))
                return false;
            else
                return jsonObject.get("success").getAsBoolean();
        }
    }

    public static Pagination getPagination(String response){
        if (!isOk(response)){
            return null;
        }else {

            if (!response.replaceAll(" ", "").contains("\"meta\":{\"pagination\":{")){
                return null;
            }else {
                Pagination pagin;
                JsonObject jsonObject = builder.create().fromJson(response, JsonObject.class);
                String paginationObj = jsonObject
                        .get("presents")
                        .getAsJsonObject()
                        .get("meta")
                        .getAsJsonObject()
                        .get("pagination")
                        .getAsJsonObject()
                        .toString();
                pagin = (Pagination) striingToModel(paginationObj, Pagination.class);
                return pagin;
            }
        }
    }

    public static String errorMsg(String json){
        if (json!=null){
            JsonObject jsonObject = builder.create().fromJson(json, JsonObject.class);
            return jsonObject.get("message").toString();
        }
        return "";
    }
}
