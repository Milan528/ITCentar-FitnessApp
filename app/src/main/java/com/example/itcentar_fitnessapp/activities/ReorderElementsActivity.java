package com.example.itcentar_fitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.adapters.ReorderElementsViewAdapter;
import com.example.itcentar_fitnessapp.clients.AppClient;
import com.example.itcentar_fitnessapp.enums.HomeScreenParts;
import com.example.itcentar_fitnessapp.interfaces.IComponentInitializer;
import com.example.itcentar_fitnessapp.models.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReorderElementsActivity extends AppCompatActivity implements IComponentInitializer, View.OnClickListener {
    RecyclerView recyclerViewReorderElements;
    ReorderElementsViewAdapter mReorderElementsAdapter;
    Button btnSave,btnCancel;
    List<HomeScreenParts> adapterOrder;

    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN
    | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition=viewHolder.getAdapterPosition();
            int targetPosition=target.getAdapterPosition();
            Collections.swap(adapterOrder,fromPosition, targetPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition,targetPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reorder_elements);
        initializeComponents();
        createAdapter();

    }

    private void createAdapter() {
        adapterOrder=createAdapterOrder();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager((Context) this, 1);
        mReorderElementsAdapter=new ReorderElementsViewAdapter(adapterOrder);
        recyclerViewReorderElements.setLayoutManager(layoutManager);
        recyclerViewReorderElements.setAdapter(mReorderElementsAdapter);
    }

    private List<HomeScreenParts> createAdapterOrder() {
        List<HomeScreenParts> adapterOrder=new ArrayList<>();
        List<HomeScreenParts> currentOrder = new ArrayList<>(AppClient.getInstance().getDisplayOrder());
        int maxElements=currentOrder.size();
        for(int i=0;i<maxElements;i++){
            if(currentOrder.get(i)==HomeScreenParts.WEEKLY_PROGRESS){
                adapterOrder.add(HomeScreenParts.PROGRESS);
                i+=2;
            }else
                adapterOrder.add(currentOrder.get(i));
        }

        return adapterOrder;
    }

    @Override
    public void initializeComponents() {
        recyclerViewReorderElements=findViewById(R.id.recycler_view_reorder_elements);
        btnSave=findViewById(R.id.button_save_reorder);
        btnCancel=findViewById(R.id.button_cancel_reorder);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewReorderElements);
    }

    @Override
    public void onClick(View view) {
        int clickedId=view.getId();
        if(clickedId==R.id.button_save_reorder){
            saveChanges();
            this.finish();

        }else if(clickedId==R.id.button_cancel_reorder){
            this.finish();
        }
    }

    private void saveChanges() {
        List<HomeScreenParts> appElementOrder=AppClient.getInstance().getDisplayOrder();
        appElementOrder.clear();
        for(int i=0;i<adapterOrder.size();i++){
            if(adapterOrder.get(i)==HomeScreenParts.PROGRESS){
                appElementOrder.add(HomeScreenParts.WEEKLY_PROGRESS);
                appElementOrder.add(HomeScreenParts.MONTHLY_PROGRESS);
                appElementOrder.add(HomeScreenParts.WORKOUT_TIP);
            }else
                appElementOrder.add(adapterOrder.get(i));
        }
        AppClient.getInstance().setDisplayOrder(appElementOrder);
        this.finish();
    }
}