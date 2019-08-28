package com.mossco.za.mvvm.catlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mossco.za.mvvm.R;
import com.mossco.za.mvvm.database.CatEntry;
import com.mossco.za.mvvm.databinding.CatListItemBinding;

import java.util.List;

public class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.ViewHolder> {

    private List<CatEntry> catListItems;
    private ItemOnclickListener itemOnclickListener;

    CatListAdapter(ItemOnclickListener itemOnclickListener) {
        this.itemOnclickListener = itemOnclickListener;
    }

    void setCatListItems(List<CatEntry> catListItems) {
        this.catListItems = catListItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CatListItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cat_list_item, parent, false);
        return new ViewHolder(binding, itemOnclickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CatEntry catListItem = catListItems.get(position);
        holder.itemBinding.titleTextView.setText(catListItem.getTitle());
        holder.itemBinding.descriptionTextView.setText(catListItem.getDescription());
        Glide.with(holder.itemBinding.catImageView.getContext()).load(catListItem.getImageUrl()).dontAnimate().fitCenter().diskCacheStrategy(
                DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(holder.itemBinding.catImageView);
        holder.setCatListItem(catListItem);
    }

    public interface ItemOnclickListener {
        void onItemClicked(CatEntry catEntry);
    }

    @Override
    public int getItemCount() {
        return catListItems != null ? catListItems.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CatListItemBinding itemBinding;
        ItemOnclickListener itemOnclickListener;
        CatEntry catEntry;

        public void setCatListItem(CatEntry catEntry) {
            this.catEntry = catEntry;
        }

        ViewHolder(@NonNull CatListItemBinding itemBinding, final ItemOnclickListener itemOnclickListener) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            this.itemOnclickListener = itemOnclickListener;
            itemBinding.contentContainer.setOnClickListener(view -> itemOnclickListener.onItemClicked(catEntry));
        }
    }
}
