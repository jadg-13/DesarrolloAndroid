package online.jadg13.ejemplomenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import online.jadg13.ejemplomenu.databinding.FragmentMenuBinding


class Menu : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentMenuBinding
        binding = FragmentMenuBinding.inflate(layoutInflater)
        binding.BtnSumar.setOnClickListener {
            findNavController().navigate(R.id.go_suma)
        }
        return binding.root
    }

}