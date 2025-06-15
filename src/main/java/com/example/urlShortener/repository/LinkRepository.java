package com.example.urlShortener.repository;

import com.example.urlShortener.domain.entity.Link;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Link save(Link link);

    Link findByShortLink(String shortLink);

    @Modifying
    @Transactional
    @Query("UPDATE Link l SET l.originalLink = :#{#link.originalLink} WHERE l.shortLink = :#{#link.shortLink}")
    void updateByShortLink(@Param("link") Link link);

    boolean existsByShortLink(String shortLink);
}
