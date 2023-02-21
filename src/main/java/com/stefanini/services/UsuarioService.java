package com.stefanini.services;

import com.stefanini.dto.AtualizacaoUsuarioDTO;
import com.stefanini.dto.CadastroUsuarioDTO;
import com.stefanini.dto.DadosBasicosUsuarioDTO;
import com.stefanini.entities.Usuario;
import com.stefanini.repositories.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequestScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public String criptografarSenha(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    @Transactional
    public CadastroUsuarioDTO CadastrarUsuario(CadastroUsuarioDTO dto) {
        dto.setSenha(criptografarSenha(dto.getSenha()));
        Usuario usuario = new Usuario(null, dto.getNome(), dto.getLogin(), dto.getEmail(), dto.getSenha(), dto.getDataNascimento(), LocalDateTime.now(), null);
        usuarioRepository.save(usuario);
        return new CadastroUsuarioDTO(usuario);
    }

    @Transactional
    public AtualizacaoUsuarioDTO atualizarUsuario(Long id, AtualizacaoUsuarioDTO atualizacaoUsuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        usuario.setSenha(atualizacaoUsuarioDTO.getSenha() != null ? criptografarSenha(atualizacaoUsuarioDTO.getSenha()) : usuario.getSenha());
        usuario.setNome(Objects.requireNonNullElse(atualizacaoUsuarioDTO.getNome(), usuario.getNome()));
        usuario.setLogin(Objects.requireNonNullElse(atualizacaoUsuarioDTO.getLogin(), usuario.getLogin()));
        usuario.setEmail(Objects.requireNonNullElse(atualizacaoUsuarioDTO.getEmail(), usuario.getEmail()));
        usuario.setDataNascimento(Objects.requireNonNullElse(atualizacaoUsuarioDTO.getDataNascimento(), usuario.getDataNascimento()));
        usuario.setDataAtualizacao(LocalDateTime.now());

        usuarioRepository.update(usuario);

        return new AtualizacaoUsuarioDTO(usuario);
    }

    public List<DadosBasicosUsuarioDTO> buscarAniversariantesDoMes(Integer mes) {
        List<Usuario> listaAniversariantes = usuarioRepository.buscarAniversariantesDoMes(mes);
        return listaAniversariantes.stream().map(x -> new DadosBasicosUsuarioDTO(x)).collect(Collectors.toList());
    }

    public List<String> listarProvedores() {
        return usuarioRepository.listarProvedores();
    }

    public List<DadosBasicosUsuarioDTO> listarUsuariosPorInicial(String inicial) {
        List<Usuario> listaUsuarios = usuarioRepository.listarUsuariosPorInicial(inicial);
        return listaUsuarios.stream().map(x -> new DadosBasicosUsuarioDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public void removerUsuario(Long id) {
        usuarioRepository.delete(id);
    }
}
