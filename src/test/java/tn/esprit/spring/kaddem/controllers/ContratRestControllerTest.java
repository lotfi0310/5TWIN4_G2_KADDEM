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
import tn.esprit.spring.kaddem.services.ContratServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ContratRestControllerTest {
    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;

    @Test
    public void testRetrieveAllContrats() {

        List<Contrat> expectedContrats = new ArrayList<>();
        expectedContrats.add(new Contrat(new Date(), new Date(), Specialite.SECURITE, true, 1000));
        expectedContrats.add(new Contrat(new Date(), new Date(), Specialite.IA, true, 2000));

        Mockito.when(contratRepository.findAll()).thenReturn(expectedContrats);


        List<Contrat> actualContrats = contratService.retrieveAllContrats();


        assertEquals(expectedContrats, actualContrats);
    }

    @Test
    public void testAddContrat() {

        Contrat newContrat = new Contrat();
        newContrat.setIdContrat(3);
        newContrat.setDateDebutContrat(new Date());
        newContrat.setDateFinContrat(new Date());
        newContrat.setSpecialite(Specialite.IA);
        newContrat.setArchive(false);
        newContrat.setMontantContrat(5000);


        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("John");
        newContrat.setEtudiant(etudiant);


        when(contratRepository.save(newContrat)).thenReturn(newContrat);


        Contrat addedContrat = contratService.addContrat(newContrat);


        assertEquals(newContrat, addedContrat);


        verify(contratRepository, times(1)).save(newContrat);

        System.out.println("testAddContrat : SUCCESS");

    }
    @Test
    public void testUpdateContrat() {

        Contrat existingContrat = new Contrat();
        existingContrat.setIdContrat(1);



        when(contratRepository.save(existingContrat)).thenReturn(existingContrat);


        Contrat updatedContrat = contratService.updateContrat(existingContrat);


        assertEquals(existingContrat, updatedContrat);


        verify(contratRepository, times(1)).save(existingContrat);

        System.out.println("testUpdateContrat : SUCCESS");
    }
    @Test
    public void testRetrieveContrat() {

        int id = 1;
        Contrat sampleContrat = new Contrat();
        sampleContrat.setIdContrat(id);

        when(contratRepository.findById(id)).thenReturn(Optional.of(sampleContrat));


        Contrat result = contratService.retrieveContrat(id);


        assertEquals(sampleContrat, result);


        verify(contratRepository, times(1)).findById(id);

        System.out.println("testRetrieveContrat : SUCCESS");
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