package com.spring.spring.service.external;

import reactor.core.publisher.Flux;

import com.spring.spring.dto.external.StudentDTO;

public interface StudentService {
    Flux<StudentDTO> getAllStudents();
}
