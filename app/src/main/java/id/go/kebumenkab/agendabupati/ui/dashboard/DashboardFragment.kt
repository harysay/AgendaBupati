package id.go.kebumenkab.agendabupati.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import id.go.kebumenkab.agendabupati.MainActivity
import id.go.kebumenkab.agendabupati.R
import id.go.kebumenkab.agendabupati.databinding.ActivityMainBinding
import id.go.kebumenkab.agendabupati.databinding.FragmentDashboardBinding
import id.go.kebumenkab.agendabupati.model.DatadaftaragendaItem
//import id.go.kebumenkab.agendabupati.model.DatadetailItem
//import id.go.kebumenkab.agendabupati.model.ListItemDashboard
import id.go.kebumenkab.agendabupati.model.ResponseUser
import id.go.kebumenkab.agendabupati.network.ApiConfig
import id.go.kebumenkab.agendabupati.ui.detail.DetailAgenda
import id.go.kebumenkab.agendabupati.utils.DialogUtils
import id.go.kebumenkab.agendabupati.utils.HawkStorage
import id.go.kebumenkab.agendabupati.viewmodel.DashboardViewModel
import io.github.muddz.styleabletoast.StyleableToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
class DashboardFragment : Fragment(),DashboardListAdapter.RecyclerViewClickListener {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var bindingActivity: ActivityMainBinding
    private lateinit var dialog: DialogUtils
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var token: String
    private lateinit var daftarAgendaList: ArrayList<DatadaftaragendaItem>

    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle? ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingActivity = (requireActivity() as MainActivity).binding
        init()
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

    private fun init() {
        dashboardViewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        dialog = DialogUtils()
        swipeRefresh()
        login("username","password")
    }

    private fun setPref() {
        val user = HawkStorage.instance(context).getUser()
        token = user.token.toString()
    }

    private fun setRefresh(isRefresh: Boolean) {
        binding.swipeRefreshLayout?.isRefreshing = isRefresh
    }
    private fun swipeRefresh() {
        binding.swipeRefreshLayout?.setOnRefreshListener {
            dashboardViewModel.getDaftarAgenda(token)
//            setDaftarAgendaList()
        }
    }

    private fun setDaftarAgendaList() {
        dashboardViewModel.isRefresh.observe(viewLifecycleOwner) {
            setRefresh(it)
        }
        dashboardViewModel.isLoading.observe(viewLifecycleOwner) {
//            setLoading(it)
            binding.swipeRefreshLayout?.isRefreshing = it
            binding.containerEmptyData?.containerEmptyData?.visibility = View.GONE
        }
        dashboardViewModel.getDaftarAgenda(token)
        dashboardViewModel.livedatadaftaragenda.observe(viewLifecycleOwner) {
            val status = it.status

            if (status == "success") {
                daftarAgendaList = it.datadaftaragenda as ArrayList<DatadaftaragendaItem>
//                val rawList = it.datadaftaragenda
                // Membersihkan item kosong sebelum menampilkannya di RecyclerView
//                val cleanedList = cleanEmptyItems(rawList)
                // Mengatur daftar yang telah dibersihkan ke adapter RecyclerView
                val suratkonsepAdapter = DashboardListAdapter(daftarAgendaList)
                binding.recyclerviewDashboard.adapter = suratkonsepAdapter
                binding.recyclerviewDashboard.setHasFixedSize(true)
                suratkonsepAdapter.listener = this
            }else{
//                suratKonsepViewModel.
                binding.errorLayout!!.visibility = View.VISIBLE
                binding.errortext!!.text = dashboardViewModel.errorDesc
            }
        }
        dashboardViewModel.isInfoserverkonsep.observe(requireActivity()) {
            setResponServerGagal(it)
        }

    }

    // Fungsi untuk membersihkan item kosong dari daftar data
    private fun cleanEmptyItems(data: List<DatadaftaragendaItem?>?): List<DatadaftaragendaItem> {
        val cleanedList = mutableListOf<DatadaftaragendaItem>()
        data?.let { list ->
            for (item in list) {
                item?.let {
                    // Periksa apakah item tidak kosong (tidak null)
                    if (it.id != null && it.tanggal != null && it.jmlkegiatan != null && it.tglformat != null) {
                        cleanedList.add(it)
                    }
                }
            }
        }
        return cleanedList
    }

    private fun setResponServerGagal(valueFromServer: String) {
        //binding.errortext.text = valueFromServer
        binding.errorLayout!!.visibility = View.VISIBLE
        binding.errortext!!.text = dashboardViewModel.errorDesc
        binding.containerEmptyData!!.containerEmptyData.visibility = View.GONE
    }

    override fun onItemClickedListItemDashboard(view: View, dataitem: DatadaftaragendaItem) {
        val intent = Intent(requireContext(), DetailAgenda::class.java)
        intent.putExtra("daftaragendalist", dataitem)
//        intent.putExtra(TAG_ARSIP, "")
        context?.startActivity(intent)
    }

    private fun login(user:String, pass:String) {
        setLoading(true)
        val client = ApiConfig.getApiService().getLogin()
        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                val status = response.body()?.status
                if (response.isSuccessful) {
                    if (status == "success") {
                        val usereletter = response.body()
                        if (usereletter != null) {
                            HawkStorage.instance(requireActivity()).setUser(usereletter)
                            Log.d("eletterUser", usereletter.toString())
                            loadHome()
                        }else{
                            StyleableToast.makeText(requireActivity(), "Server response null!!", R.style.Toast_Error).show()
                        }
                    } else {
                        StyleableToast.makeText(requireActivity(), "Status dari server tidak sukses!!", R.style.Toast_Error).show()
                    }
                } else {
                    StyleableToast.makeText(requireActivity(), response.message(), R.style.Toast_Error).show()
                }
                setLoading(false)
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                setLoading(false)
                Log.e("ElleterLoginError", "onFailure: ${t.message.toString()}")
                StyleableToast.makeText(requireActivity(), t.message.toString(), R.style.Toast_Error).show()
            }
        })
    }
    private fun loadHome(){
        setPref()
        val user = HawkStorage.instance(requireActivity()).getUser()
        val nama = user.detail?.nama.toString()
        StyleableToast.makeText(requireActivity(), "$nama Berhasil Login! ", R.style.Toast_Success).show()
        setDaftarAgendaList()
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            dialog.showProgressDialog(requireActivity())
        } else {
            dialog.hideDialog()
        }
    }
}


