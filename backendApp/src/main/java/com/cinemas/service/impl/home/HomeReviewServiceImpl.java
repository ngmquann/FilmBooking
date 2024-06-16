package com.cinemas.service.impl.home;

import com.cinemas.dto.request.PaginationHelper;
import com.cinemas.dto.request.SearchReviewRequest;
import com.cinemas.dto.response.SelectOptionAndModelReponse;
import com.cinemas.dto.response.SelectOptionReponse;
import com.cinemas.entities.Review;
import com.cinemas.enums.MovieStatus;
import com.cinemas.enums.ReviewType;
import com.cinemas.exception.AppException;
import com.cinemas.repositories.ReviewRepository;
import com.cinemas.service.home.HomeReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.cinemas.exception.ErrorCode.NOT_FOUND;

@Component
public class HomeReviewServiceImpl implements HomeReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public SelectOptionAndModelReponse<Page<Review>> getAllReviews(SearchReviewRequest searchReviewRequest) {
        List<Review> reviewList = reviewRepository.findByType(searchReviewRequest.type);

        PagedListHolder<Review> pagedListHolder = new PagedListHolder<Review>(reviewList);
        pagedListHolder.setPage(searchReviewRequest.getPageNo());
        pagedListHolder.setPageSize(searchReviewRequest.getPageSize());

        List<Review> pageList = pagedListHolder.getPageList();
        boolean ascending = searchReviewRequest.getSort().isAscending();
        PropertyComparator.sort(pageList, new MutableSortDefinition(searchReviewRequest.getSortByColumn(), true, ascending));

        Page<Review> reviews = new PageImpl<>(pageList, new PaginationHelper().getPageable(searchReviewRequest), reviewList.size());

        List<SelectOptionReponse> optionsTypes = new ArrayList<>();

        for (ReviewType reviewType : ReviewType.values()) {
            optionsTypes.add(new SelectOptionReponse(reviewType.name(), reviewType.getValue()));
        }
        return new SelectOptionAndModelReponse<>(optionsTypes, reviews);
    }

    @Override
    public Review getReviewDetail(String slug) {
        Review review = reviewRepository.findBySlug(slug);

        if (review == null) throw new AppException(NOT_FOUND);

        return review;
    }
}
