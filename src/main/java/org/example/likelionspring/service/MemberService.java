package org.example.likelionspring.service;


import org.example.likelionspring.domain.role.Lion;
import org.example.likelionspring.domain.role.Staff;
import org.example.likelionspring.dto.*;
import org.example.likelionspring.repository.MemberRepository;
import org.example.likelionspring.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public LionResponse createLion(LionCreateRequest req) {
        Lion member = new Lion(req.getName(), req.getMajor(), req.getGeneration(), req.getPart(), req.getStudentId());
        return register(member) ? LionResponse.from(member) : null;
    }

    public StaffResponse createStaff(StaffCreateRequest req) {
        Staff member = new Staff(req.getName(), req.getMajor(), req.getGeneration(), req.getPart(), req.getPosition());
        return register(member) ? StaffResponse.from(member) : null;
    }

    public LionResponse updateLion(String name, LionUpdateRequest req) {
        Lion member = new Lion(name, req.getMajor(), req.getGeneration(), req.getPart(), req.getStudentId());
        return update(member) ? LionResponse.from(member) : null;
    }

    public StaffResponse updateStaff(String name, StaffUpdateRequest req) {
        Staff member = new Staff(name, req.getMajor(), req.getGeneration(), req.getPart(), req.getPosition());
        return update(member) ? StaffResponse.from(member) : null;
    }

    public boolean deleteMember(String name){
        return repository.deleteByName(name);
    }

    private boolean update(Role member){
        if(! repository.existsByName(member.getName())){
            return false;
        }
        repository.updateByName(member.getName(), member);
        return true;
    }

    private boolean register(Role member) {
        if (repository.existsByName(member.getName())) {
            return false;
        }
        repository.save(member);
        return true;
    }

    public Role searchByName(String name) {
        return repository.findByName(name);
    }

    private List<Role> getAllMembers() {
        return repository.findAll();
    }

    private boolean isEmpty() {
        return repository.findAll().isEmpty();
    }
}