package io.ikatoo.models.dao;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

import io.ikatoo.models.Agendamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AgendamentoDAO extends CrudRepository<Agendamento, Long> {
}
