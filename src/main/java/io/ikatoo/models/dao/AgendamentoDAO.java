package io.ikatoo.models.dao;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

import io.ikatoo.models.Agendamento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AgendamentoDAO extends PagingAndSortingRepository<Agendamento, Long> {
    List<Agendamento> findByObservacaoIgnoreCaseContaining(String observacao);
}
