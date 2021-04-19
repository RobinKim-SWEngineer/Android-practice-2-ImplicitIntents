package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editTextWebSite;
    EditText editTextLocation;
    EditText editTextSharingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWebSite = findViewById(R.id.editText_webSite);
        editTextLocation = findViewById(R.id.editText_location);
        editTextSharingText = findViewById(R.id.editText_sharingText);
    }

    public void openWebsite(View view) {
        String webPageString = editTextWebSite.getText().toString();
        Uri webPageUrl = Uri.parse(webPageString);

        /**
         *  Unlike explicit intent that needs current context and target activity specified,
         *  implicit intent requires the type of action and data for that action.
         */
        Intent viewingWebPage = new Intent(Intent.ACTION_VIEW, webPageUrl);

        /**
         *  System checks if any activity is available that can handle the implicit intent.
         *  It needs PackageManger instance for investigation, which is matching Intent action and data
         *  with the Intent filters for the app in the Manifest.xml file.
         */

        if (viewingWebPage.resolveActivity(getPackageManager()) != null) {
            startActivity(viewingWebPage);
        } else {
            Log.d("ImplicitIntents","Couldn't find any qualified activity to handle this intent");
        }

//        try {
//            startActivity(viewingWebPage);
//        } catch (ActivityNotFoundException exception) {
//            Log.d("ImplicitIntents", exception.getMessage());
//        }
    }

    public void openLocation(View view) {
        String locationString = editTextLocation.getText().toString();
        Uri locationUri = Uri.parse("geo:0,0?q=" + locationString);

        Intent viewingLocation = new Intent(Intent.ACTION_VIEW, locationUri);

        if (viewingLocation.resolveActivity(getPackageManager()) != null) {
            startActivity(viewingLocation);
        } else {
            Log.d("ImplicitIntents", "Couldn't find any qualified activity to handle this intent");
        }

//        try {
//            startActivity(viewingLocation);
//        } catch (ActivityNotFoundException exception) {
//            Log.d("ImplicitIntents", exception.getMessage());
//        }
    }

    public void shareText(View view) {
        String sharingString = editTextSharingText.getText().toString();

        /**
         *  ShareCompat.IntentBuilder is a helper class for constructing sharing intents ( Intent.ACTION_SEND / ACTION_SEND_MULTIPLE ).
         *  ShareCompat.IntentBuilder.from(Activity launchingActivity) is a static method, which returns a IntentBuilder instance.
         *  The rest of methods are public ones, which are called upon the IntentBuilder instance.
         */
        ShareCompat.IntentBuilder.from(this)
                                 .setType("text/plain")
                                 .setChooserTitle("")
                                 .setText(sharingString)
                                 .startChooser();
    }
}