package com.stefanini.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stefanini.entities.Usuario;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CadastroUsuarioDTO {
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
    @NotBlank(message = "{senha.not.blank}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&*()_+^]).{4,10}", message = "{senha.not.pattern}")
    @Size(min = 4, max = 10, message = "{senha.not.size}")
    private String senha;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    CadastroUsuarioDTO(){}

    public CadastroUsuarioDTO(Usuario entity) {
        id = entity.getId();
        nome = entity.getNome();
        login = entity.getLogin();
        email = entity.getEmail();
        senha = entity.getSenha();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
