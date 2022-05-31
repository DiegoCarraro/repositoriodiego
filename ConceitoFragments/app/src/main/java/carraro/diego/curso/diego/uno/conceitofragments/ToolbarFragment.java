package carraro.diego.curso.diego.uno.conceitofragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class ToolbarFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    //Atributos de Classe
    private static int TEST_SIZE = 10;//tamanho inicial da fonte
    private Button btnAlterarTexto;
    private SeekBar skbFormatarTexto;
    private EditText edtInformarTexto;
    private ToolbarListener toolbarListener;


    //declarando interface
    public interface ToolbarListener{
        //Assinatura do método
        public void onButtonClick(int position,String texto);

    }

    //Construtor
    public ToolbarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View toolbarLayoutInflated = inflater.inflate(R.layout.fragment_tool_bar, container, false);

       btnAlterarTexto  = toolbarLayoutInflated.findViewById(R.id.btnAlterarTexto);
       skbFormatarTexto = toolbarLayoutInflated.findViewById(R.id.skbFormatarTexto);
       edtInformarTexto = toolbarLayoutInflated.findViewById(R.id.edtInformarTexto);

       btnAlterarTexto.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               buttonClicked(view);

           }
       });

       skbFormatarTexto.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)this);

       return toolbarLayoutInflated;
    }

    //Vincula com o main
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            toolbarListener = (ToolbarListener) context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                + "Obrigatório implementar a interface ToolbarListener");
        }
    }

    public void buttonClicked(View view){
        toolbarListener.onButtonClick(TEST_SIZE, edtInformarTexto.getText().toString());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        TEST_SIZE = progress;


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}