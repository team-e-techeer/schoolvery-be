package net.schoolvery.schoolveryserver.domain.school.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.schoolvery.schoolveryserver.domain.school.Dto.request.SchoolRequestDto;
import net.schoolvery.schoolveryserver.domain.school.Dto.response.SchoolResponseDto;
import net.schoolvery.schoolveryserver.domain.school.entity.School;
import net.schoolvery.schoolveryserver.domain.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    private final SchoolRepository schoolRepository;

    @Override
    public SchoolResponseDto createSchool(SchoolRequestDto schoolRequestDto) {

        School school = createSchoolRequest(schoolRequestDto);
        schoolRepository.save(school);

        return createSchoolResponse(school);
    }

    @Override
    public void deleteSchool(UUID id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public SchoolResponseDto updateSchool(UUID id, SchoolRequestDto requestDto) {
        Optional<School> school = schoolRepository.findById(id);
        School entity = school.get();
        entity.update(
                requestDto.getSchoolName()
        );
        School updateEntity = schoolRepository.save(entity);

        return createSchoolResponse(updateEntity);
    }

    @Override
    public SchoolResponseDto getSchoolById(UUID id) {
        Optional<School> result = schoolRepository.findById(id);
        return result.isPresent() ? createSchoolResponse(result.get()) : null;
    }

    @Override
    public List<School> getAllSchool(School school) {

        return schoolRepository.findAll();
    }

}
