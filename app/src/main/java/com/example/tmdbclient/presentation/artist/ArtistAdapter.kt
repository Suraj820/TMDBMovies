package com.example.tmdbclient.presentation.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.databinding.ListItemBinding


class ArtistAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val artistList = ArrayList<Artist>()
    fun setList(artist: List<Artist>) {
        artistList.clear()
        artistList.addAll(artist)

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
        return artistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artistList[position])
    }
}

class ViewHolder (private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(artist: Artist){
        binding.titleTextView.text = artist.name
        binding.descriptionTextView.text= artist.popularity.toString()
        val posterURL = "https://image.tmdb.org/t/p/w500"+artist.profilePath

        //Pass  this from out side of the class
        Glide.with(binding.imageView.context)
            .load(posterURL)
            .into(binding.imageView)
    }
}