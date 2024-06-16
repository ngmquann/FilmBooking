package com.cinemas.controller.admin;

import com.cinemas.dto.request.*;
import com.cinemas.dto.response.*;
import com.cinemas.entities.Showtimes;
import com.cinemas.exception.AppException;
import com.cinemas.service.admin.ShowTimeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.cinemas.exception.ErrorCode.*;

@RestController
@RequestMapping("/api/admin/v1/show-time")
@Tag(name = "Dashboard Show Time Controller")
public class ShowTimeController {
    @Autowired
    ShowTimeService showTimeService;

    @GetMapping("")
    public APIResponse<SelectOptionAndModelReponse<Page<ShowTimeTableResponse>>> getAllShowTime(
            @RequestParam(required = false) String cinema,
            @RequestParam(required = false) LocalDate startDay,
            @RequestParam(required = false) LocalDate endDay, @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "15") Integer pageSize, @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sort) {
        searchShowTimeRequest showTimeRequest = new searchShowTimeRequest(pageNo - 1, pageSize, sort, cinema, startDay, endDay);
        SelectOptionAndModelReponse<Page<ShowTimeTableResponse>> showtimes = showTimeService.getAllShowTime(showTimeRequest);

        APIResponse<SelectOptionAndModelReponse<Page<ShowTimeTableResponse>>> apiResponse = new APIResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(showtimes);

        return apiResponse;
    }

    @GetMapping("/create")
    public APIResponse<ShowTimeCreateResponse> getCreate() {
        ShowTimeCreateResponse response = showTimeService.getcreate();
        APIResponse<ShowTimeCreateResponse> apiResponse = new APIResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(response);

        return apiResponse;
    }

    @GetMapping("/create/room")
    public APIResponse<List<SelectOptionReponse>> getRoomCreate(Integer idCinema) {
        APIResponse<List<SelectOptionReponse>> apiResponse = new APIResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(showTimeService.getRoomCreate(idCinema));

        return apiResponse;
    }

    @PostMapping("/create")
    public APIResponse<String> create(@RequestBody @Valid ShowTimeRequest showTimeRequest) {
        boolean checkCreate = showTimeService.createShowTime(showTimeRequest);

        if (checkCreate) {
            APIResponse<String> apiResponse = new APIResponse<>();
            apiResponse.setCode(200);
            apiResponse.setMessage("Set Time Successfully");

            return apiResponse;
        }

        throw new AppException(CREATE_FAILED);
    }

    @GetMapping("{id}/edit")
    public APIResponse<showTimeItemRequet> getEdit(@PathVariable Integer id) {
        APIResponse<showTimeItemRequet> apiResponse = new APIResponse();
        apiResponse.setCode(200);
        apiResponse.setResult(showTimeService.getEdit(id));

        return apiResponse;
    }

    @DeleteMapping("{id}/delete")
    public APIResponse<String> deleteShowTime(@PathVariable Integer id) {
        boolean checkDelete = showTimeService.delete(id);
        if (checkDelete) {
            APIResponse<String> apiResponse = new APIResponse();
            apiResponse.setCode(200);
            apiResponse.setMessage("Delete successfully");

            return apiResponse;
        }

        throw new AppException(DELETE_FAILED);
    }

}
