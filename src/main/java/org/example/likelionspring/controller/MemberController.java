package org.example.likelionspring.controller;

import org.example.likelionspring.domain.role.Lion;
import org.example.likelionspring.domain.role.Role;
import org.example.likelionspring.domain.role.Staff;
import org.example.likelionspring.dto.*;
import org.example.likelionspring.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<LionResponse> lionCreate(@RequestBody LionCreateRequest request) {
        LionResponse response = memberService.createLion(request);

        return (response == null)
                ? ResponseEntity.status(HttpStatus.CONFLICT).build()
                : ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> staffCreate(@RequestBody StaffCreateRequest request) {
        StaffResponse response = memberService.createStaff(request);

        return (response == null)
                ? ResponseEntity.status(HttpStatus.CONFLICT).build()
                : ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getMemberByName(@PathVariable String name) {
        Role role = memberService.searchByName(name);

        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        if ("아기사자".equals(role.roleName())) {
            return ResponseEntity.ok(LionResponse.from((Lion) role));
        } else if ("운영진".equals(role.roleName())) {
            return ResponseEntity.ok(StaffResponse.from((Staff) role));
        }

        // 예상치 못한 역할인 경우 400 에러 혹은 500 에러 처리
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(@PathVariable String name, @RequestBody LionUpdateRequest request) {
        LionResponse response = memberService.updateLion(name, request);

        // 서비스 결과가 null이면 404, 아니면 200 반환
        return (response == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(response);
    }

    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(@PathVariable String name, @RequestBody StaffUpdateRequest request) {
        StaffResponse response = memberService.updateStaff(name, request);

        return (response == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(response);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteMember(@PathVariable String name) {
        if(! memberService.deleteMember(name)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
