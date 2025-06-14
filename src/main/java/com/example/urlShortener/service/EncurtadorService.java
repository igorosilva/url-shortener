package com.example.urlShortener.service;

import com.example.urlShortener.domain.entity.Link;
import com.example.urlShortener.domain.request.LinkRequest;
import com.example.urlShortener.domain.response.LinkResponse;
import com.example.urlShortener.exceptions.ShortenerException;
import com.example.urlShortener.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.example.urlShortener.Utils.Constants.LINK_DOESNT_EXISTS;
import static com.example.urlShortener.Utils.Constants.LINK_EXPIRED;
import static com.example.urlShortener.Utils.Constants.LINK_INVALID;
import static com.example.urlShortener.Utils.Constants.LOCALHOST;
import static com.example.urlShortener.domain.builder.LinkBuilder.createLink;
import static com.example.urlShortener.domain.builder.LinkResponseBuilder.createLinkResponse;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class EncurtadorService {

    private final LinkRepository linkRepository;

    public LinkResponse createShortenedLink(LinkRequest linkRequest) {
        if (linkRequest.getOriginalLink().isEmpty()) {
            log.info(LINK_INVALID);
            return null;
        }

        Link link = createLink(linkRequest);

        String shortenedLink = generateShortenedLink();

        while (linkRepository.existsByShortLink(shortenedLink)) {
            log.warn("The shortened link '{}' already exists. Generating a new shortened link", shortenedLink);
            shortenedLink = generateShortenedLink();
        }

        link.setShortLink(shortenedLink);

        linkRepository.save(link);

        return createLinkResponse(link);
    }

    private static String generateShortenedLink() {
        String shortenedLink = randomUUID().toString().replace("-", "").substring(0, 10);
        return getFinalUrl(shortenedLink);
    }

    private static String getFinalUrl(String shortenedLink) {
        return LOCALHOST + shortenedLink;
    }

    public String redirectByShortenedLink(String shortenedLink) {
        String finalUrl = getFinalUrl(shortenedLink);
        Link link = linkRepository.findByShortLink(finalUrl);

        String message;
        if (link == null) {
            throwShortenedLink(LINK_DOESNT_EXISTS, NOT_FOUND);
        }

        if (!link.getIsValid()) {
            throwShortenedLink(LINK_EXPIRED, BAD_REQUEST);
        }

        if (link.getExpiresAt().isBefore(now())) {
            message = LINK_EXPIRED;
            log.info(message);

            link.setIsValid(false);
            linkRepository.updateByShortLink(link);

            throw new ShortenerException(message, BAD_REQUEST);
        }

        return link.getOriginalLink();
    }

    public void throwShortenedLink(String message, HttpStatus httpStatus) {
        throw new ShortenerException(message, httpStatus);
    }
}
