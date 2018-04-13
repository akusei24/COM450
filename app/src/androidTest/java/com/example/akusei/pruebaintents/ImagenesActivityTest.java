package com.example.akusei.pruebaintents;

import junit.framework.TestCase;

/**
 * Created by Akusei on 30/05/2016.
 */
public class ImagenesActivityTest extends TestCase {
    ImagenesActivity ImagesA =new ImagenesActivity();
    public void testUploadFile() throws Exception {
        ImagesA.uploadFile("\\storage/emulated/0/Download/CC_2588197_diferencias_a_la_vista_en_la_infancia.jpg");

    }
}