package com.bluesky.automationjiahua.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bluesky.atuomationjiahua.R;
import com.bluesky.atuomationjiahua.databinding.FragmentHomeBinding;
import com.bluesky.automationjiahua.base.AppConstant;
import com.bluesky.automationjiahua.database.Device;
import com.bluesky.automationjiahua.database.DeviceRepository;
import com.bluesky.automationjiahua.viewmodel.DeviceViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private DeviceRecyclerViewAdapter mAdapter;
    private LiveData<List<Device>> mDevices;
    private LiveData<List<Device>> mFilteredDevices;

    private DeviceViewModel mViewModel;
    private DeviceRepository mRepository;
    private String mDomainPattern;
    private String mColumnPattern;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        mViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().getApplication())
                .create(DeviceViewModel.class);
        mRepository = new DeviceRepository(requireActivity().getApplicationContext());

        mAdapter = new DeviceRecyclerViewAdapter(mViewModel, requireContext(), binding.rvList);
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setAdapter(mAdapter);
        mDevices = mViewModel.getLiveDataDevices();
        mDevices.observe(getViewLifecycleOwner(), new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devices) {

                mAdapter.setData(devices);
                mAdapter.notifyDataSetChanged();
            }
        });

        //区域下拉列表
        binding.spinnerQueryDomain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mDomainPattern = AppConstant.DOMAIN[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //范围下拉列表
        binding.spinnerQueryColumn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mColumnPattern=AppConstant.COLUMN[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_home_search, menu);
        MenuItem item = menu.findItem(R.id.menu_item_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(1000);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                String pattern = s.trim();
                mFilteredDevices = mViewModel.findDevicesWithPattern(mDomainPattern, mColumnPattern, pattern);
                mFilteredDevices.removeObservers(getViewLifecycleOwner());
                mFilteredDevices.observe(getViewLifecycleOwner(), new Observer<List<Device>>() {
                    @Override
                    public void onChanged(List<Device> devices) {
                        mAdapter.setData(devices);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_search:

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        //离开当前页面时，隐藏软键盘
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        //***这里的getView也换成requireView***
        imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0);
    }
}