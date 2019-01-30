package com.example.especialista.coroeb;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.os.Handler;

import com.eva.especialista.coroeb.R;

import java.util.ArrayList;
import java.util.List;
import fr.ganfra.materialspinner.MaterialSpinner;

//import static com.example.especialista.coroeb.R.*;

public class Practicar extends AppCompatActivity  {
// Bandera De sion

final String  PDFTODOS0="https://www.pdf-archive.com/2018/04/21/004-bandera-de-sion/004-bandera-de-sion.pdf";
final String PDFT0="https://www.pdf-archive.com/2018/04/21/004-tenor/004-tenor.pdf";
final String PDFS0="https://www.pdf-archive.com/2018/04/21/004-soprano/004-soprano.pdf";
final String PDFB0="https://www.pdf-archive.com/2018/04/21/004-bajo/004-bajo.pdf";
final String PDFA0="https://www.pdf-archive.com/2018/04/21/004-alto/004-alto.pdf";

// Fuente de mis Bendiciones
    final String  PDFTODOS1="https://www.pdf-archive.com/2018/04/16/fuente-de-mis-bendiciones/fuente-de-mis-bendiciones.pdf";
    final String PDFT1="https://www.pdf-archive.com/2018/04/16/fuente-de-bendiciones-tenor/fuente-de-bendiciones-tenor.pdf";
    final String PDFS1="https://www.pdf-archive.com/2018/04/16/fuente-de-bendiciones-soprano/fuente-de-bendiciones-soprano.pdf";
    final String PDFB1="https://www.pdf-archive.com/2018/04/16/fuente-de-bendiciones-bajo/fuente-de-bendiciones-bajo.pdf";
    final String PDFA1="https://www.pdf-archive.com/2018/04/16/fuente-de-bendiciones-alto/fuente-de-bendiciones-alto.pdf";


    //Creo en Cristo

    final String  PDFTODOS2="https://www.pdf-archive.com/2018/04/21/072-creo-en-cristo/072-creo-en-cristo.pdf";
    final String PDFT2="https://www.pdf-archive.com/2018/04/21/072-creo-en-cristo-tenor/072-creo-en-cristo-tenor.pdf";
    final String PDFS2="https://www.pdf-archive.com/2018/04/21/072-creo-en-cristo-soprano/072-creo-en-cristo-soprano.pdf";
    final String PDFB2="https://www.pdf-archive.com/2018/04/21/072-creo-en-cristo-bajo/072-creo-en-cristo-bajo.pdf";
    final String PDFA2="https://www.pdf-archive.com/2018/04/21/072-creo-en-cristo-alto/072-creo-en-cristo-alto.pdf";

    //oh creaciones del Señor

    final String  PDFTODOS3="https://www.pdf-archive.com/2018/04/21/031-oh-creaciones-del-senor/031-oh-creaciones-del-senor.pdf";
    final String PDFT3="https://www.pdf-archive.com/2018/04/21/031-tenor/031-tenor.pdf";
    final String PDFS3="https://www.pdf-archive.com/2018/04/21/031-soprano/031-soprano.pdf";
    final String PDFB3="https://www.pdf-archive.com/2018/04/22/031-bajo/031-bajo.pdf";
    final String PDFA3="https://www.pdf-archive.com/2018/04/21/031-alto/031-alto.pdf";

    //oh Si a Kolob

    final String  PDFTODOS4="https://www.pdf-archive.com/2018/04/22/si-a-kolob-piano-y-flauta-satb/si-a-kolob-piano-y-flauta-satb.pdf";
    final String PDFT4="https://www.pdf-archive.com/2018/04/22/si-a-kolob-tenor/si-a-kolob-tenor.pdf";
    final String PDFS4="https://www.pdf-archive.com/2018/04/22/si-a-kolob-soprano/si-a-kolob-soprano.pdf";
    final String PDFB4="https://www.pdf-archive.com/2018/04/22/si-a-kolob-bajo/si-a-kolob-bajo.pdf";
    final String PDFA4="https://www.pdf-archive.com/2018/04/22/si-a-kolob-alto/si-a-kolob-alto.pdf";

    //Oh Dios de Israel

