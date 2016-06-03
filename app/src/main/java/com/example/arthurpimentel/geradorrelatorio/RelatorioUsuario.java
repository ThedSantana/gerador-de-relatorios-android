package com.example.arthurpimentel.geradorrelatorio;

import java.util.List;

/**
 * Created by arthur.pimentel on 01/06/2016.
 */
public class RelatorioUsuario {
    public static Relatorio<Usuario> mapper(List<Usuario> usuarios) {
        return new Relatorio.Builder<Usuario>()
                .adicionarColuna("Nome",true ,new MapeamentoObjetoColuna<Usuario>() {
                    @Override
                    public String converteObjetoParaValorDaColuna(Usuario usuario) {
                        return usuario.getNome();
                    }
                })
                .adicionarColuna("Idade",true ,new MapeamentoObjetoColuna<Usuario>() {
                    @Override
                    public String converteObjetoParaValorDaColuna(Usuario usuario) {
                        return Integer.toString(usuario.getIdade());
                    }
                })
                .adicionarColuna("Sexo",true ,new MapeamentoObjetoColuna<Usuario>() {
                    @Override
                    public String converteObjetoParaValorDaColuna(Usuario usuario) {
                        return usuario.getSexo();
                    }
                })
                .adicionarListaDeDados(usuarios)
                .create();
    }
}