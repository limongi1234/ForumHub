package br.com.alura.forumhub.dto;

public class DadosTokenJWT {
    
    private String token;

    public DadosTokenJWT() {
    }

    public DadosTokenJWT(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

