package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.Folder;
import com.nivekaa.soko.parser.FolderParser;
import com.nivekaa.soko.parser.GsonParser;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.service.dto.ResponseListDTO;
import com.nivekaa.soko.service.dto.ResultDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nivekaa
 * Created 22/03/2020 at 20:53
 * Class com.nivekaa.soko.repository.FolderService
 */

public class FolderService {
    private String baseUri = "folders";
    private SokoHttpClient httpClient;

    public FolderService(SokoHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ResponseDTO<Folder> create(String name, String parent){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("parent", parent);
        ResultDTO res = httpClient.post(baseUri, map);
       return responseObject(res);
    }

    public ResponseDTO<Folder> find(String id){
        String uri = baseUri+"/"+id;
        ResultDTO res = httpClient.get(uri);
        return responseObject(res);
    }

    public ResponseDTO<Folder> update(String name, String id){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        ResultDTO res = httpClient.put(baseUri, map, id);
        return responseObject(res);
    }

    public ResponseListDTO<Folder> list(int page, Map<String, Object> map){
        ResultDTO res = httpClient.get(baseUri, map);
        return responseList(res);
    }

    public ResponseListDTO<Folder> list(int page){
        ResultDTO res = httpClient.get(baseUri);
        return responseList(res);
    }


    private ResponseDTO<Folder> responseObject(ResultDTO res){
        if (GsonParser.isPresents(res.getResponse())){
            return ResponseDTO.builder()
                    .withStatus(res.getCode())
                    .withData(FolderParser.getInstance().toModel(res.getResponse()))
                    .withMessage(null)
                    .build();
        } else {
            return ResponseDTO.builder()
                    .withStatus(res.getCode())
                    .withData(null)
                    .withMessage(res.getResponse())
                    .build();
        }
    }

    private ResponseListDTO<Folder> responseList(ResultDTO res){
        if (GsonParser.isPresents(res.getResponse())){
            return ResponseListDTO.builder()
                    .withStatus(res.getCode())
                    .withData(FolderParser.getInstance().toListModel(res.getResponse()))
                    .withMessage(null)
                    .build();
        } else {
            return ResponseListDTO.builder()
                    .withStatus(res.getCode())
                    .withData(null)
                    .withMessage(res.getResponse())
                    .build();
        }
    }
}
