package com.cinemas.repositories;

import com.cinemas.dto.response.ItemIntroduce;
import com.cinemas.entities.Celebrity;
import com.cinemas.entities.MovieBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieBlogRepository extends JpaRepository<MovieBlog, Integer> {
    MovieBlog findByName(String name);

    MovieBlog findBySlug(String slug);

    @Query("SELECT b FROM MovieBlog b WHERE b.name = ?1 AND b.id != ?2")
    MovieBlog findByNameWithId(String name, int id);

    @Query("select b FROM MovieBlog b where (:name is null or b.name like %:name%)" + "ORDER BY b.id DESC")
    List<MovieBlog> searchByName(String name);

    @Query("SELECT new com.cinemas.dto.response.ItemIntroduce(m.id, m.name, m.slug, m.thumbnail) FROM MovieBlog m ORDER BY m.id DESC LIMIT 4")
    List<ItemIntroduce> blogRelate();

    @Query("SELECT m FROM MovieBlog m WHERE (:name is null or m.name LIKE %:name%)")
    List<MovieBlog> findListByName(String name);
}
