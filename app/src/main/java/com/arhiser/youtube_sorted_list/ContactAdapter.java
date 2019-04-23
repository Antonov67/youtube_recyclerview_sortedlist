package com.arhiser.youtube_sorted_list;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arhiser.youtube_sorted_list.model.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private SortedList<Contact> contacts = new SortedList<>(Contact.class, new SortedList.Callback<Contact>() {
        @Override
        public int compare(Contact contact, Contact contact2) {
            return contact.getName().compareTo(contact2.getName());
        }

        @Override
        public boolean areContentsTheSame(Contact contact, Contact contact2) {
            return contact2.equals(contact);
        }

        @Override
        public boolean areItemsTheSame(Contact contact, Contact contact2) {
            return contact2.getName().equals(contact.getName());
        }

        @Override
        public void onChanged(int pos, int count) {
            notifyItemRangeChanged(pos, count);
        }

        @Override
        public void onInserted(int pos, int count) {
            notifyItemRangeInserted(pos, count);
        }

        @Override
        public void onRemoved(int pos, int count) {
            notifyItemRangeRemoved(pos, count);
        }

        @Override
        public void onMoved(int pos, int count) {
            notifyItemMoved(pos, count);
        }
    });

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ContactViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        contactViewHolder.bind(contacts.get(i));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void putItem(Contact contact) {
        contacts.add(contact);
    }

    public void removeItemAt(int index) {
        contacts.removeItemAt(index);
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }

        public void bind(Contact contact) {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(contact.getName())) {
                sb.append(contact.getName());
            }
            if (!TextUtils.isEmpty(contact.getPhone())) {
                sb.append('\n').append(contact.getPhone());
            }
            if (!TextUtils.isEmpty(contact.getEmail())) {
                sb.append('\n').append(contact.getEmail());
            }
            textView.setText(sb);
        }
    }
}
