package io.ikatoo.models.dao;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

import io.ikatoo.models.Turma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TurmaDAO extends CrudRepository<Turma, Long> {
    List<Turma> findByTurma(String turma);
}
