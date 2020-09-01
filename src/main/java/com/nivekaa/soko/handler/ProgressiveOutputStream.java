package com.nivekaa.soko.handler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author nivekaa
 * Created 01/09/2020 at 20:36
 * Class com.nivekaa.soko.handler.ProgressiveOutputStream
 */

public class ProgressiveOutputStream  extends ProxyOutputStream {

    private long transferred;
    private final ProgressListener listener;

    public ProgressiveOutputStream(OutputStream proxy, final ProgressListener listener) {
        super(proxy);
        this.listener = listener;
        this.transferred = 0;
    }
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        int BUFFER_SIZE = 10000;
        int chunkSize;
        int currentOffset = 0;

        while (len>currentOffset) {
            chunkSize = len - currentOffset;
            if (chunkSize > BUFFER_SIZE) {
                chunkSize = BUFFER_SIZE;
            }
            out.write(b, currentOffset, chunkSize);
            currentOffset += chunkSize;
            this.transferred += chunkSize;
            //Log.i("CustomOutputStream WRITE","" + off + "|" + len + "|" + len + "|" + currentOffset + "|" + chunkSize + "|" + this.transferred);
            this.listener.transferred(this.transferred);
        }
    }

    @Override
    public void write(int b) throws IOException
    {
        out.write(b);
        this.transferred++;
        this.listener.transferred(this.transferred);
    }
}