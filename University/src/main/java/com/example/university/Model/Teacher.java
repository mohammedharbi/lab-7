package com.example.university.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    @NotEmpty(message = "ID is empty")
    @Size(min = 2, message = "ID should be at least 2")
    private String id;

    @NotEmpty(message = "name is empty")
    @Size(min = 4, message = "name at least should be 4 letters")
    private String name;

    @NotNull(message = "age is empty")
    @Positive
    @Min(value = 23,message = "must be 18 or higher")
    private int age;

    @NotEmpty(message = "gender is empty")
    @Pattern(regexp = "^(male|female)$", message = "gender must be a male or female")
    private String gender;

    @NotEmpty(message = "jobType is empty")
    @Pattern(regexp = "^(fulltime|parttime)$", message = "jobType must be a fulltime or parttime")
    private String jobType;

    @NotEmpty(message = "phone is empty")
    @Pattern(regexp = "^(05)([0-9]{8})")
    private String phone;

    @AssertTrue
    private boolean isAvailable;

    @NotNull(message = "daysOff is empty")
    @Positive
    @Min(1)
    private int daysOff;
}
