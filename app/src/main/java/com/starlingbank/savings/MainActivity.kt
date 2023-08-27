package com.starlingbank.savings

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.starlingbank.savings.databinding.MainLayoutBinding
import com.starlingbank.savings.viewmodels.RoundUpViewModel
import com.starlingbank.savings.viewmodels.SavingGoalTransferViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), OnClickListener {

    private lateinit var binding: MainLayoutBinding
    private lateinit var roundUpViewModel: RoundUpViewModel
    private lateinit var savingGoalTransferViewModel: SavingGoalTransferViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roundUpViewModel = ViewModelProvider(this)[RoundUpViewModel::class.java]
        roundUpViewModel.getAccounts()

        roundUpViewModel.savingsAmount?.observe(this) {
            binding.roundedUpTv.text = it
        }

        savingGoalTransferViewModel = ViewModelProvider(this)[SavingGoalTransferViewModel::class.java]
        binding.addMoneyBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.addMoneyBtn -> {
                val amount = binding.roundedUpTv.text.toString().replace("GBP ", "").toDouble()
                savingGoalTransferViewModel.transferTowardsSavingsGoal(amount)

                savingGoalTransferViewModel.success.observe(this) {
                    if (it)
                        Toast.makeText(this, "transfer towards savings goal succeeded", Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this, "transfer towards savings goal failed", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}