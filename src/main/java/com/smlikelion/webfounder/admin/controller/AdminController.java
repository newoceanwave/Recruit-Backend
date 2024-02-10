package com.smlikelion.webfounder.admin.controller;

import com.smlikelion.webfounder.admin.dto.request.SignInRequest;
import com.smlikelion.webfounder.admin.dto.request.SignUpRequest;
import com.smlikelion.webfounder.admin.dto.request.UpdateRoleRequest;
import com.smlikelion.webfounder.admin.dto.response.SignInResponse;
import com.smlikelion.webfounder.admin.dto.response.SignUpResponse;
import com.smlikelion.webfounder.admin.dto.response.UpdateRoleResponse;
import com.smlikelion.webfounder.admin.service.AdminService;
import com.smlikelion.webfounder.global.dto.response.BaseResponse;
import com.smlikelion.webfounder.security.Auth;
import com.smlikelion.webfounder.security.AuthInfo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Operation(summary="관리자 회원가입")
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<SignUpResponse> signUp(
            @RequestBody @Valid SignUpRequest request) {
        return new BaseResponse<>(adminService.signUp(request));
    }

    @Operation(summary="유저 역할부여")
    @PutMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UpdateRoleResponse> updateRoles(
            // @Auth AuthInfo authInfo,
            @RequestBody @Valid UpdateRoleRequest request) {
//        System.out.println("아이디: ");
//        // 현재 사용자의 인증 정보 가져오기
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        // UserDetails를 구현한 클래스인 경우
//        if (principal instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) principal;
//            String username = userDetails.getUsername();
//            // username을 사용하거나 필요한 정보를 가져올 수 있습니다.
//        } else {
//            // UserDetails를 구현하지 않은 경우, principal은 사용자 이름 또는 기타 정보가 될 수 있습니다.
//            String username = principal.toString();
//        }
        return new BaseResponse<>(adminService.updateRoles(request));
    }

    @Operation(summary="관리자 로그인")
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<SignInResponse> signIn(
            @RequestBody @Valid SignInRequest request) {
        return new BaseResponse<>(adminService.signIn(request));
    }

    /*
    @Operation(summary="관리자 로그아웃")
    @PostMapping("/signout")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> signOut() {
        return new BaseResponse<>("로그아웃 성공");
    }
     */

}
