package com.eazybytes.eazyschool.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Contact {
    @NotBlank(message = "Name must not be blank")
    @Size(message = "Name must not be less that 3 characters length")
    private String name;
    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNum;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide valid email")
    private String email;
    @NotBlank(message = "Subject must not be blank")
    @Size(min = 5, message = "Subject must not be less than 5 character length")
    private String subject;
    @NotBlank(message = "Message must not be blank")
    @Size(min = 10, message = "Message must not be less than 10 character length")
    private String message;
}
