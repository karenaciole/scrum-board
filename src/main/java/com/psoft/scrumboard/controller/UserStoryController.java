package com.psoft.scrumboard.controller;

import com.psoft.scrumboard.dto.ScrumMasterAtribuiUserStoryDTO;
import com.psoft.scrumboard.dto.UserStoryDTO;
import com.psoft.scrumboard.service.ProjetoService;
import com.psoft.scrumboard.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class UserStoryController {

    @Autowired
    private UserStoryService userStoryService;
    
    @Autowired
    private ProjetoService projetoService;

    @RequestMapping(value = "/userstory/", method = RequestMethod.POST)
    public ResponseEntity<?> cadastraUserStory(@PathVariable Integer projectKey, @RequestBody UserStoryDTO userStoryDTO) {

        if (this.userStoryService.contemUserStory(projectKey, userStoryDTO.getId())) {
            return new ResponseEntity<String>("UserStory já cadastrada no sistema - número não disponível", HttpStatus.CONFLICT);
        }

        String titulo = this.userStoryService.criaUserStory(projectKey, userStoryDTO);

        return new ResponseEntity<String>("UserStory cadastrada com título '" + titulo + "'.", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/userstory/", method = RequestMethod.GET)
    public ResponseEntity<?> acessaInfoUserStory(@RequestParam Integer projectKey, @RequestParam Integer idUserStory) {

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

    @RequestMapping(value = "/scrumMaster/", method = RequestMethod.POST)
    public ResponseEntity<?> atribuiUserStory(@RequestBody ScrumMasterAtribuiUserStoryDTO atribuiUserStoryDTO) {

        if (!(this.projetoService.contemProjectKey(atribuiUserStoryDTO.getProjectKey()))) {
            return new ResponseEntity<String>("Projeto não está cadastrado no sistema - nome inválido", HttpStatus.CONFLICT);
        }

        if (!(this.userStoryService.contemUserStory(atribuiUserStoryDTO.getProjectKey(), atribuiUserStoryDTO.getIdUserStory()))) {
            return new ResponseEntity<String>("UserStory não está cadastrada neste projeto", HttpStatus.CONFLICT);
        }

        if (!(this.projetoService.contemIntegrante(atribuiUserStoryDTO.getProjectKey(), atribuiUserStoryDTO.getUsername()))) {
            return new ResponseEntity<String>("Usuário não é integrante deste projeto", HttpStatus.CONFLICT);
        }

        String info = this.userStoryService.atribuiUsuarioUserStory(atribuiUserStoryDTO);

        return new ResponseEntity<String>(info, HttpStatus.OK);
    }


}
