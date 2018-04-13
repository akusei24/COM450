package com.example.akusei.pruebaintents;

import android.text.Layout;
import android.view.View;

import junit.framework.TestCase;

/**
 * Created by Akusei on 30/05/2016.
 */
public class VideoActivityTest extends TestCase {
VideoActivity vtest=new VideoActivity();
    public void testUploadFile() throws Exception {
        vtest.uploadFile("/storage/emulated/0/WhatsApp/Media/WhatsApp Video/VID-20150324-WA0004.mp4");
    }
}