package com.nivekaa.soko.service;

import com.nivekaa.soko.Soko;
import com.nivekaa.soko.model.File;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.util.Data;
import junit.framework.TestCase;

import java.util.UUID;

/**
 * @author nivekaa
 * Created 28/03/2020 at 01:17
 * Class com.nivekaa.soko.service.FileServiceTest
 */

public class FileServiceTest extends TestCase {
    public Soko soko = new Soko(Data.API_KEY, "TestCase");

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void test_sould_Upload_File_when_folder_id_is_non_specifier() {
        String fileName = "file_test_.txt";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        java.io.File file = new java.io.File(classLoader.getResource(fileName).getFile());

        ResponseDTO res = soko.file()
                .uploadFile()
                .addFile(file)
                .execute();
        assertNotNull(res);
        assertNotNull(res.getMessage());
        assertEquals(res.getMessage(), "Folder param is required");
        assertFalse(res.isSuccess());
        assertEquals(res.getStatus(), 404);
    }

    public void test_sould_Upload_File_when_data_is_correct() {
        String fileName = "file_test_.txt";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        java.io.File file = new java.io.File(classLoader.getResource(fileName).getFile());

        ResponseDTO res = soko.file()
                .uploadFile()
                .addFile(file)
                .folder("8cf06ac4-faa6-41b5-8c9b-92737e4ca6b2")
                .execute();
        assertNotNull(res);
        assertTrue(res.isSuccess());
        assertEquals(res.getStatus(), 200);
        assertEquals(res.getData().getClass(), File.class);
        assertEquals(((File)res.getData()).getExtension(), "txt");
        assertEquals(((File)res.getData()).getFilename(), "file_test_.txt");
        assertEquals(((File)res.getData()).getFolder().getName(), "my drive");
    }

    public void test_should_Create_file_ByBase64_when_data_are_correct() {
        ResponseDTO res = soko.file()
                .createByBase64()
                .fileEncoded(Data.FILE_BASE_64)
                .folder("8cf06ac4-faa6-41b5-8c9b-92737e4ca6b2")
                .execute();
        assertNotNull(res);
        assertTrue(res.isSuccess());
        assertEquals(res.getStatus(), 200);
        assertEquals(res.getData().getClass(), File.class);
        assertEquals(((File)res.getData()).getExtension(), "xlsx");
        assertEquals(((File)res.getData()).getFolder().getName(), "my drive");
    }

    public void test_should_Create_file_ByBase64_when_data_are_malformated() {
        ResponseDTO res = soko.file()
                .createByBase64()
                .fileEncoded(Data.FILE_BASE_64_MALFORMATED)
                .folder("8cf06ac4-faa6-41b5-8c9b-92737e4ca6b2")
                .execute();
        assertNotNull(res);
        assertFalse(res.isSuccess());
        assertEquals(res.getStatus(), 404);
        assertNull(res.getData());
        assertEquals(res.getMessage(), "Invalid base64 file encoded");
    }

    public void test_sould_get_file_By_its_Id() {
        // given
        ResponseDTO res = soko.file()
                .createByBase64()
                .fileEncoded(Data.FILE_BASE_64)
                .folder("8cf06ac4-faa6-41b5-8c9b-92737e4ca6b2")
                .execute();
        // when
        String id = ((File)res.getData()).getId();
        // then
        ResponseDTO resFile = soko.file().findById(id);
        assertNotNull(resFile);
        assertTrue(resFile.isSuccess());
        assertEquals(resFile.getStatus(), 200);
        assertEquals(((File)resFile.getData()).getClass(), File.class);
        assertNotNull(((File)resFile.getData()).getId());
    }

    public void test_sould_get_file_when_id_is_wrong() {
        // given
        String id = "0cd2ee3b-00a0-4g54-aa46-5a76fb7ef006";
        // then
        ResponseDTO res = soko.file()
                .findById(id);
        assertNotNull(res);
        assertFalse(res.isSuccess());
        assertTrue(res.getStatus() != 200);
    }

    public void test_should_Delete_when_is_exist() {
        // given
        ResponseDTO res = soko.file()
                .createByBase64()
                .fileEncoded(Data.FILE_BASE_64)
                .folder("8cf06ac4-faa6-41b5-8c9b-92737e4ca6b2")
                .execute();
        // when
        String id = ((File)res.getData()).getId();
        // then
        ResponseDTO resFile = soko.file().delete(id);
        assertNotNull(resFile);
        assertTrue(resFile.isSuccess());
        assertEquals(resFile.getStatus(), 200);
    }

    public void test_sould_delete_when_not_exist() {
        // given
        String id = "0cd2ee3b-00a0-4g54-aa46-5a76fb7ef006";
        // then
        ResponseDTO resFile = soko.file()
                .delete(id);
        assertNotNull(resFile);
        assertFalse(resFile.isSuccess());
        assertTrue(resFile.getStatus() != 200);
    }
}