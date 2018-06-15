package io.ikatoo.controllers;

import io.ikatoo.error.ResourceNotFoundException;
import io.ikatoo.models.Agendamento;
import io.ikatoo.models.dao.AgendamentoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {

    private final AgendamentoDAO dao;

    @Autowired
    public AgendamentoController(AgendamentoDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<Agendamento> listAll() {
        return new ResponseEntity(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getAgendamentoById(@PathVariable Long id) {
        Optional<Agendamento> agendamento = dao.findById(id);
        if (!agendamento.isPresent())
            throw new ResourceNotFoundException("Agendamento não encontrado para id:" + id);
        return new ResponseEntity<>(agendamento, HttpStatus.OK);
    }

    @GetMapping(path = "/porObservacao/{observacao}")
    public ResponseEntity<?> findAgendamentoByObservacao(@PathVariable String observacao) {
        return new ResponseEntity<>(dao.findByObservacaoIgnoreCaseContaining(observacao), HttpStatus.OK);
    }

    @PostMapping
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public ResponseEntity<?> save(@Valid @RequestBody Agendamento agendamento) {
        /*SE TIVER ID quer dizer que é atualização, então
         VERIFICAR SE O id_professor é igual ao do usuario/professor atual
         e se não for admin ou for o mesmo professor prossegue com o SAVE*/
        @Valid Agendamento action = agendamento.getIdagendamento() != null ? dao.save(agendamento) : null;
        HttpStatus status = agendamento.getIdagendamento() != null ? HttpStatus.CREATED : HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(action, status);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
