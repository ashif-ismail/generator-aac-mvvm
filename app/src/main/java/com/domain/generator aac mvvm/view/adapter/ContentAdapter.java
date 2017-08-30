package com.domain.generator aac mvvm.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import java.util.List;
import javax.inject.Inject;
import com.domain.generator aac mvvm.R;
import com.domain.generator aac mvvm.data.model.ContentModel.Content;
import com.domain.generator aac mvvm.databinding.ContentItemsBinding;


/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

public class ContentAdapter extends RecyclerView.Adapter {

  @Inject
  Picasso mPicasso;
  private ContentItemsBinding mBinding;
  private List<Content> contentList;

  public ContentAdapter(List<Content> contentList) {
    this.contentList = contentList;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.content_items, parent, false);
        return new ContentViewHolder(mBinding);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((ContentViewHolder) holder).binding.setContent(contentList.get(position));
    mPicasso.with(mBinding.getRoot().getContext())
        .load(contentList.get(position).getImg())
        .into(mBinding.imgThumbnail);
  }

  @Override
  public int getItemCount() {
    return contentList.size();
  }

  private class ContentViewHolder extends RecyclerView.ViewHolder {

    private ContentItemsBinding binding;

    public ContentViewHolder(ContentItemsBinding mBinding) {
      super(mBinding.getRoot());
      this.binding = mBinding;
    }
  }
}
