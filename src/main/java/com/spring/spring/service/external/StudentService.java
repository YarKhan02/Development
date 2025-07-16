package com.spring.spring.service.external;

import com.spring.spring.dto.external.StudentArtistDTO;
import reactor.core.publisher.Flux;

import com.spring.spring.dto.external.StudentDTO;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<StudentDTO> getAllStudents();

    Mono<StudentDTO> createStudent(StudentDTO studentDTO);

    Mono<StudentArtistDTO> getStudentsArtists(int id);
}
