package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ContratServiceImplTest {
    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;
    @Test
    void retrieveAllContrats() {

        Date startDate = new Date();
        Date endDate = new Date();
        Contrat contrat1 = new Contrat(startDate, endDate, Specialite.IA, false, 234);
        Contrat contrat2 = new Contrat(startDate, endDate, Specialite.SECURITE, true, 235);

        Mockito.when(contratRepository.findAll()).thenReturn(List.of(contrat1, contrat2));
        List<Contrat> contratList = contratService.retrieveAllContrats();

        assertEquals(2, contratList.size());
    }

    @Test
    void updateContrat() {

        Contrat initialContrat = new Contrat(new Date(), new Date(), Specialite.IA, true, 234);

        Mockito.when(contratRepository.save(initialContrat)).thenReturn(initialContrat);

        Contrat savedContrat = contratService.addContrat(initialContrat);

        Contrat updatedContrat = new Contrat(savedContrat.getDateDebutContrat(), new Date(), Specialite.SECURITE, false, 235);
        updatedContrat.setIdContrat(savedContrat.getIdContrat());

        Mockito.when(contratRepository.save(updatedContrat)).thenReturn(updatedContrat);

        Contrat result = contratService.updateContrat(updatedContrat);

        assertEquals(updatedContrat, result);
    }

    @Test
    void addContrat() {
        Contrat newContrat = new Contrat(new Date(), new Date(), Specialite.IA, false, 234);
        Mockito.when(contratRepository.save(newContrat)).thenReturn(newContrat);
        Contrat addedContrat = contratService.addContrat(newContrat);
        assertEquals(newContrat, addedContrat);
    }

    @Test
    void retrieveContrat() {

        Integer contratId = 1;


        Contrat retrievedContrat = new Contrat(new Date(), new Date(), Specialite.CLOUD, true, 235);

        Mockito.when(contratRepository.findById(contratId)).thenReturn(Optional.of(retrievedContrat));

        Contrat result = contratService.retrieveContrat(contratId);

        assertEquals(retrievedContrat, result);
    }

    @Test
    void removeContrat()   {
        int Id = 1;
        Contrat contrat1 = new Contrat(new Date(), new Date(), Specialite.CLOUD, true, 235);
        contrat1.setIdContrat(1);

        when(contratRepository.findById(Id)).thenReturn(Optional.of(contrat1));
        doNothing().when(contratRepository).delete(contrat1);

        assertAll(() -> contratService.removeContrat(Id));





    }

    @Test
    void affectContratToEtudiant() {

        Etudiant etudiant = new Etudiant();
        etudiant.setContrats(new HashSet<>());


        Contrat contrat = new Contrat();
        contrat.setArchive(false);

       Mockito.when(etudiantRepository.findByNomEAndPrenomE("dora", "kadri")).thenReturn(etudiant);
        Mockito.when(contratRepository.findByIdContrat(1)).thenReturn(contrat);

        Contrat result = contratService.affectContratToEtudiant(1, "dora", "kadri");


        assertEquals(etudiant, contrat.getEtudiant());


        Mockito.verify(contratRepository).save(contrat);


        Mockito.verify(etudiantRepository).findByNomEAndPrenomE("dora", "kadri");

    }

    @Test
    void nbContratsValides() {
        Date startDate = new Date();
        Date endDate = new Date();

        Mockito.when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(5);

        Integer result = contratService.nbContratsValides(startDate, endDate);

        assertEquals(5, result);
    }

}