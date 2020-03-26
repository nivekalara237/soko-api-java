package com.nivekaa.soko.service.dto;

/**
 * @author nivekaa
 * Created 26/03/2020 at 21:19
 * Class com.nivekaa.soko.service.dto.ResponseDTO
 */

public class ResponseDTO<D> {
    private int status;
    private String message;
    private D data;

    public ResponseDTO() {
    }

    private ResponseDTO(Builder<D> builder) {
        status = builder.status;
        message = builder.message;
        data = builder.data;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder<D> {
        private int status;
        private String message;
        private D data;

        public Builder() {
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withData(D file) {
            this.data = file;
            return this;
        }

        public ResponseDTO<D> build() {
            return new ResponseDTO<D>(this);
        }
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public D getData() {
        return data;
    }
}
