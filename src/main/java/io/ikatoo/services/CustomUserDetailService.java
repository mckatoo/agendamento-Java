package io.ikatoo.services;

import io.ikatoo.models.Usuario;
import io.ikatoo.models.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

@Component
public class CustomUserDetailService implements UserDetailsService {
    private final UsuarioDAO dao;

    @Autowired
    public CustomUserDetailService(UsuarioDAO dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario user = Optional.ofNullable(dao.findByUsuario(usuario))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        List<GrantedAuthority> authorityListAdministrador = AuthorityUtils.createAuthorityList("ROLE_"+user.getTipoUsuario().getTipo());
        List<GrantedAuthority> authorityListProfessor = AuthorityUtils.createAuthorityList("ROLE_"+user.getTipoUsuario().getTipo());
//        List<GrantedAuthority> authorityListAdministrador = AuthorityUtils.createAuthorityList("ROLE_PROFESSOR", "ROLE_ADMINISTRADOR");
//        List<GrantedAuthority> authorityListProfessor = AuthorityUtils.createAuthorityList("ROLE_PROFESSOR");
        System.out.println(user.getTipoUsuario().getTipo().equals("administrador"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsuario(),
                user.getSenha(),
                user.getTipoUsuario().getTipo().equals("administrador") ? authorityListAdministrador : authorityListProfessor);
    }
}
