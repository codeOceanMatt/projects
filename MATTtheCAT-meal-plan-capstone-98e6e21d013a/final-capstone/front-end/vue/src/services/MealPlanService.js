import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default{
    addMealPlan(mealPlan){
        return http.post(`/createPlan`, mealPlan)
    },
    addMealPlanRecipe(mealPlanRecipe){
        return http.post(`/addRecipeToMealPlan`, mealPlanRecipe)
    },
    getMealPlans(userId){
        return http.get(`/getMealPlans/${userId}`)
    }
}