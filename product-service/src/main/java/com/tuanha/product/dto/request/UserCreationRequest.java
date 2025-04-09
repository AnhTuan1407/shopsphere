package com.tuanha.product.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    String username;
    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    boolean isSeller;
}