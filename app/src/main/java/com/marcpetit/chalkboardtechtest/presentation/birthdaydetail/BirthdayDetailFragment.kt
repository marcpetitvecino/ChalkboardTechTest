package com.marcpetit.chalkboardtechtest.presentation.birthdaydetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.marcpetit.chalkboardtechtest.databinding.FragmentBirthdayDetailBinding
import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel

const val ARG_BIRTHDAY = "Birthday"

class BirthdayDetailFragment : Fragment() {

    private var bindingView: FragmentBirthdayDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingView = FragmentBirthdayDetailBinding.inflate(layoutInflater)
        return bindingView?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val birthdayItem: BirthdayDomainModel? = arguments?.getParcelable(ARG_BIRTHDAY)
        setupView(birthdayItem)
        initListeners()
    }

    private fun setupView(birthdayItem: BirthdayDomainModel?) {
        bindingView?.apply {
            birthdayItem?.let {
                firstLetterTv.text = "${it.firstName.first()}${it.lastName.first()}"
                nameTv.text = "${it.firstName} ${it.lastName}"
                ageTv.text = if (it.age == 1) "${it.age} year old" else "${it.age} years old"
            }
        }
    }

    private fun initListeners() {
        bindingView?.goBackBtn?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingView = null
    }

}