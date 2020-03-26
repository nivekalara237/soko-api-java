package com.nivekaa.soko.api;


import com.nivekaa.soko.service.dto.ResultDTO;

import java.util.Map;

/**
 * @author nivekaa
 * Created 22/03/2020 at 04:01
 * Class com.nivekaa.soko.api.IApi
 */

public interface IApi {
    public ResultDTO get(String path);
    public ResultDTO get(String path, Map<String, Object> params);
    public ResultDTO post(String path, Map<String, Object>  body);
    public ResultDTO put(String path, Map<String, Object>  body, String id);
    public ResultDTO delete(String path);
    public ResultDTO delete(String path, Map<String, Object>  body);
}
