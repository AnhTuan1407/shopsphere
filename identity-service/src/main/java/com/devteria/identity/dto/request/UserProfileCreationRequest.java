package com.devteria.identity.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileCreationRequest {
    String userId;
    String firstName;
    String lastName;
    String email;
    LocalDate dob;
    String city;
}
