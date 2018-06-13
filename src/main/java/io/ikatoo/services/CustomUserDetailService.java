package io.ikatoo.services;

import io.ikatoo.models.Usuario;
import io.ikatoo.models.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class CustomUserDetailService implements UserDetailsService {
    private final UsuarioDAO dao;

    @Autowired
    public CustomUserDetailService(UsuarioDAO dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario usuarios = Optional.ofNullable(dao.findByUsuario(usuario))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        List<GrantedAuthority> authorityListAdministrador = AuthorityUtils
                .createAuthorityList(String.valueOf(AuthorityUtils.createAuthorityList(String.valueOf(dao.findByTipoUsuario("administrador")))));
        List<GrantedAuthority> authorityListProfessor = AuthorityUtils
                .createAuthorityList(String.valueOf(AuthorityUtils.createAuthorityList(String.valueOf(dao.findByTipoUsuario("professor")))));
        return new org.springframework.security.core.userdetails.User
                (usuarios.getUsuario(), usuarios.getSenha(), usuarios.getTipoUsuario().getTipo().equals("administrador") ? authorityListAdministrador : authorityListProfessor);
    }
}
