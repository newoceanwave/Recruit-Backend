package com.smlikelion.webfounder.admin.controller;

import com.smlikelion.webfounder.admin.dto.request.SignUpRequest;
import com.smlikelion.webfounder.admin.dto.response.SignUpResponse;
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

}
