package com.example.urlShortener.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static com.example.urlShortener.Utils.Constants.TB_LINK;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.lang.Boolean.TRUE;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TB_LINK)
public class Link {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(name = "Link encurtado", example = "http://localhost:8080/dte5ab68")
    private String shortLink;

    @NotBlank
    @Schema(name = "Link original", example = "https://google.com/")
    private String originalLink;

    @CreationTimestamp
    @Schema(name = "Data de criação do link encurtado", example = "2025-05-10 13:53:04")
    private LocalDateTime createdAt;

    @Schema(name = "Data de expiração do link encurtado", example = "2025-05-10 14:53:04")
    private LocalDateTime expiresAt;

    @Schema(name = "O link expirado ainda é válido", example = "true")
    private Boolean isValid;

    @PostPersist
    public void initializeLinkMetadata() {
        if (createdAt != null) {
            this.expiresAt = createdAt.plusMinutes(30);
        }

        this.isValid = TRUE;
    }
}
