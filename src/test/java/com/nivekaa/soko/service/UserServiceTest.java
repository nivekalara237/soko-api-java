package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.User;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.util.Data;
import junit.framework.TestCase;

/**
 * @author nivekaa
 * Created 01/09/2020 at 17:25
 * Class com.nivekaa.soko.service.UserServiceTest
 */

public class UserServiceTest extends TestCase {
    private UserService service = null;

    public void setUp() throws Exception {
        SokoHttpClient client = new SokoHttpClient(Data.API_KEY, "TestCase", false);
        service = new UserService(client);
    }

    public void testInfo() {
        ResponseDTO<User> res = service.info();
        assertNotNull(res);
        assertEquals(200, res.getStatus());
        assertNotNull(res.getData());
    }
}