package com.nivekaa.soko;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.service.FileService;
import com.nivekaa.soko.service.FolderService;
import com.nivekaa.soko.service.UserService;
import junit.framework.TestCase;

/**
 * @author nivekaa
 * Created 27/03/2020 at 23:15
 * Class com.nivekaa.soko.SokoTest
 */

public class SokoTest extends TestCase {
    public Soko soko = new Soko("RagEvtpyXPuCVfzIqShMGl90wUDi0CcIprNg209y0lof7QcYV0IozVTC1bUa4eCZ", "TestCase");

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testFolder() {
        assertEquals(FolderService.class.getName(), soko.folder().getClass().getName());
    }

    public void testFile() {
        assertEquals(FileService.class.getName(), soko.file().getClass().getName());
    }

    public void testUser() {
        assertEquals(UserService.class.getName(), soko.user().getClass().getName());
    }
}