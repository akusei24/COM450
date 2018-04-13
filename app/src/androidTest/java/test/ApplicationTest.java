package test;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.akusei.pruebaintents.ContactosActivity;
import com.example.akusei.pruebaintents.HttpFileUploader;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

    }

    public void test_prueba(){
        assertTrue(true);
    }

}