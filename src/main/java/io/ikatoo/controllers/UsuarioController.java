package io.ikatoo.controllers;

import io.ikatoo.error.CustomErrorType;
import io.ikatoo.models.Usuario;
import io.ikatoo.models.dao.UsuarioDAO;
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
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioDAO dao;

    @Autowired
    public UsuarioController(UsuarioDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<Usuario> listAll() {
        return new ResponseEntity(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id) {
        Optional<Usuario> usuario = dao.findById(id);
        if (usuario == null)
            return new ResponseEntity<>(new CustomErrorType("Usuario não encontrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping(path = "/porUsuario/{usuario}")
    public ResponseEntity<?> findUsuarioByUsuario(@PathVariable String usuario) {
        return new ResponseEntity<>(dao.findByUsuarioIgnoreCaseContaining(usuario), HttpStatus.OK);
    }

    @GetMapping(path = "/porEmail/{email}")
    public ResponseEntity<?> findUsuarioByEmail(@PathVariable String email) {
        return new ResponseEntity<>(dao.findByEmailIgnoreCaseContaining(email), HttpStatus.OK);
    }

    @PostMapping
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public ResponseEntity<?> save(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(dao.save(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
