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
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ContratRestControllerTest {
    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EtudiantRepository etudiantRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
  void testRetrieveAllContrats() {

        List<Contrat> Contrats = new ArrayList<>();

        Contrat contrat1 = new Contrat();
        contrat1.setIdContrat(1);
        contrat1.setMontantContrat(1000);

        Contrats.add(contrat1);

        Contrat contrat2 = new Contrat();
        contrat2.setIdContrat(2);
        contrat2.setMontantContrat(2000);

        Contrats.add(contrat2);


        Mockito.when(contratRepository.findAll()).thenReturn(Contrats);


        List<Contrat> result = contratService.retrieveAllContrats();


        assertEquals(Contrats, result);

        System.out.println("testRetrieveAllContrats : SUCCESS");
    }

    @Test
   void testAddContrat() {

        Contrat newContrat = new Contrat();
        newContrat.setIdContrat(3);
        newContrat.setDateDebutContrat(new Date());
        newContrat.setDateFinContrat(new Date());
        newContrat.setSpecialite(Specialite.IA);
        newContrat.setArchive(false);
        newContrat.setMontantContrat(5000);


        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("iyed");
        newContrat.setEtudiant(etudiant);


        Mockito.when(contratRepository.save(newContrat)).thenReturn(newContrat);


        Contrat addedContrat = contratService.addContrat(newContrat);


        assertEquals(newContrat, addedContrat);


        Mockito.verify(contratRepository, Mockito.times(1)).save(newContrat);

        System.err.println("testAddContrat : SUCCESS");

    }
    @Test
    void testUpdateContrat() {

        Contrat existingContrat = new Contrat();
        existingContrat.setIdContrat(1);



        Mockito.when(contratRepository.save(existingContrat)).thenReturn(existingContrat);


        Contrat updatedContrat = contratService.updateContrat(existingContrat);


        assertEquals(existingContrat, updatedContrat);


        Mockito.verify(contratRepository,Mockito.times(1)).save(existingContrat);

        System.err.println("testUpdateContrat : SUCCESS");
    }
    @Test
    void testRetrieveContrat() {

        int id = 1;
        Contrat Contrat1 = new Contrat();
        Contrat1.setIdContrat(id);


        Mockito.when(contratRepository.findById(id)).thenReturn(Optional.of(Contrat1));


        Contrat result = contratService.retrieveContrat(id);


        assertEquals(Contrat1, result);

        Mockito.verify(contratRepository, Mockito.times(1)).findById(id);

        System.err.println("testRetrieveContrat : SUCCESS");
    }
    @Test
    void testDeleteContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        contratService.removeContrat(contrat.getIdContrat());
        List<Contrat> result = contratService.retrieveAllContrats();
        assertEquals(0, result.size());

        System.out.println("DeleteContratTest : Ok");
    }

}