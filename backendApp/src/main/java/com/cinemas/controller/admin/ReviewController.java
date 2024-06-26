package com.cinemas.controller.admin;

import com.cinemas.dto.request.PaginationHelper;
import com.cinemas.dto.request.ReviewRequest;
import com.cinemas.dto.request.SearchRequest;
import com.cinemas.dto.request.SearchReviewRequest;
import com.cinemas.dto.response.APIResponse;
import com.cinemas.dto.response.SelectOptionAndModelReponse;
import com.cinemas.dto.response.SelectOptionReponse;
import com.cinemas.entities.Review;
import com.cinemas.enums.ReviewType;
import com.cinemas.exception.AppException;
import com.cinemas.service.admin.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.cinemas.exception.ErrorCode.CREATE_FAILED;
import static com.cinemas.exception.ErrorCode.UPDATE_FAILED;

@RestController
@RequestMapping("/api/admin/v1/review")
@Tag(name = "Dashboard Review Controller")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    /**
     * @param name
     * @param pageNo
     * @param pageSize
     * @param sort
     * @return
     */
    @GetMapping
    public APIResponse<SelectOptionAndModelReponse<Page<Review>>> getReview(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ReviewType type,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction sort) {
        SearchRequest searchRequest = new SearchRequest(name, type, pageNo - 1, pageSize, sort);

        SelectOptionAndModelReponse<Page<Review>> reviewList = reviewService.getAllReview(searchRequest);
        APIResponse<SelectOptionAndModelReponse<Page<Review>>> apiResponse = new APIResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(reviewList);

        return apiResponse;
    }

    @GetMapping("/create")
    public APIResponse<List<SelectOptionReponse>> getReview() {

        APIResponse<List<SelectOptionReponse>> apiResponse = new APIResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(reviewService.getCreate());

        return apiResponse;
    }

    /**
     * create new Review
     *
     * @return
     * @throws IOException
     */
    @PostMapping("/create")
    public APIResponse<String> createReview(@ModelAttribute ReviewRequest reviewRequest) throws IOException {
        System.out.println("=======================================");
        System.out.println(reviewRequest);
        System.out.println("=======================================");
        boolean checkCreate = reviewService.addReview(reviewRequest);
        if (checkCreate) {
            APIResponse<String> apiResponse = new APIResponse();
            apiResponse.setCode(200);
            apiResponse.setMessage("Review created successfully");

            return apiResponse;
        }

        throw new AppException(CREATE_FAILED);
    }

    /**
     * delete Review by id
     *
     * @param slug
     * @return
     * @throws IOException
     */
    @DeleteMapping("/delete/{slug}")
    public APIResponse<Integer> deleteReview(@PathVariable String slug) throws IOException {

        int id = reviewService.deleteReview(slug);
        if (id > 0) {
            APIResponse<Integer> apiResponse = new APIResponse();
            apiResponse.setCode(200);
            apiResponse.setMessage("Successfully deleted review");
            apiResponse.setResult(id);

            return apiResponse;
        }
        throw new AppException(CREATE_FAILED);
    }

    /**
     * get Celebrity by id
     *
     * @param slug
     * @return
     */
    @GetMapping("/{slug}/edit")
    public APIResponse<SelectOptionAndModelReponse<Review>> getEditWaterCorn(@PathVariable String slug) {
        SelectOptionAndModelReponse<Review> modelReponse = reviewService.getEditReview(slug);

        APIResponse<SelectOptionAndModelReponse<Review>> apiResponse = new APIResponse();
        apiResponse.setCode(200);
        apiResponse.setResult(modelReponse);

        return apiResponse;
    }

    /**
     * @param
     * @return
     * @throws IOException
     */
    @PutMapping(value = "/update")
    public APIResponse<String> updateReview(@ModelAttribute ReviewRequest reviewRequest) throws IOException {
//        System.out.println(celebrity);
        boolean checkUpdate = reviewService.updateReview(reviewRequest);
        if (checkUpdate) {
            APIResponse<String> apiResponse = new APIResponse();
            apiResponse.setCode(200);
            apiResponse.setMessage("Review Update successfully");

            return apiResponse;
        }

        throw new AppException(UPDATE_FAILED);
    }
}
