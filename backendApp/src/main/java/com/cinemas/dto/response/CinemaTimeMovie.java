package com.cinemas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaTimeMovie {
    private String name;
    private List<HomeMovieFormatResponse> movieFormat;

    public CinemaTimeMovie(String name) {
        this.name = name;
    }
}
