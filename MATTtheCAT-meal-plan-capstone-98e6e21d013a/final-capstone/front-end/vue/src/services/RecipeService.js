import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default{

    addRecipe(recipe){
        return http.post(`/createrecipe`, recipe)
    },

    getRecipes(userID){
        return http.get(`/getRecipes/${userID}`)
    },

    getRecipe(recipeID){
        return http.get(`/getRecipe/${recipeID}`)
    },

    getIngredients(recipeID){
        return http.get(`/getIngredients/${recipeID}`)
    },

    updateIngredient(recipeID,recipe, ingredient){
        return http.put(`/addIngredient/${recipeID}`, recipe, ingredient)
    },

    deleteIngredient(recipeID, ingredientName){
        return http.delete(`/deleteIngredient/${recipeID}`, ingredientName)
    }
}