package ru.practicum.dtos.eventsDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.dtos.categoriesDto.CategoryDto;
import ru.practicum.dtos.usersDto.UserShortDto;
import ru.practicum.utils.Constants;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventShortDto {
    private Long id;
    private String annotation;
    private CategoryDto category;
    private int confirmedRequests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private boolean paid;
    private String title;
    private long views;
}