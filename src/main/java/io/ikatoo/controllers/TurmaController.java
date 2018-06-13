package io.ikatoo.controllers;

import io.ikatoo.error.CustomErrorType;
import io.ikatoo.models.Turma;
import io.ikatoo.models.dao.TurmaDAO;
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
@RequestMapping("turmas")
public class TurmaController {

    private final TurmaDAO dao;

    @Autowired
    public TurmaController(TurmaDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<Turma> listAll() {
        return new ResponseEntity(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getTurmaById(@PathVariable Long id) {
        Optional<Turma> turma = dao.findById(id);
        if (turma == null)
            return new ResponseEntity<>(new CustomErrorType("Turma não encontrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(turma, HttpStatus.OK);
    }

    @GetMapping(path = "/porTurma/{turma}")
    public ResponseEntity<?> findTurmaByTurma(@PathVariable String turma) {
        return new ResponseEntity<>(dao.findByTurmaIgnoreCaseContaining(turma), HttpStatus.OK);
    }

    @PostMapping
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public ResponseEntity<?> save(@Valid @RequestBody Turma turma) {
        return new ResponseEntity<>(dao.save(turma), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
