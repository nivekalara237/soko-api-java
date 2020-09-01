package com.nivekaa.soko.util;

import java.io.*;
import java.net.URLConnection;
import java.util.UUID;

/**
 * @author nivekaa
 * Created 22/03/2020 at 18:36
 * Class com.nivekaa.soko.util.FileUtil
 */

public class FileUtil {
    public static final double MAX_FILE_SIZE_UPLOAD_MB = 24.0;


    public static double getFileSizeMegaBytes(long l) {
        return (double) l / (1024 * 1024);
    }

    public static double getFileSizeKiloBytes(long l) {
        return (double) l / 1024;
    }

    public static long filesSize(File[] files){
        if (files==null)
            return 0L;
        long size = 0L;
        for (int i = 0; i < files.length ; i++) {
            size += files.length;
        }
        return size;
    }

    public static File bytesToFile(byte[] bytes){
        String uuid = UUID.randomUUID().toString();
        try {

            InputStream inputStream = new ByteArrayInputStream(bytes);
            String mimeType = URLConnection.guessContentTypeFromStream(inputStream);

            System.out.println("======================");
            System.out.println(mimeType);
            System.out.println("======================");

            File tempFile = File.createTempFile("soko_tmp_file", ".tmp");
            //tempFile.deleteOnExit();
            //tempFile.deleteOnExit();
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            fileOutputStream.write(bytes);
            fileOutputStream.close();

            File originalFile = tempFile.getCanonicalFile();

            System.out.println("=====================");
            System.out.println(originalFile.getAbsolutePath());
            System.out.println("=====================");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
