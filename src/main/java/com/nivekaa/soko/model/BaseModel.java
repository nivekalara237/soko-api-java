package com.nivekaa.soko.model;

import java.io.Serializable;

/**
 * @author nivekaa
 * Created 22/03/2020 at 21:11
 * Class com.nivekaa.soko.model.BaseModel
 */

public class BaseModel implements Serializable {
    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public BaseModel setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
