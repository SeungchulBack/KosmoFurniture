package com.kosmo.kosmofurniture.controller;

import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import com.kosmo.kosmofurniture.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok().body(memberService.getAllMembers());
    }

    @GetMapping("/register")
    public ModelAndView registerView() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView registerMember(Member member) {
        memberService.createMember(member);
        return new ModelAndView("redirect:/");
    }


    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getSingleMember(@PathVariable Long memberId) {
        return ResponseEntity.ok().body(memberMapper.findById(memberId));
    }

    @GetMapping("/check/{account}")
    public ResponseEntity<String> accountCheck(@PathVariable String account) {

        boolean exists = memberService.checkAccount(account);
        return ResponseEntity.ok().body("{\"accountExists\" : \"" + exists + "\"}");
    }

    @GetMapping("/ssn/{ssn}")
    public ResponseEntity<Member> getSingleMember(@PathVariable String ssn) {
        return ResponseEntity.ok().body(memberMapper.findBySsn(ssn));
    }
}
