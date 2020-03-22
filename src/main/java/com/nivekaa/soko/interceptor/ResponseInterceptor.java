package com.nivekaa.soko.interceptor;

import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpResponseInterceptor;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.io.IOException;

/**
 * @author nivekaa
 * Created 22/03/2020 at 04:36
 * Class com.nivekaa.soko.interceptor.ResponseInterceptor
 */

public class ResponseInterceptor implements HttpResponseInterceptor {
    public void process(HttpResponse httpResponse, EntityDetails entityDetails, HttpContext httpContext) throws HttpException, IOException {

    }
}
