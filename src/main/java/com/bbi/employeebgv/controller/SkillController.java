package com.bbi.employeebgv.controller;


import com.bbi.employeebgv.model.Skill;
import com.bbi.employeebgv.service.SkillService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    SkillService skillService;

    @PostMapping("/addSkill")
    public ResponseEntity<Skill> addSkill(@RequestBody Skill skill) {

        Skill skills = null;
        try {
            skills = skillService.createSkill(skill);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(skills);
    }

}
