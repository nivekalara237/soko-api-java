package com.nivekaa.soko.service.dto;

/**
 * @author nivekaa
 * Created 26/03/2020 at 22:05
 * Class com.nivekaa.soko.service.dto.ResultDTO
 */

public class ResultDTO {
    private String response;
    private int code;

    public String getResponse() {
        return response;
    }

    public int getCode() {
        return code;
    }

    public ResultDTO(String response, int code) {
        this.response = response;
        this.code = code;
    }

    private ResultDTO(Builder builder) {
        response = builder.response;
        code = builder.code;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder newBuilder(ResultDTO copy) {
        Builder builder = new Builder();
        builder.response = copy.getResponse();
        builder.code = copy.getCode();
        return builder;
    }


    /**
     * {@code ResultDTO} builder static inner class.
     */
    public static final class Builder {
        private String response;
        private int code;

        private Builder() {
        }

        /**
         * Sets the {@code response} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param response the {@code response} to set
         * @return a reference to this Builder
         */
        public Builder withResponse(String response) {
            this.response = response;
            return this;
        }

        /**
         * Sets the {@code code} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param code the {@code code} to set
         * @return a reference to this Builder
         */
        public Builder withCode(int code) {
            this.code = code;
            return this;
        }

        /**
         * Returns a {@code ResultDTO} built from the parameters previously set.
         *
         * @return a {@code ResultDTO} built with parameters of this {@code ResultDTO.Builder}
         */
        public ResultDTO build() {
            return new ResultDTO(this);
        }
    }
}
