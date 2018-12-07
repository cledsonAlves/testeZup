package br.com.testezup.adapter

import android.view.View
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import br.com.testezup.R
import br.com.testezup.model.FilmeModel


class ProdutoAdapter(

    val filmes: List<FilmeModel>,
    val onClick: (FilmeModel) -> Unit
) :
    RecyclerView.Adapter<ProdutoAdapter.FilmesViewHolder>() {

    // viewHolder com as views
    class FilmesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // views do layout
        var tcodigo = view.findViewById<TextView>(R.id.year)

    }

    override fun getItemCount() = this.filmes.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_filme, parent, false)
        val holder = FilmesViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        val produto = filmes[position]
        val view = holder.itemView
        holder.tcodigo.text = "TESTE"
        holder.itemView.setOnClickListener { onClick(produto) }
    }

}