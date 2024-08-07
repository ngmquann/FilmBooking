package com.cinemas.controller.home;

import com.cinemas.dto.request.SearchCelebRequest;
import com.cinemas.dto.request.SearchMovie;
import com.cinemas.dto.response.APIResponse;
import com.cinemas.dto.response.CelebResponse;
import com.cinemas.dto.response.SelectOptionAndModelReponse;
import com.cinemas.dto.response.SelectOptionCeleb;
import com.cinemas.entities.Celebrity;
import com.cinemas.entities.Movie;
import com.cinemas.enums.MovieStatus;
import com.cinemas.service.home.HomeCelebService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home/celebrity")
@Tag(name = "Home Celebrity Controller")
public class HomeCelebrityController {
    @Autowired
    private HomeCelebService homeCelebService;

    @GetMapping("/v1/actor")
    public APIResponse<SelectOptionCeleb<Page<Celebrity>>> getAllActor(
            @RequestParam(required = false) String slugCountry,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sort
    ) {
        SearchCelebRequest searchCelebRequest = new SearchCelebRequest(slugCountry, pageNo - 1, pageSize, sort);
        SelectOptionCeleb<Page<Celebrity>> actors = homeCelebService.getAllActor(searchCelebRequest);
        APIResponse<SelectOptionCeleb<Page<Celebrity>>> apiResponse = new APIResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(actors);

        return apiResponse;
    }

    @GetMapping("/v1/director")
    public APIResponse<SelectOptionCeleb<Page<Celebrity>>> getAllDirector(
            @RequestParam(required = false) String slugCountry,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction sort
    ) {
        SearchCelebRequest searchCelebRequest = new SearchCelebRequest(slugCountry, pageNo - 1, pageSize, sort);
        SelectOptionCeleb<Page<Celebrity>> actors = homeCelebService.getAllDirector(searchCelebRequest);
        APIResponse<SelectOptionCeleb<Page<Celebrity>>> apiResponse = new APIResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(actors);

        return apiResponse;
    }

    @GetMapping("/v1/detail/{slug}")
    public APIResponse<CelebResponse> getDetail(@PathVariable String slug){
        CelebResponse celebResponse = homeCelebService.getDetailCeleb(slug);
        APIResponse<CelebResponse> apiResponse = new APIResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(celebResponse);

        return apiResponse;
    }
}
