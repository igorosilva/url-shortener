package com.example.urlShortener.domain.builder;

import com.example.urlShortener.domain.response.LinkResponse;

public class LinkResponseBuilder {

    public static LinkResponse createLinkResponse(String link) {
        return LinkResponse.builder()
                .shortenedLink(link)
                .build();
    }
}
