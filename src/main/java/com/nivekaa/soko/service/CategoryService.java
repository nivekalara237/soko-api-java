package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.Category;
import com.nivekaa.soko.parser.CategoryParser;
import com.nivekaa.soko.parser.GsonParser;
import com.nivekaa.soko.service.dto.ResponseListDTO;
import com.nivekaa.soko.service.dto.ResultDTO;

/**
 * @author nivekaa
 * Created 27/03/2020 at 19:58
 * Class com.nivekaa.soko.service.CategoryService
 */

public class CategoryService {
    private final SokoHttpClient httpClient;
    private String baseUri = "categories";
    public CategoryService(SokoHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ResponseListDTO<Category> list(){
        ResultDTO res = httpClient.get(baseUri);
        if (GsonParser.isPresents(res.getResponse())){
            return ResponseListDTO.builder()
                    .withStatus(res.getCode())
                    .withData(CategoryParser.getInstance().toListModel(res.getResponse()))
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
}
