package com.example.aprzybylo.learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aprzybylo.learn.Model.Flickr;
import com.example.aprzybylo.learn.Model.Photo;
import com.example.aprzybylo.learn.Model.Photos;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FlickrFragment extends Fragment implements Callback<Flickr> {

	Call<Flickr> call;

	@BindView(R.id.gallery)
	RecyclerView mGallery;

	Unbinder unbinder;
	private MyAdapter mAdapter;
	private GridLayoutManager mGridLayoutManager;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_flickr, container, false);
		mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
		unbinder = ButterKnife.bind(this, view);

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RetrofitConfiguration retrofit = RetrofitConfiguration.getInstance();

		call = retrofit.getApi().loadChanges(retrofit.getUserAgent());
		call.enqueue(this);
	}

	@Override
	public void onResponse(Call<Flickr> call, Response<Flickr> response) {

		Photos photos = response.body().getPhotos();
		if (photos != null) {
			mAdapter = new MyAdapter(photos);
			mGallery.setAdapter(mAdapter);
			mGallery.setLayoutManager(mGridLayoutManager);
		}
	}

	@Override
	public void onFailure(Call<Flickr> call, Throwable t) {

		if (call.isCanceled()) {
			Toast.makeText(getContext(), "Canceled", Toast.LENGTH_LONG);
		} else {
			Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG);
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		call.cancel();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}


	private class MyAdapter extends RecyclerView.Adapter<MyHolder> {

		private Photos mPhotos;

		public MyAdapter(Photos photos) {
			mPhotos = photos;
		}

		@Override
		public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			return new MyHolder(layoutInflater, parent);
		}

		@Override
		public void onBindViewHolder(MyHolder holder, int position) {
			Photo photo = mPhotos.getPhoto().get(position);
			holder.bind(photo);
		}

		@Override
		public int getItemCount() {
			return mPhotos.getPhoto().size();
		}
	}

	public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

		public ImageView mImage;

		public MyHolder(LayoutInflater inflater, ViewGroup parent) {
			super(inflater.inflate(R.layout.gallery_item, parent, false));

			itemView.setOnClickListener(this);
			mImage = (ImageView) itemView.findViewById(R.id.imgView);

		}

		public void bind(Photo photo) {

			String url = new StringBuilder()
					.append("http://farm")
					.append(photo.getFarm())
					.append(".staticflickr.com/")
					.append(photo.getServer())
					.append("/")
					.append(photo.getId())
					.append("_")
					.append(photo.getSecret())
					.append(".jpg")
					.toString();

			Glide.with(getContext())
					.load(url)
					.into(mImage);

		}

		@Override
		public void onClick(View v) {
			// OnClick
		}

	}
}
