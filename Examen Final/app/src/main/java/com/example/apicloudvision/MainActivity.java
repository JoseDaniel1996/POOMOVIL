package com.example.apicloudvision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.FaceAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.TextAnnotation;

import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.example.apicloudvision.capturarImagen.REQUEST_IMAGE_CAPTURE;

public class MainActivity extends AppCompatActivity {


    Button btnAnalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAnalizar  = (Button) findViewById(R.id.button7);

        Vision.Builder visionBuilder = new Vision.Builder(new NetHttpTransport(),
                new AndroidJsonFactory(), null);
        visionBuilder.setVisionRequestInitializer(new VisionRequestInitializer("AIzaSyD9C2XuS6fLgNii1uu64p8wgAm6N7TDQC4"));
        vision = visionBuilder.build();
        imagen = findViewById(R.id.imageView2);

    }

    ImageView imagen;
    public Vision vision;

    public void clik(View view) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                BitmapDrawable drawable = (BitmapDrawable) imagen.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                bitmap = scaleBitmapDown(bitmap, 1200);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
                byte[] imageInByte = stream.toByteArray();


                Image inputImage = new Image();
                inputImage.encodeContent(imageInByte);

                Feature desiredFeature = new Feature();
                desiredFeature.setType("LABEL_DETECTION");
                AnnotateImageRequest request = new AnnotateImageRequest();
                request.setImage(inputImage);
                request.setFeatures(Arrays.asList(desiredFeature));
                BatchAnnotateImagesRequest batchRequest = new BatchAnnotateImagesRequest();
                batchRequest.setRequests(Arrays.asList(request));


                request = new AnnotateImageRequest();
                request.setImage(inputImage);
                request.setFeatures(Arrays.asList(desiredFeature));
                batchRequest = new BatchAnnotateImagesRequest();
                batchRequest.setRequests(Arrays.asList(request));

                try {
                    Vision.Images.Annotate annotateRequest = vision.images().annotate(batchRequest);

                    annotateRequest.setDisableGZipContent(true);
                    BatchAnnotateImagesResponse batchResponse = annotateRequest.execute();


                    TextAnnotation text = batchResponse.getResponses().get(0).getFullTextAnnotation();
                    // return text.getText();


                    final String result = text.getText();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView imageDetail = (TextView) findViewById(R.id.textView2);
                            imageDetail.setText(result);
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public void clikcaptura(View view) {
        ItemImagen();
    }

    public void clikObjetos(View view) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                BitmapDrawable drawable = (BitmapDrawable) imagen.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                bitmap = scaleBitmapDown(bitmap, 1200);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
                byte[] imageInByte = stream.toByteArray();


                Image inputImage = new Image();
                inputImage.encodeContent(imageInByte);

                Feature desiredFeature = new Feature();
                desiredFeature.setType("WEB_DETECTION");
                AnnotateImageRequest request = new AnnotateImageRequest();
                request.setImage(inputImage);
                request.setFeatures(Arrays.asList(desiredFeature));
                BatchAnnotateImagesRequest batchRequest = new BatchAnnotateImagesRequest();
                batchRequest.setRequests(Arrays.asList(request));


                request = new AnnotateImageRequest();
                request.setImage(inputImage);
                request.setFeatures(Arrays.asList(desiredFeature));
                batchRequest = new BatchAnnotateImagesRequest();
                batchRequest.setRequests(Arrays.asList(request));

                try {
                    Vision.Images.Annotate annotateRequest = vision.images().annotate(batchRequest);

                    annotateRequest.setDisableGZipContent(true);
                    BatchAnnotateImagesResponse batchResponse = annotateRequest.execute();


                    //    TextAnnotation text = batchResponse.getResponses().get(0).getFullTextAnnotation();
                    // return text.getText();

                    StringBuilder message = new StringBuilder("I found these things:\n\n");


                    List<EntityAnnotation> labels = batchResponse.getResponses().get(0).getLabelAnnotations();

                    if (labels != null) {
                        for (EntityAnnotation label : labels) {
                            message.append(String.format(Locale.ENGLISH, "%.3f: %s", label.getScore(), label.getDescription()));
                            message.append("\n");
                        }
                    } else {
                        message.append("nothing");
                    }


                    final String result = message.toString();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView imageDetail = (TextView) findViewById(R.id.textView2);
                            imageDetail.setText(result);
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public void clikRostros(View view) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                BitmapDrawable drawable = (BitmapDrawable) imagen.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                bitmap = scaleBitmapDown(bitmap, 1200);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
                byte[] imageInByte = stream.toByteArray();

                Image inputImage = new Image();
                inputImage.encodeContent(imageInByte);

                Feature desiredFeature = new Feature();
                desiredFeature.setType("FACE_DETECTION");
                AnnotateImageRequest request = new AnnotateImageRequest();
                request.setImage(inputImage);
                request.setFeatures(Arrays.asList(desiredFeature));
                BatchAnnotateImagesRequest batchRequest = new BatchAnnotateImagesRequest();
                batchRequest.setRequests(Arrays.asList(request));


                request = new AnnotateImageRequest();
                request.setImage(inputImage);
                request.setFeatures(Arrays.asList(desiredFeature));
                batchRequest = new BatchAnnotateImagesRequest();
                batchRequest.setRequests(Arrays.asList(request));

                try {
                    Vision.Images.Annotate annotateRequest = vision.images().annotate(batchRequest);

                    annotateRequest.setDisableGZipContent(true);
                    BatchAnnotateImagesResponse batchResponse = annotateRequest.execute();


                    List<FaceAnnotation> faces = batchResponse.getResponses().get(0).getFaceAnnotations();
                    int numberOfFaces = faces.size();

                    String likelihoods = "";
                    for (int i = 0; i < numberOfFaces; i++) {
                        likelihoods += "\n ES" + faces.get(i).getJoyLikelihood() + " esa cara " + i + " es Feliz";
                    }

                    String message = "Esta foto tiene " + numberOfFaces + " Caras" + likelihoods;


                    final String result = message.toString();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView imageDetail = (TextView) findViewById(R.id.textView2);
                            imageDetail.setText(result);
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;
        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    private void ItemImagen() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);
        }
    }


}
