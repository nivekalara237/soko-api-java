package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;

/**
 * @author nivekaa
 * Created 27/03/2020 at 15:56
 * Class com.nivekaa.soko.service.UserService
 */

public class UserService {
    private final SokoHttpClient httpClient;
    private String baseUri = "user";
    public UserService(SokoHttpClient httpClient) {
        this.httpClient = httpClient;
    }


}
