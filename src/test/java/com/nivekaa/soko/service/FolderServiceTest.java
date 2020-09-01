package com.nivekaa.soko.service;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.model.Folder;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.service.dto.ResponseListDTO;
import com.nivekaa.soko.util.Data;
import junit.framework.TestCase;

/**
 * @author nivekaa
 * Created 01/09/2020 at 17:46
 * Class com.nivekaa.soko.service.FolderServiceTest
 */

public class FolderServiceTest extends TestCase {

    FolderService service = null;

    public void setUp() throws Exception {
        SokoHttpClient client = new SokoHttpClient(Data.API_KEY, "TestCase", false);
        service = new FolderService(client);
    }

    public void testCreate() {
        ResponseDTO<Folder> res = service.create()
                .addName("test-create" + System.currentTimeMillis())
                //.addParent("my drive")
                .execute();
        assertNotNull(res);
        assertNotNull(res.getData().getId());
        assertEquals(res.getStatus(), 200);
    }

    public void testFind() {
        ResponseDTO<Folder> i = service.create()
                .addName("test-tm" + System.currentTimeMillis())
                // .addParent("test-create")
                .execute();
        ResponseDTO<Folder> res = service.find(i.getData().getId());
        assertNotNull(res);
        assertNotNull(res.getData().getId());
        assertEquals(res.getStatus(), 200);
        // assertTrue(res.isSuccess());
    }

    public void testList() {
        ResponseListDTO<Folder> res = service.list(1);
        assertNotNull(res);
        assertNotNull(res.getData());
        assertEquals(res.getStatus(), 200);
        assertTrue(res.isSuccess());
    }
}