package com.nivekaa.soko.handler;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

/**
 * @author nivekaa
 * Created 22/03/2020 at 04:21
 * Class com.nivekaa.soko.handler.MultiHttpClientConnThread
 */

public class MultiHttpClientConnThread extends Thread{
    private CloseableHttpClient client;
    private HttpGet get;

    public MultiHttpClientConnThread(CloseableHttpClient closeableHttpClient, HttpGet get){
        this.get = get;
        this.client = closeableHttpClient;
    }

    // standard constructors
    public void run(){
        try {
            CloseableHttpResponse response = client.execute(get);
            EntityUtils.consume(response.getEntity());
        } catch (ClientProtocolException ex) {
        } catch (IOException ex) {
        }
    }
}
