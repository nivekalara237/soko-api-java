package com.nivekaa.soko.model;

/**
 * @author nivekaa
 * Created 22/03/2020 at 15:33
 * Class com.nivekaa.soko.model.File
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

/**
 * Description
 * id    string
 * the UUID
 *
 * extension	string
 * the extension of file (e.g: png, zip, txt, etct...)
 *
 * type	string
 * type of file (e.g: file, image, zip, etct...)
 *
 * size	integer($int32)
 * the size for file
 *
 * filename	string
 * filename
 *
 * mime_type	string
 * mime type (e.g: application/zip, image/png, etc...)
 *
 * category_id	string
 * category IDd
 *
 * url	string
 * url
 *
 * owner_id	integer
 * owner ID: the current user by API KEY
 *
 * created_at	string($date-time)
 * created_at
 *
 * folder	string
 * folder UUID id or name or path
 */

/**
 * Example
 * {
 *         "extension": "pdf",
 *         "type": "file",
 *         "size": "79.782 KB",
 *         "original_name": "ab1c176206eb425590597d2ab9e637b9.pdf",
 *         "url": "http://localhost:8000/soko//ab1c176206eb425590597d2ab9e637b9.pdf",
 *         "created_at": "2020-03-20 02:12:06",
 *         "folder": {
 *           "name": "test"
 *         },
 *         "created_by": {
 *           "name": "soko admin updated"
 *         }
 *       }
 */


public class File implements Serializable {
    @SerializedName("_id")
    private String id;
    @SerializedName("extension")
    private String extension;
    @SerializedName("size")
    private String size;
    @SerializedName("original_name")
    private String filename;
    @SerializedName("url")
    private String url;
    @SerializedName("folder")
    private Folder folder;
    @SerializedName("created_by")
    private User createdBy;
    @SerializedName("created_at")
    private String createdAt;
    private transient String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public File() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id.equals(file.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "File{" +
                "id='" + id + '\'' +
                ", extension='" + extension + '\'' +
                ", size=" + size +
                ", filename='" + filename + '\'' +
                ", url='" + url + '\'' +
                ", folder='" + folder + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
