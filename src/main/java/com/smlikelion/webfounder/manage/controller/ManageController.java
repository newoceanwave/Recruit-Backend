package com.smlikelion.webfounder.manage.controller;

import com.smlikelion.webfounder.global.dto.response.BaseResponse;
import com.smlikelion.webfounder.manage.dto.request.DocsQuestRequest;
import com.smlikelion.webfounder.manage.dto.response.DocsQuestResponse;
import com.smlikelion.webfounder.manage.service.ManageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class ManageController {

    private final ManageService manageService;

    @Operation(summary = "서류 질문 등록하기")
    @PostMapping("/docs/quest")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<DocsQuestResponse> registerQuestion(
            @RequestBody @Valid DocsQuestRequest request) {
        return new BaseResponse<>(manageService.registerQuestion(request));
    }
}
