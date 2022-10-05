package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    private CircleImageView image;
    private String s_fullname,s_email,s_pw,s_confirmpw,s_hp, s_about;
    private Button okBtn;
    private ImageView uploadBtn;
    private TextInputEditText fullname, email, password, confirmpw, homepage, about;
    private Uri imageUri;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.text_fullname);
        email = findViewById(R.id.text_email);
        password = findViewById(R.id.text_password);
        confirmpw = findViewById(R.id.text_confirm_password);
        homepage = findViewById(R.id.text_homepage);
        about = findViewById(R.id.text_about);
        okBtn = findViewById(R.id.button_ok);
        uploadBtn = findViewById(R.id.imageView);
        image = findViewById(R.id.image_profile);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_fullname = fullname.getText().toString();
                s_email = email.getText().toString();
                s_pw = password.getText().toString();
                s_confirmpw = confirmpw.getText().toString();
                s_hp = homepage.getText().toString();
                s_about = about.getText().toString();


                Intent pindah = new Intent(RegisterActivity.this,ProfileActivity.class);

                pindah.putExtra("key_fullname", s_fullname);
                pindah.putExtra("key_email", s_email);
                pindah.putExtra("key_pw", s_pw);
                pindah.putExtra("key_cpw", s_confirmpw);
                pindah.putExtra("key_hp", s_hp);
                pindah.putExtra("key_about", s_about);
                pindah.putExtra("key_image", imageUri.toString());

                startActivity(pindah);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(this, "cancel input",Toast.LENGTH_SHORT).show();
            return;
        } else if (requestCode == GALLERY_REQUEST_CODE){
            if (data != null){
                try {
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this,"can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}
