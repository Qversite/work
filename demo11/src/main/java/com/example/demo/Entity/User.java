package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя пользователя обязательно")
    @Size(min = 4, message = "Имя пользователя должно содержать минимум 4 символа")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 4, message = "Пароль должен содержать минимум 4 символа")
    private String password;

    @Pattern(regexp = "^\\+7\\(\\d{3}\\)-\\d{3}-\\d{2}-\\d{2}$", message = "Номер телефона должен быть в формате +7(XXX)-XXX-XX-XX")
    @NotBlank(message = "Номер телефона обязателен")
    private String phoneNumber;

    @NotBlank(message = "ФИО обязательно")
    @Pattern(regexp = "^[А-Яа-яЁё\\s]+$", message = "ФИО должно содержать только кириллические символы и пробелы")
    private String fullName;

    @Email(message = "Некорректный адрес электронной почты")
    @NotBlank(message = "Адрес электронной почты обязателен")
    @Column(unique = true)
    private String email;

    @CollectionTable(name = "users_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    private Set<Role> roles;
}