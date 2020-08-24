<template>
  <div>
    <label>Meal Plan Name</label>
    <input type="text" v-model="mealPlan.planName" />
    <span>
      <p>Please select a day</p>
      <select id="days-of-week" v-model="mealPlanRecipe.dayOfWeek">
        <option>Sunday</option>
        <option>Monday</option>
        <option>Tuesday</option>
        <option>Wednesday</option>
        <option>Thursday</option>
        <option>Friday</option>
        <option>Saturday</option>
      </select>
    </span>
    <span>
      <p>Please select a meal type</p>
      <select id="meal-category" v-model="mealPlanRecipe.mealCategory">
        <option>Breakfast</option>
        <option>Lunch</option>
        <option>Dinner</option>
        <option>Snack</option>
        <option>Dessert</option>
      </select>
    </span>
    <span>
      <p>Please select a recipe to add to your Plan</p>
      <select id="recipe-id" v-model="mealPlanRecipe.recipeId">
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeId}}:{{recipe.recipeName}}</option>
      </select>
      <button v-on:click="submitMealPlanRecipe()">Add another Recipe</button>
    </span>
    <br />
    <br />
    <button v-on:click="submitMealPlan()">Submit Meal Plan</button>
  </div>
</template>

<script>
import mealPlanService from "@/services/MealPlanService";
export default {
  data() {
    return {
      mealPlan: {
        userId: this.$store.state.user.id,
        mealPlanRecipes: [],
        planName:""
      },
      mealPlanRecipe: {
        recipeId: 1,
        mealCategory: "",
        dayOfWeek: "",
      },
    };
  },
  //   created(){
  //       this.recipeService.getAllRecipes(this.userId).then(response => {
  //             this.$store.commit("LIST_RECIPES",response.data)
  //       )}
  //   },
  methods: {
    submitMealPlan() {
      mealPlanService.addMealPlan(this.mealPlan);
      
    },
    submitMealPlanRecipe() {
      let mealPlanRecipe=this.mealPlanRecipe.recipeId;
      let id = mealPlanRecipe.slice(0,mealPlanRecipe.indexOf(":"))
      let actualId = Number(id);
      const newMealPlanRecipe = {

        recipeId: actualId,
        
        mealCategory: this.mealPlanRecipe.mealCategory,
        dayOfWeek: this.mealPlanRecipe.dayOfWeek
      };
      this.mealPlan.mealPlanRecipes.push(newMealPlanRecipe);
    },
  },
};
</script>

<style>
</style>