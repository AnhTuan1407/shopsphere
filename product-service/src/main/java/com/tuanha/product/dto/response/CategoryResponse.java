package com.tuanha.product.dto.response;

import com.tuanha.product.enums.StatusCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    Long id;
    String name;
    String description;
    String image_url;
    StatusCategory status;
}
