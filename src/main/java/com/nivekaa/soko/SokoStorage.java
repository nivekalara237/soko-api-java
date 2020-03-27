package com.nivekaa.soko;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.Folder;
import com.nivekaa.soko.model.ListFolder;
import com.nivekaa.soko.service.dto.ResponseListDTO;

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
                .setApikey("RagEvtpyXPuCVfzIqShMGl90wUDi0CcIprNg209y0lof7QcYV0IozVTC1bUa4eCZ")
                .setAppName("Soko Lib test")
                .build();

        /*String fileName = "file_test_.txt";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());*/

        ResponseListDTO<Folder> folders = service.folder().list(1);

        System.out.println("=============kk=============");
        System.out.println(folders.getData());
        System.out.println(folders.getStatus());
        System.out.println(folders.getPagination());
        System.out.println("=============kk=============");





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
