package com.nivekaa.soko;

import com.nivekaa.soko.service.dto.ResponseDTO;
import com.nivekaa.soko.util.FileUtil;
import org.apache.commons.codec.Charsets;
import org.apache.hc.core5.http.Chars;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.net.URLEncodedUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author nivekaa
 * Created 22/03/2020 at 02:54
 * Class com.nivekaa.soko.SokoStorage
 */

public class SokoStorage {
    public static void main(String[] args) throws IOException, ParseException, URISyntaxException {
        String fileB64 = "data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,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";

        Soko service = new Soko.Builder()
                .setApikey("RagEvtpyXPuCVfzIqShMGl90wUDi0CcIprNg209y0lof7QcYV0IozVTC1bUa4eCZ")
                //.setApikey("Ba3Tpp61EZ37ljV8Q74yKsK5S63KnxHcTTbkTWLmLqcfBnFn7ubQav6pfEyLrHAm")
                .setAppName("Soko Lib test")
                .build();

        /*ResponseDTO<File> responseDTO = service.file()
                .createByBase64()
                .fileEncoded(fileB64)
                .folder("my drive")
                .execute();*/
        String url ="https://soko.isjetokoss.xyz/soko/1/my%28drive/b8ba7e4528b144f9b9439c63dbb83123.xlsx";
        String encode = URLEncoder.encode(url, "utf-8");
        URL url1 = new URL(url);
        String encod = String.format("%s://%s%s",
                url1.getProtocol(),
                url1.getHost(),
                url1.getPath().replaceAll(" ","%28"));

        System.out.println("--------------------------");
        System.out.println(encod);
        System.out.println("--------------------------");
       File file = service.client()
                .downloadAsFile(url);

        System.out.println("================");
        System.out.println(file.length());
        System.out.println("================");

    }
}
