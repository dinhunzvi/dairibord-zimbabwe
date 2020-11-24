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
import com.example.dairbordzimbabwe.models.Supplier;

import java.util.List;

public class SuppliersAdapter extends
        RecyclerView.Adapter<SuppliersAdapter.SuppliersAdapaterViewHolder> {

    private final List<Supplier> m_suppliers_list;
    private final Context m_context;

    public SuppliersAdapter(List<Supplier> m_suppliers_list, Context m_context) {
        this.m_suppliers_list = m_suppliers_list;
        this.m_context = m_context;
    }

    @NonNull
    @Override
    public SuppliersAdapaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( m_context ).inflate( R.layout.supplier_layout, parent,
                false );

        return new SuppliersAdapaterViewHolder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull SuppliersAdapaterViewHolder holder, int position) {
        Supplier supplier = m_suppliers_list.get( position );
        holder.tvAddress.setText( supplier.getAddress() );
        holder.tvName.setText( supplier.getSupplier_name() );
        holder.tvPhoneNumber.setText( supplier.getPhone_number() );
    }

    @Override
    public int getItemCount() {
        return m_suppliers_list.size();
    }

    public class SuppliersAdapaterViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public TextView tvName, tvAddress, tvPhoneNumber;
        public ItemClickListener item_click_listener;

        public SuppliersAdapaterViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAddress = itemView.findViewById( R.id.tvAddress );
            tvName = itemView.findViewById( R.id.tvName );
            tvPhoneNumber = itemView.findViewById( R.id.tvPhoneNumber );
        }

        @Override
        public void onClick(View view) {
            item_click_listener.onClick( view, getAdapterPosition(), false );
        }

        public void setItem_click_listener( ItemClickListener item_click_listener ) {
            this.item_click_listener = item_click_listener;
        }

    }

}
