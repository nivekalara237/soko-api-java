package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.File;
import com.nivekaa.soko.model.ListFile;
import com.nivekaa.soko.parser.FileParser;
import com.nivekaa.soko.parser.GsonParser;

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

    public File addOne(java.io.File fichier, String folder){
        Map<String, Object> map = new HashMap<>();
        //map.put("name", name);
        map.put("folder", folder);
        File file = new File();
        java.io.File[] fs = new java.io.File[1];
        fs[0] = fichier;
        String res = httpClient.multipartPost(baseUri, map, fs, "file");
        if (GsonParser.isPresents(res)){
            file = FileParser.getInstance().toModel(res);
            file.setStatus(200);
        } else {
            file.setStatus(404);
            file.setMessage(res);
        }
        return file;
    }

    public ListFile addMultiple(java.io.File[] files, String folder){
        Map<String, Object> map = new HashMap<>();
        //map.put("name", name);
        map.put("folder", folder);
        String res = httpClient.multipartPost(baseUri, map, files, "file");
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

    public File createByBase64(String b64, String folder){
        Map<String, Object> map = new HashMap<>();
        map.put("file", b64);
        map.put("folder", folder);
        File file = new File();
        String res = httpClient.post(baseUri+"/store/base64", map);
        if (GsonParser.isPresents(res)){
            file = FileParser.getInstance().toModel(res);
            file.setStatus(200);
        } else {
            file.setStatus(404);
            file.setMessage(res);
        }
        return file;
    }

    public File findById(String id){
        String uri = baseUri+"/"+id;
        String res = httpClient.get(uri);
        File file = new File();
        if (GsonParser.isPresents(res)){
            file = FileParser.getInstance().toModel(res);
            file.setStatus(200);
        } else {
            file.setStatus(404);
            file.setMessage(res);
        }
        return file;
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
