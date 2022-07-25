package com.sigma.pollboxapi.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AccountSignupRequest {
    @NotNull(message = "O campo e-mail não pode ser nulo.")
    @NotEmpty(message = "O campo e-mail não pode ser vazio.")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Formato de e-mail inválido. Ex: test@email.com")
    private String email;

    @NotNull(message = "O campo nome do usuário não pode ser nulo.")
    @NotEmpty(message = "O campo nome do usuário não pode ser vazio.")
    @Size(min = 4, max = 36, message = "O nome de usuário deve ser maior que 4 e menor que 36 letras.")
    private String username;

    @NotNull(message = "O campo senha não pode ser nulo.")
    @NotEmpty(message = "O campo senha não pode ser vazio.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "A senha deve conter letras maiúsculas, minúsculas, números e pelo menos 8 caracteres.")
    private String password;

    @NotNull(message = "O campo identificação do dispositivo não pode ser nulo.")
    @NotEmpty(message = "O campo identificação do dispositivo não pode ser vazio.")
    @Size(min = 16, max = 16, message = "A identificação do dispositivo deve ter 16 caracteres.")
    private String deviceIdentification;
}
