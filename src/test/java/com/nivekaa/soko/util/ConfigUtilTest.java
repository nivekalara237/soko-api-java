package com.nivekaa.soko.util;

import junit.framework.TestCase;

/**
 * @author nivekaa
 * Created 01/09/2020 at 15:54
 * Class com.nivekaa.soko.util.ConfigUtilTest
 */

public class ConfigUtilTest extends TestCase {
    public void testConstants() {
        assertEquals(ConfigUtil.CONNECTION_TIME_OUT, 5);
        assertEquals(ConfigUtil.MAX_CONNECTION, 5);
        assertEquals(ConfigUtil.RESPONSE_TIME_OUT_CONNECTION, 25);
        assertEquals(ConfigUtil.XAPIKEY_PARAM, "X-API-KEY");
        assertEquals(ConfigUtil.XAPPNAME_PARAM, "X-APP-NAME");
        assertEquals(ConfigUtil.URL, "https://soko.isjetokoss.xyz/storage/api/v1/");
    }
}