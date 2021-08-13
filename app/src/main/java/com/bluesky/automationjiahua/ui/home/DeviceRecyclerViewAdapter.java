package com.bluesky.automationjiahua.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bluesky.atuomationjiahua.R;
import com.bluesky.automationjiahua.database.Device;
import com.bluesky.automationjiahua.viewmodel.DeviceViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BlueSky
 * @date 2021/7/3
 * Description:
 */
public class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceRecyclerViewAdapter.DeviceViewHolder> {

    List<Device> mData = new ArrayList<>();
    private DeviceViewModel mViewModel;
    private Context mContext;
    private RecyclerView mRecyclerView;

    public DeviceRecyclerViewAdapter(DeviceViewModel viewModel, Context context, RecyclerView recyclerView) {
        this.mViewModel = viewModel;
        this.mContext = context;
        this.mRecyclerView = recyclerView;
    }

    public List<Device> getData() {
        return mData;
    }

    public void setData(List<Device> data) {
        mData = data;
    }

    @NonNull
    @NotNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return new DeviceViewHolder(inflater.inflate(R.layout.recyclerview_item_device, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DeviceViewHolder holder, int position) {
        Device device = mData.get(position);
        holder.tvNumber.setText(String.valueOf(position + 1));
        holder.tvTag.setText(device.getTag());
        holder.tvAffect.setText(device.getAffect());
        holder.tvStandard.setText(device.getStandard());
        holder.tvRange.setText(device.getRange());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(mRecyclerView);
                Bundle bundle = new Bundle();
                bundle.putSerializable("device", device);
                controller.navigate(R.id.action_nav_home_to_detailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber, tvTag, tvAffect, tvStandard, tvRange;
        LinearLayout root;

        public DeviceViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.ll_item_rv_list);
            tvNumber = itemView.findViewById(R.id.tv_number);
            tvTag = itemView.findViewById(R.id.tv_tag);
            tvAffect = itemView.findViewById(R.id.tv_affect);
            tvStandard = itemView.findViewById(R.id.tv_standard);
            tvRange = itemView.findViewById(R.id.tv_range);
        }
    }
}
