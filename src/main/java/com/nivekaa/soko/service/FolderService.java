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
    private String ID;
    public FolderService(SokoHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CreatableField create(){
       return new CreatableField();
    }

    public ResponseDTO<Folder> find(String id){
        String uri = baseUri+"/"+id;
        ResultDTO res = httpClient.get(uri);
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
                    .withSuccess(res.isSuccess())
                    .withMessage(null)
                    .build();
        } else {
            return ResponseDTO.builder()
                    .withStatus(res.getCode())
                    .withData(null)
                    .withSuccess(res.isSuccess())
                    .withMessage(res.getResponse())
                    .build();
        }
    }
    private ResponseListDTO<Folder> responseList(ResultDTO res){
        if (GsonParser.isPresents(res.getResponse())){
            return ResponseListDTO.builder()
                    .withStatus(res.getCode())
                    .withData(FolderParser.getInstance().toListModel(res.getResponse()))
                    .withPagination(GsonParser.getPagination(res.getResponse()))
                    .withMessage(null)
                    .withSuccess(true)
                    .build();
        } else {
            return ResponseListDTO.builder()
                    .withStatus(res.getCode())
                    .withData(null)
                    .withSuccess(false)
                    .withMessage(res.getResponse())
                    .build();
        }
    }


    public ResponseDTO<String> delete(String id){
        String uri = String.format("%s/%s", baseUri, id);
        ResultDTO res = httpClient.delete(uri);
        return ResponseDTO.builder()
                .withMessage(res.getResponse())
                .withStatus(res.getCode())
                .withSuccess(res.isSuccess())
                .withData(null)
                .build();
    }

    public UpdatableField update(String id){
        this.ID = id;
        return new UpdatableField(id);
    }


    public class UpdatableField{
        private String name;
        private String _id;

        public UpdatableField(String __id) {
            this._id = __id;
        }

        public UpdatableField addName(String name){
            this.name = name;
            return this;
        }

        public ResponseDTO<Folder> execute(){
            String uri = String.format("%s", baseUri);
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            ResultDTO res = httpClient.put(uri, map, _id);
            return responseObject(res);
        }
    }

    public class CreatableField{
        private String name;
        private String parent;

        public CreatableField() {
        }

        public CreatableField addName(String folderName){
            this.name = folderName;
            return this;
        }

        public CreatableField addParent(String parentFolderNameOrId){
            this.parent = parentFolderNameOrId;
            return this;
        }

        public ResponseDTO<Folder> execute(){
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("parent", parent);
            ResultDTO res = httpClient.post(baseUri, map);
            return responseObject(res);
        }
    }
}
