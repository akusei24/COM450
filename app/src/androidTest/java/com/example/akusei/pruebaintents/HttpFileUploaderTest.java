package com.example.akusei.pruebaintents;

import junit.framework.TestCase;

/**
 * Created by Akusei on 30/05/2016.
 */
public class HttpFileUploaderTest extends TestCase {

    public void testthirdTry() throws Exception {
        HttpFileUploader hhtp=new HttpFileUploader("192.168.1.3","sin prams","storage/emulated/0/Download/CC_2588197_diferencias_a_la_vista_en_la_infancia.jpg");
    assertNotNull(hhtp.fileName) ;
    }


}