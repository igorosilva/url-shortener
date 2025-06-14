package com.example.urlShortener.domain.builder;

import com.example.urlShortener.domain.entity.Link;
import com.example.urlShortener.domain.response.LinkResponse;

public class LinkResponseBuilder {

    public static LinkResponse createLinkResponse(Link link) {
        return LinkResponse.builder()
                .shortenedLink(link.getShortLink())
                .build();
    }
}
