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
import com.example.dairbordzimbabwe.models.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersAdapterViewHolder> {

    private Context m_context;

    private List<User> m_users_list;

    public UsersAdapter(Context m_context, List<User> m_users_list) {
        this.m_context = m_context;
        this.m_users_list = m_users_list;
    }

    @NonNull
    @Override
    public UsersAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( m_context ).inflate( R.layout.user_layout, parent,
                false );

        return new UsersAdapterViewHolder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapterViewHolder holder, int position) {
        User user = m_users_list.get( position );
        holder.tvEmail.setText( user.getEmail() );
        holder.tvName.setText( String.format( "%s%s", user.getFirst_name(), user.getLast_name() ) );
        holder.tvUsername.setText( user.getUsername() );
    }

    @Override
    public int getItemCount() {
        return m_users_list.size();
    }


    public class UsersAdapterViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public TextView tvName, tvEmail, tvUsername;
        public ItemClickListener item_click_listener;

        public UsersAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEmail = itemView.findViewById( R.id.tvEmail );
            tvName = itemView.findViewById( R.id.tvName );
            tvUsername = itemView.findViewById( R.id.tvUsername );

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
