package org.example.likelionspring.assignment.service;

import org.example.likelionspring.assignment.domain.Assignment;
import org.example.likelionspring.assignment.dto.AssignmentCreateRequest;
import org.example.likelionspring.assignment.dto.AssignmentResponse;
import org.example.likelionspring.assignment.dto.AssignmentUpdateRequest;
import org.example.likelionspring.assignment.repository.AssignmentRepository;
import org.example.likelionspring.member.domain.Member;
import org.example.likelionspring.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, MemberRepository memberRepository){
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;

    }

    // 1. 과제 등록
    @Transactional
    public AssignmentResponse registerAssignment (Long memberId, AssignmentCreateRequest request){
        Member member = memberRepository.findById(memberId).orElse(null);

        if(member == null){
            return null;
        }
        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        assignmentRepository.save(assignment);
        return AssignmentResponse.from(assignment);
    }

    // 2. 멤버별 과제 목록
    public List<AssignmentResponse> findByMemberId(Long memberId){
        List<Assignment> assignments = assignmentRepository.findByMemberId(memberId);

        if(assignments.isEmpty()) return Collections.emptyList();

        return assignments.stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
    }

    // 3. 단건 조회
    public AssignmentResponse findById(Long id){
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        return AssignmentResponse.from(assignment);
    }

    // 4. 과제 수정
    @Transactional
    public AssignmentResponse updateAssignment(Long id, AssignmentUpdateRequest request){
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if(assignment == null){
            return null;
        }

        assignment.updateInfo(request.getTitle(), request.getDescription());
        assignmentRepository.save(assignment);
        return AssignmentResponse.from(assignment);
    }

    // 5. 과제 삭제
    @Transactional
    public boolean deleteAssignment(Long id){
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if(assignment == null) return false;

        assignmentRepository.delete(assignment);
        return true;
    }
}
