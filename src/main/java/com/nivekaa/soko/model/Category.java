package com.nivekaa.soko.model;

import java.io.Serializable;

/**
 * @author nivekaa
 * Created 22/03/2020 at 15:33
 * Class com.nivekaa.soko.model.Category
 */

public class Category implements Serializable {
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    private Category(Builder builder) {
        setId(builder.id);
        setName(builder.name);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static final class Builder {
        private int id;
        private String name;

        private Builder() {
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
