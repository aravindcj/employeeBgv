package com.bbi.employeebgv.service;


import com.bbi.employeebgv.model.Skill;
import com.bbi.employeebgv.respository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;


    public Skill createSkill(Skill skill) {

        return skillRepository.save(skill);
    }
}
