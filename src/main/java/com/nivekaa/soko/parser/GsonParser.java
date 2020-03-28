package com.nivekaa.soko.parser;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.nivekaa.soko.model.Pagination;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.service.dto.ResponseListDTO;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/**
 * @author nivekaa
 * Created 22/03/2020 at 16:12
 * Class com.nivekaa.soko.parser.GsonParser
 */

public class GsonParser {
    public static GsonBuilder builder = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting();
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
        if (!isSuccess(response))
            return null;
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
            return jsonObject.get("message").getAsString();
        }
        return "";
    }

    /**
     *
     * @param dto
     * @return String
     * @Example: {
     *     "success": true,
     *     "status": 200,
     *     "message": null,
     *     "data": Object
     * }
     */
    public static String responseDtoToJsonString(ResponseDTO dto){
        if (dto == null)
            return "";
        JsonObject jsonObject = new JsonObject();
        if (dto.getData()==null){
            jsonObject.add("data", null);
        }else {
            JsonObject objString = builder.create()
                    .toJsonTree(dto.getData(), new TypeToken<Object>(){}.getType())
                    .getAsJsonObject();
            jsonObject.add("data", objString);
        }
        jsonObject.addProperty("success", dto.isSuccess());
        jsonObject.addProperty("status", dto.getStatus());
        jsonObject.addProperty("message", dto.getMessage());
        return jsonObject.toString();
    }


    /**
     *
     * @param dto
     * @return String
     * @Example: {
     *     "success": true,
     *     "status": 200,
     *     "message": null,
     *     "data": [
     *          Object,
     *          Object,
     *          ...
     *     ],
     *     "pagination": (Object)Pagination
     * }
     */
    public static String responseListDtoToJsonString(ResponseListDTO dto){
        if (dto == null)
            return "";
        List list;
        JsonObject jsonObject = new JsonObject();
        if (dto.getData() == null){
            list = Collections.<Object>emptyList();
        }else
            list = dto.getData();

        JsonArray objElt = builder
                .create()
                .toJsonTree(list, new TypeToken<List<Object>>() {}.getType())
                .getAsJsonArray();
        Pagination p = new Pagination();
        if (dto.getPagination() != null)
            p = dto.getPagination();
        JsonObject paginationObj = builder.create()
                .toJsonTree(p, new TypeToken<Pagination>() {}.getType())
                .getAsJsonObject();
        jsonObject.addProperty("success", dto.isSuccess());
        jsonObject.addProperty("status", dto.getStatus());
        jsonObject.addProperty("message", dto.getMessage());
        jsonObject.add("data", objElt);
        jsonObject.add("pagination", paginationObj);
        return jsonObject.toString();
    }

}
