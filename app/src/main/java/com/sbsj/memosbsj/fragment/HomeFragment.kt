import android.app.Service
import android.graphics.Color
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.adapter.ViewPagerAdapter
import com.sbsj.memosbsj.adapter.WriteAdapter
import com.sbsj.memosbsj.data.DataPage
import com.sbsj.memosbsj.databinding.FragmentEditBinding
import com.sbsj.memosbsj.databinding.FragmentHomeBinding
import com.sbsj.memosbsj.ext.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.*
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection

class HomeFragment : Fragment() {

    lateinit var topViewPager2: ViewPager2
    lateinit var tabLayout: TabLayout
    var zoomOutPageTransformer = DepthPageTransformer()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        activity?.let {

            binding.lifecycleOwner = this
        }

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val imm: InputMethodManager =
                activity?.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            if (binding.editTextTextPersonName.text.toString() != "") {
                GlobalScope.launch(Dispatchers.IO) {
                    translationSetting(binding.editTextTextPersonName.text.toString())
                }
            } else {
                requireActivity().showToast("????????? ?????? ?????? ????????? ??????????????????.")
            }


        }
        topViewPagerCreate(view)

    }

    fun topViewPagerCreate(view: View) /*?????? ???????????? ??????*/ {
        topViewPager2 = view.findViewById(R.id.top_viewpager2)
        tabLayout = view.findViewById(R.id.tabLayout)
        /*???????????? ?????? ?????? ????????? ?????? ????????? ??????*/
        val list: ArrayList<DataPage> = ArrayList();
        list.add(DataPage(Color.WHITE, "1 Page"));
        list.add(DataPage(Color.WHITE, "2 Page"));
        list.add(DataPage(Color.WHITE, "3 Page"));
        /*~???????????? ?????? ?????? ????????? ?????? ????????? ??????*/
        topViewPager2.adapter = ViewPagerAdapter(list)
        topViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        topViewPager2.setPageTransformer(zoomOutPageTransformer)
        TabLayoutMediator(tabLayout, topViewPager2) { tab, position ->
            //Some implementation
        }.attach()
    }

    fun translationSetting(transText: String?) {

        val clientId = "gJMUGxaF390G1OktIkKH" //?????????????????? ??????????????? ????????????";
        val clientSecret = "gT8zt_Dmlv" //?????????????????? ??????????????? ????????????";
        val apiURL = "https://openapi.naver.com/v1/papago/n2mt"
        var text: String? = transText
        val requestHeaders: MutableMap<String, String> = HashMap()
        requestHeaders["X-Naver-Client-Id"] = clientId
        requestHeaders["X-Naver-Client-Secret"] = clientSecret
        val responseBody: String = post(apiURL, requestHeaders, text)
        val jsonObject = JSONObject(responseBody)
        val resultValue =
            jsonObject.getJSONObject("message").getJSONObject("result").get("translatedText")
        resultTranslation(resultValue.toString())

    }

    fun post(apiUrl: String, requestHeaders: Map<String, String>, text: String?): String {
        val con: HttpURLConnection? = connect(apiUrl)
        val postParams =
            "source=ko&target=en&text=$text" //????????????: ????????? (ko) -> ????????????: ?????? (en)
        return try {
            con!!.requestMethod = "POST"
            for ((key, value) in requestHeaders.entries) {
                con!!.setRequestProperty(key, value)
            }
            con!!.doOutput = true
            DataOutputStream(con!!.outputStream).use { wr ->
                wr.write(postParams.toByteArray())
                wr.flush()
            }
            val responseCode = con!!.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) { // ?????? ??????
                readBody(con!!.inputStream)
            } else {  // ?????? ??????
                readBody(con!!.errorStream)
            }
        } catch (e: IOException) {
            throw java.lang.RuntimeException("API ????????? ?????? ??????", e)
        } finally {
            con!!.disconnect()
        }
    }

    fun connect(apiUrl: String): HttpURLConnection {
        try {
            val url: URL = URL(apiUrl)
            return url.openConnection() as HttpURLConnection
        } catch (e: MalformedURLException) {
            throw  RuntimeException("API URL??? ?????????????????????. : $apiUrl", e);
        } catch (e: IOException) {
            throw  RuntimeException("????????? ??????????????????. : $apiUrl", e);
        }
    }

    fun readBody(body: InputStream): String {
        val streamReader = InputStreamReader(body)

        try {
            BufferedReader(streamReader).use { lineReader ->
                val responseBody = StringBuilder()
                var line: String?
                while (lineReader.readLine().also { line = it } != null) {
                    responseBody.append(line)
                }
                return responseBody.toString()
            }
        } catch (e: IOException) {
            throw java.lang.RuntimeException("API ????????? ????????? ??????????????????.", e)
        }
    }

    fun resultTranslation(result: String?) {
        binding.textView.text = result

    }


}