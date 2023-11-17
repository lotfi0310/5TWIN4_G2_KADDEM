package tn.esprit.spring.kaddem.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.services.DetailEquipeService;


@RestController
@RequestMapping("/detailEquipe")
public class DetailEquipeController {
	@Autowired
	DetailEquipeService eServ;
	
	@GetMapping("/displayDetailEquipe/{idDetailEquipe}")
	public DetailEquipe displayDetailEquipe(@PathVariable("idDetailEquipe")int idDetailEquipe) {
		return eServ.retrieveDetailEquipe(idDetailEquipe);
	}
	
	@GetMapping("/displayDetailEquipes")
	public List<DetailEquipe> displayAllDetailEquipes(){
		return eServ.retrieveAllDetailEquipes();
	}
	
	@PostMapping("/addDetailEquipe/{idEquipe}")
	public DetailEquipe addDetailEquipe( @RequestBody DetailEquipe e) {
		return eServ.addDetailEquipe(e);
	}
	
	@PutMapping("/updateDetailEquipe")
	public DetailEquipe updateDetailEquipe(@RequestBody DetailEquipe e) {
		return eServ.updateDetailEquipe(e);
	}
	
	@DeleteMapping("/deleteDetailEquipe/{idDetailEquipe}")
	public void deleteDetailEquipe(@PathVariable("idDetailEquipe")Integer id) {
		eServ.removeDetailEquipe(id);
	}
}
