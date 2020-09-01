package com.nivekaa.soko.service;

import com.nivekaa.soko.Soko;
import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.Category;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.service.dto.ResponseListDTO;
import com.nivekaa.soko.util.Data;
import junit.framework.TestCase;

/**
 * @author nivekaa
 * Created 01/09/2020 at 17:05
 * Class com.nivekaa.soko.service.CategoryServiceTest
 */

public class CategoryServiceTest extends TestCase {
    private CategoryService service = null;

    public void setUp() throws Exception {
        SokoHttpClient client = new SokoHttpClient(Data.API_KEY, "TestCase", false);
        service = new CategoryService(client);
    }

    public void testList() {
        ResponseListDTO<Category> res = service.list();
        assertNotNull(res);
        assertTrue(res.isSuccess());
        assertEquals(res.getStatus(), 200);
    }
}