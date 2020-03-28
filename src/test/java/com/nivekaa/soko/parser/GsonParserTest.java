package com.nivekaa.soko.parser;

import com.nivekaa.soko.model.File;
import com.nivekaa.soko.model.Pagination;
import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.service.dto.ResponseListDTO;
import com.nivekaa.soko.util.Data;
import junit.framework.TestCase;

/**
 * @author nivekaa
 * Created 28/03/2020 at 02:43
 * Class com.nivekaa.soko.parser.GsonParserTest
 */

public class GsonParserTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testStriingToModel() {
        Object object = GsonParser.striingToModel(Data.sf, File.class);
        assertNotNull(object);
        assertTrue(object instanceof File);
        assertEquals("994ec4c7-e1d3-480b-a45a-7dae42414a36", ((File)object).getId());
    }

    public void testIsOk() {
        boolean ok = GsonParser.isOk(Data.singleFile);
        assertTrue(ok);
    }

    public void testIsPresents() {
        boolean isPresents = GsonParser.isPresents(Data.singleFile);
        assertTrue(isPresents);
    }

    public void testIsSuccess() {
        boolean success = GsonParser.isSuccess(Data.singleFile);
        assertTrue(success);
    }

    public void testGetPagination() {
        Pagination pagination = GsonParser.getPagination(Data.fileList);
        assertNotNull(pagination);
        assertEquals(6, pagination.getTotal());
    }

    public void testErrorMsg() {
        String msg = GsonParser.errorMsg("{\"success\": false, \"message\": \"File not found\"}");
        assertNotNull(msg);
        assertEquals(msg, "File not found");
    }

    public void testResponseDtoToJsonString() {
        ResponseDTO<File> dto = ResponseDTO.builder()
                .withSuccess(true)
                .withStatus(200)
                .withMessage(null)
                .withData(GsonParser.striingToModel(Data.sf, File.class))
                .build();
        assertTrue(dto.isSuccess());
        assertEquals("994ec4c7-e1d3-480b-a45a-7dae42414a36", dto.getData().getId());
        assertEquals(dto.toJson(), "{\"data\":{\"_id\":\"994ec4c7-e1d3-480b-a45a-7dae42414a36\",\"extension\":\"png\",\"size\":\"435.953 KB\",\"original_name\":\"Capture d’écran du 2020-02-07 12-49-51.png\",\"url\":\"http://127.0.0.1:8000/soko//2987cc10450745e99aabc60346bf5b1e.png\",\"folder\":{\"_id\":null,\"name\":\"test\",\"parent\":null,\"is_base\":false,\"created_at\":null},\"created_by\":{\"id\":null,\"name\":\"soko admin updated\",\"max_api\":0,\"space_left\":0,\"number_files\":0,\"updated_at\":null},\"created_at\":\"2020-03-27 21:52:39\"},\"success\":true,\"status\":200,\"message\":null}");
    }
}