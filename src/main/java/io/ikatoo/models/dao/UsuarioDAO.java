package io.ikatoo.models.dao;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

import io.ikatoo.models.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long> {
    List<Usuario> findByUsuarioIgnoreCaseContaining(String usuario);
    List<Usuario> findByEmailIgnoreCaseContaining(String email);
    List<Usuario> findByTipoUsuario(String tipo);
    Usuario findByUsuario(String usuario);
}
