package com.techBMT.YTV_Player_Pro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.techBMT.YTV_Player_Pro.R;
import com.techBMT.YTV_Player_Pro.fragment_file.PDF_Fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDocumentAdapter extends RecyclerView.Adapter<FileDocumentAdapter.ViewHolder> {
    Context context;
    List<File> fileList = new ArrayList<>();
    private  DOCUMENT_TYPE documentType;

    private OnClickListener onClickListener;

    public FileDocumentAdapter(Context context, DOCUMENT_TYPE documentType) {
        this.context = context;
        this.documentType = documentType;
    }

    public FileDocumentAdapter setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
        return this;
    }


    public void setData(List<File> fileList) {
        this.fileList.clear();
        this.fileList.addAll(fileList);
        notifyDataSetChanged();
    }


    public List<File> getFileList() {
        return fileList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_file, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_name.setText(fileList.get(position).getName());
        holder.txt_name.setSelected(true);


        switch (documentType){
            case PDF: holder.imageView.setImageResource(R.drawable.pdf_pc);
                break;
            case PPD: holder.imageView.setImageResource(R.drawable.ptt);
                break;
            case NOTE: holder.imageView.setImageResource(R.drawable.word);
                break;
            case EXCEL: holder.imageView.setImageResource(R.drawable.excel);
                break;
        }

      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              onClickListener.onChooseItem(fileList.get(position).getPath());
          }
      });

    }


    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.pdf_txtname);
            cardView = itemView.findViewById(R.id.pdf_cardview);
            imageView = itemView.findViewById(R.id.pdf_img);
        }
    }

    public enum DOCUMENT_TYPE{
        PDF, EXCEL, PPD, NOTE
    }

    public interface OnClickListener{
        void onChooseItem(String path);
    }

}
