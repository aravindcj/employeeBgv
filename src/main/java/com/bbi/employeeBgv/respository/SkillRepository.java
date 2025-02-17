package com.bbi.employeeBgv.respository;

import com.bbi.employeeBgv.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findBySkillNameIn(List<String> names);
    Optional<Skill> findBySkillName(String names);
}
