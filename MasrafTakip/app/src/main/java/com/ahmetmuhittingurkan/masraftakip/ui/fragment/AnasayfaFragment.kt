package com.ahmetmuhittingurkan.masraftakip.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetmuhittingurkan.masraftakip.R
import com.ahmetmuhittingurkan.masraftakip.data.entity.ButceItem
import com.ahmetmuhittingurkan.masraftakip.databinding.FragmentAnasayfaBinding
import com.ahmetmuhittingurkan.masraftakip.ui.adapter.AnasayfaAdapter
import com.ahmetmuhittingurkan.masraftakip.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment(), PopupMenu.OnMenuItemClickListener{

    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel:AnasayfaViewModel
    private lateinit var popup:PopupMenu
    private lateinit var anasayfaAdapter: AnasayfaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaViewModel by viewModels()
        viewModel=tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentAnasayfaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener { fabTikla(it) }

        popup= PopupMenu(requireContext(),binding.fab)
        val inflater=popup.menuInflater
        inflater.inflate(R.menu.my_popup_menu,popup.menu)
        popup.setOnMenuItemClickListener(this)

        viewModel.getButceItems().observe(viewLifecycleOwner) { butceItems ->
            anasayfaAdapter = AnasayfaAdapter(butceItems) { item ->
                when (item) {
                    is ButceItem.GelirItem -> viewModel.silGelir(item.gelir)
                    is ButceItem.GiderItem -> viewModel.silGider(item.gider)
                }
            }

            binding.recyclerView.apply {
                adapter = anasayfaAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        viewModel.getToplamGelir().observe(viewLifecycleOwner) { toplamGelir ->
            binding.gelir.text= "${toplamGelir.toInt()}₺"
        }

        viewModel.getToplamGider().observe(viewLifecycleOwner) { toplamGider ->
            binding.gider.text = "${toplamGider.toInt()}₺"
        }

        viewModel.getKalan().observe(viewLifecycleOwner) { kalan ->
            if (kalan < 0) {
                binding.kalan.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            } else {
                binding.kalan.setTextColor(ContextCompat.getColor(requireContext(), R.color.defaultColor))
            }
            binding.kalan.text = "${kalan}₺"
        }

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId==R.id.giderItem){
            val gecis= AnasayfaFragmentDirections.actionAnasayfaFragmentToGiderFragment()
            Navigation.findNavController(requireView()).navigate(gecis)
        }
        else if (item?.itemId==R.id.gelirItem){
            val gecis= AnasayfaFragmentDirections.actionAnasayfaFragmentToGelirFragment()
            Navigation.findNavController(requireView()).navigate(gecis)
        }
        return true
    }

    fun fabTikla(view:View) {
        popup.show()
    }

}