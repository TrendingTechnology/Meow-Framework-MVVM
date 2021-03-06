/*
 * Copyright (C) 2020 Hamidreza Etebarian & Ali Modares.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.ui.material.buttons

import android.os.Bundle
import meow.ktx.instanceViewModel
import sample.R
import sample.databinding.FragmentButtonsBinding
import sample.ui.base.BaseFragment

/**
 * Material Buttons Fragment.
 *
 * @author  Ali Modares
 * @version 1.0.0
 * @since   2020-03-22
 */

class ButtonsFragment : BaseFragment<FragmentButtonsBinding>() {

    private val viewModel: ButtonsViewModel by instanceViewModel()
    override fun layoutId() = R.layout.fragment_buttons

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btg.addOnButtonCheckedListener { group, checkedId, isChecked ->
            // do stuff
        }
    }

    override fun initViewModel() {
        binding.viewModel = viewModel
    }

}