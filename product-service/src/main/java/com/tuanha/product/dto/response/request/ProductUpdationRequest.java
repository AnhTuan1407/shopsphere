package com.tuanha.product.dto.response.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdationRequest {
    @NotBlank(message = "PRODUCT_NAME_NOT_BLANK")
    String name;

    @NotBlank(message = "PRODUCT_DESCRIPTION_NOT_BLANK")
    String description;

    @NotBlank(message = "PRODUCT_IMAGE_NOT_BLANK")
    String imageUrl;

    @NotNull(message = "CATEGORY_ID_NOT_BLANK")
    Long categoryId;
}
