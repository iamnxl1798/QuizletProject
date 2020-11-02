package com.example.quizlet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlet.model.ThuMucHoc;
import com.example.quizlet.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<ThuMucHoc> thuMucHocs;
    private OnItemClickListener listener;

    public HomeAdapter(Context context, List<ThuMucHoc> thuMucHocs) {
        this.context = context;
        this.thuMucHocs = thuMucHocs;
    }

    public HomeAdapter(Context context, List<ThuMucHoc> thuMucHocs, OnItemClickListener listener) {
        this.context = context;
        this.thuMucHocs = thuMucHocs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reviewhome, parent, false);
        return new HomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ThuMucHoc thuMucHoc = thuMucHocs.get(position);
        holder.avatar.setImageResource(thuMucHoc.getAvatar());
        holder.author.setText(thuMucHoc.getAuthor());
        holder.title.setText(thuMucHoc.getTitle());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClickMore(position, thuMucHoc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return thuMucHocs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, author;
        private ImageView avatar;
        private ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title1);
            author = itemView.findViewById(R.id.author);
            avatar = itemView.findViewById(R.id.avatar);
            constraintLayout = itemView.findViewById(R.id.item_ryce);
        }
    }

    public interface OnItemClickListener {
        void OnClickMore(int position, ThuMucHoc doanhthu);
    }
}
