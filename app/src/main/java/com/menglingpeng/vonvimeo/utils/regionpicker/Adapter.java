package com.menglingpeng.vonvimeo.utils.regionpicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.VH> {

    private ArrayList<Region> selectedCountries = new ArrayList<>();
    private final LayoutInflater inflater;
    private OnPick onPick = null;
    private final Context context;
    public Adapter(Context ctx) {
        inflater = LayoutInflater.from(ctx);
        context = ctx;
    }


    public void setSelectedCountries(ArrayList<Region> selectedCountries) {
        this.selectedCountries = selectedCountries;
        notifyDataSetChanged();
    }

    public void setOnPick(OnPick onPick) {
        this.onPick = onPick;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(inflater.inflate(R.layout.item_Region, parent, false));
    }

    private int itemHeight = -1;
    public void setItemHeight(float dp) {
        itemHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, context.getResources().getDisplayMetrics());
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final Region Region = selectedCountries.get(position);
        holder.tvName.setText(Region.name);
        holder.tvCode.setText("+" + Region.code);
        if(itemHeight != -1) {
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            params.height = itemHeight;
            holder.itemView.setLayoutParams(params);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onPick != null) onPick.onPick(Region);
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectedCountries.size();
    }

    class VH extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvCode;

        VH(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code);
        }
    }
}
