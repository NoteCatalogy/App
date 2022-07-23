package com.sid.firenotes.model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sid.firenotes.MainActivity;
import com.sid.firenotes.R;
import com.sid.firenotes.noteDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> title;
    List<String> content;

    public Adapter(List<String> title,List<String> content){
        this.title = title;
        this.content = content;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.noteTitle.setText(title.get(position));
        holder.noteContent.setText(content.get(position));
        final int code = getRandomColor();
        holder.mCardView.setCardBackgroundColor(holder.view.getResources().getColor(code,null));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), noteDetails.class);
                intent.putExtra("title",title.get(position));
                intent.putExtra("content",content.get(position));
                intent.putExtra("code",code);
                view.getContext().startActivity(intent);
            }
        });
    }
    private int getRandomColor(){
        List<Integer>  colorCode = new ArrayList<>();
        colorCode.add(R.color.lightPurple);
        colorCode.add(R.color.grey);
        colorCode.add(R.color.colorAncient);
        colorCode.add(R.color.pink);
        colorCode.add(R.color.yellow);
        colorCode.add(R.color.skyBlue);
        colorCode.add(R.color.teal_200);

        Random randomColor = new Random();
        int number = randomColor.nextInt(colorCode.size());
        return colorCode.get(number);
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle,noteContent;
        View view;
        CardView mCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.titles);
            noteContent = itemView.findViewById(R.id.content);
            mCardView = itemView.findViewById(R.id.noteCard);
            view = itemView;
        }
    }
}
