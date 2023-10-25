package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EtudiantRestControllerTest {
    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;



    @Test
    void getEtudiants() {
    }

    @Test
    public void retrieveAllEtudiants() {

        List<Etudiant> expectedEtudiants = new ArrayList<>();
        expectedEtudiants.add(new Etudiant(new String(), new String(), Option.GAMIX));
        expectedEtudiants.add(new Etudiant(new String(), new String(), Option.SIM));



        Mockito.when(etudiantRepository.findAll()).thenReturn(expectedEtudiants);


        List<Etudiant> actualEtudiants = etudiantService.retrieveAllEtudiants();


        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    void addEtudiant() {
    }

    @Test
    void removeEtudiant() {
    }

    @Test
    void updateEtudiant() {
    }
}