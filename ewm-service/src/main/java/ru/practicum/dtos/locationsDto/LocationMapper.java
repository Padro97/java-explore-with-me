package ru.practicum.dtos.locationsDto;

import org.mapstruct.Mapper;
import ru.practicum.models.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location toLocation(LocationDto location);
}
