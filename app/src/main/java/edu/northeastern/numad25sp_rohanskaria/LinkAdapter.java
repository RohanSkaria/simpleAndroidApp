package edu.northeastern.numad25sp_rohanskaria;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkViewHolder> {

    private ArrayList<Link> links;
    private OnLinkLongClickListener longClickListener;

    public interface OnLinkLongClickListener {
        void onLinkLongClick(int pos, Link link);
    }

    public LinkAdapter(OnLinkLongClickListener listener) {
        this.links = new ArrayList<>();
        this.longClickListener = listener;
    }
    public LinkAdapter() {
        this.links = new ArrayList<>();
        this.longClickListener = null;
    }



    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_card,parent,false);
        return new LinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder holder, int position) {
        Link currentLink = links.get(position);
        holder.name.setText(currentLink.getName());
        holder.url.setText(currentLink.getUrl());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentLink.getUrl()));
            v.getContext().startActivity(intent);
        });

        if (longClickListener != null) {
            holder.itemView.setOnLongClickListener(v -> {
                longClickListener.onLinkLongClick(position, currentLink);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    public Link getLink(int pos) {
        return links.get(pos);
    }

    public void addLink(Link link) {
        links.add(link);
        notifyItemInserted(links.size() -1);
    }

    public ArrayList<Link> getLinks() {
        return new ArrayList<>(links);
    }

    public void setLinks(ArrayList<Link> newLinks) {
        links = newLinks;
        notifyDataSetChanged();
    }

    public void removeLink(int pos) {
        links.remove(pos);
        notifyItemRemoved(pos);
    }

    public void updateLink(int pos, Link link) {
        links.set(pos, link);
        notifyItemChanged(pos);
    }

    public void insertLink(int pos, Link link) {
        links.add(pos , link);
        notifyItemInserted(pos);
    }

    static class LinkViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView url;

        LinkViewHolder(View link) {
            super(link);
            name = link.findViewById(R.id.linkName);
            url = link.findViewById(R.id.linkUrl);
        }
    }
}
