package kr.hhplus.be.server.api.common.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private int status;
    private String message;
    private Object data; // `data`는 어떤 데이터든 담을 수 있도록 Object 타입 사용
}
