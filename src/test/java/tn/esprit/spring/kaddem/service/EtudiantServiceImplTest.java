package tn.esprit.spring.kaddem.service;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

 class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        // Ajouter des étudiants à la liste
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertEquals(etudiants, result);
    }

    @Test
     void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);

        assertEquals(etudiant, result);
    }

    @Test
     void testRemoveEtudiant() {
        Integer etudiantId = 1;
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(etudiantId)).thenReturn(java.util.Optional.of(etudiant));

        etudiantService.removeEtudiant(etudiantId);

        verify(etudiantRepository, times(1)).delete(etudiant);
    }


    @Test
     void testAssignEtudiantToDepartement() {
        Integer etudiantId = 1;
        Integer departementId = 2;
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(etudiantId)).thenReturn(java.util.Optional.of(etudiant));
        Departement departement = new Departement();
        when(departementRepository.findById(departementId)).thenReturn(java.util.Optional.of(departement));

        etudiantService.assignEtudiantToDepartement(etudiantId, departementId);

        assertEquals(departement, etudiant.getDepartement());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
     void testAddAndAssignEtudiantToEquipeAndContract() {
        Integer idContrat = 1;
        Integer idEquipe = 2;
        Etudiant etudiant = new Etudiant();
        Contrat contrat = new Contrat();
        Equipe equipe = new Equipe();
        when(contratRepository.findById(idContrat)).thenReturn(java.util.Optional.of(contrat));
        when(equipeRepository.findById(idEquipe)).thenReturn(java.util.Optional.of(equipe));

        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, idContrat, idEquipe);

        assertEquals(etudiant, result);
        assertEquals(contrat.getEtudiant(), etudiant);
        assertTrue(equipe.getEtudiants().contains(etudiant));
    }

    @Test
     void testGetEtudiantsByDepartement() {
        Integer idDepartement = 1;
        List<Etudiant> etudiants = new ArrayList<>();
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(idDepartement)).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(idDepartement);

        assertEquals(etudiants, result);
    }


}

