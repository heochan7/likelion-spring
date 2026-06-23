package org.example.likelionspring.member.service;

import org.example.likelionspring.member.domain.Member;
import org.example.likelionspring.member.domain.RoleType;
import org.example.likelionspring.member.dto.*;
import org.example.likelionspring.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // 1. Lion 등록
    @Transactional
    public MemberResponse registerLion(LionCreateRequest req) {
        if (isDuplicateName(req.getName())) {
            return null;
        }

        Member lion = new Member(
                req.getName(),
                req.getMajor(),
                req.getPart(),
                req.getGeneration(),
                RoleType.LION,
                req.getStudentId(),
                null
        );

        repository.save(lion);
        return MemberResponse.from(lion);
    }

    // 2. Staff 등록
    @Transactional
    public MemberResponse registerStaff(StaffCreateRequest req) {
        if (isDuplicateName(req.getName())) {
            return null;
        }

        Member staff = new Member(
                req.getName(),
                req.getMajor(),
                req.getPart(),
                req.getGeneration(),
                RoleType.STAFF,
                null,
                req.getPosition()
        );

        repository.save(staff);
        return MemberResponse.from(staff);
    }

    // 3. Lion 수정
    @Transactional
    public MemberResponse updateLion(Long id, LionUpdateRequest req) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) return null;

        member.updateInfo(req.getMajor(), req.getGeneration(), req.getPart());
        member.updateStudentId(req.getStudentId());

        repository.save(member);
        return MemberResponse.from(member);
    }

    // 4. Staff 수정
    @Transactional
    public MemberResponse updateStaff(Long id, StaffUpdateRequest req) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) return null;

        member.updateInfo(req.getMajor(), req.getGeneration(), req.getPart());
        member.updatePosition(req.getPosition());

        repository.save(member);
        return MemberResponse.from(member);
    }

    // 5. 단건 조회
    public MemberResponse getMember(Long id) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) return null;

        return MemberResponse.from(member);
    }

    // 6. 삭제
    @Transactional
    public boolean deleteMember(Long id) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) return false;

        repository.delete(member);
        return true;
    }

    // 7. 전체 조회
    public List<MemberResponse> getAllMembers() {
        List<Member> members = repository.findAll();
        if (members.isEmpty()) return Collections.emptyList();

        return members.stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    // 중복 여부 확인 함수
    private boolean isDuplicateName(String name) {
        return repository.existsByName(name);
    }
}