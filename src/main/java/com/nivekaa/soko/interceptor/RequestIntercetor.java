package com.nivekaa.soko.interceptor;

import com.nivekaa.soko.util.ConfigUtil;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author nivekaa
 * Created 22/03/2020 at 03:17
 * Class com.nivekaa.soko.interceptor.RequestIntercetor
 */

public class RequestIntercetor implements HttpRequestInterceptor {
    private AtomicLong count = new AtomicLong(0);
    private String apiKey;
    private String appName;
    private boolean debug;
    public RequestIntercetor(String apikey, String appName, boolean debug) {
        this.apiKey = apikey;
        this.appName = appName;
        this.debug = debug;
    }

    public void process(HttpRequest request, EntityDetails entityDetails, HttpContext context) throws HttpException, IOException {
        //System.out.println("addRequestInterceptorFirst with HttpRequestInterceptor");
        request.setHeader("requestId", Long.toString(count.incrementAndGet()));
        request.addHeader(ConfigUtil.XAPIKEY_PARAM, apiKey);
        request.addHeader(ConfigUtil.XAPPNAME_PARAM, appName);
        request.addHeader("accept", "application/json");
        if (debug) {
            System.out.println("-------------------------------------------------");
            System.out.println("|  PATH    : " + request.getPath());
            System.out.println("|  METHOD  : " + request.getMethod());
            System.out.println("|  VERSION : " + request.getScheme());
            System.out.println("'-------------------------------------------------");
            System.out.println("\n>>>>>>>>------ Headers ------->>>>>>>");
            logHeaders(request.getHeaders());
            // logEntities();
        }

    }

    private void logHeaders(Header[] headers){
        Arrays.asList(headers).forEach(header -> System.out.println(header.getName()+" : "+ header.getValue()));
    }
}
