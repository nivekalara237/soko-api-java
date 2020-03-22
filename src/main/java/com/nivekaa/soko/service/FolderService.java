package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.Folder;
import com.nivekaa.soko.model.ListFolder;
import com.nivekaa.soko.parser.FolderParser;
import com.nivekaa.soko.parser.GsonParser;

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

    public Folder create(String name, String parent){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("parent", parent);
        Folder folder = new Folder();
        String res = httpClient.post(baseUri, map);
        if (GsonParser.isPresents(res)){
            folder = FolderParser.getInstance().toModel(res);
            folder.setStatus(200);
        } else {
            folder.setStatus(404);
            folder.setMessage(res);
        }
        return folder;
    }

    public Folder find(String id){
        String uri = baseUri+"/"+id;
        String res = httpClient.get(uri);
        Folder folder = new Folder();
        if (GsonParser.isPresents(res)){
            folder = FolderParser.getInstance().toModel(res);
            folder.setStatus(200);
        } else {
            folder.setStatus(404);
            folder.setMessage(res);
        }
        return folder;
    }

    public Folder update(String name, String id){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        Folder folder = new Folder();
        String res = httpClient.put(baseUri, map, id);
        if (GsonParser.isPresents(res)){
            folder = FolderParser.getInstance().toModel(res);
            folder.setStatus(200);
        } else {
            folder.setStatus(404);
            folder.setMessage(res);
        }
        return folder;
    }

    public ListFolder list(int page, Map<String, Object> map){
        String res = httpClient.get(baseUri, map);
        ListFolder listFolder = new ListFolder();
        if (GsonParser.isPresents(res)){
            listFolder.setList(FolderParser.getInstance().toListModel(res));
            listFolder.setStatus(200);
        } else {
            listFolder.setStatus(404);
            listFolder.setMessage(res);
        }
        return listFolder;
    }

    public ListFolder list(int page){
        String res = httpClient.get(baseUri);
        ListFolder listFolder = new ListFolder();
        if (GsonParser.isPresents(res)){
            listFolder.setList(FolderParser.getInstance().toListModel(res));
            listFolder.setStatus(200);
        } else {
            listFolder.setStatus(404);
            listFolder.setMessage(res);
        }
        return listFolder;
    }
}
