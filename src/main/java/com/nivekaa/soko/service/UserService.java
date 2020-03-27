package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.User;
import com.nivekaa.soko.parser.GsonParser;
import com.nivekaa.soko.parser.UserParser;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.service.dto.ResultDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nivekaa
 * Created 27/03/2020 at 15:56
 * Class com.nivekaa.soko.service.UserService
 */

public class UserService {
    private final SokoHttpClient httpClient;
    private String baseUri = "user";
    public UserService(SokoHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ResponseDTO<User> info(){
        ResultDTO res = httpClient.get(baseUri);
        return responseObject(res);
    }

    public UpdatableField update(String id){
        return new UpdatableField(id);
    }

    private ResponseDTO<User> responseObject(ResultDTO res){
        if (GsonParser.isPresents(res.getResponse())){
            return ResponseDTO.builder()
                    .withStatus(res.getCode())
                    .withData(UserParser.getInstance().toModel(res.getResponse()))
                    .withMessage(null)
                    .withSuccess(res.isSuccess())
                    .build();
        } else {
            return ResponseDTO.builder()
                    .withStatus(res.getCode())
                    .withMessage(res.getResponse())
                    .withSuccess(res.isSuccess())
                    .withData(null)
                    .build();
        }
    }

    public class UpdatableField{
        private String name;
        private String _id;

        public UpdatableField(String __id) {
            this._id = __id;
        }

        private UpdatableField addName(String userName){
            this.name = userName;
            return this;
        }

        public ResponseDTO<User> execute(){
            String uri = String.format("%s/%s", baseUri, "update");
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            ResultDTO res = httpClient.put(uri, map, _id);
            return responseObject(res);
        }
    }

}
