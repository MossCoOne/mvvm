package com.mossco.za.mvvm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.mossco.za.mvvm.catlist.model.CatDto;
import com.mossco.za.mvvm.databinding.ActivityCatDetailsBinding;

public class CatDetailsActivity extends AppCompatActivity {

    private SimpleExoPlayer simpleExoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCatDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_cat_details);
        final String catEntryKey = "cat_entry";
        if (getIntent() != null && getIntent().hasExtra(catEntryKey)) {

            CatDto catEntry = getIntent().getParcelableExtra(catEntryKey);
            Glide.with(getApplicationContext()).load(catEntry.getImageUrl()).dontAnimate().fitCenter()
                    .placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(binding.catImageView);
            binding.descriptionTextView.setText(catEntry.getDescription());
        }
    }

    void initializeAudioPlayer() {
        DefaultRenderersFactory defaultRenderersFactory =
                new DefaultRenderersFactory(this, null, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF);
    }
}
