package io.ikatoo.controllers;

import io.ikatoo.error.CustomErrorType;
import io.ikatoo.models.Curso;
import io.ikatoo.models.dao.CursoDAO;
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
@RequestMapping("cursos")
public class CursoController {

    private final CursoDAO dao;

    @Autowired
    public CursoController(CursoDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<Curso> listAll() {
        return new ResponseEntity(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCursoById(@PathVariable Long id) {
        Optional<Curso> curso = dao.findById(id);
        if (curso == null)
            return new ResponseEntity<>(new CustomErrorType("Curso não encontrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @GetMapping(path = "/porCurso/{curso}")
    public ResponseEntity<?> findCursoByCurso(@PathVariable String curso) {
        return new ResponseEntity<>(dao.findByCursoIgnoreCaseContaining(curso), HttpStatus.OK);
    }

    @PostMapping
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public ResponseEntity<?> save(@Valid @RequestBody Curso curso) {
        return new ResponseEntity<>(dao.save(curso), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
