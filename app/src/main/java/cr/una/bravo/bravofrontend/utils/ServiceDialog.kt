package cr.una.bravo.bravofrontend.utils

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import cr.una.bravo.bravofrontend.databinding.DialogServiceInputBinding
import cr.una.bravo.bravofrontend.model.Service
import cr.una.bravo.bravofrontend.viewmodel.ServiceViewModel

class ServiceDialog(
    private val onSubmitClickListener: (Service) -> Unit
): DialogFragment() {
    private lateinit var binding : DialogServiceInputBinding
    private var itemList: Array<String> = arrayOf("Cambio de aceite", "Mantenimiento preventivo", "Mantenimiento de neumaticos")
    private lateinit var itemSelect : String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogServiceInputBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)


        val spinnerService: Spinner = binding.spinnerServices
        val adapterService: ArrayAdapter<String> = ArrayAdapter<String>(
            activity?.applicationContext!!,
            R.layout.simple_spinner_dropdown_item,
            itemList
        )
        spinnerService.adapter = adapterService

        binding.bAddQuantity.setOnClickListener {
            val service = Service(
                name = spinnerService.selectedItem.toString(),
                observations = binding.etAmount.text.toString(),
                state = "INCOMPLETO",
            )
            onSubmitClickListener.invoke(service)
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}