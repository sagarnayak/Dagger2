package com.sagar.android_projects.dagger2.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.android_projects.dagger2.R;
import com.sagar.android_projects.dagger2.pojo.RepoResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.sagar.android_projects.dagger2.activity.MainActivity.TAG;

/**
 * Created by sagar on 11/13/2017.
 * adapter for showing the repo list.
 */
public class AdapterRepoList extends RecyclerView.Adapter<AdapterRepoList.ViewHolder> {
    private ArrayList<RepoResponse> data;
    private com.sagar.android_projects.dagger2.adapter.AdapterRepoList.CallBack callBack;

    @Inject
    public AdapterRepoList(CallBack callBack) {
        this.callBack = callBack;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewId.setText(data.get(position).getId());
        holder.textViewName.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewId;
        private TextView textViewName;
        private ConstraintLayout constraintLayoutContainer;

        ViewHolder(View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.textview_id);
            textViewName = itemView.findViewById(R.id.textview_name);
            constraintLayoutContainer = itemView.findViewById(R.id.constraintlayout_container_repo_item);

            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: " + data.get(getAdapterPosition()).getId());
                    callBack.startDetailActivity(data.get(getAdapterPosition()).getName());
                }
            });
        }
    }

    public interface CallBack {
        void startDetailActivity(String userName);
    }

    public void setData(ArrayList<RepoResponse> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
