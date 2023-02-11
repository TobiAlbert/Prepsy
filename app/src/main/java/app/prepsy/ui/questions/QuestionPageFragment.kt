package app.prepsy.ui.questions

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.ViewCompat
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.prepsy.R
import app.prepsy.databinding.FragmentQuestionPageBinding
import app.prepsy.managers.SharedPreferenceManagers
import app.prepsy.managers.SharedPreferenceManagers.Companion.HAS_SWIPED
import app.prepsy.ui.models.Question
import app.prepsy.ui.models.UserScore
import app.prepsy.ui.models.args.QuestionPageMode
import app.prepsy.ui.models.args.ResultFragmentPayload
import app.prepsy.ui.questions.adapters.QuestionPageAdapter
import app.prepsy.ui.questions.dialog.QuestionNavigationDialog
import app.prepsy.utils.getActionSnackBar
import app.prepsy.utils.onPageSelected
import app.prepsy.vendors.ads.AdEvent
import app.prepsy.vendors.ads.IAdManager
import app.prepsy.vendors.ads.listenForUpdates
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class QuestionPageFragment : Fragment() {

    @Inject
    lateinit var adManager: IAdManager

    @Inject
    lateinit var sharedPrefsManager: SharedPreferenceManagers
    private val questionViewModel: QuestionViewModel by activityViewModels()
    private val args: QuestionPageFragmentArgs by navArgs()

    private lateinit var mSnackBar: Snackbar

    private var _binding: FragmentQuestionPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        questionViewModel.getTestQuestions(args.args.subjectId, args.args.yearId)
        setupUi()
        observeViewModel()
    }

    private fun setupUi() {
        // handle back button press
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    dismissSnackBarIfShown()
                    findNavController().navigateUp()
                }
            })

        val mode: QuestionPageMode = args.args.mode

        // setup toolbar
        binding.toolbar.inflateMenu(R.menu.menu_question)
        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_submit -> {
                    questionViewModel.onSubmitClicked(
                        subjectId = args.args.subjectId,
                        yearId = args.args.yearId
                    ).observe(viewLifecycleOwner, Observer { isComplete: Boolean ->
                        when (isComplete) {
                            true -> calculateUserScore()
                            else -> showIncompleteQuestionDialog()
                        }
                    })
                    true
                }
                else -> false
            }
        }

        if (mode == QuestionPageMode.VIEW_ANSWER_MODE) {
            // hide the submit button
            binding.toolbar.menu.findItem(R.id.menu_submit).apply {
                isVisible = false
                isEnabled = false
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            dismissSnackBarIfShown()
            findNavController().navigateUp()
        }

        val topPadding = binding.appbar.paddingTop
        ViewCompat.setOnApplyWindowInsetsListener(binding.appbar) { v, insets ->
            v.updatePadding(top = insets.systemWindowInsetTop + topPadding)
            insets
        }

        val marginBottom = binding.adView.marginBottom
        ViewCompat.setOnApplyWindowInsetsListener(binding.adView) { v, insets ->
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                leftMargin = insets.systemWindowInsetLeft
                rightMargin = insets.systemWindowInsetRight
                bottomMargin = insets.systemWindowInsetBottom + marginBottom
            }
            insets
        }

        adManager.loadAd(binding.adView)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            binding.adView.listenForUpdates().collect { event: AdEvent ->
                when (event) {
                    // send ad error to crashlytics
                    is AdEvent.AdFailedToLoad -> {
                        val exception = Exception(event.toString())
                        FirebaseCrashlytics.getInstance().recordException(exception)
                    }

                    // do nothing for other events
                    else -> Unit
                }
            }
        }
    }

    private fun observeViewModel() {
        questionViewModel.getQuestions().observe(viewLifecycleOwner, Observer {
            it?.let { questions: List<Question> ->

                val questionPageAdapter = QuestionPageAdapter(
                    this@QuestionPageFragment,
                    questions,
                    args.args.mode
                )

                val numOfQuestions = questions.size

                binding.viewpager.adapter = questionPageAdapter
                binding.viewpager.onPageSelected { position: Int ->
                    val currentQuestionIndex = position + 1

                    binding.progressBar.progress = (currentQuestionIndex * 100 / numOfQuestions)
                    binding.questionNumber.text =
                        getString(
                            R.string.page_question_number,
                            currentQuestionIndex,
                            numOfQuestions
                        )
                }

                // show swipe navigation info after questions have been fetched and displayed
                val hasSwiped: Boolean =
                    sharedPrefsManager.getBoolean(HAS_SWIPED)

                val swipeCallback: (View) -> Unit =
                    { sharedPrefsManager.saveBoolean(HAS_SWIPED, true) }

                when {
                    !hasSwiped -> showSwipeInfoSnackBar(swipeCallback)
                }
            }
        })

        // allows for observing the answers a user has selected
        questionViewModel.getQuestionsFlow(
            args.args.subjectId,
            args.args.yearId
        ).observe(viewLifecycleOwner, Observer { questions ->

            binding.questionNumber.setOnClickListener {
                QuestionNavigationDialog.newInstance(
                    questions = questions,
                    mode = args.args.mode
                )
                    .apply {
                        isCancelable = true
                        onQuestionSelected =
                            { position: Int -> binding.viewpager.currentItem = position }
                    }
                    .show(requireActivity().supportFragmentManager, "")
            }
        })
    }

    private fun showSwipeInfoSnackBar(callback: (View) -> Unit) {
        mSnackBar = binding.root.getActionSnackBar(
            R.string.swipe_message,
            R.string.swipe_action_text,
            callback
        )

        mSnackBar.show()
    }

    private fun calculateUserScore() {
        questionViewModel.calculateScore(
            args.args.subjectId,
            args.args.yearId
        ).observe(viewLifecycleOwner, Observer { userScore: UserScore ->
            dismissSnackBarIfShown()

            val payload = ResultFragmentPayload(
                userScore = userScore,
                subjectId = args.args.subjectId,
                yearId = args.args.yearId
            )

            val action =
                QuestionPageFragmentDirections.actionQuestionPageFragmentToResultFragment(payload)

            findNavController().navigate(action)
        })
    }

    private fun showIncompleteQuestionDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.incomplete_question_dialog_title))
            setMessage(getString(R.string.incomplete_question_dialog_message))
            setNegativeButton(getString(android.R.string.cancel)) { dialog: DialogInterface?, _: Int -> dialog?.dismiss() }
            setPositiveButton(getString(android.R.string.ok)) { _: DialogInterface?, _: Int -> calculateUserScore() }
            setCancelable(true)

            create()
            show()
        }
    }

    private fun dismissSnackBarIfShown() {
        if (::mSnackBar.isInitialized && mSnackBar.isShown) {
            mSnackBar.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}