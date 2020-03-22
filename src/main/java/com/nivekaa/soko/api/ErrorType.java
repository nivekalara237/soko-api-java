package com.nivekaa.soko.api;

/**
 * @author nivekaa
 * Created 22/03/2020 at 18:47
 * Class com.nivekaa.soko.api.ErrorType
 */

public enum ErrorType {
    FILE_MAX_LENGTH_ATTEMPT("file_max_length_attempt"),
    MISSING_PARAMETER("missing_parameter"),
    UNKNOW_ERROR("unknow_error"),
    MISSING_FILES("missing_files");
    private String type;

    public String getType() {
        return type;
    }

    ErrorType(String type) {
        this.type = type;
    }
}
