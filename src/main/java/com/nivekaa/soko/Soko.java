package com.nivekaa.soko;

import com.nivekaa.soko.api.SokoHttpClient;
import com.nivekaa.soko.service.CategoryService;
import com.nivekaa.soko.service.FileService;
import com.nivekaa.soko.service.FolderService;
import com.nivekaa.soko.service.UserService;

/**
 * @author nivekaa
 * Created 22/03/2020 at 17:51
 * Class com.nivekaa.soko.Soko
 */

public class Soko {
    private String ak;
    private String an;
    private SokoHttpClient httpClient;

    public Soko(String apikey, String appname){ // throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        this.ak = apikey;
        this.an = appname;
        this.httpClient = new SokoHttpClient(apikey, appname);
    }

    public static class Builder{
        private String apikey;
        private String appname;

        public Builder setApikey(String apikey) {
            this.apikey = apikey;
            return this;
        }

        public Builder setAppName(String appname) {
            this.appname = appname;
            return this;
        }

        public Builder() {
        }

        public Builder(String apikey) {
            this.apikey = apikey;
        }

        public Builder(String apikey, String appName) {
            this.apikey = apikey;
            this.appname = appName;
        }

        public Soko build(){
            /*try {
                return new Soko(apikey, appname);
            } catch (KeyManagementException | IOException | KeyStoreException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;*/
            return new Soko(apikey, appname);
        }
    }

    public FolderService folder(){
        return new FolderService(this.httpClient);
    }

    public FileService file(){
        return new FileService(this.httpClient);
    }

    public UserService user(){
        return new UserService(this.httpClient);
    }

    public CategoryService category(){
        return new CategoryService(this.httpClient);
    }
}