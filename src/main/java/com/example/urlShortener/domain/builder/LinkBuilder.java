package com.example.urlShortener.domain.builder;

import com.example.urlShortener.domain.entity.Link;
import com.example.urlShortener.domain.request.LinkRequest;

public class LinkBuilder {

    public static Link createLink(LinkRequest link) {
        return Link.builder()
                .originalLink(link.getOriginalLink())
                .build();
    }
}
