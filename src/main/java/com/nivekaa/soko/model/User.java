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
    private int updatedAt;

    private User(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setMaxApi(builder.maxApi);
        setSpaceLeft(builder.spaceLeft);
        setNumberFiles(builder.numberFiles);
        setUpdatedAt(builder.updatedAt);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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

    public int getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User() {
    }

    public static final class Builder {
        private Long id;
        private String name;
        private int maxApi;
        private int spaceLeft;
        private int numberFiles;
        private int updatedAt;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withMaxApi(int maxApi) {
            this.maxApi = maxApi;
            return this;
        }

        public Builder withSpaceLeft(int spaceLeft) {
            this.spaceLeft = spaceLeft;
            return this;
        }

        public Builder withNumberFiles(int numberFiles) {
            this.numberFiles = numberFiles;
            return this;
        }

        public Builder withUpdatedAt(int updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
