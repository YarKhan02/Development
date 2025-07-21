package com.spring.spring.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.spring.emailservice.EmailService;
import com.spring.spring.entity.ArtPiece;
import com.spring.spring.projections.ArtPieceProjection;
import com.spring.spring.repository.ArtPieceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailSenderScheduler {

    private static final Logger logger = LoggerFactory.getLogger(EmailSenderScheduler.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    ArtPieceRepository artPieceRepository;

//    @Scheduled(fixedRate = 10000)
    public void sendEmail() {
        Optional<ArtPieceProjection> message = artPieceRepository.findProjectedArtPieceById(3);

        if (message.isPresent()) {
            try {
                String body = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(message.get());
                String to = "iyark2002@gmail.com";
                String subject = "Scheduled Email";
                emailService.sendEmail(to, subject, body);
            } catch (Exception e) {
                logger.info("Sent email with projection data as JSON");
            }
        } else {
            logger.warn("Art piece with ID 2 not found");
        }
    }
}
