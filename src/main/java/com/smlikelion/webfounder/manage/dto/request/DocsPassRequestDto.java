package com.smlikelion.webfounder.manage.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DocsPassRequestDto {
    @NotNull(message = "해당 지원자를 선택해주세요.")
    private Long joinerId;
}
