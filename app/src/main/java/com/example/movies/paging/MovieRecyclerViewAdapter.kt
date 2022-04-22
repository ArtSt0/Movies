package com.example.movies.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.RecyclerViewMovieBinding
import com.example.movies.retrofit.OnItemClickMovie
import com.example.movies.retrofit.models.MovieModel

class MovieRecyclerViewAdapter:
    PagingDataAdapter<MovieModel, MovieRecyclerViewAdapter.MovieHolder>(MovieHolder.REPO_COMPARATOR){

    class MovieHolder(private val binding: RecyclerViewMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(holder: MovieHolder, item: MovieModel?) {
            item?.let {mm->

                mm.multimedia?.let {mmm->
                    Glide.with(holder.itemView.context).load(mmm.src).into(binding.ivAvatar)
                }
                binding.tvName.text = mm.displayTitle
                binding.tvSummaryShort.text = mm.summaryShort
            }

            binding.movie = item
            binding.movieClick = object : OnItemClickMovie {
                override fun onItemClickMovie(movieModel: MovieModel) {
                    /*
                    val intent = Intent(binding.root.context, DetailMoviesActivity::class.java)
                    intent.putExtra("Movies",item)
                    binding.root.context.startActivity(intent)
                    */
                }
            }
        }

        companion object{
            val REPO_COMPARATOR = object : DiffUtil.ItemCallback<MovieModel>(){
                override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                    return oldItem.displayTitle==newItem.displayTitle
                }

                override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                    return oldItem==newItem
                }
            }

            fun from(parent: ViewGroup): MovieHolder {
                val binding = DataBindingUtil.inflate<RecyclerViewMovieBinding>(
                    LayoutInflater.from(parent.context), R.layout.recycler_view_movie, parent, false)
                return MovieHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(holder, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder.from(parent)
    }
}
