package com.smlikelion.webfounder.admin.service;

import com.smlikelion.webfounder.admin.dto.request.SignUpRequest;
import com.smlikelion.webfounder.admin.dto.response.SignUpResponse;
import com.smlikelion.webfounder.admin.entity.Admin;
import com.smlikelion.webfounder.admin.entity.Block;
import com.smlikelion.webfounder.admin.entity.Role;
import com.smlikelion.webfounder.admin.exception.AlreadyExistsAccountException;
import com.smlikelion.webfounder.admin.exception.AlreadyExistsNameException;
import com.smlikelion.webfounder.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

   private final AdminRepository adminRepository;

    public SignUpResponse signUp(SignUpRequest request) {

        if(adminRepository.existsByAccountId(request.getAccountId())) {
            throw new AlreadyExistsAccountException("이미 존재하는 아이디입니다.");
        }
        if(adminRepository.existsByName(request.getName())) {
            throw new AlreadyExistsNameException("이미 존재하는 이름입니다.");
        }

        Admin admin = adminRepository.save(
                Admin.builder()
                        .accountId(request.getAccountId())
                        .password(request.getPassword())
                        .name(request.getName())
                        .role(Role.USER)
                        .block(Block.ISACTIVE)
                        .build()
        );

        return mapAdminToSignUpResponse(admin);
    }

    private SignUpResponse mapAdminToSignUpResponse(Admin admin) {
        return SignUpResponse.builder()
                .id(admin.getAdminId())
                .accountId(admin.getAccountId())
                .name(admin.getName())
                .build();
    }

}
