package com.marcpetit.chalkboardtechtest.presentation.birthdaylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcpetit.chalkboardtechtest.databinding.BirthdayListItemBinding
import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import java.time.format.DateTimeFormatter

class BirthdayListAdapter(private val birthdayList: List<BirthdayDomainModel>,
                          private val listener: (BirthdayDomainModel) -> Unit)
    : RecyclerView.Adapter<BirthdayListAdapter.BirthdayListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthdayListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindingView = BirthdayListItemBinding.inflate(inflater, parent, false)
        return BirthdayListViewHolder(bindingView, listener)
    }

    override fun onBindViewHolder(holder: BirthdayListViewHolder, position: Int) =
        holder.bind(birthdayList[position])

    override fun getItemCount(): Int = birthdayList.size

    class BirthdayListViewHolder(
        private val bindingView: BirthdayListItemBinding,
        private val listener: (BirthdayDomainModel) -> Unit
    ):
        RecyclerView.ViewHolder(bindingView.root) {
        fun bind(item: BirthdayDomainModel) {
            bindingView.apply {
                firstLetterTv.text = "${item.firstName.first()}${item.lastName.first()}"
                nameTv.text = "${item.firstName} ${item.lastName}"
                dobTv.text = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(item.dateOfBirth)
                itemView.setOnClickListener{listener(item)}
            }
        }
    }
}