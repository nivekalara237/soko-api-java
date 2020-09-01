package com.nivekaa.soko.parser;

import com.nivekaa.soko.model.File;
import com.nivekaa.soko.util.Data;
import junit.framework.TestCase;

import java.util.List;

/**
 * @author nivekaa
 * Created 28/03/2020 at 01:25
 * Class com.nivekaa.soko.parser.FileParserTest
 */

public class FileParserTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testToModel() {
        File file = FileParser.getInstance()
                .toModel(Data.singleFile);
        assertNotNull(file);
        assertEquals("994ec4c7-e1d3-480b-a45a-7dae42414a36", file.getId());
        assertEquals("png", file.getExtension());
    }

    public void testToListModel() {
        List<File> files = FileParser.getInstance()
                .toListModel(Data.fileList);
        assertNotNull(files);
        assertNotNull(files.get(0));
        assertEquals(files.get(1).getId(), "4dc7adb0-ed1e-4c9c-ad5c-d75a25b89ba2");
    }
}