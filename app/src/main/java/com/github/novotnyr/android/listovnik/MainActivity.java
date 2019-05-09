package com.github.novotnyr.android.listovnik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumbersViewModel viewModel = ViewModelProviders.of(this).get(NumbersViewModel.class);

        RecyclerViewListAdapter recyclerViewListAdapter = new RecyclerViewListAdapter();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewListAdapter);

        recyclerViewListAdapter.submitList(viewModel.getNumbers().getValue());
    }

    private static class RecyclerViewListAdapter extends ListAdapter<String, RecyclerViewHolder> {
        public RecyclerViewListAdapter() {
            super(new DiffUtil.ItemCallback<String>() {
                @Override
                public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }
            });
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View textView = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new RecyclerViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            String item = getItem(position);
            holder.setText(item);
        }
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public RecyclerViewHolder(View textView) {
            super(textView);
        }

        public void setText(String text) {
            ((TextView) itemView).setText(text);
        }
    }
}