    final String  PDFTODOS5="https://www.pdf-archive.com/2018/04/22/005-oh-dios-de-israel/005-oh-dios-de-israel.pdf";
    final String PDFT5="https://www.pdf-archive.com/2018/04/22/005-oh-dios-de-israel-tenor/005-oh-dios-de-israel-tenor.pdf";
    final String PDFS5="https://www.pdf-archive.com/2018/04/22/005-oh-dios-de-israel-soprano/005-oh-dios-de-israel-soprano.pdf";
    final String PDFB5="https://www.pdf-archive.com/2018/04/22/005-oh-dios-de-israel-bajo/005-oh-dios-de-israel-bajo.pdf";
    final String PDFA5="https://www.pdf-archive.com/2018/04/22/005-oh-dios-de-israel-alto/005-oh-dios-de-israel-alto.pdf";


    String pdfSoprano,pdfAlto, pdfTenor, pdfBajo, pdfTodos;


    LinearLayout linearLayoutVoces;
    LinearLayout linearLayoutReproductor;
    int swPausa=0;
    int swSoprano=0,swAlto=0,swTenor=0,swBajo=0,swPiano=0;
    int comportamientoPausarOIiniciar=0;
    Handler handler;
    Runnable runnable;
    int eleccionSpiner=-1;
    Button btnPlay,btnStop, btnPause,btnMas10,btnMenos10, btnSwSoprano, btnSwAlto, btnSwTenor, btnSwBajo, btnSwPiano,btnPDFSoprano, btnPDFAlto, btnPDFTenor, btnPDFBajo,btnPDFTodos;
    MediaPlayer mpSoprano,mpAlto, mpTenor,mpBajo,mpPiano,mpPrueba;
    MaterialSpinner spinner;
    List<String> listItems= new ArrayList<>();
    ArrayAdapter<String>  adapter;
    ImageButton imgButton;
    SeekBar seekBarBarraReproductor, seekBarVolSoprano, seekBarVolAlto, seekBarVolTenor,seekBarVolBajo, seekBarVolPiano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicar);
        handler= new Handler();
//casteo de elementos
        //Layouts
        linearLayoutVoces=(LinearLayout)findViewById(R.id.LinearLayoutVoces);
        linearLayoutReproductor=(LinearLayout)findViewById(R.id.LinearlayoutReproductor);
        btnPDFSoprano=(Button)findViewById(R.id.btnPDFSoprano);
        btnPDFAlto=(Button)findViewById(R.id.btnPDFAlto) ;
        btnPDFTenor=(Button)findViewById(R.id.btnPDFTenor);
        btnPDFBajo=(Button)findViewById(R.id.btnPDFBajo);
        btnPDFTodos=(Button)findViewById(R.id.btnPDFPiano);



        btnPlay=(Button)findViewById(R.id.btnPlay);
        btnPause=(Button)findViewById(R.id.btnPause);
        btnStop=(Button)findViewById(R.id.btnStop);
        btnMas10=(Button)findViewById(R.id.btnMas10);
        btnMenos10=(Button)findViewById(R.id.btnMenos10);
        btnSwSoprano=(Button)findViewById(R.id.btnSWSoprano);
        btnSwAlto=(Button)findViewById(R.id.btnSWAlto);
        btnSwTenor=(Button)findViewById(R.id.btnSWTenor);
        btnSwBajo=(Button)findViewById(R.id.btnSWBajo);
        btnSwPiano=(Button)findViewById(R.id.btnSWPiano);

        seekBarBarraReproductor=(SeekBar)findViewById(R.id.seekBarReproductor);
        seekBarVolSoprano=(SeekBar)findViewById(R.id.seekBarVolumenSoprano);
        seekBarVolAlto=(SeekBar)findViewById(R.id.seekBarVolumenAlto);
        seekBarVolTenor=(SeekBar)findViewById(R.id.seekBarVolumenTenor);
        seekBarVolBajo=(SeekBar)findViewById(R.id.seekBarVolumenBajo);
        seekBarVolPiano=(SeekBar)findViewById(R.id.seekBarVolumenPiano);
        imgButton = (ImageButton) findViewById(R.id.atrasPracticar);
        linearLayoutVoces.setVisibility(View.INVISIBLE);
        linearLayoutReproductor.setVisibility(View.INVISIBLE);
        setupListeners();
        preprarListenerControlesdeVolumen();
        swSoprano=0;swAlto=0;swTenor=0;swBajo=0;swPiano=0;
        btnSwSoprano.setBackgroundResource(R.drawable.off);
        spinner= (MaterialSpinner)findViewById(R.id.spinner);

        //color blanco para cabecera practicar
        spinner.setBaseColor(0xFFFFFFFF);
        imgButton.setColorFilter(0xFFFFFFFF);



