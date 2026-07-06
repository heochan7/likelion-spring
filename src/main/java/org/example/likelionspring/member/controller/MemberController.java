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
    public ResponseEntity<List<MemberResponse>> getMembers(@RequestParam(required = false) String part) {
        if(part != null && !part.trim().isEmpty()){
            return ResponseEntity.ok(memberService.getMemberByPart(part));
        }
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    // ID로 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id) {
        MemberResponse response = memberService.getMember(id);
        return ResponseEntity.ok(response);
    }

    // Lion 생성
    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request) {
        MemberResponse response = memberService.registerLion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Staff 생성
    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request) {
        MemberResponse response = memberService.registerStaff(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Lion 수정
    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id, @RequestBody LionUpdateRequest request) {
        MemberResponse response = memberService.updateLion(id, request);
        return ResponseEntity.ok(response);
    }

    // Staff 수정
    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id, @RequestBody StaffUpdateRequest request) {
        MemberResponse response = memberService.updateStaff(id, request);
        return ResponseEntity.ok(response);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        boolean response = memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    //
}