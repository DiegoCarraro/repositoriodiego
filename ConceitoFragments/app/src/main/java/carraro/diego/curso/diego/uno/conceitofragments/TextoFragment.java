package carraro.diego.curso.diego.uno.conceitofragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TextoFragment extends Fragment {
    private TextView tvTexto;

    public TextoFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View textoFragmentLayoutInflated = inflater.inflate(R.layout.fragment_texto, container, false);

        tvTexto = textoFragmentLayoutInflated.findViewById(R.id.tvTexto);

        return textoFragmentLayoutInflated;
    }

    public void trocarPropriedadeDoTexto(int tamanhoFonte, String texto) {
        tvTexto.setTextSize(tamanhoFonte);
        tvTexto.setText(texto);
    }
}