PDFBtnListeners();

                imgButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mpSoprano!=null ){stopReproductores();}
                        Intent q = new Intent(Practicar.this, MainActivity.class);
                        startActivity(q);
                        finish();
                    }
                });



                initItems();
                spinner = (MaterialSpinner) findViewById(R.id.spinner);
                adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, listItems);
                adapter.setDropDownViewResource(R.layout.spinner_item);
                spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {

                            case -1:
                            linearLayoutVoces.setVisibility(View.INVISIBLE);
                            linearLayoutReproductor.setVisibility(View.INVISIBLE);
                            break;

                            case 0:
                               btnPlay.setEnabled(true);
                                eleccionSpiner = 0;
                                linearLayoutVoces.setVisibility(View.VISIBLE);
                                linearLayoutReproductor.setVisibility(View.VISIBLE);
                                seekBarBarraReproductor.setEnabled(false);
                               colorearSeekBars();


                                break;
                            case 1:
                                btnPlay.setEnabled(true);
                                eleccionSpiner = 1;
                                linearLayoutVoces.setVisibility(View.VISIBLE);
                                linearLayoutReproductor.setVisibility(View.VISIBLE);
                                seekBarBarraReproductor.setEnabled(false);
                               colorearSeekBars();

                                break;
                            case 2:
                                btnPlay.setEnabled(true);
                                eleccionSpiner = 2;
                                linearLayoutVoces.setVisibility(View.VISIBLE);
                                linearLayoutReproductor.setVisibility(View.VISIBLE);
                                seekBarBarraReproductor.setEnabled(false);
                                colorearSeekBars();

                                break;
                            case 3:
                                btnPlay.setEnabled(true);
                                eleccionSpiner = 3;
                                linearLayoutVoces.setVisibility(View.VISIBLE);
                                linearLayoutReproductor.setVisibility(View.VISIBLE);
                                seekBarBarraReproductor.setEnabled(false);
                                colorearSeekBars();

                                break;
                            case 4:
                                btnPlay.setEnabled(true);
                                eleccionSpiner = 4;
                                linearLayoutVoces.setVisibility(View.VISIBLE);
                                linearLayoutReproductor.setVisibility(View.VISIBLE);
                                seekBarBarraReproductor.setEnabled(false);
                                colorearSeekBars();

                                break;
                            case 5:
                                btnPlay.setEnabled(true);
                                eleccionSpiner = 5;
                                linearLayoutVoces.setVisibility(View.VISIBLE);
                                linearLayoutReproductor.setVisibility(View.VISIBLE);
                                seekBarBarraReproductor.setEnabled(false);
                                colorearSeekBars();

                                break;
                        }
                        comportamientoPausarOIiniciar=0;
                        prepararPathPDF();
                        apagarVoces();
                        if(mpSoprano!=null) {
                            stopReproductores();
                            deshabilitarBotones();
                            btnPlay.setEnabled(true);
                            seekBarBarraReproductor.setProgress(0);
                        colorearSeekBars();

                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

    @Override
    public void onBackPressed()
    {
        if(mpSoprano!=null ){stopReproductores();}
        Intent q = new Intent(this, MainActivity.class);
        startActivity(q);
      finish();
        super.onBackPressed();  // Invoca al método
    }

private void PDFBtnListeners(){



    btnPDFSoprano.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setDataAndType(Uri.parse(pdfSoprano), "application/pdf");
            startActivity(intent);
        }
    });


  btnPDFAlto.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent = new Intent();
          intent.setDataAndType(Uri.parse(pdfAlto), "application/pdf");
          startActivity(intent);
      }
  });

  btnPDFTenor.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent = new Intent();
          intent.setDataAndType(Uri.parse(pdfTenor), "application/pdf");
          startActivity(intent);
      }
  });

  btnPDFBajo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent = new Intent();
          intent.setDataAndType(Uri.parse(pdfBajo), "application/pdf");
          startActivity(intent);
      }
  });

  btnPDFTodos.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent intent = new Intent();
          intent.setDataAndType(Uri.parse(pdfTodos), "application/pdf");
          startActivity(intent);
      }
  });

}


         private void cycleActualizadorBarraReproductor(){
                seekBarBarraReproductor.setProgress(mpSoprano.getCurrentPosition());
                if(mpSoprano.isPlaying()){
                    runnable =  new Runnable() {
                        @Override
                        public void run() {
                            if(mpSoprano.getCurrentPosition()>mpSoprano.getDuration()-20){
                               deshabilitarBotones();
                                stopReproductores();
                               btnPlay.setEnabled(true);
                            }
                                cycleActualizadorBarraReproductor();
                        }
                    };
                    handler.postDelayed(runnable,10);
                }

            }


    private void deshabilitarBotones() {
        btnPlay.setEnabled(false);
        btnStop.setEnabled(false);
        btnMenos10.setEnabled(false);
        btnPause.setEnabled(false);
        btnMas10.setEnabled(false);
    }

    private void initItems() {
        listItems.add("1. Bandera de Sión");
        listItems.add("2. Fuente de mis Bendiciones");
        listItems.add("3. Creo en Cristo");
        listItems.add("4. Oh, creaciones del Señor");
        listItems.add("5. Si a Kolob");
        listItems.add("6. Oh Dios de Israel");




            }

    private void setupListeners(){

             ///////////////////////////////////////////////////////////////////////aca crear los listeners de los SW ON/OFF


        btnMas10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seekToReproductores(mpSoprano.getCurrentPosition()+10000);


            }
        });

        btnMenos10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekToReproductores(mpSoprano.getCurrentPosition()-10000);
            }
        });

        btnSwSoprano.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                if(swSoprano==0){
                    seekBarVolSoprano.setProgress(80);
                    btnSwSoprano.setBackgroundResource(R.drawable.on);
                    if(mpSoprano!=null){
                        mpSoprano.setVolume(0.8f,0.8f);
                    }
                    swSoprano=1;
                }else{
                    seekBarVolSoprano.setProgress(0);
                    btnSwSoprano.setBackgroundResource(R.drawable.off);
                    if(mpSoprano!=null){
                        mpSoprano.setVolume(0,0);
                    }
                   // volSoprano=0;
                    swSoprano=0;
                }
            }
        });

        btnSwAlto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(swAlto==0){
                    seekBarVolAlto.setProgress(80);
                    btnSwAlto.setBackgroundResource(R.drawable.on);
                    if(mpAlto!=null){
                        mpAlto.setVolume(0.8f,0.8f);
                    }
                    //volAlto=80f;
                    swAlto=1;
                }else{
                    seekBarVolAlto.setProgress(0);
                    btnSwAlto.setBackgroundResource(R.drawable.off);
                    if(mpAlto!=null){
                        mpAlto.setVolume(0,0);
                    }
                   // volAlto=0;
                    swAlto=0;
                }
            }
        });

        btnSwTenor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swTenor==0){

                    seekBarVolTenor.setProgress(80);
                    btnSwTenor.setBackgroundResource(R.drawable.on);
                    if(mpTenor!=null){
                        mpTenor.setVolume(0.8f,0.8f);
                    }
                    //volTenor=80f;
                    swTenor=1;
                }else{
                    btnSwTenor.setBackgroundResource(R.drawable.off);
                    seekBarVolTenor.setProgress(0);

                    if(mpTenor!=null){
                        mpTenor.setVolume(0,0);
                    }
                    swTenor=0;
                }

            }
        });

        btnSwBajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swBajo==0){
                    btnSwBajo.setBackgroundResource(R.drawable.on);
                    seekBarVolBajo.setProgress(80);

                    if(mpBajo!=null){
                        mpBajo.setVolume(0.8f,0.8f);
                    }
                   // volBajo=80f;
                    swBajo=1;
                }else{
                    btnSwBajo.setBackgroundResource(R.drawable.off);
                    seekBarVolBajo.setProgress(0);
                    if(mpBajo!=null){
                        mpBajo.setVolume(0,0);
                    }
                    swBajo=0;
                }
            }
        });

        btnSwPiano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swPiano==0){
                    btnSwPiano.setBackgroundResource(R.drawable.on);
                    seekBarVolPiano.setProgress(50);
                    if(mpPiano!=null){
                        mpPiano.setVolume(0.8f,0.8f);
                    }
                   // volPiano=80f;
                    swPiano=1;
                }else{
                    btnSwPiano.setBackgroundResource(R.drawable.off);
                    seekBarVolPiano.setProgress(0);;
                    if(mpPiano!=null){
                        mpPiano.setVolume(0,0);
                    }
                    swPiano=0;
                }
            }
        });


                btnPlay.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(swSoprano==0 && swAlto==0 && swTenor==0 && swBajo==0 && swPiano==0 ){
                            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Practicar.this);
                            dialogo1.setTitle("Instrucción");
                            dialogo1.setMessage("Se debe encender al menos una voz antes de iniciar la reproducción");
                            dialogo1.setCancelable(true);
                            dialogo1.setIcon(R.drawable.onmensaje);
                            dialogo1.setPositiveButton("Entendido", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which){
                                }

                            });
                            //dialogo1.setIcon(drawable.ic_menu_slideshow);
                            dialogo1.create();
                            dialogo1.show();


                        }else{
    if (comportamientoPausarOIiniciar == 0) {




        crearReproductores();


        /////////////////////////////////////////////el mpSoprano debe estar preprado

        prepararBarraReproductor();
        playReproductores();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        comportamientoPausarOIiniciar = 1;
        btnPlay.setEnabled(false);
        seekBarBarraReproductor.setEnabled(true);

    } else{
        prepararBarraReproductor();
        playReproductores();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        cycleActualizadorBarraReproductor();
        seekBarBarraReproductor.setEnabled(true);
    }

    swPausa=0;
    setVolumenAReproductores();
    habilitarBotones();
    btnPlay.setEnabled(false);
                        colorearSeekBars();

                        }
}
                });

                btnPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pauseReproductores();
                        comportamientoPausarOIiniciar=1;
                        btnPlay.setEnabled(true);
                        cycleActualizadorBarraReproductor();
                        seekBarBarraReproductor.setProgress(mpSoprano.getCurrentPosition());
                        swPausa=1;
                    }
                });

                btnStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      stopReproductores();
                        btnPlay.setEnabled(true);
                        seekBarBarraReproductor.setProgress(0);
                     deshabilitarBotones();
                      btnPlay.setEnabled(true);
                    }
                });

                seekBarBarraReproductor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                          if (fromUser){
                              mpSoprano.seekTo(progress);
                              cycleActualizadorBarraReproductor();
                              colorearSeekBars();
                          }
                        }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar)
                    {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar)
                    {}
                });
            }

    private void habilitarBotones() {
        btnPlay.setEnabled(true);
        btnStop.setEnabled(true);
        btnMenos10.setEnabled(true);
        btnPause.setEnabled(true);
        btnMas10.setEnabled(true);
    }

    private void crearReproductores(){
    switch(eleccionSpiner){
        case 0:
            mpSoprano=MediaPlayer.create(this, R.raw.s0);
            mpAlto=MediaPlayer.create(this, R.raw.a0);
            mpTenor=MediaPlayer.create(this, R.raw.t0);
            mpBajo=MediaPlayer.create(this, R.raw.b0);
            mpPiano=MediaPlayer.create(this, R.raw.piano0);
            setVolumenAReproductores();
            break;
        case 1:
            mpSoprano=MediaPlayer.create(this, R.raw.s1);
            mpAlto=MediaPlayer.create(this, R.raw.a1);
            mpTenor=MediaPlayer.create(this, R.raw.t1);
            mpBajo=MediaPlayer.create(this, R.raw.b1);
            mpPiano=MediaPlayer.create(this, R.raw.todos1);
            setVolumenAReproductores();
            break;
        case 2:
            mpSoprano=MediaPlayer.create(this, R.raw.s2);
            mpAlto=MediaPlayer.create(this, R.raw.a2);
            mpTenor=MediaPlayer.create(this, R.raw.t2);
            mpBajo=MediaPlayer.create(this, R.raw.b2);
            mpPiano=MediaPlayer.create(this, R.raw.piano2);
            setVolumenAReproductores();
            break;
        case 3:
            mpSoprano=MediaPlayer.create(this, R.raw.s3);
            mpAlto=MediaPlayer.create(this, R.raw.a3);
            mpTenor=MediaPlayer.create(this, R.raw.t3);
            mpBajo=MediaPlayer.create(this, R.raw.b3);
            mpPiano=MediaPlayer.create(this, R.raw.piano3);
            setVolumenAReproductores();
            break;
        case 4:
            mpSoprano=MediaPlayer.create(this, R.raw.s4);
            mpAlto=MediaPlayer.create(this, R.raw.a4);
            mpTenor=MediaPlayer.create(this, R.raw.t4);
            mpBajo=MediaPlayer.create(this, R.raw.b4);
            mpPiano=MediaPlayer.create(this, R.raw.piano4);
            setVolumenAReproductores();

            break;case 5:
            mpSoprano=MediaPlayer.create(this, R.raw.s5);
            mpAlto=MediaPlayer.create(this, R.raw.a5);
            mpTenor=MediaPlayer.create(this, R.raw.t5);
            mpBajo=MediaPlayer.create(this, R.raw.b5);
            mpPiano=MediaPlayer.create(this, R.raw.piano5);
            setVolumenAReproductores();
            break;
    }
}

