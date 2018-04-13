package com.example.akusei.pruebaintents;

import junit.framework.TestCase;

/**
 * Created by Akusei on 30/05/2016.
 */
public class MusicActivityTest extends TestCase {
MusicActivity mtest=new MusicActivity();
    public void testUploadFile() throws Exception {
        mtest.uploadFile("/storage/emulated/0/WhatsApp/Media/WhatsApp Audio/Sent/AUD-20160331-WA0000.mp3");
    }
}