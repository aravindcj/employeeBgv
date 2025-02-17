package com.bbi.employeeBgv.controller;


import com.bbi.employeeBgv.model.Skill;
import com.bbi.employeeBgv.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Skill addSkill(@RequestBody Skill skill){
        return skillService.createSkill(skill);
    }

}
