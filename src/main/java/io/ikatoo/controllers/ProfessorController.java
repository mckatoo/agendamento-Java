package io.ikatoo.controllers;

import io.ikatoo.error.ResourceNotFoundException;
import io.ikatoo.models.Professor;
import io.ikatoo.models.dao.ProfessorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Professor> listAll(Pageable pageable) {
        return new ResponseEntity(dao.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProfessorById(@PathVariable Long id,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
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
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public ResponseEntity<?> save(@Valid @RequestBody Professor professor) {
        return new ResponseEntity<>(dao.save(professor), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        verifyIfProfessorExists(id);
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfProfessorExists(Long id) {
        if (!dao.findById(id).isPresent())
            throw new ResourceNotFoundException("Professor com id: " + id + " não encontrado.");
    }

    private void verifyIfProfessorExists(String professor) {
        if (dao.findByProfessorIgnoreCaseContaining(professor).isEmpty())
            throw new ResourceNotFoundException("Não encontrado nenhum professor contendo " + professor + " no nome.");
    }
}
