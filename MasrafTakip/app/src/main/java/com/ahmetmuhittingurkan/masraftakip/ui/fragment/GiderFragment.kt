package com.ahmetmuhittingurkan.masraftakip.ui.fragment

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.ahmetmuhittingurkan.masraftakip.R
import com.ahmetmuhittingurkan.masraftakip.data.entity.GelirEntity
import com.ahmetmuhittingurkan.masraftakip.data.entity.GiderEntity
import com.ahmetmuhittingurkan.masraftakip.databinding.FragmentGiderBinding
import com.ahmetmuhittingurkan.masraftakip.ui.viewmodel.GiderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GiderFragment : Fragment() {

    private lateinit var binding: FragmentGiderBinding
    private lateinit var viewModel: GiderViewModel
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: GiderViewModel by viewModels()
        viewModel=tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentGiderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonKaydet.setOnClickListener { kayitGider(it) }
        binding.buttonTarih.setOnClickListener { showDatePickerDialog() }
    }

    private fun kayitGider(view: View) {
        val miktarText = binding.giderMiktari.text.toString()
        val not = binding.giderBilgisi.text.toString()
        val tarih = selectedDate

        if (miktarText.isNotBlank() && not.isNotBlank() && !tarih.isNullOrBlank()) {
            val miktar = miktarText.toDoubleOrNull()

            if (miktar != null) {
                val yeniGider = GiderEntity(0, miktar, not, tarih)
                viewModel.kayitGider(yeniGider)
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