package com.nivekaa.soko.api;

import com.google.gson.Gson;
import com.nivekaa.soko.interceptor.RequestIntercetor;
import com.nivekaa.soko.parser.GsonParser;
import com.nivekaa.soko.service.dto.ResultDTO;
import com.nivekaa.soko.util.FileUtil;
import com.nivekaa.soko.util.Stringutil;
import org.apache.hc.client5.http.ConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.HttpRoute;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeaderElementIterator;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.apache.http.protocol.HTTP;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author nivekaa
 * Created 22/03/2020 at 03:26
 * Class com.nivekaa.soko.api.SokoHttpClient
 */

public class SokoHttpClient implements IApi{
    public static final String XAPIKEY_PARAM = "X-API-KEY";
    public static final String XAPPNAME_PARAM = "X-APP-NAME";
    public Properties properties = new Properties();
    private PoolingHttpClientConnectionManager poolingConnManager;
    private CloseableHttpClient client;
    public String getUrl() {
        return properties.getProperty("soko.url");
    }

    private ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {
        @Override
        public TimeValue getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
            BasicHeaderElementIterator iterator = new BasicHeaderElementIterator(httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE));
            if (iterator.hasNext()) {
                HeaderElement he = iterator.next();
                String value = he.getValue();
                String param = he.getName();
                if (value!=null && param.equalsIgnoreCase("timeout")){
                    return TimeValue.ofMilliseconds(Long.parseLong(value) * 1000);
                }
            }
            return TimeValue.ofMilliseconds(5 * 1000);
        }
    };

    public SokoHttpClient(String apikey, String appName) throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String appConfigPath = rootPath + "application.yaml";
        properties.load(new FileInputStream(appConfigPath));

        TrustStrategy acceptingTrustStrategy = (cert, authType)-> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();

        poolingConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // Augmenter le nombre de connexions pouvant être ouvertes et gérées au-delà des limites par défaut
        // définir le nombre maximal de connexions ouvertes totales.
        poolingConnManager.setMaxTotal(200);
        // définir le nombre maximal de connexions simultanées par route, qui est de 2 par défaut.
        poolingConnManager.setDefaultMaxPerRoute(20);

        int timeoutConnection = Integer.parseInt(properties.getProperty("time-out-connection"));
        int responseTimeoutConnection = Integer.parseInt(properties.getProperty("response-time-out-connection"));
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(1000*timeoutConnection))
                .setConnectTimeout(Timeout.ofMilliseconds(1000 * timeoutConnection))
                .setResponseTimeout(responseTimeoutConnection, TimeUnit.SECONDS)
                .build();
        HttpHost host = new HttpHost(getUrl(), 80);
        poolingConnManager.setMaxPerRoute(new HttpRoute(host), 20);

        client = HttpClients
                .custom()
                .disableRedirectHandling()
                .addRequestInterceptorFirst(new RequestIntercetor(apikey, appName))
                .setKeepAliveStrategy(keepAliveStrategy)
                //.setDefaultHeaders(headers)
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(poolingConnManager)
                .build();
    }

    public ResultDTO get(String path){
        return get(path,null);
    }

    public ResultDTO get(String path, Map<String, Object> params) {
        path = Stringutil.isEmpty(path) ? "" : path;
        URIBuilder builder = new URIBuilder(URI.create(getUrl()+path));
        if (params!=null && !params.isEmpty()){
            List<NameValuePair> valuePairs = new ArrayList<>();
            params.forEach((key, value)-> valuePairs.add(new BasicNameValuePair(key, value.toString())));
            builder.addParameters(valuePairs);
        }
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(builder.build().toString());
            CloseableHttpResponse clientResponse = client.execute(httpGet);
            int code = clientResponse.getCode();
            String body = EntityUtils.toString(clientResponse.getEntity());
            ResultDTO resultDTO = ResultDTO.builder()
                    .withCode(code)
                    .withResponse(body)
                    .build();
            if (clientResponse.getCode() <= 299 && clientResponse.getCode()>= 200){
                return resultDTO;
            }else {
                return ResultDTO.builder()
                        .withCode(code)
                        .withResponse(GsonParser.errorMsg(body))
                        .build();
            }
        } catch (IOException | ParseException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    // basic post request
    public ResultDTO post(String path, Map<String, Object> body) throws RuntimeException{
        HttpPost httpPost = httpPost(path);
        Gson gson = new Gson();
        String stringJson = gson.toJson(body);
        StringEntity entity = new StringEntity(stringJson);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(entity);
        try {
            CloseableHttpResponse clientResponse = client.execute(httpPost);
            int code = clientResponse.getCode();
            String resbody = EntityUtils.toString(clientResponse.getEntity());
            ResultDTO resultDTO = ResultDTO.builder()
                    .withCode(code)
                    .withResponse(resbody)
                    .build();
            if (clientResponse.getCode() <= 299 && clientResponse.getCode()>= 200){
                return resultDTO;
            }else {
                return ResultDTO.builder()
                        .withCode(code)
                        .withResponse(GsonParser.errorMsg(resbody))
                        .build();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private HttpPost httpPost(String path){
        return new HttpPost(URI.create(getUrl()+path));
    }

    public ResultDTO put(String path, Map<String, Object> body, String id) {
        HttpPut httpPost = new HttpPut(URI.create(String.format("%s%s/%s", getUrl(), path, id)));
        String stringJson = new Gson().toJson(body);
        StringEntity entity = new StringEntity(stringJson);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(entity);
        try {
            CloseableHttpResponse clientResponse = client.execute(httpPost);
            int code = clientResponse.getCode();
            String resbody = EntityUtils.toString(clientResponse.getEntity());
            ResultDTO resultDTO = ResultDTO.builder()
                    .withCode(code)
                    .withResponse(resbody)
                    .build();
            if (clientResponse.getCode() <= 299 && clientResponse.getCode()>= 200){
                return resultDTO;
            }else {
                return ResultDTO.builder()
                        .withCode(code)
                        .withResponse(GsonParser.errorMsg(resbody))
                        .build();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResultDTO delete(String path) {
        HttpDelete httpDelete = new HttpDelete(URI.create(String.format("%s%s", getUrl(), path)));
        // String stringJson = new Gson().toJson(body);

        try {
            CloseableHttpResponse clientResponse = client.execute(httpDelete);
            int code = clientResponse.getCode();
            String resbody = EntityUtils.toString(clientResponse.getEntity());
            ResultDTO resultDTO = ResultDTO.builder()
                    .withCode(code)
                    .withResponse(resbody)
                    .withSuccess(GsonParser.isSuccess(resbody))
                    .build();
            if (clientResponse.getCode() <= 299 && clientResponse.getCode()>= 200){
                return resultDTO;
            }else {
                return ResultDTO.builder()
                        .withCode(code)
                        .withResponse(GsonParser.errorMsg(resbody))
                        .withSuccess(GsonParser.isSuccess(resbody))
                        .build();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResultDTO delete(String path, Map<String, Object> body) {
        HttpDelete httpDelete = new HttpDelete(URI.create(String.format("%s%s", getUrl(), path)));
        // String stringJson = new Gson().toJson(body);
        String stringJson = new Gson().toJson(body);
        StringEntity entity = new StringEntity(stringJson);
        httpDelete.addHeader("Content-Type", "application/json");
        httpDelete.setEntity(entity);
        try {
            CloseableHttpResponse clientResponse = client.execute(httpDelete);
            int code = clientResponse.getCode();
            String resbody = EntityUtils.toString(clientResponse.getEntity());
            ResultDTO resultDTO = ResultDTO.builder()
                    .withCode(code)
                    .withResponse(resbody)
                    .build();
            if (clientResponse.getCode() <= 299 && clientResponse.getCode()>= 200){
                return resultDTO;
            }else {
                return ResultDTO.builder()
                        .withCode(code)
                        .withResponse(GsonParser.errorMsg(resbody))
                        .build();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResultDTO multipartPost(String path, Map<String, Object> body, File[] files, String fileQueryName) {
        if (files!=null){
            if (FileUtil.getFileSizeMegaBytes(FileUtil.filesSize(files)) > FileUtil.MAX_FILE_SIZE_UPLOAD_MB){
                return ResultDTO.builder()
                        .withCode(400)
                        .withResponse("bad request: " + ErrorType.FILE_MAX_LENGTH_ATTEMPT.getType())
                        .build();
            }
        }

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if (body!=null && !body.isEmpty()){
            body.forEach((key, value) -> builder.addTextBody(key, value.toString()));
        }

        if (files==null || files.length==0){
            return ResultDTO.builder()
                    .withCode(400)
                    .withResponse("bad request: " + ErrorType.MISSING_FILES.getType())
                    .build();
        }else{
            for (File file : files) {
                builder.addBinaryBody(fileQueryName, file, ContentType.MULTIPART_FORM_DATA, file.getName());
            }
        }

        HttpPost httpPost = httpPost(path);
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
        try {
            CloseableHttpResponse clientResponse = client.execute(httpPost);
            int code = clientResponse.getCode();
            String resbody = EntityUtils.toString(clientResponse.getEntity());
            ResultDTO resultDTO = ResultDTO.builder()
                    .withCode(code)
                    .withResponse(resbody)
                    .build();
            if (clientResponse.getCode() <= 299 && clientResponse.getCode()>= 200){
                return resultDTO;
            }else {
                return ResultDTO.builder()
                        .withCode(code)
                        .withResponse(GsonParser.errorMsg(resbody))
                        .build();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
