package com.bbi.employeeBgv.service;


import com.bbi.employeeBgv.model.Skill;
import com.bbi.employeeBgv.respository.SkillRepository;
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
