package com.example.parking.domain.member.controller;

import com.example.parking.domain.member.service.MemberService;
import com.example.parking.global.dto.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

import static com.example.parking.domain.member.dto.CommonMemberRequestDto.*;
import static com.example.parking.domain.member.dto.CommonMemberResponseDto.*;

@RequestMapping("/api/members/")
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("join")
    public ResponseEntity<ResultResponse<MemberJoinResponseDto>> joinMember(@RequestBody final MemberJoinRequestDto dto) {
        ResultResponse<MemberJoinResponseDto> result = new ResultResponse<>(memberService.joinMember(dto), HttpStatus.CREATED);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("join/check-email")
    public ResponseEntity<ResultResponse<String>> checkEmail(@RequestParam("email") final String email) {
        ResultResponse<String> result = new ResultResponse<>(memberService.checkEmail(email), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("join/check-email-token")
    public ResponseEntity<ResultResponse<String>> checkEmailToken(@RequestBody final EmailTokenRequestDto dto) {
        ResultResponse<String> result = new ResultResponse<>(memberService.checkEmailToken(dto), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("find/email")
    public ResponseEntity<ResultResponse<FindEmailResponseDto>> findEmail(@RequestBody final FindEmailRequestDto dto) {
        ResultResponse<FindEmailResponseDto> result = new ResultResponse<>(memberService.findEmail(dto), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("find/password")
    public ResponseEntity<ResultResponse<String>> findPassword(@RequestBody final FindPasswordRequestDto dto) {
        ResultResponse<String> result = new ResultResponse<>(memberService.findPassword(dto), HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ResultResponse<String>> deleteMember(@RequestBody final DeleteMemberRequestDto dto) {
        return null;
    }


}
