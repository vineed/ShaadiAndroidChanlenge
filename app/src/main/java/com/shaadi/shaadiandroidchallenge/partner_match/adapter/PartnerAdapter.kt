package com.shaadi.shaadiandroidchallenge.partner_match.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.Disposable
import com.shaadi.shaadiandroidchallenge.R
import com.shaadi.shaadiandroidchallenge.databinding.UserMatchLayoutBinding
import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import com.shaadi.shaadiandroidchallenge.utils.GenericDiffUtil

class PartnerAdapter(
    private val context: Context,
    private var userMatchList: MutableList<UserMatch> = mutableListOf(),
    val onActionListener: (userMatch: UserMatch, isAccepted: Boolean) -> Unit
) :
    RecyclerView.Adapter<PartnerAdapter.PartnerViewHolder>() {

    companion object {
        const val FIELD_FNAME = "first_name"
        const val FIELD_LNAME = "last_name"
        const val FIELD_USERIMAGE = "user_img"
        const val FIELD_USERDESC = "user_desc"
        const val FIELD_USER_STATUS = "user_status"

    }

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val areItemTheSameAction by lazy {
        { oldUserMatchItem: UserMatch, newUserMatch: UserMatch ->
            oldUserMatchItem.uuid == newUserMatch.uuid
        }
    }

    /*private val payloadAction =
        { oldItem: UserMatch, newItem: UserMatch ->
            Bundle().apply {
                putString(
                    FIELD_FNAME,
                    if (oldItem.firstName != newItem.firstName) newItem.firstName else null
                )
                putString(
                    FIELD_LNAME,
                    if (oldItem.lastName != newItem.lastName) newItem.lastName else null
                )
                putString(
                    FIELD_USERIMAGE,
                    if (oldItem.largeImage != newItem.largeImage) newItem.largeImage else null
                )
                putString(
                    FIELD_USERDESC,
                    if (oldItem.shortDesc != newItem.shortDesc) newItem.shortDesc else null
                )
                putInt(
                    FIELD_USER_STATUS,
                    if (oldItem.isAccepted != newItem.isAccepted)
                        newItem.isAccepted.let { if (it == null) -1 else if (it) 1 else 0 }
                    else -1
                )
            }
        }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnerViewHolder {
        return PartnerViewHolder(layoutInflater.inflate(R.layout.user_match_layout, parent, false))
    }


    override fun onBindViewHolder(holder: PartnerViewHolder, position: Int) {
        holder.bindData(userMatchList[position])
    }

    /*override fun onBindViewHolder(
        holder: PartnerViewHolder, position: Int, payloads: MutableList<Any>
    ) {
        val payloadItem = payloads.getOrNull(position)

        if (payloads.isEmpty() || payloadItem == null || payloadItem !is Bundle) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.patchData(payloadItem)
        }
    }*/

    override fun onViewRecycled(holder: PartnerViewHolder) {
        super.onViewRecycled(holder)
        holder.unBind()
    }

    fun setItems(updatedUserMatchList: List<UserMatch>) {
        val userMatchDiffUtil =
            DiffUtil.calculateDiff(
                GenericDiffUtil(
                    this.userMatchList,
                    updatedUserMatchList,
                    areItemTheSameAction/*,
                    payloadAction = payloadAction*/
                )
            )
        userMatchDiffUtil.dispatchUpdatesTo(this)

        this.userMatchList.apply {
            clear()
            addAll(updatedUserMatchList)
        }
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
                tvAccepted.isEnabled = true
                tvDecline.isEnabled = true

                tvAcceptStatus.isVisible = false

                userMatch.isAccepted?.let { isAccepted ->
                    processAcceptedStatus(isAccepted, userMatch)
                }

                tvAccepted.setOnClickListener {
                    onActionListener(userMatch, true)
                }

                tvDecline.setOnClickListener {
                    onActionListener(userMatch, false)
                }
            }
        }

        private fun UserMatchLayoutBinding.processAcceptedStatus(
            isAccepted: Boolean,
            userMatch: UserMatch
        ) {
            tvAccepted.isEnabled = false
            tvDecline.isEnabled = false

            if (isAccepted) {
                tvAcceptStatus.setText(R.string.message_member_accepted)
                tvAcceptStatus.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.accept_green
                    )
                )
                tvAccepted.setBackgroundResource(R.drawable.accepted_selected)
            } else {
                tvAcceptStatus.setText(R.string.message_member_rejected)
                tvAcceptStatus.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.reject_red
                    )
                )
                tvDecline.setBackgroundResource(R.drawable.rejected_selected)
            }

            tvAcceptStatus.isVisible = true

            if (userMatch.animateIsAccepted)
                animateAcceptedPanel(userMatch, tvAcceptStatus)
        }

        private fun animateAcceptedPanel(userMatch: UserMatch, tvAcceptStatus: TextView) {

            val slideUpAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_fade_up)
                .apply {
                    duration = 400
                    interpolator = FastOutSlowInInterpolator()
                }

            tvAcceptStatus.animation = slideUpAnimation

            slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}

                override fun onAnimationEnd(p0: Animation?) {
                    userMatch.animateIsAccepted = false
                }

                override fun onAnimationRepeat(p0: Animation?) {}
            })

            slideUpAnimation.start()
        }

        fun unBind() {
            imageDisposable?.dispose()
        }

        /*fun patchData(payloadItem: Bundle) {
            payloadItem.getString(FIELD_FNAME)?.let {
                userMatchLayoutBinding.tvName.text = it
            }

            payloadItem.getString(FIELD_LNAME)?.let {
                userMatchLayoutBinding.tvName.text = it
            }

            payloadItem.getString(FIELD_USERIMAGE)?.let {
                imageDisposable?.dispose()
                imageDisposable = userMatchLayoutBinding.sivUserImage.load(it) {
                    crossfade(true)
                }
            }

            payloadItem.getString(FIELD_USERDESC)?.let {
                userMatchLayoutBinding.tvUserShortDesc.text = it
            }

            payloadItem.getInt(FIELD_USER_STATUS).let {
                userMatchLayoutBinding.tvDecline.isEnabled = it == -1
                userMatchLayoutBinding.tvAccepted.isEnabled = it == -1

                if (it != -1) {
                    val isAccepted = it != 0

                    userMatchLayoutBinding.tvAcceptStatus.isVisible = isAccepted
                }
            }
        }*/
    }
}