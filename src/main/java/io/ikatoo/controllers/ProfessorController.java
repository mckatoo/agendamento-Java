package io.ikatoo.controllers;

import io.ikatoo.error.ResourceNotFoundException;
import io.ikatoo.models.Professor;
import io.ikatoo.models.dao.ProfessorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

@RestController
@RequestMapping("professores")
public class ProfessorController {

    private final ProfessorDAO dao;

    @Autowired
    public ProfessorController(ProfessorDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<Professor> listAll() {
        return new ResponseEntity(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProfessorById(@PathVariable Integer id) {
        verifyIfProfessorExists(id);
        Optional<Professor> professor = dao.findById(id);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @GetMapping(path = "/porProfessor/{professor}")
    public ResponseEntity<?> findProfessorByProfessor(@PathVariable String professor) {
        verifyIfProfessorExists(professor);
        return new ResponseEntity<>(dao.findByProfessorIgnoreCaseContaining(professor), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Professor professor) {
        return new ResponseEntity<>(dao.save(professor), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        verifyIfProfessorExists(id);
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfProfessorExists(Integer id) {
        if (!dao.findById(id).isPresent())
            throw new ResourceNotFoundException("Professor com id: " + id + " não encontrado.");
    }

    private void verifyIfProfessorExists(String professor) {
        if (dao.findByProfessorIgnoreCaseContaining(professor).isEmpty())
            throw new ResourceNotFoundException("Não encontrado nenhum professor contendo " + professor + " no nome.");
    }
}
