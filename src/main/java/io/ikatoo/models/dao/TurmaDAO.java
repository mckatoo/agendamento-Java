package io.ikatoo.models.dao;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

import io.ikatoo.models.Turma;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TurmaDAO extends PagingAndSortingRepository<Turma, Long> {
    List<Turma> findByTurmaIgnoreCaseContaining(String turma);
}
