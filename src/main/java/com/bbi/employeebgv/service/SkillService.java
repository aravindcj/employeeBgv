package com.bbi.employeebgv.service;


import com.bbi.employeebgv.exception.ResourceAlreadyExistsException;
import com.bbi.employeebgv.model.Skill;
import com.bbi.employeebgv.respository.SkillRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;


    public Skill createSkill(Skill skill) throws BadRequestException {
        if (skill == null || skill.getSkillName() == null) {
            throw new BadRequestException("Skill name is required");
        }

        skill.setSkillName(skill.getSkillName().toLowerCase().trim());

        try {
            return skillRepository.save(skill);
        } catch (DataIntegrityViolationException ex) {
            // UNIQUE INDEX violation
            throw new ResourceAlreadyExistsException(
                    skill.getSkillName()+" already exists"
            );
        }
    }
}
