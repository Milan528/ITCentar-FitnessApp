package com.example.itcentar_fitnessapp.holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.models.Mindset;
import com.makeramen.roundedimageview.RoundedImageView;

public class MindsetHolder extends RecyclerView.ViewHolder {
    static final String TAG="Featured mindset";
    RoundedImageView imageToDisplay;
    TextView holderTittle,tittle;
    Mindset mindset;
    Context context;
    public MindsetHolder(@NonNull View itemView, Mindset mindsetToBind, Context context) {
        super(itemView);
        this.tittle=itemView.findViewById(R.id.text_view_recipe_mindset_workout_tittle);
        this.imageToDisplay=itemView.findViewById(R.id.image_view_recipe_mindset_workout);
        this.holderTittle=itemView.findViewById(R.id.text_view_recipe_mindset_workout);
        this.holderTittle.setText(MindsetHolder.TAG);
        this.context=context;
        mindset=mindsetToBind;
        tittle.setText(mindset.getTitle());
        Glide.with(context)
                .load(mindset.getBackground())
                .into(imageToDisplay);
    }

    public void setHolderTittleVisibility(int visibility){
        holderTittle.setVisibility(visibility);
    }
    public void setMindsetTittleVisibility(int visibility){
        tittle.setVisibility(visibility);
    }
}
