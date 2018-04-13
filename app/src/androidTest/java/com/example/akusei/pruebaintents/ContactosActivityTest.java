package com.example.akusei.pruebaintents;

import android.app.Instrumentation;

import junit.framework.TestCase;

/**
 * Created by Akusei on 29/05/2016.
 */
public class ContactosActivityTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
        //Instrumentation.class.getSuperclass();

    }

    public void tearDown() throws Exception {

    }

    public void testDatosContactos() throws Exception {
        ContactosActivity Contactos = new ContactosActivity();
    //assertThat(ContactosActivity.datosContactos());
        Contactos.datosContactos();
        assertNotNull(Contactos.pathcontactos);


    }

    public void testUploadFile() throws Exception {

    }
}