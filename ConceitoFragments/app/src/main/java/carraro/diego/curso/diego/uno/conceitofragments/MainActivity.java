package carraro.diego.curso.diego.uno.conceitofragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ToolbarFragment.ToolbarListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }


    @Override
    public void onButtonClick(int tamanhoFonte, String texto) {
        TextoFragment tvTexto = (TextoFragment) getSupportFragmentManager().findFragmentById(R.id.frgtextoFragment);
        tvTexto.trocarPropriedadeDoTexto(tamanhoFonte, texto);
    }


}