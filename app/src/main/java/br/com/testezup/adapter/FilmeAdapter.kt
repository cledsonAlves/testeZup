package br.com.testezup.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.testezup.R
import br.com.testezup.model.FilmeModel


class ProdutoAdapter(

    val filmes: List<FilmeModel>,
    val onClick: (FilmeModel) -> Unit
) :
    RecyclerView.Adapter<ProdutoAdapter.FilmesViewHolder>() {

    // viewHolder com as views
    class FilmesViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }

    override fun getItemCount() = this.filmes.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        val holder = FilmesViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        val produto = filmes[position]
        val view = holder.itemView
        holder.itemView.setOnClickListener { onClick(produto) }
    }

}
