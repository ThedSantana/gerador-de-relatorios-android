package com.example.arthurpimentel.geradorrelatorio;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Usuario> usuarios = new ArrayList<>();
    List<Veiculo> veiculos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adicionarFragmento();
    }

    private void adicionarFragmento() {
        usuarios = criarListaDeUsuarios();
        veiculos = criarListaDeVeiculos();
        final Relatorio<Veiculo> mVeicRelatorio = RelatorioVeiculo.mapper(veiculos);
        Fragment fragmento = FragmentoRelatorio.newInstance(mVeicRelatorio);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmento_relatorio, fragmento)
                .commit();
    }

    public List<Usuario> criarListaDeUsuarios() {
        Usuario u1 = new Usuario("Arthur", 22, "Masculino");
        Usuario u2 = new Usuario("Moises", 30, "Masculino");
        Usuario u3 = new Usuario("Vinicius", 23, "Masculino");
        return Arrays.asList(u1,u2,u3);
    }

    public List<Veiculo> criarListaDeVeiculos(){
        Veiculo v1 = new Veiculo("Civic","branco","2016","legalizado","honda");
        Veiculo v2 = new Veiculo("Corolla","preto","2015","ilegal","toyota");
        Veiculo v3 = new Veiculo("Sentra","prata","2014","legalizado","nissan");
        return Arrays.asList(v1,v2,v3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
