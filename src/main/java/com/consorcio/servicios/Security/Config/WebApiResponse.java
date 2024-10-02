package com.consorcio.servicios.Security.Config;

import lombok.*;
import java.time.LocalDateTime;

/**
 *
 * @author Cabrera Lautaro
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebApiResponse<T> {

    // Contenido principal de la respuesta
    private T data;

    // Mensaje adicional para el cliente
    private String message;

    // Indica si la operación fue exitosa o no
    private Boolean success;

    // Código de estado HTTP o de negocio
    private Integer statusCode;

    // En caso de error, detalles adicionales
    private String error;

    // Marca de tiempo de cuando se creó la respuesta
    private LocalDateTime timestamp;

    // Constructor para respuestas exitosas
    public static <T> WebApiResponse<T> success(T data, String message, Integer statusCode) {
        return WebApiResponse.<T>builder()
                .data(data)
                .message(message)
                .success(true)
                .statusCode(statusCode)
                .timestamp(LocalDateTime.now().minusHours(3))
                .build();
    }

    // Constructor para respuestas fallidas
    public static <T> WebApiResponse<T> error(String error, String message, Integer statusCode) {
        return WebApiResponse.<T>builder()
                .error(error)
                .message(message)
                .success(false)
                .statusCode(statusCode)
                .timestamp(LocalDateTime.now().minusHours(3))
                .build();
    }

}