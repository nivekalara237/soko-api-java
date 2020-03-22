package com.nivekaa.soko.model;

import java.util.List;

/**
 * @author nivekaa
 * Created 22/03/2020 at 22:11
 * Class com.nivekaa.soko.model.BaseListModel
 */

public class BaseListModel<M> {
    private int status;
    private String message;
    private List<M> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<M> getList() {
        return list;
    }

    public void setList(List<M> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseListModel{");
        sb.append("status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
