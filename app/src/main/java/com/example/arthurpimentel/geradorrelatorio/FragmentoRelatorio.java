package com.example.arthurpimentel.geradorrelatorio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Created by luciane.araujo on 24/05/2016.
 */
public class FragmentoRelatorio<T> extends Fragment {

    private Relatorio<T> mRelatorio;
    private RelatorioAdapter<T> adapter;

    @BindView(R.id.et_filtros)
    EditText mEditText;
    @BindView(R.id.recycleViewRelatorio)
    RecyclerView mRecyclerView;

    @OnTextChanged(R.id.et_filtros)
    void onTextChangeFiltros(CharSequence text) {
        adapter.getFilter().filter(text);
    }

    public FragmentoRelatorio() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relatorio, container, false);
        ButterKnife.bind(this,view);
        configurarRecyclerView();
        return view;
    }

    public static <T> FragmentoRelatorio newInstance(Relatorio<T> relatorio) {
        FragmentoRelatorio fragmento = new FragmentoRelatorio();
        fragmento.setRelatorio(relatorio);
        return fragmento;
    }

    public Relatorio<T> getRelatorio() {
        return mRelatorio;
    }

    public void setRelatorio(Relatorio<T> relatorio) {
        mRelatorio = relatorio;
    }

    private void configurarRecyclerView() {
        adapter = new RelatorioAdapter(mRelatorio);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), mRelatorio.numeroDeColunas()));
        mRecyclerView.setAdapter(adapter);
    }

    public static class RelatorioAdapter<T> extends RecyclerView.Adapter<RelatorioAdapter.ViewHolder> implements Filterable {

        Relatorio<T> mRelatorio;
        public List<String> mValoresParaExibicao;

        public RelatorioAdapter(Relatorio<T> relatorio) {
            mRelatorio = relatorio;
            mValoresParaExibicao = relatorio.obterListaDeValores();
        }

        @Override
        public RelatorioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_relatorio, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(RelatorioAdapter.ViewHolder holder, int position) {
            String valorParaExibicao = mValoresParaExibicao.get(position);
            holder.mTexto.setText(valorParaExibicao);
        }

        @Override
        public int getItemCount() {
            return mValoresParaExibicao.size();
        }


        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence campoDigitado) {
                    final FilterResults oReturn = new FilterResults();
                    oReturn.values = mRelatorio.getListaDeResultados(campoDigitado.toString().toLowerCase());
                    return oReturn;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    mValoresParaExibicao = ((List<String>) results.values);
                    notifyDataSetChanged();
                }
            };
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView mTexto;

            public ViewHolder(View itemView) {
                super(itemView);
                mTexto = (TextView) itemView.findViewById(R.id.texto_nome);
            }
        }
    }
}