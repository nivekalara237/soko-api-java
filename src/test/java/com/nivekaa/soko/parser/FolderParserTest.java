package com.nivekaa.soko.parser;

import com.nivekaa.soko.model.Folder;
import com.nivekaa.soko.util.Data;
import junit.framework.TestCase;

import java.util.List;

/**
 * @author nivekaa
 * Created 28/03/2020 at 02:29
 * Class com.nivekaa.soko.parser.FolderParserTest
 */

public class FolderParserTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {

    }

    public void testToModel() {
        Folder file = FolderParser.getInstance()
                .toModel(Data.singleFolder);
        assertNotNull(file);
        assertEquals("14f8c621-02b6-4238-92d3-3655cb294435", file.getId());
        assertEquals("mydrive", file.getName());
    }

    public void testToListModel() {
        List<Folder> files = FolderParser.getInstance()
                .toListModel(Data.listFolder);
        assertNotNull(files);
        assertNotNull(files.get(0));
        assertEquals(files.get(0).getId(), "14f8c621-02b6-4238-92d3-3655cb294435");
    }
}