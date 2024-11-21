package com.example.university.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "ID is empty")
    @Size(min = 2, message = "ID should be at least 2")
    private String id;

    @NotEmpty(message = "title is empty")
    @Size(max = 20,message = "maximum 20 letters only.")
    private String title;

    @NotEmpty(message = "description is empty")
    @Size(max = 50,message = "max 50 letters only for description")
    private String description;

    @NotEmpty(message = "content is empty")
    @Size(min = 4,message = "min 4 letters only for content")
    private String subject;

    @NotNull(message = "capacity is empty")
    @Positive
    @Min(value = 10,message = "min capacity is 10")
    private int capacity;
}
