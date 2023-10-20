package com.example.gymmate.caloriespage

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmate.R
import com.example.gymmate.data.ReadExerciseCSV
import com.example.gymmate.data.fooddata.FoodConsumptionEntity
import com.example.gymmate.data.fooddata.FoodConsumptionRepository
import com.example.gymmate.data.userdata.UserInstance
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CaloriesPageViewModel(
    foodConsumptionRepository: FoodConsumptionRepository
) : ViewModel() {

    val caloriesPageUiState: StateFlow<CaloriesPageUiState> =
        foodConsumptionRepository.getAllFoodFromEmail(getEmail()).map {
            CaloriesPageUiState(it)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = CaloriesPageUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    var displayAddFood by mutableStateOf(false)

    var openBottomSheet by mutableStateOf(false)
    var skipPartiallyExpanded by mutableStateOf(false)
    var edgeToEdgeEnabled by mutableStateOf(false)
    var addWeightPressed by mutableStateOf(false)
    var addFoodPressed by mutableStateOf(false)

    var searchFoodText by mutableStateOf("")

    var foodList = mutableStateListOf<FoodConsumptionEntity>()
    var foodListCreated by mutableStateOf(false)

    val searchFoodListResult = mutableStateListOf<FoodConsumptionEntity>()

    var foodItem by mutableStateOf(
        FoodConsumptionEntity(
            id = 0,
            email = "",
            foodName = "",
            grams = 0f,
            calories = 0f,
            protein = 0f,
            fat = 0f,
            carbs = 0f
        )
    )

    var foodItemSlider by mutableFloatStateOf(100f)
    var foodItemGram by mutableFloatStateOf(0f)
    var foodItemCalories by mutableFloatStateOf(0f)
    var foodItemCarb by mutableFloatStateOf(0f)
    var foodItemProtein by mutableFloatStateOf(0f)
    var foodItemFat by mutableFloatStateOf(0f)

    private fun getEmail(): String {
        var email: String = ""
        if (UserInstance.currentUser != null) {
            email = UserInstance.currentUser!!.user_email
        }
        println("User email in calories page: $email")
        return email
    }

    fun loadCSV(context: Context) {
        if (!foodListCreated) {
            val inputStream = context.resources.openRawResource(R.raw.nutrients_csvfile_cleaned)
            val csvFile = ReadExerciseCSV(inputStream)
            val foodItemCSV = csvFile.read()
            val tempFoodList = ConvertFoodCSVToList(getEmail(), foodItemCSV).convertToFoodList()
            foodList.addAll(tempFoodList)
            foodListCreated = true
        }
    }

    fun searchFood() {
        println("search food run")
        searchFoodListResult.clear()
        if (searchFoodText.isEmpty()) {
            return
        }

        val lowerCaseSearchText = searchFoodText.lowercase()

        for (foodItem in foodList) {
            val lowerCaseFoodName = foodItem.foodName.lowercase()

            if (lowerCaseFoodName.startsWith(lowerCaseSearchText)) {
                searchFoodListResult.add(foodItem)
            }
        }
    }
}


data class CaloriesPageUiState(val foodConsumptionList: List<FoodConsumptionEntity> = listOf()) {
}