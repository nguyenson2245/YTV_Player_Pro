package com.techBMT.YTV_Player_Pro.activity2file;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.techBMT.YTV_Player_Pro.R;

import java.io.File;

public class PDfViewerActivity extends AppCompatActivity {

    public static final String PATH_PDF_FILE ="path_pdf_file";
    private PDFView pdfView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.techBMT.YTV_Player_Pro.R.layout.activity_pdf_viewer);

        pdfView = findViewById(R.id.pdf_viewer);
        findViewById(R.id.iv_back_pdf).setOnClickListener(v -> finish());
        initData();
    }
    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String path = bundle.getString(PATH_PDF_FILE);
            if (path!=null && !path.isEmpty()){
                pdfView.fromFile(new File(path)).load();
            }
        }
    }
}