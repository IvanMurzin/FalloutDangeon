package ru.ivanmurzin.falloutdungeon.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.item.aid.Aid;

public class AidAdapter extends RecyclerView.Adapter<AidAdapter.Holder> {

    private final List<Aid> aids;

    public AidAdapter(List<Aid> aids) {
        this.aids = aids;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aid_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Aid aid = aids.get(position);
        holder.points.setText(aid.toString());
        holder.item.setOnClickListener(view -> {
            notifyItemRemoved(position);
            aids.remove(aid);
            aid.use();
        });
    }

    @Override
    public int getItemCount() {
        return aids.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        public final TextView points;
        public final View item;

        public Holder(@NonNull View itemView) {
            super(itemView);
            points = itemView.findViewById(R.id.points);
            item = itemView.findViewById(R.id.item);
        }
    }
}
