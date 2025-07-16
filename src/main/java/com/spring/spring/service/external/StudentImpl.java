package com.spring.spring.service.external;

import com.spring.spring.dto.external.StudentDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class StudentImpl implements StudentService {

    private final WebClient webClient;

    public StudentImpl(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://192.168.10.163:8081").build();
    }

    @Override
    public Flux<StudentDTO> getAllStudents() {
        return webClient.get()
                .uri("/students")
                .retrieve()
                .bodyToFlux(StudentDTO.class)
                .doOnError(error -> System.err.println("Error fetching students: " + error.getMessage()));
    }
}
