package com.tuanha.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {
    @NotBlank(message = "PRODUCT_NAME_NOT_BLANK")
    String name;

    @NotBlank(message = "PRODUCT_DESCRIPTION_NOT_BLANK")
    String description;

    @NotBlank(message = "PRODUCT_IMAGE_NOT_BLANK")
    String imageUrl;

    @NotNull(message = "CATEGORY_ID_NOT_BLANK")
    Long categoryId;

    Long supplierId;
}
