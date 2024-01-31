package com.smlikelion.webfounder.admin.service;

import com.smlikelion.webfounder.admin.dto.request.SignUpRequest;
import com.smlikelion.webfounder.admin.dto.request.UpdateRoleRequest;
import com.smlikelion.webfounder.admin.dto.response.SignUpResponse;
import com.smlikelion.webfounder.admin.dto.response.UpdateRoleResponse;
import com.smlikelion.webfounder.admin.entity.Admin;
import com.smlikelion.webfounder.admin.entity.Block;
import com.smlikelion.webfounder.admin.entity.Role;
import com.smlikelion.webfounder.admin.exception.AlreadyExistsAccountException;
import com.smlikelion.webfounder.admin.exception.AlreadyExistsNameException;
import com.smlikelion.webfounder.admin.exception.NotFoundAdminException;
import com.smlikelion.webfounder.admin.exception.UnsupportedRoleException;
import com.smlikelion.webfounder.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

   private final AdminRepository adminRepository;

   public void createSuperUser() {
       if(adminRepository.existsByRole(Role.SUPERUSER)) {
           log.info("SuperUser already exists");
           return;
       }

       Admin admin = adminRepository.save(
               Admin.builder()
                       .accountId("smlikelion")
                       .password("smlikelion1234")
                       .name("admin")
                       .role(Role.SUPERUSER)
                       .block(Block.ISACTIVE)
                       .build()
       );

       log.info("SuperUser created: {}", admin);
   }

   public UpdateRoleResponse updateRoles(UpdateRoleRequest request) {
       Admin admin = adminRepository.findByAdminIdAndAccountId(request.getId(), request.getAccountId())
               .orElseThrow(() -> new NotFoundAdminException("해당하는 아이디가 없습니다."));

       if(request.getRole().toUpperCase().equals(Role.MANAGER.name())) {
           admin.setRole(Role.MANAGER);
           adminRepository.save(admin);
       } else if(request.getRole().toUpperCase().equals(Role.USER.name())) {
           admin.setRole(Role.USER);
           adminRepository.save(admin);
       } else {
           throw new UnsupportedRoleException("해당하는 역할이 없습니다.");
       }

       return mapAdminToUpdateRoleResponse(admin);
   }

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

    private UpdateRoleResponse mapAdminToUpdateRoleResponse(Admin admin) {
        return UpdateRoleResponse.builder()
                .id(admin.getAdminId())
                .accountId(admin.getAccountId())
                .role(admin.getRole().toString())
                .build();
    }

}
