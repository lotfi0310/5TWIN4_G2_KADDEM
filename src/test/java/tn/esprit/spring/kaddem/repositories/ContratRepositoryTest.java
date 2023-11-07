package tn.esprit.spring.kaddem.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)

@SpringBootTest
class ContratRepositoryTest {

    @Autowired
    private ContratRepository  ContratRepo ;


    @Test
    void findAll() {
        Contrat c1 = new Contrat(new Date(),new Date(), Specialite.IA,true,234);
        Contrat c2 = new Contrat(new Date(),new Date(), Specialite.SECURITE,false,2354);
        ContratRepo.save(c1);
        ContratRepo.save(c2);

        List<Contrat> contratlist = ContratRepo.findAll();
        assertEquals(2,contratlist.size());
    }

    @Test
    void findByIdContrat() {
        Contrat c1 = new Contrat(new Date(),new Date(), Specialite.IA,true,234);
        ContratRepo.save(c1);
        Contrat c = ContratRepo.findByIdContrat(c1.getIdContrat()) ;
        assertNotNull(c);
    }


}