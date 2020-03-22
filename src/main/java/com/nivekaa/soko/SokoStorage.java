package com.nivekaa.soko;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.Folder;
import com.nivekaa.soko.model.ListFolder;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author nivekaa
 * Created 22/03/2020 at 02:54
 * Class com.nivekaa.soko.SokoStorage
 */

public class SokoStorage {
    private final SokoHttpClient sokoHttpClient;

    public SokoStorage(SokoHttpClient sokoHttpClient) {
        this.sokoHttpClient = sokoHttpClient;
    }


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {



        Soko service = new Soko.Builder()
                .setApikey("Ba3Tpp61EZ37ljV8Q74yKsK5S63KnxHcTTbkTWLmLqcfBnFn7ubQav6pfEyLrHAm")
                .setAppName("Soko Lib test")
                .build();

        String fileName = "file_test_.txt";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        com.nivekaa.soko.model.File f = service.file()
                .addOne(file,"paratics");

        System.out.println("==========================");
        System.out.println(f);
        System.out.println("==========================");







       /* String fileName = "file_test_.txt";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        File[] files = new File[1];
        files[0] = file;
        Map<String, Object> body = new HashMap<>();
        body.put("folder", "test");
        String res = sokoHttpClient.multipartPost("files", body, files, "file");

        System.out.println("===================");
        System.out.println(res);
        System.out.println("===================");*/
    }
}
