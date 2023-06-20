package com.example.hw3_4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw3_4.databinding.FragmentContinentsBinding
import com.example.hw3_4.databinding.FragmentCountryBinding

class CountryFragment : Fragment() {
    lateinit var binding: FragmentCountryBinding
    private var countryList = ArrayList<CountryModel>()
    private lateinit var adapter: CountryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater)
        filData()
        adapter = CountryAdapter(sampleList())
        return binding.root
    }

    private fun filData() {
        countryList.add(
            CountryModel(
                "Палау",
                "Палау – государство-архипелаг в Микронезии в западной части Тихого океана, состоящее из более чем 500 островов. На острове Корор находится одноименный город – бывшая столица и деловой центр страны",
                "https://st3.depositphotos.com/33790680/36785/v/450/depositphotos_367858878-stock-illustration-palau-flag-vector-graphic-rectangle.jpg",
                19409,
                "Австралия",
                "Нгерулмуд"
            )
        )
        countryList.add(
            CountryModel(
                "Соломоновы Острова",
                "Соломоновы острова – государство в южной части Тихого океана. На островах сохранилось много памятных мест времен Второй мировой войны.",
                "data:image/png;base64,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",
                494786,
                "Австралия",
                "Хониара"
            )
        )
        countryList.add(
            CountryModel(
                "Австралия",
                "Австралия – это страна, занимающая одноименный материк, который омывается Индийским и Тихим океанами. На побережьях континента находятся такие крупные города, как Сидней, Брисбен, Мельбурн, Перт и Аделаида.",
                "https://simvolplus.ru/upload/iblock/526/52625aed09f47a814bd5e11957f6dac7.jpg",
                21050000,
                "Австралия",
                "Канберра"
            )
        )
        countryList.add(
            CountryModel(
                "Вануату",
                "Вануату - тихоокеанское государство в Меланезии. Граничит на севере с водами Соломоновых Островов, на западе — с экономической зоной Австралии, на юго-западе — с территориальными водами Новой Каледонии",
                "https://flagshub.com/images/flag-of-vanuatu.png",
                196178,
                "Австралия",
                "Порт-Вила"
            )
        )
        countryList.add(
            CountryModel(
                "Остров Норфолк",
                "О́стров Но́рфолк — небольшой обитаемый остров в Тихом океане, расположенный между Австралией, Новой Каледонией и Новой Зеландией.",
                "https://cdn.xxl.thumbs.canstockphoto.ru/%D0%B2%D0%B5%D0%BA%D1%82%D0%BE%D1%80-%D0%BE%D1%81%D1%82%D1%80%D0%BE%D0%B2-%D1%84%D0%BB%D0%B0%D0%B3-%D0%BE%D1%84%D0%B8%D1%86%D0%B8%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9-%D0%BD%D0%BE%D1%80%D1%84%D0%BE%D0%BB%D0%BA-%D0%B8%D0%BB%D0%BB%D1%8E%D1%81%D1%82%D1%80%D0%B0%D1%86%D0%B8%D1%8F_csp45800522.jpg",
                1866,
                "Австралия",
                "Кингстон"
            )
        )
        countryList.add(
            CountryModel(
                "Эквадор",
                "Эквадор – страна в Южной Америке на побережье Тихого океана, территорию которой пересекает экватор. Она пользуется популярностью у туристов благодаря джунглям Амазонии, горной системе Анды и Галапагосским островам, дикая природа которых очень многообразна.",
                "https://w7.pngwing.com/pngs/706/201/png-transparent-santa-cruz-flag-of-ecuador-mestizo-mulatto-flag-miscellaneous-flag-text.png",
                16080778,
                "Южная Америка",
                "Кито"
            )
        )
        countryList.add(
            CountryModel(
                "Чили",
                "Чили – страна на юго-западе Южной Америки, которая занимает узкую полосу земли протяженностью более 6000 км, омываемую водами Тихого океана.",
                "https://www.zastavki.com/pictures/1920x1200/2020Backgrounds_Chile_flag_close_up_142685_18.jpg",
                17789267,
                "Южная Америка",
                "Сантьяго"
            )
        )
        countryList.add(
            CountryModel(
                "Перу",
                "Перу – страна в Южной Америке, на территории которой расположены тропические леса Амазонии и Мачу-Пикчу – древний город инков на вершине горного хребта в Андах.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Flag_of_Peru_%28state%29.svg/800px-Flag_of_Peru_%28state%29.svg.png",
                32162184,
                "Южная Америка",
                "Лима"
            )
        )
        countryList.add(
            CountryModel(
                "Колумбия",
                "Колумбия – страна на северо-западе Южной Америки, облик которой формируют тропические леса, горный массив Анды и многочисленные плантации кофе.",
                "https://cdn.pixabay.com/photo/2014/11/16/21/23/colombia-533967_1280.jpg",
                48400388,
                "Южная Америка",
                "Богота"
            )
        )
        countryList.add(
            CountryModel(
                "Аргентина",
                "Аргентина – это крупная страна в Южной Америке, знаменитая своей музыкой и танцем танго. Территория Аргентины охватывает горы Анды, ледниковые озера, а также пампу – степи, на которых традиционно разводят крупный рогатый скот.",
                "https://img.freepik.com/premium-vector/argentina-flag-flag-of-argentina-vector-illustration_685751-66.jpg",
                43417000,
                "Южная Америка",
                "Буэнос-Айрес"
            )
        )
        countryList.add(
            CountryModel(
                "Гренада",
                "Гренада – страна в Карибском море, расположенная на крупном острове с таким же названием и ещё нескольких меньшей площади.",
                "https://img.freepik.com/premium-vector/waving-flag-of-the-country-grenada-vector-illustration_601298-11110.jpg",
                89502,
                "Северная Америка",
                "Сент-Джорджес"
            )
        )
        countryList.add(
            CountryModel(
                "Гондурас",
                "Гондурас – страна в Центральной Америке, омываемая водами Карибского моря на севере и Тихого океана на юге. В тропических лесах близ Гватемалы расположен Копан – центр одного из древних государств майя, который известен своими вырезанными на камне иероглифическими знаками и высокими каменными стелами.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Flag_of_Honduras.svg/1200px-Flag_of_Honduras.svg.png",
                7875204,
                "Северная Америка",
                "Тегусигальпа"
            )
        )
        countryList.add(
            CountryModel(
                "Гаити",
                "Гаити – страна в Карибском море, занимающая западную часть одноименного острова и граничащая с Доминиканской Республикой.",
                "https://www.flagistrany.ru/data/flags/ultra/ht.png",
                10033000,
                "Северная Америка",
                "Порт-о-Пренс"
            )
        )
        countryList.add(
            CountryModel(
                "Белиз",
                "Белиз – государство на восточном побережье Центральной Америки. Омывается Карибским морем. В прибрежной зоне пролегает Белизский Барьерный риф, усеянный сотнями низменных коралловых островов.",
                "https://infoearth.ru/wp-content/uploads/2019/06/1280px-Flag_of_Belize.svg_-1024x682.png",
                307899,
                "Северная Америка",
                "Бельмопан"
            )
        )
        countryList.add(
            CountryModel(
                "Барбадос",
                "Барбадос – островное государство в восточной части Карибского моря, входящее в Британское Содружество наций. Столицу, город-порт Бриджтаун, отличает колониальная архитектура.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Flag_of_Barbados.svg/1024px-Flag_of_Barbados.svg.png",
                284589,
                "Северная Америка",
                "Бриджтаун"
            )
        )
        countryList.add(
            CountryModel(
                "Буркина-Фасо",
                "Буркина́-Фасо́ — государство в Западной Африке. До августа 1984 года называлась Ве́рхняя Во́льта. Согласно Конституции страны «Фасо — это республиканская форма государства», то есть «Фасо» синоним слова «Республика»",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Flag_of_Burkina_Faso.svg/1280px-Flag_of_Burkina_Faso.svg.png",
                18450494,
                "Африка",
                "Уагадугу"
            )
        )
        countryList.add(
            CountryModel(
                "Ботсвана",
                "Ботсвана – не имеющая выхода к морю страна в Южной Африке, на территории которой расположены пустыня Калахари и дельта реки Окаванго, куда во время половодья откочевывают стада степных животных.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Flag_of_Botswana.svg/1280px-Flag_of_Botswana.svg.png",
                2262485,
                "Африка",
                "Габороне"
            )
        )
        countryList.add(
            CountryModel(
                "Бенин",
                "Бенин – франкоязычное государство в Западной Африке. Именно здесь зародилась религия вуду (водун). В период примерно с 1600 по 1900 г. на этой территории располагалось королевство Дагомея.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Flag_of_Benin.svg/1280px-Flag_of_Benin.svg.png",
                10315244,
                "Африка",
                "Порто-Ново"
            )
        )
        countryList.add(
            CountryModel(
                "Ангола",
                "Ангола – это государство в Южной Африке со сложной речной системой, тропическими пляжами Атлантического океана и пустыней, раскинувшейся с юга страны до ее границы с Намибией.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Flag_of_Angola.svg/1280px-Flag_of_Angola.svg.png",
                25021974,
                "Африка",
                "Луанда"
            )
        )
        countryList.add(
            CountryModel(
                "Амбазония",
                "Федерати́вная Респу́блика Амбазо́ния — непризнанное государственное образование в Центральной Африке, претендующее на англоязычную часть Камеруна.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_the_Federal_Republic_of_Southern_Cameroons.svg/1280px-Flag_of_the_Federal_Republic_of_Southern_Cameroons.svg.png",
                3521898,
                "Африка",
                "Буэа"
            )
        )
        countryList.add(
            CountryModel(
                "Австрия",
                "Австрия – это центральноевропейская страна с имперской историей, архитектурой в стиле барокко, горными деревушками и альпийским ландшафтом. Государственный язык здесь немецкий.",
                "https://militaryarms.ru/wp-content/uploads/2021/08/%D0%A4%D0%BB%D0%B0%D0%B3-%D0%90%D0%B2%D1%81%D1%82%D1%80%D0%B8%D0%B8.jpg",
                8662588,
                "Евразия",
                "Вена"
            )
        )
        countryList.add(
            CountryModel(
                "Бангладеш",
                "Бангладеш – государство в Южной Азии, расположенное к востоку от Индии и омываемое Бенгальским заливом. Бангладеш отличает богатая фауна и обилие водных путей.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Flag_of_Bangladesh.svg/2560px-Flag_of_Bangladesh.svg.png",
                169400000,
                "Евразия",
                "Дакка"
            )
        )
        countryList.add(
            CountryModel(
                "Афганистан",
                "Афганиста́н, официальное название под управлением движения «Талибан» с 2021 года — Исла́мский Эмира́т Афганиста́н — государство в Центральной Азии, также относящееся и к Южной",
                "data:image/png;base64,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",
                40100000,
                "Евразия",
                "Кабул"
            )
        )
        countryList.add(
            CountryModel(
                "Армения",
                "Армения – это государство на Кавказе, расположенное на границе Европы и Азии; бывшая союзная республика СССР. Армения является одной из древнейших христианских стран, поэтому здесь много важных религиозных памятников.",
                "data:image/jpeg;base64,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",
                2791000,
                "Евразия",
                "Ереван"
            )
        )
        countryList.add(
            CountryModel(
                "Андорра",
                "Андорра – это маленькое независимое княжество, расположенное на Пиренеях между Францией и Испанией. Оно является известной зоной беспошлинной торговли, а также привлекает туристов своими горнолыжными курортами.",
                "data:image/png;base64,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",
                7934000,
                "Евразия",
                "Андорра-ла-Велья"
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() = with(binding) {
        rvCountry.layoutManager = LinearLayoutManager(requireContext())
        rvCountry.adapter = adapter
    }

    private fun sampleList(): ArrayList<CountryModel> {
        var list = ArrayList<CountryModel>()

        for (item in countryList) {
            if (item.continent == arguments?.getString("continents").toString()) {
                list.add(item)
            }
        }
        return list
    }
}