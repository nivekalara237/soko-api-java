package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.File;
import com.nivekaa.soko.parser.FileParser;
import com.nivekaa.soko.parser.GsonParser;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.service.dto.ResponseListDTO;
import com.nivekaa.soko.service.dto.ResultDTO;

import java.util.*;

/**
 * @author nivekaa
 * Created 22/03/2020 at 20:52
 * Class com.nivekaa.soko.repository.FileService
 */

public class FileService {
    private final SokoHttpClient httpClient;
    private String baseUri = "files";
    private String ID;
    public FileService(SokoHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CreateFile uploadFile(){
        return new CreateFile();
    }

    public CreateEncodeB64 createByBase64(){
        return new CreateEncodeB64();
    }

    private ResponseDTO<File> responseObject(ResultDTO res){
        if (GsonParser.isPresents(res.getResponse())){
            return ResponseDTO.builder()
                    .withStatus(res.getCode())
                    .withData(FileParser.getInstance().toModel(res.getResponse()))
                    .withMessage(null)
                    .withSuccess(true)
                    .build();
        } else {
                return ResponseDTO.builder()
                        .withStatus(res.getCode())
                        .withData(null)
                        .withSuccess(false)
                        .withMessage(res.getResponse())
                        .build();
        }
    }

    private ResponseListDTO<File> responseList(ResultDTO res){
        if (GsonParser.isPresents(res.getResponse())){
            return ResponseListDTO.builder()
                    .withStatus(res.getCode())
                    .withData(FileParser.getInstance().toListModel(res.getResponse()))
                    .withSuccess(res.isSuccess())
                    .withPagination(GsonParser.getPagination(res.getResponse()))
                    .withMessage(null)
                    .build();
        } else {
            return ResponseListDTO.builder()
                    .withStatus(res.getCode())
                    .withSuccess(res.isSuccess())
                    .withMessage(res.getResponse())
                    .withData(null)
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

    public UpdatableField upadte(String id){
        this.ID = id;
        return new UpdatableField(id);
    }

    private class UpdatableField{
        private String name;
        private String _id;

        public UpdatableField(String __id) {
            this._id = __id;
        }

        public UpdatableField addName(String name){
            this.name = name;
            return this;
        }

        public ResponseDTO<File> execute(){
            String uri = String.format("%s", baseUri);
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            ResultDTO res = httpClient.put(uri, map, _id);
            return responseObject(res);
        }
    }

    public class CreateEncodeB64{
        private String b64;
        private String folder;

        public CreateEncodeB64() {}

        public CreateEncodeB64 fileEncoded(String b64){
            this.b64 = b64;
            return this;
        }

        public CreateEncodeB64 folder(String folderIdOrNameOrPath){
            this.folder = folderIdOrNameOrPath;
            return this;
        }

        public ResponseDTO<File> execute(){
            Map<String, Object> map = new HashMap<>();
            map.put("file", b64);
            map.put("folder", folder);
            ResultDTO res = httpClient.post(baseUri+"/store/base64", map);
            return responseObject(res);
        }
    }

    public class CreateFile{
        private String folder;
        private List<java.io.File> files;

        public CreateFile() {
            files = new ArrayList<>();
        }

        public CreateFile folder(String folderIdOrNameOrPath){
            this.folder = folderIdOrNameOrPath;
            return this;
        }

        public CreateFile addFile(java.io.File file){
            if (file!=null){
                files.add(file);
            }
            return this;
        }

        public CreateFile addFile(byte[] bytes){
            if (bytes==null || bytes.length ==0){
                return this;
            }else {
                //java.io.File file = new java.io.File(bytes);
                return null;
            }
        }


        public ResponseListDTO<File> executes(){
            if (files==null || files.isEmpty()){
                return ResponseListDTO.builder()
                        .withPagination(null)
                        .withSuccess(false)
                        .withData(null)
                        .withMessage("Method executes() required an least file")
                        .withStatus(404)
                        .build();
            }
            String uri = String.format("%s/%s", baseUri, "multi/store");
            java.io.File[] filesArr = new java.io.File[files.size()];
            files.toArray(filesArr);
            Map<String, Object> map = new HashMap<>();
            map.put("folder", folder);
            ResultDTO res = httpClient.multipartPost(uri, map, filesArr, "files");
            return responseList(res);
        }


        public ResponseDTO<File> execute(){
            if (files==null || files.isEmpty()){
                return ResponseDTO.builder()
                        .withSuccess(false)
                        .withData(null)
                        .withMessage("Method execute() required an file")
                        .withStatus(404)
                        .build();
            }else if (files.size()>1){
                return ResponseDTO.builder()
                        .withSuccess(false)
                        .withData(null)
                        .withMessage("Method execute() required only one file, you must call executes() if you should upload many files")
                        .withStatus(404)
                        .build();
            }

            java.io.File[] filesArr = new java.io.File[files.size()];
            files.toArray(filesArr);
            Map<String, Object> map = new HashMap<>();
            map.put("folder", folder);
            ResultDTO res = httpClient.multipartPost(baseUri, map, filesArr, "file");
            return responseObject(res);
        }
    }

}
