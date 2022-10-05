package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private TextView about, fullname, email, homepage;
    private Button viewHomepage;
    CircleImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String fn = getIntent().getExtras().getString("key_fullname");
        String em = getIntent().getExtras().getString("key_email");
        final String hp = getIntent().getExtras().getString("key_hp");
        String ab = getIntent().getExtras().getString("key_about");
        Uri bitmap = Uri.parse(getIntent().getExtras().getString("key_image"));

        viewHomepage = findViewById(R.id.button_homepage);
        fullname = findViewById(R.id.label_fullname);
        email = findViewById(R.id.label_email);
        homepage = findViewById(R.id.label_homepage);
        about = findViewById(R.id.label_about);
        profile = findViewById(R.id.image_profile);

        fullname.setText(fn);
        email.setText(em);
        homepage.setText(hp);
        about.setText(ab);
        profile.setImageURI(bitmap);

        viewHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink(hp);
            }
        });
    }

    private void goLink(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}
