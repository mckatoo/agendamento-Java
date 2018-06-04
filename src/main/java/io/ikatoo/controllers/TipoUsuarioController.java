package io.ikatoo.controllers;

import io.ikatoo.error.CustomErrorType;
import io.ikatoo.models.TipoUsuario;
import io.ikatoo.models.dao.TipoUsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

@RestController
@RequestMapping("tipousuario")
public class TipoUsuarioController {

    private final TipoUsuarioDAO dao;

    @Autowired
    public TipoUsuarioController(TipoUsuarioDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<TipoUsuario> listAll() {
        return new ResponseEntity(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getTipoUsuarioById(@PathVariable Integer id) {
        Optional<TipoUsuario> tipousuario = dao.findById(id);
        if (tipousuario == null)
            return new ResponseEntity<>(new CustomErrorType("TipoUsuario não encontrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tipousuario, HttpStatus.OK);
    }

    @GetMapping(path = "/porTipoUsuario/{tipousuario}")
    public ResponseEntity<?> findTipoUsuarioByTipoUsuario(@PathVariable String tipousuario) {
        return new ResponseEntity<>(dao.findByTipoIgnoreCaseContaining(tipousuario), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TipoUsuario tipousuario) {
        return new ResponseEntity<>(dao.save(tipousuario), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
