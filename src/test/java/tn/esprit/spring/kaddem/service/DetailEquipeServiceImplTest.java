package tn.esprit.spring.kaddem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.kaddem.services.DetailEquipeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 public class DetailEquipeServiceImplTest {

    @InjectMocks
    private DetailEquipeServiceImpl detailEquipeService;

    @Mock
    private DetailEquipeRepository detailEquipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllDetailEquipes() {

        List<DetailEquipe> detailEquipes = new ArrayList<>();
        detailEquipes.add(new DetailEquipe(1,10, "thematique 1"));
        detailEquipes.add(new DetailEquipe(2,20, "thematique 2"));

        Mockito.when(detailEquipeRepository.findAll()).thenReturn(detailEquipes);

         List<DetailEquipe> result = detailEquipeService.retrieveAllDetailEquipes();
         assertEquals(2, result.size());
        assertEquals(10, result.get(0).getSalle());
        assertEquals("thematique 1", result.get(0).getThematique());
         assertEquals(20, result.get(1).getSalle());
        assertEquals("thematique 2", result.get(1).getThematique());

    }

    @Test
    void addDetailEquipe() {

        DetailEquipe newDetailEquipe = new DetailEquipe(3,30, "New thematique");

        Mockito.when(detailEquipeRepository.save(newDetailEquipe)).thenReturn(newDetailEquipe);

         DetailEquipe addedDetailEquipe = detailEquipeService.addDetailEquipe(newDetailEquipe);

         assertNotNull(addedDetailEquipe);
        assertEquals(30, addedDetailEquipe.getSalle());
        assertEquals("New thematique", addedDetailEquipe.getThematique());
    }


    @Test
    void retrieveDetailEquipe() {
        int detailEquipeId = 7;
        DetailEquipe detailEquipe = new DetailEquipe(detailEquipeId, 70,"thematique 7");

        Mockito.when(detailEquipeRepository.findById(detailEquipeId)).thenReturn(Optional.of(detailEquipe));

         DetailEquipe retrievedDetailEquipe = detailEquipeService.retrieveDetailEquipe(detailEquipeId);

         assertNotNull(retrievedDetailEquipe);
        assertEquals(70, retrievedDetailEquipe.getSalle());
        assertEquals("thematique 7", retrievedDetailEquipe.getThematique());
    }

    @Test
    void removeDetailEquipe() {
        int detailEquipeId = 8;
        DetailEquipe detailEquipe = new DetailEquipe(detailEquipeId, 80,"thematique 8");

        Mockito.when(detailEquipeRepository.findById(detailEquipeId)).thenReturn(Optional.of(detailEquipe));

         detailEquipeService.removeDetailEquipe(detailEquipeId);

         Mockito.verify(detailEquipeRepository, times(1)).deleteById(detailEquipeId);
    }
}
