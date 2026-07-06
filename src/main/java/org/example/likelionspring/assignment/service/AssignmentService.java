package org.example.likelionspring.assignment.service;

import org.example.likelionspring.assignment.domain.Assignment;
import org.example.likelionspring.assignment.dto.AssignmentCreateRequest;
import org.example.likelionspring.assignment.dto.AssignmentResponse;
import org.example.likelionspring.assignment.dto.AssignmentUpdateRequest;
import org.example.likelionspring.assignment.repository.AssignmentRepository;
import org.example.likelionspring.global.exception.AssignmentNotFoundException;
import org.example.likelionspring.global.exception.MemberNotFoundException;
import org.example.likelionspring.member.domain.Member;
import org.example.likelionspring.member.dto.MemberResponse;
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

    public List<AssignmentResponse> getAllAssignment(){
        List<Assignment> assignments = assignmentRepository.findAll();

        if(assignments.isEmpty()) return Collections.emptyList();
        return assignments.stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
    }

    public List<AssignmentResponse> searchAssignmentByTitle(String keyword){
        List<Assignment> assignments = assignmentRepository.findByTitleContaining(keyword);
        if(assignments.isEmpty()) return Collections.emptyList();

        return assignments.stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
    }

    // 1. 과제 등록
    @Transactional
    public AssignmentResponse registerAssignment(Long memberId, AssignmentCreateRequest request){
        // 과제를 등록할 멤버가 없다면 404 예외 던짐
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("해당 회원을 찾을 수 없어 과제를 등록할 수 없습니다. ID: " + memberId));

        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        assignmentRepository.save(assignment);
        return AssignmentResponse.from(assignment);
    }

    // 2. 멤버별 과제 목록
    public List<AssignmentResponse> findByMemberId(Long memberId){
         if (!memberRepository.existsById(memberId)) throw new MemberNotFoundException("...");

        List<Assignment> assignments = assignmentRepository.findByMemberId(memberId);
        if(assignments.isEmpty()) return Collections.emptyList();

        return assignments.stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
    }

    // 3. 단건 조회
    public AssignmentResponse findById(Long id){
        // 기존 코드에서는 null이 들어가서 AssignmentResponse.from(null) 시 NullPointerException 위험이 있었음 -> 예외 처리로 방어
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("해당 과제를 찾을 수 없습니다. ID: " + id));

        return AssignmentResponse.from(assignment);
    }

    // 4. 과제 수정
    @Transactional
    public AssignmentResponse updateAssignment(Long id, AssignmentUpdateRequest request){
        // 수정할 과제가 없다면 404 예외 던짐
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("해당 과제를 찾을 수 없습니다. ID: " + id));

        assignment.updateInfo(request.getTitle(), request.getDescription());
        assignmentRepository.save(assignment);
        return AssignmentResponse.from(assignment);
    }

    // 5. 과제 삭제
    @Transactional
    public boolean deleteAssignment(Long id){
        // 삭제할 과제가 없다면 404 예외 던짐
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("해당 과제를 찾을 수 없습니다. ID: " + id));

        assignmentRepository.delete(assignment);
        return true;
    }


}