private void playReproductores(){
    mpSoprano.start();
    mpAlto.start();
    mpTenor.start();
    mpBajo.start();
    mpPiano.start();
    setVolumenAReproductores();

    }


private void pauseReproductores(){
    setVolumenAReproductores();
    mpSoprano.pause();
    mpAlto.pause();
    mpTenor.pause();
    mpBajo.pause();
    mpPiano.pause();
}

private void stopReproductores(){
    mpSoprano.stop();
    mpAlto.stop();
    mpTenor.stop();
    mpBajo.stop();
    mpPiano.stop();
    mpSoprano.release();
    mpAlto.release();
    mpTenor.release();
    mpBajo.release();
    mpPiano.release();
    crearReproductores();
    setVolumenAReproductores();

}

private void prepararBarraReproductor(){
    mpSoprano.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            cycleActualizadorBarraReproductor();
        }
    });
    seekBarBarraReproductor.setMax(mpSoprano.getDuration());
    colorearSeekBars();
    seekBarBarraReproductor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){

                seekToReproductores(progress);
                setVolumenAReproductores();
                cycleActualizadorBarraReproductor();

            }
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { setVolumenAReproductores();}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    });
}

private void seekToReproductores(int progress){
    swPausa=0;
    habilitarBotones();
    btnPlay.setEnabled(false);
    stopReproductores();
    crearReproductores();
    prepararBarraReproductor();
    mpSoprano.seekTo(progress);
    mpAlto.seekTo(progress);
    mpTenor.seekTo(progress);
    mpBajo.seekTo(progress);
    mpPiano.seekTo(progress);
    mpSoprano.start();
    mpAlto.start();
    mpTenor.start();
    mpBajo.start();
    mpPiano.start();
    }

    private void preprarListenerControlesdeVolumen(){

    seekBarVolSoprano.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            float p= (float) (progress*0.01);
            if(fromUser){
                if(mpSoprano!=null){
                    mpSoprano.setVolume(p,p);
                }
              //  volSoprano=p;
                if(progress==0){
                    btnSwSoprano.setBackgroundResource(R.drawable.off);

                    swSoprano=0;
                }else{
                    btnSwSoprano.setBackgroundResource(R.drawable.on);
                    swSoprano=1;
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });

        seekBarVolAlto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float p= (float) (progress*0.01);
                if(fromUser){
                    if(mpAlto!=null){
                        mpAlto.setVolume(p,p);
                    }
                    //volAlto=p;
                    if(progress==0){
                        btnSwAlto.setBackgroundResource(R.drawable.off);
                        swAlto=0;
                    }else{
                        btnSwAlto.setBackgroundResource(R.drawable.on);
                        swAlto=1;
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarVolTenor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float p= (float) (progress*0.01);
                if(fromUser){
                    if(mpTenor!=null){
                        mpTenor.setVolume(p,p);
                    }

                    if(progress==0){
                        btnSwTenor.setBackgroundResource(R.drawable.off);
                        swTenor=0;
                    }else{
                        btnSwTenor.setBackgroundResource(R.drawable.on);
                        swTenor=1;
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarVolBajo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float p= (float) (progress*0.01);
                if(fromUser){
                    if(mpBajo!=null){
                        mpBajo.setVolume(p,p);
                    }

                    if(progress==0){
                        btnSwBajo.setBackgroundResource(R.drawable.off);
                        swBajo=0;
                    }else{
                        btnSwBajo.setBackgroundResource(R.drawable.on);
                        swBajo=1;
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarVolPiano.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float p= (float) (progress*0.01);
                if(fromUser){
                    if(mpPiano!=null){
                        mpPiano.setVolume(p,p);
                    }

                    if(progress==0){
                        btnSwPiano.setBackgroundResource(R.drawable.off);
                        swPiano=0;
                    }else{
                        btnSwPiano.setBackgroundResource(R.drawable.on);
                        swPiano=1;
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setVolumenAReproductores(){

        float s= (float) (seekBarVolSoprano.getProgress()*0.01);
        float a= (float) (seekBarVolAlto.getProgress()*0.01);
        float t= (float) (seekBarVolTenor.getProgress()*0.01);
        float b= (float) (seekBarVolBajo.getProgress()*0.01);
        float p= (float) (seekBarVolPiano.getProgress()*0.01);
        mpSoprano.setVolume(s,s);
        mpAlto.setVolume(a,a);
        mpTenor.setVolume(t,t);
        mpBajo.setVolume(b,b);
        mpPiano.setVolume(p,p);
    }

    private void apagarVoces(){
        btnSwSoprano.setBackgroundResource(R.drawable.off);
        btnSwAlto.setBackgroundResource(R.drawable.off);
        btnSwTenor.setBackgroundResource(R.drawable.off);
        btnSwBajo.setBackgroundResource(R.drawable.off);
        btnSwPiano.setBackgroundResource(R.drawable.off);
        swSoprano=0;
        swAlto=0;
        swTenor=0;
        swBajo=0;
        swPiano=0;
        seekBarVolSoprano.setProgress(0);
        seekBarVolAlto.setProgress(0);
        seekBarVolTenor.setProgress(0);
        seekBarVolBajo.setProgress(0);
        seekBarVolPiano.setProgress(0);
    }


    private void prepararPathPDF(){

        if(eleccionSpiner==0){
            pdfSoprano=PDFS0;
            pdfAlto=PDFA0;
            pdfTenor=PDFT0;
            pdfBajo=PDFB0;
            pdfTodos=PDFTODOS0;
        }
        if(eleccionSpiner==1){
            pdfSoprano=PDFS1;
            pdfAlto=PDFA1;
            pdfTenor=PDFT1;
            pdfBajo=PDFB1;
            pdfTodos=PDFTODOS1;
        }
        if(eleccionSpiner==2){
            pdfSoprano=PDFS2;
            pdfAlto=PDFA2;
            pdfTenor=PDFT2;
            pdfBajo=PDFB2;
            pdfTodos=PDFTODOS2;
        }
        if(eleccionSpiner==3){
            pdfSoprano=PDFS3;
            pdfAlto=PDFA3;
            pdfTenor=PDFT3;
            pdfBajo=PDFB3;
            pdfTodos=PDFTODOS3;
        }
        if(eleccionSpiner==4){
            pdfSoprano=PDFS4;
            pdfAlto=PDFA4;
            pdfTenor=PDFT4;
            pdfBajo=PDFB4;
            pdfTodos=PDFTODOS4;
        }
        if(eleccionSpiner==5){
            pdfSoprano=PDFS5;
            pdfAlto=PDFA5;
            pdfTenor=PDFT5;
            pdfBajo=PDFB5;
            pdfTodos=PDFTODOS5;
        }
    }


 private void colorearSeekBars(){

     seekBarVolPiano.getProgressDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
     seekBarVolPiano.getThumb().setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_IN);
     seekBarVolSoprano.getProgressDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
     seekBarVolSoprano.getThumb().setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_IN);
     seekBarVolAlto.getProgressDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
     seekBarVolAlto.getThumb().setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_IN);
     seekBarVolTenor.getProgressDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
     seekBarVolTenor.getThumb().setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_IN);
     seekBarVolBajo.getProgressDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
     seekBarVolBajo.getThumb().setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_IN);
     seekBarBarraReproductor.getProgressDrawable().setColorFilter(Color.parseColor("#fffcb1"), PorterDuff.Mode.SRC_IN);
     seekBarBarraReproductor.getThumb().setColorFilter(Color.parseColor("#fffcb1"), PorterDuff.Mode.SRC_IN);

 }
}





