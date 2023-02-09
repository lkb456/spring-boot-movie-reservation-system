package com.example.movie_reservation.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    @Transactional
    public void test() {
        // 회원
        Member member = Member.builder()
                .nickname("이기영")
                .phoneNumber("01012341234")
                .build();
        Member savedMember = memberRepository.save(member);

        // 영화
        Movie movie = Movie.builder()
                .title("아바타")
                .runningTime(Duration.ZERO)
                .releaseDate(LocalDate.MIN)
                .build();
        Movie savedMovie = movieRepository.save(movie);

        // 상영관
        Theater aTheater = Theater.builder()
                .name("A관")
                .numberOfSeat(100)
                .build();
        Theater bTheater = Theater.builder()
                .name("B관")
                .numberOfSeat(100)
                .build();
        Theater savedATheater = theaterRepository.save(aTheater);
        Theater savedBTheater = theaterRepository.save(bTheater);

        // 상영 정보
        Screening aScreening = Screening.builder()
                .movie(savedMovie)
                .fee(BigDecimal.ZERO)
                .whenScreened(LocalDateTime.of(2023, 1, 12, 10, 30))
                .build();
        aScreening.addTheater(savedATheater);

        Screening bScreening = Screening.builder()
                .movie(savedMovie)
                .fee(BigDecimal.ZERO)
                .whenScreened(LocalDateTime.of(2023, 1, 12, 10, 30))
                .build();
        bScreening.addTheater(savedBTheater);
        Screening savedAScreening = screeningRepository.save(aScreening);
        Screening savedBScreening = screeningRepository.save(bScreening);

        // 예매
        Reservation aReservation = Reservation.builder()
                .nickname(savedMember.getNickname())
                .audienceCount(10)
                .screening(savedAScreening)
                .reservationStatus(ReservationStatus.RESERVATION)
                .build();
        Reservation bReservation = Reservation.builder()
                .nickname(savedMember.getNickname())
                .audienceCount(10)
                .screening(savedBScreening)
                .reservationStatus(ReservationStatus.RESERVATION)
                .build();
        Reservation savedAReservation = reservationRepository.save(aReservation);
        Reservation savedBreservation = reservationRepository.save(bReservation);

        System.out.println(savedAReservation);
    }

}