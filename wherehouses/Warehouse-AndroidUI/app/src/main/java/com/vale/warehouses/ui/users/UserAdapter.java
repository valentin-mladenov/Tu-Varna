package com.vale.warehouses.ui.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vale.warehouses.R;
import com.vale.warehouses.service.model.User;

public class UserAdapter extends ListAdapter<User, UserAdapter.UserHolder> {
    private OnItemClickListener listener;

    private static final DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(User oldItem, User newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(User oldItem, User newItem) {
            return oldItem.getId() == newItem.getId() &&
                    oldItem.getEmail().equals(newItem.getEmail()) &&
                    oldItem.getUserName().equals(newItem.getUserName());
        }
    };

    public UserAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User current = getUserAt(position);
        holder.textViewEmail.setText(current.getEmail());
        holder.textViewFullName.setText(String.valueOf(current.getId()));
        holder.textViewUserName.setText(current.getUserName());
    }


    public User getUserAt(int position) {
        return getItem(position);
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView textViewEmail, textViewFullName, textViewUserName;

        public UserHolder(View itemView) {
            super(itemView);
            textViewEmail = itemView.findViewById(R.id.text_view_email);
            textViewFullName = itemView.findViewById(R.id.text_view_full_name);
            textViewUserName = itemView.findViewById(R.id.text_view_user_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getUserAt(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
