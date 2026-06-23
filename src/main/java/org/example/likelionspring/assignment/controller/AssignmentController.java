package org.example.likelionspring.assignment.controller;


import org.example.likelionspring.assignment.dto.AssignmentCreateRequest;
import org.example.likelionspring.assignment.dto.AssignmentResponse;
import org.example.likelionspring.assignment.dto.AssignmentUpdateRequest;
import org.example.likelionspring.assignment.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService){
        this.assignmentService = assignmentService;
    }

    // 1. 과제 등록
    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> createAssignment(@PathVariable Long memberId, @RequestBody AssignmentCreateRequest request){
        AssignmentResponse response = assignmentService.registerAssignment(memberId, request);

        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2. 멤버별 과제 목록
    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByMemberId(@PathVariable Long memberId){
        List<AssignmentResponse> assignments = assignmentService.findByMemberId(memberId);
        if(assignments.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(assignments);
    }

    // 3. 과제 단건 조회
    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> getAssignment(@PathVariable Long id){
        AssignmentResponse response = assignmentService.findById(id);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(response);
    }

    // 4. 과제 수정
    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(@PathVariable Long id, @RequestBody AssignmentUpdateRequest request){
        AssignmentResponse response = assignmentService.updateAssignment(id, request);
        if (response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 5. 과제 삭제
    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id){
        boolean bool = assignmentService.deleteAssignment(id);
        if(! bool) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
