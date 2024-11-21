package com.example.university.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "ID is empty")
    @Size(min = 2, message = "ID should be at least 2")
    private String ID;

    @NotEmpty(message = "name is empty")
    @Size(min = 4, message = "name at least should be 4 letters")
    private String name;

    @NotNull(message = "age is empty")
    @Positive
    @Min(value = 18,message = "must be 18 or higher")
    private int age;

    @NotEmpty(message = "gender is empty")
    @Pattern(regexp = "^(male|female)$", message = "gender must be a male or female")
    private String gender;

    @NotNull(message = "hours is empty")
    @Min(value = 12,message = "min hours is 12")
    @Max(value = 24,message = "max hours is 24")
    private int registerdHours;


    @NotEmpty(message = "phone is empty")
    @Pattern(regexp = "^(05)([0-9]{8})")
    private String phone;

    @AssertTrue
    private boolean isAvailable;
}
