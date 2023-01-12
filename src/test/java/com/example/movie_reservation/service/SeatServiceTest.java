package com.example.movie_reservation.service;

import com.example.movie_reservation.domain.seat.Seat;
import com.example.movie_reservation.domain.seat.SeatRepository;
import com.example.movie_reservation.fixture.CreateDto;
import com.example.movie_reservation.fixture.CreateEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(value = MockitoExtension.class)
class SeatServiceTest {

    private ClassPathResource resource = new ClassPathResource("static/seat/position.txt");

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private SeatFileReaderService seatFileReaderService;

    @InjectMocks
    private SeatService seatService;

    @Test
    @DisplayName("파일에 있는 좌석 정보를 저장하는 테스트")
    void initSeat() throws IOException {
        // given
        Path path = Paths.get(resource.getURI());
        List<String[]> list = CreateDto.createFileContent(path);

        given(seatFileReaderService.readerResult()).willReturn(list);

        for (String[] seatInfo : list) {
            for (String info : seatInfo) {
                String[] seatRowAndColum = info.split(" - ");
                Seat seat = CreateEntity.createInitSeat(seatRowAndColum);

                given(seatRepository.save(any())).willReturn(seat);
            }
        }

        // when
        seatService.initSeat();

        // then
        verify(seatFileReaderService, atLeast(1)).readerResult();
        verify(seatRepository, atLeastOnce()).save(any());
    }

    @Test
    @DisplayName("좌석 정보 조회하기 테스트")
    void findSeat() {
        // given
        Seat seat = CreateEntity.createSingleSeat(1);
        given(seatRepository.findById(any())).willReturn(Optional.of(seat));

        // when
        Seat findSeat = seatService.findSeat(seat.getId());

        // then
        assertThat(seat).isEqualTo(findSeat);

        verify(seatRepository, atLeast(1)).findById(any());
    }
}