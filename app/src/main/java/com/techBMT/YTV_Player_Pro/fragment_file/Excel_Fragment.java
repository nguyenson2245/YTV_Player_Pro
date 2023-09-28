package com.techBMT.YTV_Player_Pro.fragment_file;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.techBMT.YTV_Player_Pro.BuildConfig;
import com.techBMT.YTV_Player_Pro.R;
import com.techBMT.YTV_Player_Pro.adapter.FileDocumentAdapter;
import com.techBMT.YTV_Player_Pro.itf.IFragmentParent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Excel_Fragment extends Fragment  implements IFragmentParent {

    private static final String MANAGE_ALL_FILES_ACCESS_PERMISSION = "android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION";
    View view;
    FileDocumentAdapter pdfAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_excel, container, false);
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
        if (directory.isFile() && directory.getName().contains(".xlsx")) {
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
        recyclerView = view.findViewById(R.id.recycleView_excel);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pdfAdapter = new FileDocumentAdapter(getContext(), FileDocumentAdapter.DOCUMENT_TYPE.EXCEL);

        recyclerView.setAdapter(pdfAdapter);

        pdfAdapter.setOnClickListener(new FileDocumentAdapter.OnClickListener() {
            @Override
            public void onChooseItem(String path) {
                openXLS(path);
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


    private void openXLS(final String path) {
        try {
            Uri theUri =  FileProvider.getUriForFile(getContext(),
                    BuildConfig.APPLICATION_ID + ".provider", new File(path));
            MimeTypeMap myMime = MimeTypeMap.getSingleton();
            String mimeType=myMime.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(theUri.toString()));//It will return the mimetype

            Intent intent = new Intent();

            intent.setAction(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            intent.setDataAndType(theUri, mimeType);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "Application not found"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

