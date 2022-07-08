package com.kw.starter.common.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.kw.starter.common.constant.AppStatus

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class AppResponse<D>(
    val status: Status,
    val data: D? = null,
) {
    companion object {
        fun <T> fromAppStatus(
            appStatus: AppStatus,
            description: String? = null,
            data: T? = null,
        ): AppResponse<T> =
            AppResponse(
                status = Status(
                    code = appStatus.code,
                    message = appStatus.message,
                    description = description ?: appStatus.description,
                ),
                data = data,
            )

        fun <T> fromCustomStatus(
            code: String,
            message: String,
            description: String? = null,
            data: T? = null,
        ): AppResponse<T> =
            AppResponse(
                status = Status(code = code, message = message, description = description),
                data = data,
            )
    }

    data class Status(
        val code: String? = null,
        val message: String? = null,
        val description: String? = null,
    )
}
