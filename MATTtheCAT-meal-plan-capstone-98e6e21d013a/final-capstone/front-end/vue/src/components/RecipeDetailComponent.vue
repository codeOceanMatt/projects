<template>
  <div id="recipe-detail-page">
    <hr id="hr-top" />
    <h1>{{ recipe.recipeName }}</h1>
    <hr id="hr-bottom" />
    <h3>Ingredients</h3>
    <span>
      <p v-for="(ingredient,index) in ingredients" v-bind:key="ingredient.name">
        {{ingredients[index].name}}, {{ingredients[index].quantity}}
        <button
          id="delete-ingredient"
          v-on:click="deleteIngredient(this.ingredients[index].name, this.ingredients[index].quantity)"
        >X</button>
      </p>
    </span>
    <h3>Instructions</h3>
    <p>{{ recipe.recipeInstructions }}</p>
    <!-- <p v-for="ingredient in this.ingredients.name" v-bind:key="ingredient.name" tag='li'>{{ ingredients.name }}</p> -->
    <br />
    <br />
    <div id="ingredients-field" class="field">
      <span>
        <label for="ingredient">Ingredient</label>&nbsp;&nbsp;
        <input type="text" v-model="ingredient.name" />&nbsp;&nbsp;&nbsp;&nbsp;
        <label for="ingredient-quantity">Quantity</label>&nbsp;&nbsp;
        <input type="text" v-model="ingredient.quantity" />&nbsp;&nbsp;&nbsp;&nbsp;
        <button v-on:click.prevent="addIngredient">Add Ingredient</button>
      </span>
    </div>
    <br />
  </div>
</template>

<script>
import recipeService from "@/services/RecipeService";
export default {
  name: "recipe-detail",
  data() {
    return {
      recipe: {},

      ingredients: [],
      ingredient: {
        name: "",
        quantity: "",
      },
    };
  },
  created() {
    recipeService.getRecipe(this.$route.params.id).then((response) => {
      this.recipe = response.data;
      this.$store.commit("SET_ACTIVE_RECIPE", response.data);
    }),
      recipeService.getIngredients(this.$route.params.id).then((response) => {
        this.ingredients = response.data;
        this.$store.commit("SET_ACTIVE_INGREDIENTS", response.data);
      });
  },

  methods: {
    addIngredient() {
      // const current = this.$store.state.recipe;
      // let currentRecipe = {

      //   recipeId: this.$store.state.recipe.recipeId,
      //   recipeName: this.$store.state.recipe.recipeName,
      //   // ingredients: this.$store.state.recipe.ingredients,
      //   recipeInstructions: this.$store.state.recipe.recipeId,
      // };
      let currentIngredient = {
        name: this.ingredient.name,
        quantity: this.ingredient.quantity,
      };
      this.ingredients.push(currentIngredient);
      //this.$store.commit('SET_ACTIVE_INGREDIENTS',this.ingredients)
      recipeService.updateIngredient(this.recipe.recipeId, this.recipe);
      // this.ingredient.name = "";
      // this.ingredient.quantity = "";
    },
    //   deleteIngredient(ingredientName, ingredientQuantity){
    //       let selectedIngredient = {
    //       name: this.$store.state.ingredients.name,
    //       quantity: ingredientQuantity,
    //     };
    //     recipeService.deleteIngredient(this.recipe.recipeId, selectedIngredient);
    //   }
    // }
  },
};
</script>

<style scoped>

#recipe-detail-page{
  background: #f2f2f2;
  padding: 10px;
  text-transform: uppercase;
  text-align: center;
  margin-bottom: 20px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

#hr-top {
  background-color: #d90b38;
  height: 20px;
  width: 100%;
}

#hr-bottom {
  background-color: #26b762;
  height: 20px;
  width: 100%;
}

h1{
  font-size: 36px;
}

h3{
  font-size: 36px;
}

#ingredients-field{
  margin-left: 29%
}

input{
  margin-bottom: 50px;
}


</style>