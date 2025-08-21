package com.bank.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "This model is used to return errors in RFC 7807")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiExceptionResponse {
    @Schema(description = "The unique uri that identifies that categorizes the error", name = "type",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "/errors/authentication/not-authorized")
    private String type;

    @Schema(description = "A brief, human-readable message about the error", name = "title",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "The user does not have authorization")
    private String title;

    @Schema(description = "The unique error code", name = "code",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "192")
    private String code;

    @Schema(description = "A human-readable explanation of the error", name = "detail",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "The user does not have the properly permissions to acces the resource, please contact with us https://softobots.com")
    private String detail;

    @Schema(description = "A URI that identifies the specific occurrence of the error", name = "instance",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "/errors/authentication/not-authorized/01")
    private String instance;

    public ApiExceptionResponse(String type, String title, String code, String detail) {
        super();
        this.title = title;
        this.code = code;
        this.detail = detail;
        this.type = type;
    }
}
