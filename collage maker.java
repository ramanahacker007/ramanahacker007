import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGES = 2;

    private Button selectImagesButton;
    private Button createCollageButton;
    private Button saveImageButton;
    private ImageView collageImageView;

    private List<Bitmap> selectedImages;
    private Bitmap collageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectImagesButton = findViewById(R.id.select_images_button);
        createCollageButton = findViewById(R.id.create_collage_button);
        saveImageButton = findViewById(R.id.save_image_button);
        collageImageView = findViewById(R.id.collage_image_view);

        selectImagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    openImageChooser();
                } else {
                    requestPermission();
                }
            }
        });
