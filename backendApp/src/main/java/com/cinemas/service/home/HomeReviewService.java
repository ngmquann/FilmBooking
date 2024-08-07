package com.cinemas.service.home;

import com.cinemas.dto.request.SearchReviewRequest;
import com.cinemas.dto.response.HomeReviewResponse;
import com.cinemas.dto.response.ItemIntroduce;
import com.cinemas.dto.response.SelectOptionAndModelReponse;
import com.cinemas.entities.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HomeReviewService {
    SelectOptionAndModelReponse<Page<Review>> getAllReviews(SearchReviewRequest searchReviewRequest);
    List<Review> getAllReviews2(String name);

    HomeReviewResponse getReviewDetail(String slug);
}
