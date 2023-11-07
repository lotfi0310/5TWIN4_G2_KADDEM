package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ContratRestControllerTest {
    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;



    @Test
    void getContrats() {
    }

    @Test
    public void testRetrieveAllContrats() {

        List<Contrat> expectedContrats = new ArrayList<>();
        expectedContrats.add(new Contrat(new Date(), new Date(), Specialite.SECURITE, true, 1000));
        expectedContrats.add(new Contrat(new Date(), new Date(), Specialite.IA, true, 2000));



        Mockito.when(contratRepository.findAll()).thenReturn(expectedContrats);


        List<Contrat> actualContrats = contratService.retrieveAllContrats();


        assertEquals(expectedContrats, actualContrats);
    }

}