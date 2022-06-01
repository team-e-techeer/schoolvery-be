package net.schoolvery.schoolveryserver.domain.school.service;

import net.schoolvery.schoolveryserver.domain.school.Dto.request.SchoolRequestDto;
import net.schoolvery.schoolveryserver.domain.school.Dto.response.SchoolResponseDto;
import net.schoolvery.schoolveryserver.domain.school.entity.School;


import java.util.List;
import java.util.UUID;

public interface SchoolService {
    SchoolResponseDto createSchool(SchoolRequestDto schoolRequestDto);
    void deleteSchool(UUID id);
    SchoolResponseDto updateSchool(UUID id, SchoolRequestDto requestDto);
    SchoolResponseDto getSchoolById(UUID id);
   List<School> getAllSchool(School school);

    default School createSchoolRequest(SchoolRequestDto schoolRequestDto) {
        School school = School.builder()
                .schoolName(schoolRequestDto.getSchoolName())
                .build();

        return school;
    }

    default SchoolResponseDto createSchoolResponse(School school) {

        return SchoolResponseDto.builder()
                .id(school.getSchoolId())
                .schoolName(school.getSchoolName())
                .build();
    }
}
