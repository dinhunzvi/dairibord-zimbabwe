package com.example.dairbordzimbabwe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairbordzimbabwe.R;
import com.example.dairbordzimbabwe.interfaces.ItemClickListener;
import com.example.dairbordzimbabwe.models.Product;

import java.util.List;
import java.util.Locale;

public class ProductsAdapter extends
        RecyclerView.Adapter<ProductsAdapter.ProductsAdapterViewHolder> {

    private final List<Product> m_products_list;
    private final Context m_context;

    public ProductsAdapter(List<Product> m_products_list, Context m_context) {
        this.m_products_list = m_products_list;
        this.m_context = m_context;
    }

    @NonNull
    @Override
    public ProductsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( m_context ).inflate( R.layout.product_layout, parent,
                false );

        return new ProductsAdapterViewHolder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapterViewHolder holder, int position) {
        Product product = m_products_list.get( position );

        holder.tvQuantityInStock.setText( String.format( Locale.ENGLISH,"%2f",
                product.getMilk_required() ));
        holder.tvProductName.setText( product.getProduct_name() );
        holder.tvQuantityInStock.setText( String.format( Locale.ENGLISH, "%d",
                product.getQuantity_in_stock() ));
    }

    @Override
    public int getItemCount() {
        return m_products_list.size();
    }

    public class ProductsAdapterViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public ItemClickListener m_item_click_listener;
        public TextView tvMilkRequired, tvProductName, tvQuantityInStock;

        public ProductsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMilkRequired = itemView.findViewById( R.id.tvMilkRequired );
            tvProductName = itemView.findViewById( R.id.tvProductName );
            tvQuantityInStock = itemView.findViewById( R.id.tvQuantityInStock );

        }

        @Override
        public void onClick(View view) {
            m_item_click_listener.onClick( view, getAdapterPosition(), false );
        }

        public void setItem_click_listener( ItemClickListener item_click_listener ) {
            m_item_click_listener = item_click_listener;
        }
    }
}
