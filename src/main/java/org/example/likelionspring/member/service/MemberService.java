package org.example.likelionspring.member.service;

import org.example.likelionspring.global.exception.DuplicateMemberException;
import org.example.likelionspring.global.exception.MemberNotFoundException;
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
        // 중복 시 null 반환 대신 예외 발생 (409)
        if (isDuplicateName(req.getName())) {
            throw new DuplicateMemberException("이미 존재하는 이름입니다: " + req.getName());
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
        // 중복 시 null 반환 대신 예외 발생 (409)
        if (isDuplicateName(req.getName())) {
            throw new DuplicateMemberException("이미 존재하는 이름입니다: " + req.getName());
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
        // orElseThrow를 사용해 없을 경우 바로 예외 던짐 (404)
        Member member = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원을 찾을 수 없습니다. ID: " + id));

        member.updateInfo(req.getMajor(), req.getGeneration(), req.getPart());
        member.updateStudentId(req.getStudentId());

        repository.save(member);
        return MemberResponse.from(member);
    }

    // 4. Staff 수정
    @Transactional
    public MemberResponse updateStaff(Long id, StaffUpdateRequest req) {
        // orElseThrow를 사용해 없을 경우 바로 예외 던짐 (404)
        Member member = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원을 찾을 수 없습니다. ID: " + id));

        member.updateInfo(req.getMajor(), req.getGeneration(), req.getPart());
        member.updatePosition(req.getPosition());

        repository.save(member);
        return MemberResponse.from(member);
    }

    // 5. 단건 조회
    public MemberResponse getMember(Long id) {
        // 조회 실패 시 예외 던짐 (404)
        Member member = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원을 찾을 수 없습니다. ID: " + id));

        return MemberResponse.from(member);
    }

    // 6. 삭제
    @Transactional
    public boolean deleteMember(Long id) {
        // 삭제 대상을 찾지 못하면 예외 던짐 (404)
        Member member = repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원을 찾을 수 없습니다. ID: " + id));

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

    // 8. 파트별 조회
    public List<MemberResponse> getMemberByPart(String part){
        List<Member> members = repository.findByPart(part);
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