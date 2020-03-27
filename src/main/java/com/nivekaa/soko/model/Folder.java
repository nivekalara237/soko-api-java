package com.nivekaa.soko.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author nivekaa
 * Created 22/03/2020 at 15:33
 * Class com.nivekaa.soko.model.Folder
 */

/**
 * Example
 *
 * {
 *    "_id": "48043fcc-ac1c-43bb-8d0d-0031e9166595",
 *    "name": "sous dossier de test",
 *    "base": false,
 *    "created_at": "2020-03-14 18:07:06",
 *    "created_by": {
 *       "name": "soko admin updated"
 *     },
 *     "parent": {
 *          "_id": "9d99929c-fab1-4c69-91e1-e54993fbca33",
 *          "name": "test"
 *      }
 *    }
 */

public class Folder implements Serializable {
    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("parent")
    private Folder parent;
    @SerializedName("is_base")
    private boolean isBase;
    @SerializedName("created_at")
    private String createdAt;

    public Folder() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public boolean isBase() {
        return isBase;
    }

    public void setBase(boolean base) {
        isBase = base;
    }

    public String isCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return id.equals(folder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parent='" + parent + '\'' +
                ", isBase=" + isBase +
                ", createdAt=" + createdAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
