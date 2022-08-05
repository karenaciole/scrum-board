package com.psoft.scrumboard.model;

import com.psoft.scrumboard.repository.UserStoryRepository;

public class Projeto {

    private String nome;

    private String descricao;

    private String instituicaoParceira;
    
    private Integrante scrumMaster;
    
    private UserStoryRepository userStoryRepository;
    
    public Projeto(String nome, String descricao, String instituicaoParceira, Integrante scrumMaster) {
        this.nome = nome;
        this.descricao = descricao;
        this.instituicaoParceira = instituicaoParceira;
        this.scrumMaster = scrumMaster;
        this.userStoryRepository = new UserStoryRepository();
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getInstituicaoParceira() {
        return this.instituicaoParceira;
    }
    
    public Integrante getScrumMaster() {
    	return this.scrumMaster;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setInstituicaoParceira(String instituicaoParceira) {
        this.instituicaoParceira = instituicaoParceira;
    }
    
    public UserStoryRepository getUserStoryRepository() {
    	return this.userStoryRepository;
    }

    public String toString() {

        String userInfo = "Informações do projeto de nome '" + this.nome + "'\n"
                + "Descricao: " + this.descricao + "\n"
                + "Instituicao Parceira: " + this.instituicaoParceira;

        return userInfo;
    }
}
