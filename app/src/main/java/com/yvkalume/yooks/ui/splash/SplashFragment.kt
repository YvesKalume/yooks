package com.yvkalume.yooks.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.airbnb.mvrx.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.yooks.R
import com.yvkalume.yooks.activity.main.MainActivity
import com.yvkalume.yooks.activity.splash.SplashScreenActivity
import com.yvkalume.yooks.databinding.FragmentSplashBinding
import com.yvkalume.yooks.ui.splash.SplashFragment.RequestCode.RC_GOOGLE_SIGN_IN
import com.yvkalume.yooks.util.runAnimationUp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment(R.layout.fragment_splash), MavericksView {

    private val binding: FragmentSplashBinding by viewBinding()
    private val viewModel: SplashViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpListener()
    }

    private fun setUpListener() {
        binding.googleSignin.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun checkAuthentication(isAuth: Boolean) {
        if (isAuth) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        } else {
            binding.googleSignin.runAnimationUp()
            binding.googleSignin.isVisible = true
        }
    }

    private fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        startActivityForResult(googleSignInClient.signInIntent, RC_GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                viewModel.signIn(account)
                binding.loader.isVisible = true

                Log.d("SplashScreenActivity", "firebaseAuthWithGoogle:" + account.id)
            } catch (e: ApiException) {
                // Google Sign In failed
                // TODO :  update UI appropriately
                Log.w("SplashScreenActivity", "Google sign in failed", e)

            }
        }
    }

    companion object RequestCode {
        const val RC_GOOGLE_SIGN_IN = 1
    }

    override fun invalidate() = withState(viewModel) {

        when(it.isAuthUser) {
            is Success -> checkAuthentication(it.isAuthUser.invoke())
            is Fail -> {
                Toast.makeText(requireContext(),"Une Erreur s'est produite",Toast.LENGTH_LONG).show()
            }

        }
    }

}