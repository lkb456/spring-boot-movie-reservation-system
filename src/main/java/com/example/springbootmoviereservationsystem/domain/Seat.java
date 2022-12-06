package com.example.springbootmoviereservationsystem.domain;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public enum Seat {

    A_LINE("A열"),
    B_LINE("B열"),
    C_LINE("C열"),
    D_LINE("D열"),
    E_LINE("E열"),
    F_LINE("F열"),
    G_LINE("G열"),
    PASS("Pass");

    private final String line;
    private final Map<Integer, Boolean> seatLine = new ConcurrentHashMap<>() {{
        for (int index = 1; index <= 20; index++) {
            put(index, false);
        }
    }};

    Seat(String line) {
        this.line = line;
    }

    public static Seat selectLine(Seat selectSeat, int index) {
        if (selectSeat.getSeatLine().containsKey(index)) {
            if (!isSelect(selectSeat, index)) {
                throw new IllegalArgumentException("이미 예약된 좌석입니다.");
            }

            selectSeat.getSeatLine().put(index, true);
        }

        return selectSeat;
    }

    private static boolean isSelect(Seat selectSeat, int index) {
        return selectSeat.getSeatLine().get(index).equals(Boolean.FALSE);
    }

}
