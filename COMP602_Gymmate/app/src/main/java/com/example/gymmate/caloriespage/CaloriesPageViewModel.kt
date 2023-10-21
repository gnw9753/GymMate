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
import com.example.gymmate.data.weightdata.WeightEntity
import com.example.gymmate.data.weightdata.WeightRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CaloriesPageViewModel(
    private val foodConsumptionRepository: FoodConsumptionRepository,
    private val weightRepository: WeightRepository
) : ViewModel() {

    val foodEntityUiState: StateFlow<FoodEntityUiState> =
        foodConsumptionRepository.getAllFoodFromEmail(getEmail()).map {
            FoodEntityUiState(it)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FoodEntityUiState()
            )

    val weightEntityUiState: StateFlow<WeightEntityUiState> =
        weightRepository.getAllWeightFromEmail(getEmail()).map {
            WeightEntityUiState(it)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = WeightEntityUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    var displayAddFood by mutableStateOf(false)
    var openBottomSheet by mutableStateOf(false)
    var skipPartiallyExpanded by mutableStateOf(false)
    var edgeToEdgeEnabled by mutableStateOf(false)
    var openWeightBottomSheet by mutableStateOf(false)
    var searchFoodText by mutableStateOf("")
    var foodList = mutableStateListOf<FoodConsumptionEntity>()
    var foodListCreated by mutableStateOf(false)
    val searchFoodListResult = mutableStateListOf<FoodConsumptionEntity>()

    var foodItemSlider by mutableFloatStateOf(100f)
    var foodItemGram by mutableFloatStateOf(0f)
    var foodItemCalories by mutableFloatStateOf(0f)
    var foodItemCarb by mutableFloatStateOf(0f)
    var foodItemProtein by mutableFloatStateOf(0f)
    var foodItemFat by mutableFloatStateOf(0f)

    var totalCalories by mutableFloatStateOf(0f)
    var totalCarbs by mutableFloatStateOf(0f)
    var totalProtein by mutableFloatStateOf(0f)
    var totalFat by mutableFloatStateOf(0f)

    var caloriesRequired by mutableStateOf(0)

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

    var pickedWeight by mutableFloatStateOf(0f)

    suspend fun addFoodToDatabase() {
        foodConsumptionRepository.insertFood(
            FoodConsumptionEntity(
                email = getEmail(),
                foodName = foodItem.foodName,
                grams = foodItemSlider,
                calories = foodItemCalories,
                protein = foodItemProtein,
                fat = foodItemFat,
                carbs = foodItemCarb
            )
        )
    }

    suspend fun addFoodButton(food: FoodConsumptionEntity) {
        println("add food button pressed")
        foodConsumptionRepository.insertFood(
            FoodConsumptionEntity(
                email = food.email,
                foodName = food.foodName,
                grams = food.grams,
                calories = food.calories,
                protein = food.protein,
                fat = food.fat,
                carbs = food.carbs
            )
        )
    }

    suspend fun addWeightToDatabase() {
        weightRepository.insertWeight(
            WeightEntity(
                email = getEmail(),
                weight = pickedWeight
            )
        )
    }



    fun calculateNutrition(foodList: List<FoodConsumptionEntity>) {
        totalCalories = 0f
        totalCarbs = 0f
        totalFat = 0f
        totalProtein = 0f
        for(food in foodList) {
            totalCalories += food.calories
            totalCarbs += food.carbs
            totalFat += food.fat
            totalProtein += food.protein
        }
    }

    fun calculateCalorieRequirement() {
        if (getGender().equals("Male", ignoreCase = true)) {
            if (getAge() <= 14) {
                caloriesRequired = if (getGoal().contains("Gain Muscle", ignoreCase = true)) (2500 * 1.1).toInt() else (2500 - 500)
            } else if (getAge() <= 18) {
                caloriesRequired = if (getGoal().contains("Gain Muscle", ignoreCase = true)) (3000 * 1.1).toInt() else (3000 - 500)
            } else if (getAge() <= 24) {
                caloriesRequired = if (getGoal().contains("Gain Muscle", ignoreCase = true)) (2900 * 1.1).toInt() else (2900 - 500)
            } else if (getAge() <= 50) {
                caloriesRequired = if (getGoal().contains("Gain Muscle", ignoreCase = true)) (2900 * 1.1).toInt() else (2900 - 500)
            } else if (getAge() >= 51) {
                caloriesRequired = if (getGoal().contains("Gain Muscle", ignoreCase = true)) (3000 * 1.1).toInt() else (3000 - 500)
            }
        } else {
            if (getAge() <= 14) {
                caloriesRequired = if (getGoal().contains("Gain Muscle", ignoreCase = true)) (2200 * 1.1).toInt() else (2200 - 500)
            } else if (getAge() <= 18) {
                caloriesRequired = if (getGoal().contains("Gain Muscle", ignoreCase = true)) (2200 * 1.1).toInt() else (2200 - 500)
            } else if (getAge() <= 24) {
                caloriesRequired = if (getGoal().contains("Gain Muscle", ignoreCase = true)) (2200 * 1.1).toInt() else (2200 - 500)
            } else if (getAge() <= 50) {
                caloriesRequired = if (getGoal().contains("Gain Muscle", ignoreCase = true)) (2200 * 1.1).toInt() else (2200 - 500)
            } else if (getAge() >= 51) {
                caloriesRequired = if (getGoal().contains("Gain Muscle", ignoreCase = true)) (1900 * 1.1).toInt() else (1900 - 500)
            }
        }


    }

    private fun getEmail(): String {
        var email: String = ""
        if (UserInstance.currentUser != null) {
            email = UserInstance.currentUser!!.user_email
        }
        return email
    }

    fun getGender(): String {
        var gender = "Male"
        if (UserInstance.currentUser != null && UserInstance.currentUser!!.user_gender != "") {
            gender = UserInstance.currentUser!!.user_gender
        }
        return gender
    }

    fun getAge(): Int {
        var age = 60
        if (UserInstance.currentUser != null && UserInstance.currentUser!!.user_age > 0) {
            age = UserInstance.currentUser!!.user_age
        }
        return age
    }

    fun getGoal(): String {
        var goal = "Gain Muscle"
        if (UserInstance.currentUser != null && UserInstance.currentUser!!.user_goal != "") {
            goal = UserInstance.currentUser!!.user_goal
        }
        return goal
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


data class FoodEntityUiState(val foodConsumptionList: List<FoodConsumptionEntity> = listOf()) {
}

data class WeightEntityUiState(val weightList: List<WeightEntity> = listOf())