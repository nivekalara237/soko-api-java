package com.nivekaa.soko.interceptor;

import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author nivekaa
 * Created 22/03/2020 at 04:36
 * Class com.nivekaa.soko.interceptor.ResponseInterceptor
 */

public class ResponseInterceptor implements HttpResponseInterceptor {
    private boolean debug;
    public ResponseInterceptor(boolean debuggable) {
        this.debug = debuggable;
    }

    public void process(HttpResponse response, EntityDetails entityDetails, HttpContext httpContext) throws HttpException, IOException {
        if (debug) {
            System.out.println("<<<<<<<<<<<<<<<<<------ OUTPUT ------|");
            System.out.println(response.getCode() + " " + response.getReasonPhrase());
        }
    }
}
