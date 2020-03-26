package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.File;
import com.nivekaa.soko.parser.FileParser;
import com.nivekaa.soko.parser.GsonParser;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.service.dto.ResponseListDTO;
import com.nivekaa.soko.service.dto.ResultDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nivekaa
 * Created 22/03/2020 at 20:52
 * Class com.nivekaa.soko.repository.FileService
 */

public class FileService {
    private final SokoHttpClient httpClient;
    private String baseUri = "files";
    public FileService(SokoHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ResponseDTO<File> addOne(java.io.File fichier, String folder){
        Map<String, Object> map = new HashMap<>();
        //map.put("name", name);
        map.put("folder", folder);
        java.io.File[] fs = new java.io.File[1];
        fs[0] = fichier;
        ResultDTO res = httpClient.multipartPost(baseUri, map, fs, "file");
        return responseObject(res);
    }

    public ResponseListDTO<File> addMultiple(java.io.File[] files, String folder){
        Map<String, Object> map = new HashMap<>();
        //map.put("name", name);
        map.put("folder", folder);
        ResultDTO res = httpClient.multipartPost(baseUri, map, files, "file");
        return responseList(res);
    }

    public ResponseDTO<File> createByBase64(String b64, String folder){
        Map<String, Object> map = new HashMap<>();
        map.put("file", b64);
        map.put("folder", folder);
        ResponseDTO<File> response;
        ResultDTO res = httpClient.post(baseUri+"/store/base64", map);
        return responseObject(res);
    }

    private ResponseDTO<File> responseObject(ResultDTO res){
        if (GsonParser.isPresents(res.getResponse())){
            return ResponseDTO.builder()
                    .withStatus(res.getCode())
                    .withData(FileParser.getInstance().toModel(res.getResponse()))
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

    private ResponseListDTO<File> responseList(ResultDTO res){
        if (GsonParser.isPresents(res.getResponse())){
            return ResponseListDTO.builder()
                    .withStatus(res.getCode())
                    .withData(FileParser.getInstance().toListModel(res.getResponse()))
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

    public ResponseDTO<File> findById(String id){
        String uri = baseUri+"/"+id;
        ResultDTO res = httpClient.get(uri);
        return responseObject(res);
    }

    public ResponseListDTO<File> findByFolder(String folder){
        String uri = baseUri+"/folder/"+folder+"/show";
        ResultDTO res = httpClient.get(uri);
        return responseList(res);
    }

    public ResponseListDTO<File> list(int page, Map<String, Object> map){
        String uri = baseUri+"?page="+page;
        ResultDTO res = httpClient.get(uri, map);
        return responseList(res);
    }

    public ResponseListDTO<File> list(int page){
        String uri = baseUri+"?page="+page;
        ResultDTO res = httpClient.get(uri);
        return responseList(res);
    }
}
