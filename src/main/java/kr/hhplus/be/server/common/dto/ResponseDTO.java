package kr.hhplus.be.server.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private int status;
    private String message;
    private Object data; // `data`는 어떤 데이터든 담을 수 있도록 Object 타입 사용
}
