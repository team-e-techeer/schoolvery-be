package net.schoolvery.schoolveryserver.domain.school.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.schoolvery.schoolveryserver.domain.school.Dto.request.SchoolRequestDto;
import net.schoolvery.schoolveryserver.domain.school.Dto.response.SchoolResponseDto;
import net.schoolvery.schoolveryserver.domain.school.SchoolService;
import net.schoolvery.schoolveryserver.domain.school.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/school")
@RequiredArgsConstructor
@Tag(name = "School Controller", description = "School Controller REST API")
public class SchoolController {

    @Autowired
    private final SchoolService schoolService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<SchoolResponseDto> createSchool(@RequestBody SchoolRequestDto requestDto) {
        SchoolResponseDto responseDto = schoolService.createSchool(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScool(@PathVariable UUID id) {
        schoolService.deleteSchool(id);

        return ResponseEntity.ok()
                .body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolResponseDto> updateSchool(@PathVariable UUID id, @RequestBody SchoolRequestDto requestDto) {
        SchoolResponseDto responseDto = schoolService.updateSchool(id, requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<School>> getAllSchool(School school) {
        List<School> result = schoolService.getAllSchool(school);

        return ResponseEntity.ok()
                .body(result);
    }


}
