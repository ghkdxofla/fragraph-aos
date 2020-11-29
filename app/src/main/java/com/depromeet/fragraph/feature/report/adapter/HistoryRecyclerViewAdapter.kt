package com.depromeet.fragraph.feature.report.adapter

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.fragraph.R
import com.depromeet.fragraph.base.ui.IRecyclerViewAdapter
import com.depromeet.fragraph.core.ext.FRAGRAPH_HISTORY_FORMAT
import com.depromeet.fragraph.core.ext.miliSecondsToStringFormat
import com.depromeet.fragraph.databinding.ItemHistoryBinding
import com.depromeet.fragraph.feature.report.model.HistoryUiModel
import timber.log.Timber

class HistoryRecyclerViewAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val scale: Float,
    private val firstScrollCallback: (position: Int) -> Unit
): RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>(), IRecyclerViewAdapter<HistoryUiModel> {

    private val historyList = mutableListOf<HistoryUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.item_history, parent, false)
        binding.flHistoryFront.cameraDistance = scale
        binding.flHistoryBack.cameraDistance = scale
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Timber.d("position: ${holder.bindingAdapterPosition}")
        holder.bind(historyList[position], lifecycleOwner)
    }

    override fun getItemCount(): Int = historyList.size

    override fun setItems(data: List<HistoryUiModel>) {
        if (historyList.size == 0) {
            historyList.clear()
            historyList.addAll(data)
            notifyDataSetChanged()
            val position = historyList.indexOfFirst {
                it.createdAt.miliSecondsToStringFormat(FRAGRAPH_HISTORY_FORMAT) == System.currentTimeMillis().miliSecondsToStringFormat(FRAGRAPH_HISTORY_FORMAT)
            }
            firstScrollCallback(position)
        } else {
            historyList.clear()
            historyList.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun changeCenterValue(position: Int, isCenter: Boolean) {
        historyList[position].changeCenterPosition(isCenter)
    }

    class ViewHolder(
        val binding: ItemHistoryBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(history: HistoryUiModel, lifecycleOwner: LifecycleOwner) {
            binding.history = history
            binding.lifecycleOwner = lifecycleOwner

            val mSetRightIn: AnimatorSet = AnimatorInflater.loadAnimator(itemView.context, R.animator.hisotry_flip_right_in) as AnimatorSet
            val mSetRightOut: AnimatorSet = AnimatorInflater.loadAnimator(itemView.context, R.animator.hisotry_flip_right_out) as AnimatorSet
            val mSetLeftIn: AnimatorSet = AnimatorInflater.loadAnimator(itemView.context, R.animator.hisotry_flip_left_in) as AnimatorSet
            val mSetLeftOut: AnimatorSet = AnimatorInflater.loadAnimator(itemView.context, R.animator.hisotry_flip_left_out) as AnimatorSet

            binding.flHistoryItem.setOnClickListener {
                if (!history.isBack) {
                    mSetRightOut.setTarget(binding.flHistoryFront)
                    mSetLeftIn.setTarget(binding.flHistoryBack)
                    mSetRightOut.start()
                    mSetLeftIn.start()
                    history.isBack = true
                } else {
                    mSetRightIn.setTarget(binding.flHistoryFront)
                    mSetLeftOut.setTarget(binding.flHistoryBack)
                    mSetRightIn.start()
                    mSetLeftOut.start()
                    history.isBack = false
                }
            }
        }
    }
}