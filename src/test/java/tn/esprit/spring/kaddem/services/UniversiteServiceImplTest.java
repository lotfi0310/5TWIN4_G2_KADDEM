package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;


import java.util.*;

import static org.mockito.Mockito.*;

public class UniversiteServiceImplTest {

    @InjectMocks
   private UniversiteServiceImpl universiteService;
    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllUniversites() {
        Universite universite = new Universite();
        universite.setIdUniv(1);
        List<Universite> universiteList = Arrays.asList(universite);

        when(universiteRepository.findAll()).thenReturn(universiteList);

        List<Universite> result = universiteService.retrieveAllUniversites();
        assert !result.isEmpty();
        assert result.size() == 1;
    }

    @Test
    public void testAddUniversite() {
        Universite universite = new Universite();
        universite.setIdUniv(1);

        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);
        assert result != null;
        assert result.getIdUniv() == 1;
    }

    @Test
    public void testUpdateUniversite() {
        Universite universite = new Universite();
        universite.setIdUniv(1);

        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);
        assert result != null;
        assert result.getIdUniv() == 1;
    }

    @Test
    public void testRetrieveUniversite() {
        Universite universite = new Universite();
        universite.setIdUniv(1);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1);
        assert result != null;
        assert result.getIdUniv() == 1;
    }

    @Test
    public void testDeleteUniversite() {
        Universite universite = new Universite();
        universite.setIdUniv(1);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        universiteService.deleteUniversite(1);
        verify(universiteRepository, times(1)).delete(universite);
    }

    /*@Test
    public void testAssignUniversiteToDepartement() {
        Universite universite = new Universite(1,"esprit");
        universite.setIdUniv(1);
        Departement departement = new Departement(1,"ti");
        departement.setIdDepart(1);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(universite.getIdUniv(), departement.getIdDepart());
        assert universite.getDepartements().contains(departement);
       verify(universiteRepository, times(1)).save(universite);
    }*/

    @Test
    public void testRetrieveDepartementsByUniversite() {
        Universite universite = new Universite();
        universite.setIdUniv(1);
        Departement departement = new Departement();
        departement.setIdDepart(1);

        Set<Departement> departementSet = new HashSet<>();
        departementSet.add(departement);
        universite.setDepartements(departementSet);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));


        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(1);
        assert result != null;
        assert !result.isEmpty();
        assert result.size() == 1;
    }
}