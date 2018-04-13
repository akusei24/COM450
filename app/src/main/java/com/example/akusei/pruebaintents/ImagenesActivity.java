package com.example.akusei.pruebaintents;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ImagenesActivity extends AppCompatActivity {
    private static int CODE_IMAGEN1 = 0;
    private String path_imagen1=null;
    private String[] nombreI;
    private ArrayList<String> paths=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);

    }
    public void seleccionarImagen1 (View view) {
        //Intent a galeria de imagenes para seleccionar una
        // Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        //startActivityForResult(intent, CODE_IMAGEN1);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), CODE_IMAGEN1);
        System.out.println("Entrando al if de intent ");


    }

    /*public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Files.FileColumns.MEDIA_TYPE };
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }*/

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
    // * @param context The context.
     //* @param uri The Uri to query.
     * //@param selection (Optional) Filter used in the query.
     * //@param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     *
    */
    /*
    * protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case SELECT_PHOTOS_RESULT:
                    //how to get the Uris?
                    ...
                    break;

        }

    }
    *
    *
    *
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {

            //Se obtiene la uri de la imagen seleccionada
            //  Uri selectedImage = data.getData();
            //   String ruta = getPath(selectedImage);//selectedImage.getPath();

            //Se coloca en el Textview que corresponda
            switch (requestCode)
            {
                case 0:
                    TextView tv_imagen1 = (TextView)findViewById(R.id.textView1);
                    //tv_imagen1.setText(ruta);
                    // nombreI=ruta.split("/");
                   /* path_imagen1 = ruta;
                    System.out.println("RUTA: "+ path_imagen1   );*/
                    if (intent != null) {
                        ClipData clipData = intent.getClipData();
                        System.out.println("Clip Data");
                        if (clipData != null) {
                            System.out.println("Clip data not NULL");
                            for (int i = 0; i < clipData.getItemCount(); i++) {
                                ClipData.Item item = clipData.getItemAt(i);
                                Uri uri = item.getUri();
                                System.out.println("Uri: "+ uri.toString());
                                System.out.println("URIS");
                                //In case you need image's absolute path
                                String path= GetPathUris.getPath(getApplicationContext(), uri);
                                System.out.println(path);
                                paths.add(path);

                            }
                        }
                        else{
                            System.out.println("ClipData Null"+intent.getData().toString());
                            String path=GetPathUris.getPath(getApplicationContext(),intent.getData());
                            System.out.println("Path cuando ClipData Null"+path);
                            paths.add(path);
                        }
                    }
                    break;

            }
        }

    }
    public String getPath(Uri uri) {

        System.out.println("Valisar que uri no este vacia");
        if( uri == null ) {
            return null;
        }

        System.out.println("Obtener path completo de la imagen seleccionada");
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        System.out.println("Cursor diferente de null");
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            System.out.println("cursor.getString()= "+cursor.getString(column_index) );
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }
    public void uploadFile(String filename){
        System.out.println(filename);
        ProgressDialog pdialog=new ProgressDialog(this);
        pdialog.setMessage("Subiendo Archivo");
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdialog.setProgress(5);
        pdialog.dismiss();

      //  pdialog.setIndeterminate(true);
        pdialog.show();
        try {
            System.out.println("Aun no peta try de FileNotFound");
            FileInputStream fis = new FileInputStream(filename);
            System.out.println("No petó creado el file");
            HttpFileUploader htfu = new HttpFileUploader("http://192.168.43.197/soft3/upload.php","noparamshere", filename);
            System.out.println("No puedo creer que no petara llamado el ws");
            htfu.doStart(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void enviar(View view){
        for(int i=0;i<paths.size();i++){
            uploadFile(paths.get(i));
            System.out.println("paths de: "+i+" = "+ paths.get(i));
        }
        paths.clear();
    }

}
