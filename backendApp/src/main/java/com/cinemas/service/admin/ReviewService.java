package com.cinemas.service.admin;

import com.cinemas.dto.request.PaginationHelper;
import com.cinemas.dto.request.ReviewRequest;
import com.cinemas.dto.request.SearchRequest;
import com.cinemas.dto.request.SearchReviewRequest;
import com.cinemas.dto.response.SelectOptionAndModelReponse;
import com.cinemas.dto.response.SelectOptionReponse;
import com.cinemas.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    SelectOptionAndModelReponse<Page<Review>> getAllReview(SearchRequest paginationHelper);
    boolean addReview(ReviewRequest review) throws IOException;

    Integer deleteReview(String slug) throws IOException;

    SelectOptionAndModelReponse<Review> getEditReview(String slug);

    boolean updateReview(ReviewRequest review) throws IOException;

    List<SelectOptionReponse> getCreate();
}
