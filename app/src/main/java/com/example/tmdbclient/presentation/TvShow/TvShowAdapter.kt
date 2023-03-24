package com.example.tmdbclient.presentation.TvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.databinding.ListItemBinding

class TvShowAdapter:RecyclerView.Adapter<ViewHolder>() {

    private val tvShowList = ArrayList<TvShow>()
    fun setList(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList.addAll(tvShows)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding :ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvShowList[position])
    }

}

class ViewHolder (private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(tvShow:TvShow){
        binding.titleTextView.text = tvShow.name
        binding.descriptionTextView.text= tvShow.overview
        val posterURL = "https://image.tmdb.org/t/p/w500"+tvShow.posterPath

        //Pass  this from out side of the class
        Glide.with(binding.imageView.context)
            .load(posterURL)
            .into(binding.imageView)
    }
}