package com.nivekaa.soko.util;

import junit.framework.TestCase;

import java.io.File;
import java.nio.file.Files;

/**
 * @author nivekaa
 * Created 01/09/2020 at 15:57
 * Class com.nivekaa.soko.util.FileUtilTest
 */

public class FileUtilTest extends TestCase {
    File[] mockFiles = new File[3];
    public void setUp() throws Exception {
        mockFiles = mock();
    }

    public void testGetFileSizeMegaBytes() {
        assertEquals(FileUtil.getFileSizeMegaBytes(1024*1024*9), 9D);
    }

    public void testGetFileSizeKiloBytes() {
        assertEquals(FileUtil.getFileSizeKiloBytes(1024*9), 9D);
    }

    public void testFilesSize() {
        assertFalse(FileUtil.filesSize(mockFiles) == 0L);
    }

    public void testDownload() {
    }

    public void testBytesToFile() {
    }

    public File[] mock() {
        String fileName = "file_test_.txt";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return new File[]{file, file, file};
    }
}