package com.example.samuraitravel.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavForm {
@NotNull
private Integer favId;

@NotNull
private LocalDateTime updatedAt;
}
