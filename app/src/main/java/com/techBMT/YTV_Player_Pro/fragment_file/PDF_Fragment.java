package com.techBMT.YTV_Player_Pro.fragment_file;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.techBMT.YTV_Player_Pro.R;
import com.techBMT.YTV_Player_Pro.activity2file.PDfViewerActivity;
import com.techBMT.YTV_Player_Pro.adapter.FileDocumentAdapter;
import com.techBMT.YTV_Player_Pro.itf.IFragmentParent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PDF_Fragment extends Fragment implements IFragmentParent {

    private static final String MANAGE_ALL_FILES_ACCESS_PERMISSION = "android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION";
    View view;
    FileDocumentAdapter pdfAdapter;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pdf, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        runtimePermission();
        if (Environment.isExternalStorageManager()) {
            readFileInBackground();
        } else {
            Intent intent = new Intent();
            intent.setAction(MANAGE_ALL_FILES_ACCESS_PERMISSION);
            startActivityForResult(intent, 9999);
        }
        displayPDF();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9999) {
            readFileInBackground();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void readFileInBackground() {
        Log.d("tagTest", "readFileInBackground");
        AsyncTask<Void, Void, ArrayList<File>> loadFile = new AsyncTask<Void, Void, ArrayList<File>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected ArrayList<File> doInBackground(Void... voids) {
                return getPDFFile(Environment.getExternalStorageDirectory().toString());
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(ArrayList<File> files) {
                super.onPostExecute(files);
                pdfAdapter.setData(files);
            }
        };
        loadFile.execute();
    }

    private ArrayList<File> getPDFFile(String path) {
        ArrayList<File> list = new ArrayList<>();
        File directory = new File(path);
        if (directory.isFile() && directory.getName().contains(".pdf")) {
            list.add(directory);
            return list;
        }
        File[] files = directory.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file != null && !file.isHidden()) {
                    list.addAll(getPDFFile(path + "/" + file.getName()));
                }
            }
        }
        return list;
    }

    private void runtimePermission() {
        Dexter.withContext(getContext()).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    public void displayPDF() {
        recyclerView = view.findViewById(R.id.recycleView_pdf);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pdfAdapter = new FileDocumentAdapter(getContext(), FileDocumentAdapter.DOCUMENT_TYPE.PDF);

        recyclerView.setAdapter(pdfAdapter);

        pdfAdapter.setOnClickListener(new FileDocumentAdapter.OnClickListener() {
            @Override
            public void onChooseItem(String path) {
                Intent intent = new Intent(getContext(), PDfViewerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(PDfViewerActivity.PATH_PDF_FILE,path);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public List<File> getDataFiles() {
        if (pdfAdapter != null) {
            return pdfAdapter.getFileList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void updateListSort(ArrayList<File> listSort) {
        pdfAdapter.setData(listSort);
    }


}

