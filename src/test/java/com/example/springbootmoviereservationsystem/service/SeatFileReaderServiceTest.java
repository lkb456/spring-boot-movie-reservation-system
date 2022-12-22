package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.fixture.CreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(value = MockitoExtension.class)
class SeatFileReaderServiceTest {

    private ClassPathResource resource = new ClassPathResource("static/seat/position.txt");
    private String fileContent = "A열 - 1, A열 - 2, A열 - 3, A열 - 4, A열 - 5, A열 - 6, A열 - 7, A열 - 8, A열 - 9, A열 - 10, A열 - 11, A열 - 12, A열 - 13, A열 - 14, A열 - 15, A열 - 16, A열 - 17, A열 - 18, A열 - 19, A열 - 20\n" +
            "B열 - 1, B열 - 2, B열 - 3, B열 - 4, B열 - 5, B열 - 6, B열 - 7, B열 - 8, B열 - 9, B열 - 10, B열 - 11, B열 - 12, B열 - 13, B열 - 14, B열 - 15, B열 - 16, B열 - 17, B열 - 18, B열 - 19, B열 - 20\n" +
            "C열 - 1, C열 - 2, C열 - 3, C열 - 4, C열 - 5, C열 - 6, C열 - 7, C열 - 8, C열 - 9, C열 - 10, C열 - 11, C열 - 12, C열 - 13, C열 - 14, C열 - 15, C열 - 16, C열 - 17, C열 - 18, C열 - 19, C열 - 20\n" +
            "D열 - 1, D열 - 2, D열 - 3, D열 - 4, D열 - 5, D열 - 6, D열 - 7, D열 - 8, D열 - 9, D열 - 10, D열 - 11, D열 - 12, D열 - 13, D열 - 14, D열 - 15, D열 - 16, D열 - 17, D열 - 18, D열 - 19, D열 - 20\n" +
            "E열 - 1, E열 - 2, E열 - 3, E열 - 4, E열 - 5, E열 - 6, E열 - 7, E열 - 8, E열 - 9, E열 - 10, E열 - 11, E열 - 12, E열 - 13, E열 - 14, E열 - 15, E열 - 16, E열 - 17, E열 - 18, E열 - 19, E열 - 20\n" +
            "F열 - 1, F열 - 2, F열 - 3, F열 - 4, F열 - 5, F열 - 6, F열 - 7, F열 - 8, F열 - 9, F열 - 10, F열 - 11, F열 - 12, F열 - 13, F열 - 14, F열 - 15, F열 - 16, F열 - 17, F열 - 18, F열 - 19, F열 - 20\n" +
            "G열 - 1, G열 - 2, G열 - 3, G열 - 4, G열 - 5, G열 - 6, G열 - 7, G열 - 8, G열 - 9, G열 - 10, G열 - 11, G열 - 12, G열 - 13, G열 - 14, G열 - 15, G열 - 16, G열 - 17, G열 - 18, G열 - 19, G열 - 20\n" +
            "H열 - 1, H열 - 2, H열 - 3, H열 - 4, H열 - 5, H열 - 6, H열 - 7, H열 - 8, H열 - 9, H열 - 10, H열 - 11, H열 - 12, H열 - 13, H열 - 14, H열 - 15, H열 - 16, H열 - 17, H열 - 18, H열 - 19, H열 - 20\n" +
            "I열 - 1, I열 - 2, I열 - 3, I열 - 4, I열 - 5, I열 - 6, I열 - 7, I열 - 8, I열 - 9, I열 - 10, I열 - 11, I열 - 12, I열 - 13, I열 - 14, I열 - 15, I열 - 16, I열 - 17, I열 - 18, I열 - 19, I열 - 20\n" +
            "J열 - 1, J열 - 2, J열 - 3, J열 - 4, J열 - 5, J열 - 6, J열 - 7, J열 - 8, J열 - 9, J열 - 10, J열 - 11, J열 - 12, J열 - 13, J열 - 14, J열 - 15, J열 - 16, J열 - 17, J열 - 18, J열 - 19, J열 - 20\n" +
            "K열 - 1, K열 - 2, K열 - 3, K열 - 4, K열 - 5, K열 - 6, K열 - 7, K열 - 8, K열 - 9, K열 - 10, K열 - 11, K열 - 12, K열 - 13, K열 - 14, K열 - 15, K열 - 16, K열 - 17, K열 - 18, K열 - 19, K열 - 20";

    @Mock
    private SeatFileReaderService fileReaderService;

    @Test
    @DisplayName("Seat File 을 읽어온 데이터를 비교하는 테스트")
    void fileDateEqualsTest() throws IOException {
        // given
        Path path = Paths.get(resource.getURI());

        // when
        String content = Files.readString(path);

        // then
        assertThat(content).isEqualTo(fileContent);
    }

    @Test
    @DisplayName("seat file 을 읽어와 List<String[]> 타입으로 변환되어 파일 내용이 저장되어 있는지 확인하는 테스트")
    void readerResult() throws IOException {
        // given
        Path path = Paths.get(resource.getURI());
        List<String[]> list = CreateDto.createFileContent(path);

        given(fileReaderService.readerResult()).willReturn(list);

        // when
        List<String[]> result = fileReaderService.readerResult();

        // then
        assertThat(list).isEqualTo(result);

        verify(fileReaderService).readerResult();
    }

}