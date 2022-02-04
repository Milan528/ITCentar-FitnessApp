package com.example.itcentar_fitnessapp.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itcentar_fitnessapp.R;

public class ReorderElementHolder extends RecyclerView.ViewHolder {
    TextView elementText;
    public ReorderElementHolder(@NonNull View itemView) {
        super(itemView);
        elementText=itemView.findViewById(R.id.text_view_element_reorder);

    }
    public String getElementText() {
        return elementText.getText().toString();
    }

    public void setElementText(String elementText) {
        this.elementText.setText(elementText);
    }
}
