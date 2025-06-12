package kr.hhplus.be.server.infrastructure.external;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NaverSearchClientImplTest {

    private final NaverSearchClientImpl client = new NaverSearchClientImpl(/* 의존성 */);

    @Test
    public void 네이버API_요청URL이_정상적으로_생성되는지_검증한다() {
        // TODO: MockWebServer 활용 or URL 조합 검증
    }
}

