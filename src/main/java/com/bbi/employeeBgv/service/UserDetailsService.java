package com.bbi.employeeBgv.service;

import com.bbi.employeeBgv.model.Skill;
import com.bbi.employeeBgv.model.User;
import com.bbi.employeeBgv.model.UserDetails;
import com.bbi.employeeBgv.dto.UserDetailsRequest;
import com.bbi.employeeBgv.respository.SkillRepository;
import com.bbi.employeeBgv.respository.UserDetailsRepository;
import com.bbi.employeeBgv.respository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    UserRepository userRepository;

//    @Transactional
//    public UserDetails createUserDetails(UserDetails userDetails) {
//        // Ensure the User exists
//        Long userId = userDetails.getUser().getUserId(); // Get the userId from the request
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
//
//        // Ensure the Skills exist
//        Set<Skill> skills = new HashSet<>();
//        for (Skill skill : userDetails.getSkills()) {
//            Skill existingSkill = skillRepository.findById(skill.getSkillId())
//                    .orElseThrow(() -> new RuntimeException("Skill not found with id: " + skill.getSkillId()));
//            skills.add(existingSkill);
//        }
//
//        // Set the User and Skills in UserDetails
//        userDetails.setUser(user); // Set the User entity
//        userDetails.setSkills(skills); // Set the Skills
//
//        // Save UserDetails
//        return userDetailsRepository.save(userDetails);
//    }


    @Transactional
    public UserDetails createUserDetails(UserDetailsRequest request) {

    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));


        Set<Skill> skillSet = new HashSet<>();
        for(String skillName : request.getSkills()){
            Skill skill = skillRepository.findBySkillName(skillName)
                    .orElseGet(() -> {
                            Skill skills = new Skill();
                            skills.setSkillName(skillName);
                            return skillRepository.save(skills);});
            skillSet.add(skill);
        }

        UserDetails userDetails = getUserDetails(request, skillSet);
        userDetails.setUser(user);
        userDetails.setSkills(skillSet);
//        user.setUserDetails(userDetails);
//        userRepository.save(user);

        return userDetailsRepository.save(userDetails);


    }

    private static UserDetails getUserDetails(UserDetailsRequest request, Set<Skill> skillSet) {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(request.getFirstName());
        userDetails.setLastName(request.getLastName());
        userDetails.setPhone(request.getPhone());
        userDetails.setCurrentAddress(request.getCurrentAddress());
        userDetails.setPermanentAddress(request.getPermanentAddress());
        userDetails.setDob(request.getDob());
        userDetails.setProfilePicture(request.getProfilePicture());
        userDetails.setVerificationStatus(request.getVerificationStatus());
        userDetails.setSkills(skillSet);
        return userDetails;
    }


    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }
}
