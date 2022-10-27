package com.example.parking.domain.admin.controller;

import com.example.parking.domain.admin.dto.CommonAdminResponseDto.MemberApprovalResponseDto;
import com.example.parking.domain.admin.service.AdminService;
import com.example.parking.global.dto.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.parking.domain.admin.dto.CommonAdminRequestDto.*;
import static com.example.parking.domain.admin.dto.CommonAdminResponseDto.*;

@RequestMapping("/api/admin")
@RequiredArgsConstructor
@RestController
public class AdminApiController {

    private final AdminService adminService;

    @PostMapping("/join")
    public ResponseEntity<ResultResponse<AdminJoinResponseDto>> joinAdmin(@RequestBody final AdminJoinDto adminJoinDto) {
        AdminJoinResponseDto joinAdmin = adminService.joinAdmin(adminJoinDto);
        ResultResponse<AdminJoinResponseDto> resultResponse = ResultResponse.<AdminJoinResponseDto>builder()
                .response(joinAdmin)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }

    /**
     * TODO 관리자(관리사무소) 로그인했을때 토큰으로부터 계정정보 가져와야함
     * */

    @PostMapping("/members-approval")
    public ResponseEntity<ResultResponse<?>> joinMemberApprove(@RequestBody final MemberApprovalDto memberApprovalDto) {

        MemberApprovalResponseDto memberApprovalByAdmin = adminService.memberApprovalByAdmin(memberApprovalDto);
        ResultResponse<MemberApprovalResponseDto> resultResponse = ResultResponse.<MemberApprovalResponseDto>builder()
                .response(memberApprovalByAdmin).status(HttpStatus.OK).build();

        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }


//   -------> MemberController로 이관
//    @GetMapping("/members")
//    public ResponseEntity<ResultResponse<MemberInfoPagingDto>> getMemberInfoByAdmin(
//            @RequestParam("userId") String userId,
//            @PageableDefault(size = 20, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
//
//
//    }


}
