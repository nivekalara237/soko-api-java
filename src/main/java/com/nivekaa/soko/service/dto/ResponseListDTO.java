package com.nivekaa.soko.service.dto;

import java.util.List;

/**
 * @author nivekaa
 * Created 26/03/2020 at 21:19
 * Class com.nivekaa.soko.service.dto.ResponseDTO
 */

public class ResponseListDTO<D> {
    private int status;
    private String message;
    private List<D> data;

    private ResponseListDTO(Builder<D> builder) {
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
        private List<D> data;

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

        public Builder withData(D file) {
            this.data = data;
            return this;
        }

        public ResponseListDTO<D> build() {
            return new ResponseListDTO<D>(this);
        }
    }
}
