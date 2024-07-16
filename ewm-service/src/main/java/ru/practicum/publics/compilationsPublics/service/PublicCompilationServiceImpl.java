package ru.practicum.publics.compilationsPublics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dtos.compilationsDto.CompilationDto;
import ru.practicum.dtos.compilationsDto.CompilationMapper;
import ru.practicum.models.Compilation;
import ru.practicum.repositories.CompilationRepository;
import ru.practicum.utils.GeneralMethods;
import ru.practicum.utils.PageParams;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicCompilationServiceImpl implements PublicCompilationService {
    private final CompilationRepository compilationRepository;

    private final CompilationMapper compilationMapper;

    @Override
    public List<CompilationDto> getAll(Boolean pinned, PageParams pageParams) {
        return compilationRepository.findAll(pinned, pageParams.getPageRequest())
                .getContent()
                .stream()
                .map(compilationMapper::toCompilationDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getById(long id) {
        Compilation compilation = GeneralMethods.findCompilation(id, compilationRepository);
        return compilationMapper.toCompilationDto(compilation);
    }
}
