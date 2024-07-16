package ru.practicum.dtos.eventsDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventUserRequest extends UpdateEventDto {
    private UserStateAction stateAction;
}