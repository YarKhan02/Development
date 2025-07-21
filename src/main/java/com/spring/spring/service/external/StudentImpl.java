package com.spring.spring.service.external;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.dto.external.StudentArtistDTO;
import com.spring.spring.dto.external.StudentDTO;
import com.spring.spring.mapper.ArtistMapper;
import com.spring.spring.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class StudentImpl implements StudentService {

    private final WebClient studentsApiClient;
    private final ArtistRepository artistRepository;

    public StudentImpl(@Qualifier("studentsApiClient") WebClient studentsApiClientBuilder, ArtistRepository artistRepository) {
        this.studentsApiClient = studentsApiClientBuilder;
        this.artistRepository = artistRepository;
    }

    @Override
    public Flux<StudentDTO> getAllStudents() {
        return studentsApiClient.get()
                .uri("/students")
                .retrieve()
                .bodyToFlux(StudentDTO.class)
                .doOnError(error -> System.err.println("Error fetching students: " + error.getMessage()));
    }

    @Override
    public Mono<StudentArtistDTO> getStudentsArtists(int id) {
        return studentsApiClient.get()
                .uri("/students/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), response -> {
                    // Handle 404 or 500 errors from student API
                    return response.bodyToMono(String.class)
                            .defaultIfEmpty("Unknown error")
                            .flatMap(body -> Mono.error(new RuntimeException("Failed to fetch student: " + body)));
                })
                .bodyToMono(StudentDTO.class)
                .switchIfEmpty(Mono.error(new RuntimeException("Student not found or response body is empty")))
                .flatMap(student -> {
                    if (student.getDepartmentId() == null) {
                        return Mono.just(new StudentArtistDTO(student, null));
                    }
                    return Mono.fromCallable(() -> artistRepository.findProjected(student.getDepartmentId()))
                            .subscribeOn(Schedulers.boundedElastic())
                            .flatMap(optional -> optional
                                    .map(artist -> {
                                        ArtistDTO artistDTO = ArtistMapper.fromProjection(artist);
                                        return Mono.just(new StudentArtistDTO(student, artistDTO));
                                    })
                                    .orElseGet(() -> Mono.just(new StudentArtistDTO(student, null))));
                });
    }

    @Override
    public Mono<StudentDTO> createStudent(StudentDTO studentDTO) {
        return studentsApiClient.post()
                .uri("/students")
                .bodyValue(studentDTO)
                .retrieve()
                .bodyToMono(StudentDTO.class)
                .doOnError(error -> System.err.println("Error creating student: " + error.getMessage()));
    }
}
