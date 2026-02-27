package br.com.ednei.bipedneims.helper;

import br.com.ednei.bipedneims.http.dto.ApiError;

import java.util.Arrays;

public class ApiErrorBuilder {

    public static ApiError build(String message, String code) {
        if (message != null && message.contains(":")) {
            message = Arrays.stream(message.split(":")).toList().get(1);
        }

        return new ApiError(message, code);
    }

}
