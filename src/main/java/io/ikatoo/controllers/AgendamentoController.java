package io.ikatoo.controllers;

import io.ikatoo.error.CustomErrorType;
import io.ikatoo.models.Agendamento;
import io.ikatoo.models.dao.AgendamentoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAgendamentoById(@PathVariable Integer id) {
        Optional<Agendamento> agendamento = dao.findById(id);
        if (agendamento == null)
            return new ResponseEntity<>(new CustomErrorType("Agendamento não encontrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(agendamento, HttpStatus.OK);
    }

    @GetMapping(path = "/porObservacao/{observacao}")
    public ResponseEntity<?> findAgendamentoByObservacao(@PathVariable String observacao) {
        return new ResponseEntity<>(dao.findByObservacaoIgnoreCaseContaining(observacao), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Agendamento agendamento) {
        return new ResponseEntity<>(dao.save(agendamento), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody Agendamento agendamento) {
        return new ResponseEntity<>(dao.save(agendamento), HttpStatus.OK);
    }
}
