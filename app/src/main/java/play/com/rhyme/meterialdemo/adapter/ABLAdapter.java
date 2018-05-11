package play.com.rhyme.meterialdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import play.com.rhyme.meterialdemo.AppBarLayoutActivity;

public class ABLAdapter extends RecyclerView.Adapter<ABLAdapter.ABLViewHolder> {
     private Context context;
     private List<String> items;

        public ABLAdapter(Context context, List<String> items){
            this.context=context;
            this.items=items;

        }
        @Override
        public ABLAdapter.ABLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ABLAdapter.ABLViewHolder(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ABLAdapter.ABLViewHolder holder, int position) {
            holder.tv.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ABLViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public ABLViewHolder(View itemView) {
                super(itemView);
                tv = itemView.findViewById(android.R.id.text1);
            }
        }
    }