package io.ikatoo.controllers;

import io.ikatoo.models.Professor;
import io.ikatoo.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Milton Carlos Katoo for iKatoo on 02/06/2018.
 */

@RestController
@RequestMapping("professores")
public class ProfessorController {
    @Autowired
//    public DateUtil dateUtil;

    @GetMapping
    public List<Professor> listAll() {
//        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return asList(new Professor("Milton"), new Professor("Rapadura"));
    }
}
