package com.example.arthurpimentel.geradorrelatorio;

import java.util.List;

/**
 * Created by arthur.pimentel on 01/06/2016.
 */
public class RelatorioVeiculo {
    public static Relatorio<Veiculo> mapper(List<Veiculo> veiculos) {
        return new Relatorio.Builder<Veiculo>()
                .adicionarColuna("Nome",true ,new MapeamentoObjetoColuna<Veiculo>() {
                    @Override
                    public String converteObjetoParaValorDaColuna(Veiculo veiculo) {
                        return veiculo.getNome();
                    }
                })
                .adicionarColuna("Cor",true ,new MapeamentoObjetoColuna<Veiculo>() {
                    @Override
                    public String converteObjetoParaValorDaColuna(Veiculo veiculo) {
                        return veiculo.getCor();
                    }
                })
                .adicionarColuna("Ano",false ,new MapeamentoObjetoColuna<Veiculo>() {
                    @Override
                    public String converteObjetoParaValorDaColuna(Veiculo veiculo) {
                        return veiculo.getAno();
                    }
                })
                .adicionarColuna("Condicao",true ,new MapeamentoObjetoColuna<Veiculo>() {
                    @Override
                    public String converteObjetoParaValorDaColuna(Veiculo veiculo) {
                        return veiculo.getCondicao();
                    }
                })
                .adicionarColuna("Marca",false ,new MapeamentoObjetoColuna<Veiculo>() {
                    @Override
                    public String converteObjetoParaValorDaColuna(Veiculo veiculo) {
                        return veiculo.getMarca();
                    }
                })
                .adicionarListaDeDados(veiculos)
                .create();
    }
}
