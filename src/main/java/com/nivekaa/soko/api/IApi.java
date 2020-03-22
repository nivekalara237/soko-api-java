package com.nivekaa.soko.api;


import java.util.Map;

/**
 * @author nivekaa
 * Created 22/03/2020 at 04:01
 * Class com.nivekaa.soko.api.IApi
 */

public interface IApi {
    public String get(String path);
    public String get(String path, Map<String, Object> params);
    public String post(String path, Map<String, Object>  body);
    public String put(String path, Map<String, Object>  body, String id);
    public String delete(String path);
    public String delete(String path, Map<String, Object>  body);
}
