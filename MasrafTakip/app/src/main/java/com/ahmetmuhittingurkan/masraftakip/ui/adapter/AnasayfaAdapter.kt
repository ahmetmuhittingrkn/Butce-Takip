package com.ahmetmuhittingurkan.masraftakip.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmetmuhittingurkan.masraftakip.data.entity.ButceItem
import com.ahmetmuhittingurkan.masraftakip.data.entity.GelirEntity
import com.ahmetmuhittingurkan.masraftakip.data.entity.GiderEntity
import com.ahmetmuhittingurkan.masraftakip.databinding.RecyclerRowBinding
import com.ahmetmuhittingurkan.masraftakip.ui.viewmodel.AnasayfaViewModel
import com.google.android.material.snackbar.Snackbar

class AnasayfaAdapter(val butceList: List<ButceItem>,val onDeleteClick: (ButceItem) -> Unit): RecyclerView.Adapter<AnasayfaAdapter.ButceHolder>() {

    inner class ButceHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButceHolder {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ButceHolder(binding)
    }

    override fun onBindViewHolder(holder: ButceHolder, position: Int) {
        val item= butceList[position]
        when(item) {
            is ButceItem.GelirItem -> {
                holder.binding.textViewMiktar.text="+${item.gelir.miktar.toInt()}₺"
                holder.binding.textViewNot.text=item.gelir.bilgi
                holder.binding.textViewTarih.text=item.gelir.tarih
            }
            is ButceItem.GiderItem -> {
                holder.binding.textViewMiktar.text="-${item.gider.miktar.toInt()}₺"
                holder.binding.textViewNot.text=item.gider.bilgi
                holder.binding.textViewTarih.text=item.gider.tarih
            }
        }
        holder.binding.imageViewSil.setOnClickListener {
            Snackbar.make(holder.itemView,"Bu kaydı silmek istediğinizden emin misiniz?",Snackbar.LENGTH_LONG)
                .setAction("Evet"){
                    onDeleteClick(item)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return butceList.size
    }
}