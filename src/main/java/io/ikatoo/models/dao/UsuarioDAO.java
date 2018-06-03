package io.ikatoo.models.dao;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

import io.ikatoo.models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
    List<Usuario> findByUsuarioIgnoreCaseContaining(String usuario);
}
