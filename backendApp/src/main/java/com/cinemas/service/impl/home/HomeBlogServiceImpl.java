package com.cinemas.service.impl.home;

import com.cinemas.dto.request.PaginationHelper;
import com.cinemas.dto.response.APIResponse;
import com.cinemas.dto.response.HomeMovieBlogResponse;
import com.cinemas.dto.response.ItemIntroduce;
import com.cinemas.entities.Movie;
import com.cinemas.entities.MovieBlog;
import com.cinemas.exception.AppException;
import com.cinemas.repositories.MovieBlogRepository;
import com.cinemas.service.home.HomeBlogService;
import com.cinemas.service.impl.FileStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.cinemas.exception.ErrorCode.NOT_FOUND;


@Component
public class HomeBlogServiceImpl implements HomeBlogService {
    @Autowired
    private MovieBlogRepository movieBlogRepository;

    @Autowired
    FileStorageServiceImpl fileStorageServiceImpl;

    @Override
    public Page<MovieBlog> getAllBlog(PaginationHelper paginationHelper) {
        List<MovieBlog> movieBlogs = movieBlogRepository.findAll();

        movieBlogs.forEach(blog -> {
            String imageUrl = fileStorageServiceImpl.getUrlFromPublicId(blog.getThumbnail());
            blog.setThumbnail(imageUrl);
        });

        PagedListHolder<MovieBlog> pagedListHolder = new PagedListHolder<MovieBlog>(movieBlogs);
        pagedListHolder.setPage(paginationHelper.getPageNo());
        pagedListHolder.setPageSize(paginationHelper.getPageSize());

        List<MovieBlog> pageList = pagedListHolder.getPageList();
        boolean ascending = paginationHelper.getSort().isAscending();
        PropertyComparator.sort(pageList, new MutableSortDefinition(paginationHelper.getSortByColumn(), true, ascending));

        Page<MovieBlog> blogs = new PageImpl<>(pageList, new PaginationHelper().getPageable(paginationHelper), movieBlogs.size());

        return blogs;
    }

    @Override
    public HomeMovieBlogResponse getBlogDetail(String slug) {
        MovieBlog movieBlog = movieBlogRepository.findBySlug(slug);
        incrementViewCount(slug);
        if (movieBlog == null) throw new AppException(NOT_FOUND);

        movieBlog.setThumbnail(fileStorageServiceImpl.getUrlFromPublicId(movieBlog.getThumbnail()));

        HomeMovieBlogResponse homeMovieBlogResponse = new HomeMovieBlogResponse();
        homeMovieBlogResponse.setMovieBlog(movieBlog);
        homeMovieBlogResponse.setBlogRelate(movieBlogRepository.blogRelate());

        homeMovieBlogResponse.getBlogRelate().forEach(item -> {
            item.setImagePortrait(fileStorageServiceImpl.getUrlFromPublicId(item.getImagePortrait()));
        });
//        homeMovieBlogResponse.setViewCount(movieBlog.getView());

        return homeMovieBlogResponse;
    }

    @Override
    public void incrementViewCount(String slug) {
        MovieBlog movieBlog = movieBlogRepository.findBySlug(slug);
        if (movieBlog != null) {
            movieBlog.setView(movieBlog.getView() + 1);
            movieBlogRepository.save(movieBlog);
        }
    }

    public List<MovieBlog> getAllBlog2(String name) {
//        List<MovieBlog> blogs = movieBlogRepository.findListByName(name);
//
//        blogs.forEach(item -> {
//            item.setThumbnail(fileStorageServiceImpl.getUrlFromPublicId(item.getThumbnail()));
//        });

        List<MovieBlog> blogs = movieBlogRepository.findListByName(name)
                .stream()
                .peek(item -> item.setThumbnail(fileStorageServiceImpl.getUrlFromPublicId(item.getThumbnail())))
                .collect(Collectors.toList());

        return blogs;
    }
}
