package com.example.hw3_4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw3_4.databinding.FragmentContinentsBinding

class ContinentsFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentContinentsBinding
    private var continentsList = ArrayList<ContinentsModel>()
    private var adapter = ContinentsAdapter(continentsList, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContinentsBinding.inflate(inflater)
        filData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() = with(binding) {
        rvContinents.layoutManager = LinearLayoutManager(requireContext())
        rvContinents.adapter = adapter
    }

    private fun filData() {
        continentsList.add(
            ContinentsModel(
                "Евразия",
                "Евразия — это самый большой материк на планете Земля. На нём находится самая высокая гора — Эверест, самое глубокое озеро — Байкал и самое большое озеро — Каспийское море",
                "data:image/png;base64,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"
            )
        )
        continentsList.add(
            ContinentsModel(
                "Африка",
                "А́фрика — второй по площади материк после Евразии, омывается Средиземным морем с севера, Красным — с северо-востока, Атлантическим океаном с запада и Индийским океаном с востока, и обоими океанами с юга, с разделением по 20 градусу восточной долготы.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/African_countries_by_GDP_%28PPP%29_per_capita_in_2020.png/260px-African_countries_by_GDP_%28PPP%29_per_capita_in_2020.png"
            )
        )
        continentsList.add(
            ContinentsModel(
                "Северная Америка",
                "Северная Америка — третий по величине континент (24,221,490 км²), в котором расположено 24 стран, и который является родиной 514,6 млн. человек. Он находится в северном полушарии, между Атлантическим и Тихим океанами, и к северу от Южной Америки",
                "https://natworld.info/wp-content/uploads/2020/12/karta-klimaticheskih-poyasov-severnoj-ameriki.jpg"
            )
        )
        continentsList.add(
            ContinentsModel(
                "Южная Америка",
                "Ю́жная Аме́рика — один из шести материков планеты Земля, расположенный на юге Западного полушария. Омывается на западе Тихим океаном, на востоке — Атлантическим, на севере — Карибским морем, которое является естественным рубежом между двумя Америками.",
                "https://mirplaneta.ru/images/295.jpg"
            )
        )
        continentsList.add(
            ContinentsModel(
                "Австралия",
                "Австралия — материк в Южном полушарии площадью 7 659 861 км²(5 % площади суши). Длина континента с севера на юг составляет около 3,2 тысяч км, ширина с запада на восток — около 4000 км, длина береговой линии материка (без островов) — 35 877 км.",
                "https://сезоны-года.рф/sites/default/files/images/okruzhayushhij_mir/Australia_map.jpg"
            )
        )
    }

    override fun onItemClick(position: Int) {
        var continent = continentsList[position].title
        var bundle = Bundle()
        bundle.putString("continents", continent)
        val countryFragment = CountryFragment()
        countryFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fl_mine, countryFragment).addToBackStack(null).commit()
    }
}