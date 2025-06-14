package com.example.urlShortener.controller;

import com.example.urlShortener.domain.request.LinkRequest;
import com.example.urlShortener.domain.response.LinkResponse;
import com.example.urlShortener.service.EncurtadorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import static com.example.urlShortener.Utils.Constants.METHOD;
import static com.example.urlShortener.Utils.Constants.STEP;
import static com.example.urlShortener.Utils.Constants.STEP_END;
import static com.example.urlShortener.Utils.Constants.STEP_START;
import static com.example.urlShortener.Utils.Util.logBuilder;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class UrlShortenerController {

    private final EncurtadorService service;

    @PostMapping
    public LinkResponse createShortenedLink(@RequestBody LinkRequest linkRequest) {
        log.info(logBuilder(METHOD, STEP), "createShortenedLink", STEP_START);

        LinkResponse response = service.createShortenedLink(linkRequest);

        log.info(logBuilder(METHOD, STEP), "createShortenedLink", STEP_END);

        return response;
    }

    @GetMapping("/{shortenedLink}")
    public RedirectView redirectByShortenedLink(@PathVariable String shortenedLink) {
        log.info(logBuilder(METHOD, STEP), "redirectByShortenedLink", STEP_START);

        String response = service.redirectByShortenedLink(shortenedLink);

        log.info(logBuilder(METHOD, STEP), "redirectByShortenedLink", STEP_END);

        return new RedirectView(response);
    }
}
