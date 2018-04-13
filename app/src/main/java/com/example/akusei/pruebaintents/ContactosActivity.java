package com.example.akusei.pruebaintents;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class ContactosActivity extends AppCompatActivity {

    boolean sdAccesoEscritura=false;
    boolean sdDisponible=false;



    public String pathcontactos=null;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final int PERMISSIONS_WRITE_EXTERNAL_STORAGE=1;
    private static String[] PERMISSIONS_READ_CONTACTS = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        int permission2=ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission3=ActivityCompat.checkSelfPermission(activity,Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED || permission2!=PackageManager.PERMISSION_GRANTED || permission3!=PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_READ_CONTACTS,
                    PERMISSIONS_REQUEST_READ_CONTACTS
            );
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //txt=(TextView)findViewById(R.id.textView);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    }
    public void datosContactos(){

        String[] projeccion = new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE };
        String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor c = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projeccion,
                selectionClause,
                null,
                sortOrder);

        //txt.setText("");
        String sto= Environment.getExternalStorageState();
        if(sto.equals(Environment.MEDIA_MOUNTED)){
            sdDisponible=true;
            sdAccesoEscritura=true;
        }
        else if(sto.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
            sdDisponible=true;
            sdAccesoEscritura=false;
        }
        if(sdDisponible && sdAccesoEscritura)
            try
            {
                File ruta_sd=Environment.getExternalStorageDirectory();
                File f =new File(ruta_sd.getAbsolutePath(),"Contactos.vcf");
                if(f.exists()){
                    f.delete();
                    f=new File(ruta_sd.getAbsolutePath(),"Contactos.vcf");
                }
                System.out.println("Crea Archivo");
                OutputStreamWriter fout=new OutputStreamWriter(new FileOutputStream(f));
                fout.write("ID,Nombre,Numero,Tipo\n");
                System.out.println("LLenando Archivo...");
                //llenar archivo

                while(c.moveToNext()){
                    //txt.append("Identificador: " + c.getString(0) + " Nombre: " + c.getString(1) + " Número: " + c.getString(2)+  " Tipo: " + c.getString(3)+"\n");
                    fout.write(c.getString(0) + "," + c.getString(1) + "," + c.getString(2) + "," + c.getString(3) + "\n");
                }
                System.out.println("Archivo LLeno");
                c.close();
                fout.close();
                // System.out.println(ruta_sd.getAbsolutePath()+"/Contactos.csv");
                pathcontactos=ruta_sd.getAbsolutePath()+"/Contactos.vcf";
            }catch(Exception e)
            {
                Log.e("error: ", "Petó no escribió el archivo\n" + e);
            }
        uploadFile(pathcontactos);
    }
    public String getPathcontactos() {
        return pathcontactos;
    }
    public void obtenerdatos(View v){
        verifyStoragePermissions(this);
        datosContactos();

    }
    public void uploadFile(String filename){
        ProgressDialog pdialog=new ProgressDialog(this);
        pdialog.setMessage("Subiendo Archivo");
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdialog.setIndeterminate(true);
        pdialog.show();
        System.out.println(filename);
        verifyStoragePermissions(this);
        try {
            System.out.println("Aun no peta try de FileNotFound");
            FileInputStream fis = new FileInputStream(filename);
            System.out.println("No petó creado el file");
            HttpFileUploader htfu = new HttpFileUploader("http://192.168.43.197/soft3/upload.php","noparams", filename);
            System.out.println("No puedo creer que no petara llamado el ws");
            htfu.doStart(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Toast.makeText(MainActivity.this,"Enviando Archivo...",Toast.LENGTH_SHORT).show();

        //Toast.makeText(MainActivity.this, Resultados.respuesta, Toast.LENGTH_LONG).show();
        //pdialog.hide();
    }
}
