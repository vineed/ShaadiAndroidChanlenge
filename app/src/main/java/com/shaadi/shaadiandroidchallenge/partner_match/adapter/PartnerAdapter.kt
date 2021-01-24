package com.shaadi.shaadiandroidchallenge.partner_match.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.Disposable
import com.shaadi.shaadiandroidchallenge.R
import com.shaadi.shaadiandroidchallenge.databinding.UserMatchLayoutBinding
import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch

class PartnerAdapter(
    private val context: Context,
    private var userMatchList: List<UserMatch> = mutableListOf(),
    val onActionListener: (userMatch: UserMatch, isAccepted: Boolean) -> Unit
) :
    RecyclerView.Adapter<PartnerAdapter.PartnerViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnerViewHolder {
        return PartnerViewHolder(layoutInflater.inflate(R.layout.user_match_layout, parent, false))
    }

    override fun onBindViewHolder(holder: PartnerViewHolder, position: Int) {
        holder.bindData(userMatchList[position])
    }

    override fun onViewRecycled(holder: PartnerViewHolder) {
        super.onViewRecycled(holder)
        holder.unBind()
    }

    fun setItems(userMatchList: List<UserMatch>) {
        this.userMatchList = userMatchList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = userMatchList.size

    inner class PartnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var userMatchLayoutBinding: UserMatchLayoutBinding =
            UserMatchLayoutBinding.bind(itemView)

        private var imageDisposable: Disposable? = null

        fun bindData(userMatch: UserMatch) {
            userMatchLayoutBinding.apply {
                tvName.text = userMatch.displayName
                tvUserShortDesc.text = userMatch.shortDesc

                if (userMatch.thumbnail.isNotBlank()) {
                    imageDisposable = sivUserImage.load(userMatch.largeImage) {
                        crossfade(true)
                    }
                }

                tvAccepted.setBackgroundResource(R.drawable.accepted_unselected)
                tvDecline.setBackgroundResource(R.drawable.rejected_unselected)

                userMatch.isAccepted?.let { isAccepted ->
                    if (isAccepted) {
                        tvAccepted.setBackgroundResource(R.drawable.accepted_selected)
                    } else {
                        tvDecline.setBackgroundResource(R.drawable.rejected_selected)
                    }
                }

                tvAccepted.setOnClickListener {
                    onActionListener(userMatch, true)
                }

                tvDecline.setOnClickListener {
                    onActionListener(userMatch, false)
                }
            }
        }

        fun unBind() {
            imageDisposable?.dispose()
        }
    }
}