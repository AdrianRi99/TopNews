import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topnews.api.NewsApiService
import com.example.topnews.api.RetrofitInstance
import com.example.topnews.models.NewsItem
import com.example.topnews.repositories.NewsRepository
import com.example.topnews.utility.Constants
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val repository = NewsRepository()

    private lateinit var newsApiService: NewsApiService

    private val _newsItems = MutableLiveData<List<NewsItem>>()
    val newsItems: LiveData<List<NewsItem>> = _newsItems

    init {
        setUpNewsApiService()
    }

    private fun setUpNewsApiService() {
        val retrofit = RetrofitInstance.retrofit // Hier wird die RetrofitInstance aufgerufen
        newsApiService = retrofit.create(NewsApiService::class.java)
    }

    fun loadTopNews() {
        viewModelScope.launch {
            val newsList = repository.getTopNews(newsApiService, Constants.API_KEY)
            _newsItems.value = newsList
        }
    }

    fun loadTopNewsAustria() {
        viewModelScope.launch {
            val newsList = repository.getTopNewsAustria(newsApiService, Constants.API_KEY)
            _newsItems.value = newsList
        }
    }

    fun loadTopNewsSports() {
        viewModelScope.launch {
            val newsList = repository.getTopNewsSports(newsApiService, Constants.API_KEY)
            _newsItems.value = newsList
        }
    }
}
