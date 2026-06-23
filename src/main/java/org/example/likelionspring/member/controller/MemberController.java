package org.example.likelionspring.member.controller;

import org.example.likelionspring.member.dto.*;
import org.example.likelionspring.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getMembers() {
        List<MemberResponse> members = memberService.getAllMembers();
        if(members.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(members);

        return ResponseEntity.ok(members);
    }

    // ID로 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id) {
        MemberResponse response = memberService.getMember(id);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        return ResponseEntity.ok(response);
    }

    // Lion 생성
    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request) {
        MemberResponse response = memberService.registerLion(request);
        if(response == null) return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Staff 생성
    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request) {
        MemberResponse response = memberService.registerStaff(request);
        if(response == null) return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Lion 수정
    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id, @RequestBody LionUpdateRequest request) {
        MemberResponse response = memberService.updateLion(id, request);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        return ResponseEntity.ok(response);
    }

    // Staff 수정
    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id, @RequestBody StaffUpdateRequest request) {
        MemberResponse response = memberService.updateStaff(id, request);
        if(response == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        return ResponseEntity.ok(response);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        boolean response = memberService.deleteMember(id);
        if(! response) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}