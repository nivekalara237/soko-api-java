package com.nivekaa.soko.service.dto;

import com.nivekaa.soko.model.Pagination;
import com.nivekaa.soko.parser.GsonParser;

import java.util.List;

/**
 * @author nivekaa
 * Created 26/03/2020 at 21:19
 * Class com.nivekaa.soko.service.dto.ResponseDTO
 */

public class ResponseListDTO<D> {
    private int status;
    private boolean success;
    private String message;
    private List<D> data;
    private Pagination pagination;

    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<D> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    private ResponseListDTO(Builder<D> builder) {
        status = builder.status;
        success = builder.success;
        message = builder.message;
        data = builder.data;
        pagination = builder.pagination;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder<D> {
        private int status;
        private boolean success;
        private String message;
        private List<D> data;
        private Pagination pagination;

        private Builder() {
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withSuccess(Boolean success) {
            this.success = success;
            return this;
        }

        public Builder withData(List<D> _data) {
            this.data = _data;
            return this;
        }

        public Builder withPagination(Pagination pagination) {
            this.pagination = pagination;
            return this;
        }

        public ResponseListDTO<D> build() {
            return new ResponseListDTO<D>(this);
        }
    }

    public String toJson(){
        return GsonParser.responseListDtoToJsonString(this);
    }
}
