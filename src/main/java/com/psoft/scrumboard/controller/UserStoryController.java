package com.psoft.scrumboard.controller;

import com.psoft.scrumboard.dto.UserStoryDTO;
import com.psoft.scrumboard.service.ProjetoService;
import com.psoft.scrumboard.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class UserStoryController {

    @Autowired
    private UserStoryService userStoryService;
    
    @Autowired
    private ProjetoService projetoService;

    @RequestMapping(value = "/userstory/", method = RequestMethod.POST)
    public ResponseEntity<?> cadastraUserStory(@RequestParam Integer projectKey, @RequestBody UserStoryDTO userStoryDTO, UriComponentsBuilder ucBuilder) {

        if (this.userStoryService.contemUserStory(projectKey, userStoryDTO.getId())) {
            return new ResponseEntity<String>("UserStory já cadastrada no sistema - número não disponível", HttpStatus.CONFLICT);
        }

        String titulo = this.userStoryService.criaUserStory(projectKey, userStoryDTO);

        return new ResponseEntity<String>("UserStory cadastrada com título '" + titulo + "'.", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/userstory/{projectKey}/{idUserStory}", method = RequestMethod.GET)
    public ResponseEntity<?> acessaInfoUserStory(@PathVariable Integer projectKey, @PathVariable Integer idUserStory) {

        if (!(this.userStoryService.contemUserStory(projectKey, idUserStory))) {
            return new ResponseEntity<String>("UserStory não está cadastrada neste projeto.", HttpStatus.CONFLICT);
        }

        String info = this.userStoryService.getInfoUserStory(projectKey, idUserStory);

        return new ResponseEntity<String>(info, HttpStatus.OK);
    }

    @RequestMapping(value = "/userstory/{projectKey}/{idUserStory}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeUserStory(@PathVariable Integer projectKey, @PathVariable Integer idUserStory) {

        if (!(this.userStoryService.contemUserStory(projectKey, idUserStory))) {
            return new ResponseEntity<String>("UserStory não está cadastrada neste projeto.", HttpStatus.CONFLICT);
        }

        String info = this.userStoryService.deletaUserStory(projectKey, idUserStory);

        return new ResponseEntity<String>(info, HttpStatus.OK);
    }

    @RequestMapping(value = "/userstory/{projectKey}/{idUserStory}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizaUserStory(@PathVariable Integer projectKey, @PathVariable Integer idUserStory, @RequestBody UserStoryDTO userStoryDTO) {

        if (!(this.userStoryService.contemUserStory(projectKey, idUserStory))) {
            return new ResponseEntity<String>("UserStory não está cadastrada neste projeto.", HttpStatus.CONFLICT);
        }

        String info = this.userStoryService.updateInfoUserStory(projectKey, userStoryDTO);

        return new ResponseEntity<String>(info, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/userstory/{projectKey}/{idUserStory}/responsaveis/", method = RequestMethod.POST)
    public ResponseEntity<?> participaDesenvolvimentoUserStory(@PathVariable Integer projectKey, @PathVariable Integer idUserStory, @RequestParam String responsavelUsername) {

    	if (!(this.projetoService.contemProjectKey(projectKey))) {
            return new ResponseEntity<String>("Projeto não está cadastrado no sistema - nome inválido", HttpStatus.CONFLICT);
        }
    	
    	if (!(this.userStoryService.contemUserStory(projectKey, idUserStory))) {
            return new ResponseEntity<String>("UserStory não está cadastrada neste projeto", HttpStatus.CONFLICT);
        }
    	
    	if (!(this.projetoService.contemIntegrante(projectKey, responsavelUsername))) {
            return new ResponseEntity<String>("Usuário não é integrante deste projeto", HttpStatus.CONFLICT);
        }
    	
    	String info = this.userStoryService.atribuiUsuarioUserStory(projectKey, idUserStory, responsavelUsername);
    	
    	return new ResponseEntity<String>(info, HttpStatus.OK);
    }

}
