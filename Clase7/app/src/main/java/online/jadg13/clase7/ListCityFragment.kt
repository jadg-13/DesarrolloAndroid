package online.jadg13.clase7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import online.jadg13.clase7.adapter.CityAdapter
import online.jadg13.clase7.adapter.CityViewModel
import online.jadg13.clase7.adapter.CityViewModelFactory
import online.jadg13.clase7.dao.DatabaseBuilder
import online.jadg13.clase7.databinding.ActivityMainBinding
import online.jadg13.clase7.databinding.FragmentListCityBinding
import online.jadg13.clase7.entities.City


class ListCityFragment : Fragment() {
    private lateinit var binding: FragmentListCityBinding

    private val cityViewModel : CityViewModel by viewModels {
        CityViewModelFactory(DatabaseBuilder.getInstance(requireContext()).cityDao())
    }

    private lateinit var cityListAdapter: CityAdapter

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListCityBinding.inflate(layoutInflater)
        startList()
        return binding.root
    }

    private fun startList() {

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listCityFragment_to_newCityragment)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityListAdapter = CityAdapter(requireContext(), emptyList(), this::onEditClick, this::onDeleteClick)
        binding.lvRegistros.adapter = cityListAdapter

        cityViewModel.allCities.observe(viewLifecycleOwner, Observer{
            cities -> cityListAdapter.updateCities(cities)
        } )

    }

    private fun onEditClick(city: City){
        val bundle = Bundle()
        bundle.putInt("city_id", city.id)
        findNavController().navigate(R.id.action_listCityFragment_to_editCityFragment, bundle)

    }

    private fun onDeleteClick(city: City){
        cityViewModel.deleteCity(city)
        Toast.makeText(context, "Registro eliminado", Toast.LENGTH_LONG).show()
    }

}