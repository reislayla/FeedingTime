package com.example.feedingtime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_lista.view.*


class DataAdapter (var list: ArrayList<DatabaseModel>) :RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is ViewHolder ->{
                holder.bind(list.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitList(animalList: List<DatabaseModel>){
        list = animalList as ArrayList<DatabaseModel>
    }

    //without image
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var image : ImageView = itemView.imageView
        var name : TextView = itemView.textView
        var breed: TextView = itemView.textView2


        fun bind(databaseModel: DatabaseModel){

            val url = "https://firebasestorage.googleapis.com/v0/b/feedingtime-3213a.appspot.com/o/images%2Flabrador.jpg?alt=media&token=801012de-5974-4d04-a3e4-c214cfcdf25c"

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(url)
                .into(image)
            name.text = databaseModel.animalname
            breed.text = databaseModel.animalbreed

        }
    }

}