package com.tuanha.review.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponse {
    String id;
    String userId;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
}
