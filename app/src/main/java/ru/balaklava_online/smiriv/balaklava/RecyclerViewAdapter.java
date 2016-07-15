package ru.balaklava_online.smiriv.balaklava;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by smiriv on 14.07.2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    private List<Record> records;

    protected OnItemClickListener mListener;

    public RecyclerViewAdapter(List<Record> records) {
        this.records = records;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        //v.setOnClickListener(mOnClickListener);
        return new ViewHolder(v);
    }

    public void setmListener(OnItemClickListener mListener){
        this.mListener = mListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Record record = records.get(i);
        int iconResourceId = 0;
        switch (record.getType()) {
            case GREEN:
                iconResourceId = R.drawable.green_circle;
                break;
            case RED:
                iconResourceId = R.drawable.red_circle;
                break;
            case YELLOW:
                iconResourceId = R.drawable.yellow_circle;
                break;
        }
        viewHolder.icon.setImageResource(iconResourceId);
        viewHolder.name.setText(record.getName());
        viewHolder.deleteButtonListener.setRecord(record);
        viewHolder.copyButtonListener.setRecord(record);
        viewHolder.mOnClickListener.setRecord(record);
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    private void copy(Record record) {
        int position = records.indexOf(record);
        Record copy = record.copy();
        records.add(position + 1, copy);
        notifyItemInserted(position + 1);
    }

    private void delete(Record record) {
        int position = records.indexOf(record);
        records.remove(position);
        notifyItemRemoved(position);
    }
    public interface OnItemClickListener {
        void onItemClick(int position, Record record);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView icon;
        private Button deleteButton;
        private Button copyButton;
        private DeleteButtonListener deleteButtonListener;
        private CopyButtonListener copyButtonListener;

        private MyOnClickListener mOnClickListener;

        private RelativeLayout mOnClickRecyclerItemListener;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.recyclerViewItemName);
            icon = (ImageView) itemView.findViewById(R.id.recyclerViewItemIcon);
            deleteButton = (Button) itemView.findViewById(R.id.recyclerViewItemDeleteButton);
            copyButton = (Button) itemView.findViewById(R.id.recyclerViewItemCopyButton);

            mOnClickRecyclerItemListener = (RelativeLayout) itemView.findViewById(R.id.recyclerItem);

            mOnClickListener = new MyOnClickListener();

            deleteButtonListener = new DeleteButtonListener();
            copyButtonListener = new CopyButtonListener();
            deleteButton.setOnClickListener(deleteButtonListener);
            copyButton.setOnClickListener(copyButtonListener);
            mOnClickRecyclerItemListener.setOnClickListener(mOnClickListener);

        }
    }


    private class CopyButtonListener implements View.OnClickListener {
        private Record record;

        @Override
        public void onClick(View v) {
            copy(record);
        }

        public void setRecord(Record record) {
            this.record = record;
        }
    }

    private class DeleteButtonListener implements View.OnClickListener {
        private Record record;

        @Override
        public void onClick(View v) {
            delete(record);
        }

        public void setRecord(Record record) {
            this.record = record;
        }
    }
    private class MyOnClickListener implements View.OnClickListener  {
        private Record record;

        public void setRecord(Record record){
            this.record = record;
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(v.getId(),record);
        }
    }

}
