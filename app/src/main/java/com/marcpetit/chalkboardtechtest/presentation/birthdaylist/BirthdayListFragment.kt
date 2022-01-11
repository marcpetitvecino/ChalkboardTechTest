package com.marcpetit.chalkboardtechtest.presentation.birthdaylist

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcpetit.chalkboardtechtest.R
import com.marcpetit.chalkboardtechtest.databinding.FragmentBirthdayListBinding
import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import com.marcpetit.chalkboardtechtest.presentation.birthdaydetail.ARG_BIRTHDAY
import org.koin.androidx.viewmodel.ext.android.viewModel

class BirthdayListFragment : Fragment() {

    private val viewModel: BirthdayListFragmentViewModel by viewModel()

    private var bindingView: FragmentBirthdayListBinding? = null

    private lateinit var progressBar: ProgressBar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.dispatch(BirthdayListEvent.InitEvent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingView = FragmentBirthdayListBinding.inflate(layoutInflater)
        return bindingView?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createProgressBar()
        initObservers()
    }

    private fun createProgressBar() {
        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyle)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT)
        progressBar.progressTintList = ColorStateList.valueOf(Color.RED)
        progressBar.isIndeterminate = true
        bindingView?.root?.addView(progressBar, params)
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)
    }

    private fun handleViewState(state: BirthdayListViewState) {
        when (state) {
            BirthdayListViewState.Error -> showErrorMessage()
            BirthdayListViewState.Loading -> showLoadingView()
            is BirthdayListViewState.ShowData -> setupList(state.birthdayList)
        }
    }

    private fun setupList(birthdayList: List<BirthdayDomainModel>) {
        val adapter = BirthdayListAdapter(birthdayList, ::handleClick)
        bindingView?.birthdayList?.adapter = adapter
        bindingView?.birthdayList?.apply {
            addItemDecoration(DividerItemDecoration(this.context,
                (layoutManager as LinearLayoutManager).orientation))
        }

        progressBar.visibility = View.GONE
    }

    private fun handleClick(item: BirthdayDomainModel) {
        findNavController().navigate(
            R.id.action_birthdayListFragment_to_birthdayDetailFragment,
            Bundle().apply {
                putParcelable(ARG_BIRTHDAY, item)
            }
        )
    }

    private fun showErrorMessage() {
        Toast.makeText(context, "There was an error", Toast.LENGTH_LONG).show()
    }

    private fun showLoadingView() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingView = null
    }
}