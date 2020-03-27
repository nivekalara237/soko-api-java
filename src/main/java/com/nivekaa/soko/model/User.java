package com.nivekaa.soko.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author nivekaa
 * Created 22/03/2020 at 15:33
 * Class com.nivekaa.soko.model.User
 */

public class User implements Serializable {
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("max_api")
    private int maxApi;
    @SerializedName("space_left")
    private int spaceLeft;
    @SerializedName("number_files")
    private int numberFiles;
    @SerializedName("updated_at")
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxApi() {
        return maxApi;
    }

    public void setMaxApi(int maxApi) {
        this.maxApi = maxApi;
    }

    public int getSpaceLeft() {
        return spaceLeft;
    }

    public void setSpaceLeft(int spaceLeft) {
        this.spaceLeft = spaceLeft;
    }

    public int getNumberFiles() {
        return numberFiles;
    }

    public void setNumberFiles(int numberFiles) {
        this.numberFiles = numberFiles;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User() {
    }
}
