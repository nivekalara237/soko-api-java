package com.nivekaa.soko.handler;

import org.apache.hc.core5.function.Supplier;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

/**
 * @author nivekaa
 * Created 01/09/2020 at 20:30
 * Class com.nivekaa.soko.handler.CustomMultiPartEntity
 */

public class CustomMultiPartEntity implements HttpEntity {
    private final ProgressListener progressListener;
    private HttpEntity mHttpEntity;

    public CustomMultiPartEntity(ProgressListener progressListener, HttpEntity mHttpEntity) {
        this.progressListener = progressListener;
        this.mHttpEntity = mHttpEntity;
    }

    @Override
    public boolean isRepeatable() {
        return mHttpEntity.isRepeatable();
    }

    @Override
    public InputStream getContent() throws IOException, UnsupportedOperationException {
        return mHttpEntity.getContent();
    }

    @Override
    public void writeTo(OutputStream outputStream) throws IOException {
        mHttpEntity.writeTo(new ProgressiveOutputStream(outputStream, this.progressListener));
    }

    @Override
    public boolean isStreaming() {
        return mHttpEntity.isStreaming();
    }

    @Override
    public Supplier<List<? extends Header>> getTrailers() {
        return mHttpEntity.getTrailers();
    }

    @Override
    public void close() throws IOException {
        mHttpEntity.close();
    }

    @Override
    public long getContentLength() {
        return mHttpEntity.getContentLength();
    }

    @Override
    public String getContentType() {
        return mHttpEntity.getContentType();
    }

    @Override
    public String getContentEncoding() {
        return mHttpEntity.getContentEncoding();
    }

    @Override
    public boolean isChunked() {
        return mHttpEntity.isChunked();
    }

    @Override
    public Set<String> getTrailerNames() {
        return mHttpEntity.getTrailerNames();
    }
}
