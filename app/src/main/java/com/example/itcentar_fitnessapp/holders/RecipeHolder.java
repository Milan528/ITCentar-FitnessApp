package com.example.itcentar_fitnessapp.holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.itcentar_fitnessapp.R;
import com.example.itcentar_fitnessapp.models.Recipe;
import com.makeramen.roundedimageview.RoundedImageView;

public class RecipeHolder extends RecyclerView.ViewHolder {
    static final String TAG="Recipe of the day";
    RoundedImageView imageToDisplay;
    TextView recipeTextView,tittle;
    Recipe recipe;
    Context context;
    public RecipeHolder(@NonNull View itemView,Recipe recipeToBind,Context context) {
        super(itemView);
        this.tittle=itemView.findViewById(R.id.text_view_recipe_mindset_tittle);
        this.imageToDisplay=itemView.findViewById(R.id.image_view_recipe_mindset);
        this.recipeTextView=itemView.findViewById(R.id.text_view_recipe_mindset);
        this.context=context;
        recipeTextView.setText(RecipeHolder.TAG);
        recipe=recipeToBind;
        tittle.setText(recipe.getTitle());
        Glide.with(context)
                .load(recipe.getBackground())
                .into(imageToDisplay);

    }
}
