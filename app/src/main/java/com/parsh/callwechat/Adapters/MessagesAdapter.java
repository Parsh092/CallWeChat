package com.parsh.callwechat.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.parsh.callwechat.Models.Message;
import com.parsh.callwechat.R;
import com.parsh.callwechat.databinding.ItemReceiveBinding;
import com.parsh.callwechat.databinding.ItemSentBinding;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter {

    ArrayList<Message> messages;
    Context context;
    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;


    public MessagesAdapter(Context context, ArrayList<Message> messagesModel) {
        this.context = context;
        this.messages = messagesModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (SENDER_VIEW_TYPE == viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_sent, parent, false);
            return new SenderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_receive, parent, false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).getUid().equals(FirebaseAuth.getInstance().getUid())) {
            return SENDER_VIEW_TYPE;
        } else {
            return RECEIVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message messageModel = messages.get(position);
        if (holder.getClass() == SenderViewHolder.class) {
            ((SenderViewHolder) holder).binding.message.setText(messageModel.getMessage());
        } else {
            ((ReceiverViewHolder) holder).binding.message.setText(messageModel.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
ItemReceiveBinding binding;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemReceiveBinding.bind(itemView);
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {
ItemSentBinding binding;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSentBinding.bind(itemView);
        }
    }
}
