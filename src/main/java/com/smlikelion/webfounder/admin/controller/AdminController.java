package com.smlikelion.webfounder.admin.controller;

import com.smlikelion.webfounder.admin.dto.request.SignUpRequest;
import com.smlikelion.webfounder.admin.dto.request.UpdateRoleRequest;
import com.smlikelion.webfounder.admin.dto.response.SignUpResponse;
import com.smlikelion.webfounder.admin.dto.response.UpdateRoleResponse;
import com.smlikelion.webfounder.admin.service.AdminService;
import com.smlikelion.webfounder.global.dto.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Operation(summary="유저 역핣부여")
    @PutMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UpdateRoleResponse> updateRoles(
            @RequestBody @Valid UpdateRoleRequest request) {
        return new BaseResponse<>(adminService.updateRoles(request));
    }

    /*
    @Operation(summary="관리자 로그인")
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> signIn() {
        return new BaseResponse<>("로그인 성공");
    }

    @Operation(summary="관리자 로그아웃")
    @PostMapping("/signout")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> signOut() {
        return new BaseResponse<>("로그아웃 성공");
    }
     */

}
