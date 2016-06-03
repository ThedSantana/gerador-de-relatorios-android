package com.example.arthurpimentel.geradorrelatorio;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthur.pimentel on 30/05/2016.
 */
public class Relatorio<T> {
    private final List<String> mTitulosColunas;
    private final List<MapeamentoObjetoColuna<T>> mMapeamentosObjetoColunas;
    private final List<T> mObjetos;
    private final List<String> mFiltros;


    private Relatorio(@NonNull List<String> titulosColunas,
                      @NonNull List<MapeamentoObjetoColuna<T>> mapeamentosObjetoColunas,
                      @NonNull List<T> objetos,
                      @NonNull List<String> filtros) {
        if (titulosColunas.size() != mapeamentosObjetoColunas.size()) {
            throw new IllegalArgumentException("Colunas e mapeamentos tem que ter o mesmo tamanho.");
        }
        mTitulosColunas = titulosColunas;
        mMapeamentosObjetoColunas = mapeamentosObjetoColunas;
        mObjetos = objetos;
        mFiltros = filtros;
    }

    public int numeroDeLinhas() {
        return mObjetos.size();
    }

    public List<String> getListaDeResultados(String campoDigitado) {

        if (campoDigitado == null || campoDigitado.equals("")) {
            return obterListaDeValores();
        }

        List<T> objetosFiltrados = new ArrayList<>();
        List<String> resultados = new ArrayList<>();

        adicionarHeadersNaLista(resultados);

        if (campoDigitado != null) {
            if (mObjetos != null & mObjetos.size() > 0) {
                for (T objeto : mObjetos) {
                    for (int i = 0; i < numeroDeColunas(); i++) {
                        if (mFiltros.contains(getTituloColuna(i)) && campoDigitado.equals(getValorColuna(i).converteObjetoParaValorDaColuna(objeto).toLowerCase())) {
                            objetosFiltrados.add(objeto);
                        }
                    }
                }

                converterListaDeObjetosParaListaDeValores(objetosFiltrados, resultados);
            }
        }
        return resultados;
    }

    private void converterListaDeObjetosParaListaDeValores(List<T> objetosFiltrados, List<String> resultados) {
        for (T objeto : objetosFiltrados) {
            for (int i = 0; i < numeroDeColunas(); i++) {
                resultados.add(getValorColuna(i).converteObjetoParaValorDaColuna(objeto));
            }
        }
    }

    private void adicionarHeadersNaLista(List<String> resultados) {
        for (int i = 0; i < numeroDeColunas(); i++) {
            resultados.add(getTituloColuna(i));
        }
    }

    public List<T> getListaDeObjetos() {
        return mObjetos;
    }


    public int numeroDeColunas() {
        return mTitulosColunas.size();
    }

    public String getTituloColuna(int index) {
        verificarSeIndiceMaiorQueTamanhoDeColunas(index);
        return mTitulosColunas.get(index);
    }

    private void verificarSeIndiceMaiorQueTamanhoDeColunas(int index) {
        if (index > numeroDeColunas()) {
            throw new IllegalArgumentException("Index não pode ser maior que número de colunas.");
        }
    }

    public List<String> obterListaDeValores() {

        List<String> valores = new ArrayList<>();
        adicionarHeadersNaLista(valores);
        converterListaDeObjetosParaListaDeValores(mObjetos, valores);
        return valores;
    }


    public MapeamentoObjetoColuna<T> getValorColuna(int index) {
        verificarSeIndiceMaiorQueTamanhoDeColunas(index);
        return mMapeamentosObjetoColunas.get(index);
    }

    public static class Builder<T> {
        private final List<String> mTitulosColunas = new ArrayList<>();
        private final List<MapeamentoObjetoColuna<T>> mMapeamentoObjetoColunas = new ArrayList<>();
        private List<T> mDados = new ArrayList<>();
        private List<String> mFiltros = new ArrayList<>();

        public Builder() {
        }

        public Builder<T> adicionarColuna(@NonNull String titulo, boolean filtravel, @NonNull MapeamentoObjetoColuna<T> mapeamentoObjetoColuna) {
            mTitulosColunas.add(titulo);
            mMapeamentoObjetoColunas.add(mapeamentoObjetoColuna);
            if (filtravel == true) {
                mFiltros.add(titulo);
            }
            return this;
        }

        public Builder<T> adicionarListaDeDados(@NonNull List<T> dados) {
            mDados = dados;
            return this;
        }

        public Relatorio<T> create() {
            return new Relatorio<T>(mTitulosColunas, mMapeamentoObjetoColunas, mDados, mFiltros);
        }

    }
}