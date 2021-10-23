package com.arbonik.soft_logic_test.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arbonik.soft_logic_test.R
import com.arbonik.soft_logic_test.data.allMovies.Movy
import com.arbonik.soft_logic_test.databinding.MovieItemBinding
import com.bumptech.glide.Glide

class MoviesAdapter(context: Context) : PagingDataAdapter<Movy, MovyViewHolder>(
    MoviesDiffItemCallback,
) {
    private val inflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: MovyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovyViewHolder {
        return MovyViewHolder(inflater.inflate(R.layout.movie_item, parent, false))
    }
}

class MovyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val viewBinding = MovieItemBinding.bind(itemView)
    fun bind(movy: Movy?) {
        Glide.with(itemView).load("https://"+movy?.poster).into(viewBinding.image)
        viewBinding.title.text = movy?.title
    }
}

private object MoviesDiffItemCallback : DiffUtil.ItemCallback<Movy>() {
    override fun areItemsTheSame(oldItem: Movy, newItem: Movy): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movy, newItem: Movy): Boolean {
        return oldItem.id == newItem.id
    }
}