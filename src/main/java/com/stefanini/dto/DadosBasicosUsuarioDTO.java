package com.stefanini.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stefanini.entities.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class DadosBasicosUsuarioDTO {
    Long id;
    @NotBlank(message = "{nome.not.blank}")
    @Size(max = 50)
    private String nome;
    @NotBlank(message = "{login.not.blank}")
    @Size(min = 3, max = 20, message = "{login.not.valid}")
    private String login;
    @NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    @Size(min = 10)
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    public DadosBasicosUsuarioDTO(Usuario entity) {
        id = entity.getId();
        nome = entity.getNome();
        login = entity.getLogin();
        email = entity.getEmail();
        dataNascimento = entity.getDataNascimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
