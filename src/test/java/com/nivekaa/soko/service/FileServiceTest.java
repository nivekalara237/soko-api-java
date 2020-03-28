package com.nivekaa.soko.service;

import com.nivekaa.soko.Soko;
import com.nivekaa.soko.service.dto.ResponseDTO;
import junit.framework.TestCase;

/**
 * @author nivekaa
 * Created 28/03/2020 at 01:17
 * Class com.nivekaa.soko.service.FileServiceTest
 */

public class FileServiceTest extends TestCase {
    public Soko soko = new Soko("RagEvtpyXPuCVfzIqShMGl90wUDi0CcIprNg209y0lof7QcYV0IozVTC1bUa4eCZ", "TestCase");


    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testUploadFile() {
        String fileName = "file_test_.txt";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        java.io.File file = new java.io.File(classLoader.getResource(fileName).getFile());

        ResponseDTO res = soko.file()
                .uploadFile()
                .addFile(file)
                .execute();
        assertNotNull(res);
        assertNotNull(res.getMessage());
        assertFalse(res.isSuccess());
    }

    public void testCreateByBase64() {
    }

    public void testFindById() {
    }

    public void testDelete() {
    }
}