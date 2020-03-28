package com.nivekaa.soko;

import com.nivekaa.soko.api.SokoHttpClient;

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
    }
}
