package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.File;
import com.nivekaa.soko.model.ListFile;
import com.nivekaa.soko.parser.FileParser;
import com.nivekaa.soko.parser.GsonParser;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.service.dto.ResponseListDTO;

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
        ResponseDTO<File> response = new ResponseDTO<File>();
        map.put("folder", folder);
        java.io.File[] fs = new java.io.File[1];
        fs[0] = fichier;
        String res = httpClient.multipartPost(baseUri, map, fs, "file");
        if (GsonParser.isPresents(res)){
            response = ResponseDTO.builder()
                    .withData(FileParser.getInstance().toModel(res))
                    .withStatus(200)
                    .withMessage(null)
                    .build();
        } else {
            response = ResponseDTO.builder()
                    .withData(null)
                    .withStatus(200)
                    .withMessage(res)
                    .build();
        }
        return response;
    }

    public ResponseListDTO<File> addMultiple(java.io.File[] files, String folder){
        Map<String, Object> map = new HashMap<>();
        //map.put("name", name);
        map.put("folder", folder);
        ResponseListDTO<File> response;
        String res = httpClient.multipartPost(baseUri, map, files, "file");
        ListFile listFile = new ListFile();
        if (GsonParser.isPresents(res)){
            return ResponseListDTO.builder()
                    .withStatus(200)
                    .withData(FileParser.getInstance().toListModel(res))
                    .withMessage(null)
                    .build();
        } else {
            return ResponseListDTO.builder()
                    .withStatus(404)
                    .withData(null)
                    .withMessage(res)
                    .build();
        }
    }

    public ResponseDTO<File> createByBase64(String b64, String folder){
        Map<String, Object> map = new HashMap<>();
        map.put("file", b64);
        map.put("folder", folder);
        ResponseDTO<File> response;
        String res = httpClient.post(baseUri+"/store/base64", map);
        if (GsonParser.isPresents(res)){
            response = ResponseDTO.builder()
                    .withStatus(200)
                    .withData(FileParser.getInstance().toModel(res))
                    .withMessage(null)
                    .build();
        } else {
            response = ResponseDTO.builder()
                    .withStatus(200)
                    .withData(null)
                    .withMessage(res)
                    .build();
        }
        return response;
    }

    public ResponseDTO<File> findById(String id){
        String uri = baseUri+"/"+id;
        String res = httpClient.get(uri);
        if (GsonParser.isPresents(res)){
            return ResponseDTO.builder()
                    .withStatus(200)
                    .withData(FileParser.getInstance().toModel(res))
                    .withMessage(null)
                    .build();
        } else {
            return ResponseDTO.builder()
                    .withStatus(200)
                    .withData(null)
                    .withMessage(res)
                    .build();
        }
    }

    public ListFile findByFolder(String folder){
        String uri = baseUri+"/folder/"+folder+"/show";
        String res = httpClient.get(uri);
        ListFile listFile = new ListFile();
        if (GsonParser.isPresents(res)){
            listFile.setList(FileParser.getInstance().toListModel(res));
            listFile.setStatus(200);
        } else {
            listFile.setStatus(404);
            listFile.setMessage(res);
        }
        return listFile;
    }

    public ListFile list(int page, Map<String, Object> map){
        String uri = baseUri+"?page="+page;
        String res = httpClient.get(uri, map);
        ListFile listFile = new ListFile();
        if (GsonParser.isPresents(res)){
            listFile.setList(FileParser.getInstance().toListModel(res));
            listFile.setStatus(200);
        } else {
            listFile.setStatus(404);
            listFile.setMessage(res);
        }
        return listFile;
    }

    public ListFile list(int page){
        String uri = baseUri+"?page="+page;
        String res = httpClient.get(uri);
        ListFile listFile = new ListFile();
        if (GsonParser.isPresents(res)){
            listFile.setList(FileParser.getInstance().toListModel(res));
            listFile.setStatus(200);
        } else {
            listFile.setStatus(404);
            listFile.setMessage(res);
        }
        return listFile;
    }

}
