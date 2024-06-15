package com.jay.gsaconversionz.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.gsaconversionz.databinding.ItemMoviesBinding
import com.jay.gsaconversionz.model.data.Search

class MoviesPagingAdapter(
    private val listener: OnItemClickListener
) :
    PagingDataAdapter<Search, MoviesPagingAdapter.ViewHolder>(COMPARATOR) {



    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Search>() {
            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem == newItem
            }

        }
    }



    class ViewHolder(binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var movieTitle = binding.tvMovieTitle
        var movieType = binding.tvMovieType
        var movieYear = binding.tvMovieYear
        val id = binding.tvMovieImdbId
        val movie_image = binding.ivMovieImage

    }

    interface OnItemClickListener {
        fun OnItemClick(search: Search)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.movieYear.text = "Year : ${item.Year}"
            holder.movieTitle.text = item.Title
            holder.movieType.text = "Type: ${ item.Type }"
            holder.id.text=item.imdbID

            holder.itemView.setOnClickListener {
                item.let { listener.OnItemClick(it) }
            }

            Glide.with(holder.movie_image).load(item.Poster).centerCrop().into(holder.movie_image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}