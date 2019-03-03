package com.iibcsad.recursion.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iibcsad.recursion.R;

import java.util.ArrayList;

public class SequenceAdapter extends RecyclerView.Adapter<SequenceAdapter.SequenceViewHolder> {
    private ArrayList<String> numbers;

    public SequenceAdapter(ArrayList<String> numbers)
    {
        this.numbers = numbers;
    }

    @NonNull
    @Override
    public SequenceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_grid, viewGroup, false);
        return new SequenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SequenceViewHolder sequenceViewHolder, int i) {
        sequenceViewHolder.number.setText(numbers.get(i));
        sequenceViewHolder.index.setText(String.valueOf(i));
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public class SequenceViewHolder extends  RecyclerView.ViewHolder {
        public TextView number, index;
        public SequenceViewHolder(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.l_grid_number);
            index = itemView.findViewById(R.id.l_grid_index);
        }
    }
}
