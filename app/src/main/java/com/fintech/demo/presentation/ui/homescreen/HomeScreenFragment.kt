package com.fintech.demo.presentation.ui.homescreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.core.domain.CardDetails
import com.fintech.demo.R
import com.fintech.demo.databinding.FragmentHomeScreenBinding
import com.fintech.demo.presentation.adapters.CardListAdapter
import com.fintech.demo.presentation.adapters.TransactionAdapter
import com.fintech.demo.presentation.ui.alert.Alerter
import com.fintech.demo.presentation.utils.BOTTOM_TO_TOP_DURATION
import com.fintech.demo.presentation.utils.animateFromBottomToTop
import com.fintech.demo.presentation.utils.obtainViewModel
import java.util.UUID

class HomeScreenFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeScreenBinding
    private val mAlerter by lazy { Alerter() }
    private val mHomeScreenViewModel by lazy {
        requireActivity().obtainViewModel(HomeScreenViewModel::class.java)
    }
    private val mCardListAdapter by lazy {
        CardListAdapter { cardData ->

        }
    }
    private val mTransactionAdapter by lazy {
        TransactionAdapter { cardData ->

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHomeScreenViewModel.setup()

        loadData()

        animateTheViews()
        setupScrollBackground()

        setUpRecycler()
    }

    private fun loadData() {
        mHomeScreenViewModel.isDataPresent {
            if (!it) {
                saveCardDetails()
                mHomeScreenViewModel.getCardDetails()
            }
        }

        mHomeScreenViewModel.getCardDetails()
    }

    private fun saveCardDetails() {
        val cardDetailsList = listOf(
            CardDetails(
                id = UUID.randomUUID().toString(),
                cardName = "VISA",
                cardType = "David Card",
                cardBalance = "$1,314.75",
                cardNumber = "455645645454545",
                cardExpiryDate = "09/27",
                backgroundImage = "https://res.cloudinary.com/dzklqz4hm/image/upload/v1721042438/Group_1321315031_jeboqs.png",
                isVisible = true
            ),
            CardDetails(
                id = UUID.randomUUID().toString(),
                cardName = "VISA",
                cardType = "David Card",
                cardBalance = "$1,314.75",
                cardNumber = "455645645454545",
                cardExpiryDate = "09/27",
                backgroundImage = "https://res.cloudinary.com/dzklqz4hm/image/upload/v1721042438/Group_1321315035_vxfzms.png",
                isVisible = true
            ),
            CardDetails(
                id = UUID.randomUUID().toString(),
                cardName = "VISA",
                cardType = "David Card",
                cardBalance = "$1,314.75",
                cardNumber = "455645645454545",
                cardExpiryDate = "09/27",
                backgroundImage = "https://res.cloudinary.com/dzklqz4hm/image/upload/v1721042438/Group_1321315033_pvxpc3.png",
                isVisible = true
            ),
            CardDetails(
                id = UUID.randomUUID().toString(),
                cardName = "VISA",
                cardType = "David Card",
                cardBalance = "$1,314.75",
                cardNumber = "455645645454545",
                cardExpiryDate = "09/27",
                backgroundImage = "https://res.cloudinary.com/dzklqz4hm/image/upload/v1721042438/Group_1321315034_iml6he.png",
                isVisible = true
            ),
        )
        mHomeScreenViewModel.saveCardDetails(
            cardDetailsList
        ) {
            mAlerter.showSuccess("Created", it, requireActivity())
        }
    }

    private fun setupScrollBackground() {
        mBinding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {}

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float,
            ) {
            }

            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float,
            ) {
                if (progress < 0.95) {
                    mBinding.nestedScrollView.background = ContextCompat.getDrawable(
                        requireActivity(), R.drawable.tops_cornered_white_bg
                    )
                }
            }
        })
    }

    private fun animateTheViews() {
        mBinding.nestedScrollView.visibility = View.INVISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            mBinding.nestedScrollView.animateFromBottomToTop()
        }, BOTTOM_TO_TOP_DURATION)
    }

    private fun HomeScreenViewModel.setup() {
        getContactList()

        getCardDetailsLiveData.observe(viewLifecycleOwner) {
            mCardListAdapter.addAll(it)
        }
        getContactLiveData.observe(viewLifecycleOwner) {
            mTransactionAdapter.addAll(it)
        }
    }

    private fun setUpRecycler() {
        mBinding.rvHomeContainer.adapter = mCardListAdapter
        mBinding.rvTransaction.adapter = mTransactionAdapter
    }

    companion object {
        const val TASK_ID = "TASK_ID"
    }
}