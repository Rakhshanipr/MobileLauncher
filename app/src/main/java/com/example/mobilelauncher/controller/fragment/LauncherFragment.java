package com.example.mobilelauncher.controller.fragment;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilelauncher.R;
import com.example.mobilelauncher.controller.utils.PackageUtils;

import java.util.List;

public class LauncherFragment extends Fragment {

    //region defind variable
    RecyclerView mRecyclerView;
    LauncherAdapter mLauncherAdapter;
    //endregion

    //region defind static method and variable
    public static LauncherFragment newInstance() {
        LauncherFragment fragment = new LauncherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //endregion

    public LauncherFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_launcher, container, false);
        findViews(view);
        initUI();
        return view;
    }

    private void initUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLauncherAdapter=new LauncherAdapter(PackageUtils.getLauncherActivities(getContext()));
        mRecyclerView.setAdapter(mLauncherAdapter);
    }

    private void findViews(View view) {
        mRecyclerView=view.findViewById(R.id.fragmentLauncher_recyclerView_List);
    }

    public class LauncherViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewLabel;
        ResolveInfo mResolveInfo;
        public LauncherViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewLabel=itemView.findViewById(R.id.listRowApp_textView_label);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(PackageUtils.getComponentName(mResolveInfo));
                    startActivity(intent);
                }
            });
        }

        public void bind(ResolveInfo resolveInfo){
            mResolveInfo=resolveInfo;
            mTextViewLabel.setText(resolveInfo.loadLabel(getActivity().getPackageManager()).toString());
        }

    }

    public class LauncherAdapter extends RecyclerView.Adapter<LauncherViewHolder>{

        List<ResolveInfo> mResolveInfoList;

        public LauncherAdapter(List<ResolveInfo> resolveInfoList) {
            mResolveInfoList = resolveInfoList;
        }

        public List<ResolveInfo> getResolveInfoList() {
            return mResolveInfoList;
        }

        public void setResolveInfoList(List<ResolveInfo> resolveInfoList) {
            mResolveInfoList = resolveInfoList;
        }

        @NonNull
        @Override
        public LauncherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getContext())
                    .inflate(R.layout.list_row_app,parent,false);
            return new LauncherViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LauncherViewHolder holder, int position) {
            holder.bind(mResolveInfoList.get(position));
        }

        @Override
        public int getItemCount() {
            return mResolveInfoList.size();
        }

    }
}