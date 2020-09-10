package com.myk.feature.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myk.feature.teams.databinding.TeamsFragmentBinding

class TeamsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TeamsFragmentBinding.inflate(inflater, container, false).root
    }
}
