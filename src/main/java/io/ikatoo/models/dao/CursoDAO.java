package io.ikatoo.models.dao;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

import io.ikatoo.models.Curso;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CursoDAO extends PagingAndSortingRepository<Curso, Long> {
    List<Curso> findByCursoIgnoreCaseContaining(String curso);
}
