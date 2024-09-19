package com.ahmetmuhittingurkan.masraftakip.ui.fragment

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.ahmetmuhittingurkan.masraftakip.R
import com.ahmetmuhittingurkan.masraftakip.data.entity.GelirEntity
import com.ahmetmuhittingurkan.masraftakip.databinding.FragmentGelirBinding
import com.ahmetmuhittingurkan.masraftakip.ui.viewmodel.GelirViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GelirFragment : Fragment() {

    private lateinit var binding: FragmentGelirBinding
    private lateinit var viewModel: GelirViewModel
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: GelirViewModel by viewModels()
        viewModel=tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentGelirBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonKaydet.setOnClickListener { kayitGelir(it) }
        binding.buttonTarih.setOnClickListener { showDatePickerDialog() }
    }

    private fun kayitGelir(view: View) {
        val miktarText = binding.gelirMiktari.text.toString()
        val not = binding.gelirBilgisi.text.toString()
        val tarih = selectedDate

        if (miktarText.isNotBlank() && not.isNotBlank() && !tarih.isNullOrBlank()) {
            val miktar = miktarText.toDoubleOrNull()

            if (miktar != null) {
                val yeniGelir = GelirEntity(0, miktar, not, tarih)
                viewModel.kayitGelir(yeniGelir)
                Toast.makeText(requireContext(), "Gelir bilgileri başarıyla eklendi!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Geçerli bir miktar giriniz!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Lütfen tüm alanları doldurunuz ve bir tarih seçiniz!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            // Seçilen tarihi TextView'e yaz
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        }, year, month, day)

        datePickerDialog.show()

    }
}