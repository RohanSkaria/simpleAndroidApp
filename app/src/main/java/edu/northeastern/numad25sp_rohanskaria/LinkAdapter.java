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

    public LinkAdapter() {
        this.links = new ArrayList<>();
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

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentLink.getUrl()));
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return links.size();
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
