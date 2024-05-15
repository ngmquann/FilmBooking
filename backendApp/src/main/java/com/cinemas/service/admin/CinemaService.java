package com.cinemas.service.admin;

import com.cinemas.dto.request.CinemaRequest;
import com.cinemas.dto.request.PaginationHelper;
import com.cinemas.dto.response.SelectOptionReponse;
import com.cinemas.entities.Cinema;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface CinemaService {
    List<SelectOptionReponse> getCreateCinema();

    boolean createCinema(CinemaRequest cinemaRequest) throws IOException;

    Page<Cinema> getAllCinema(PaginationHelper PaginationHelper);

    Integer deleteCinema(String slug) throws IOException;
}
