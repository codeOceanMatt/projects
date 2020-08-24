<template>
  <div>
    <table style="width:100%">
      <caption>My Weekly Meal Plan</caption>
      <tr>
        <th></th>
        <th>Sunday</th>
        <th>Monday</th>
        <th>Tuesday</th>
        <th>Wednesday</th>
        <th>Thursday</th>
        <th>Friday</th>
        <th>Saturday</th>
      </tr>
      <tr>
        <td>Breakfast</td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
      </tr>
      <tr>
        <td>Lunch</td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
      </tr>
      <tr>
        <td>Dinner</td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
      </tr>
      <tr>
        <td>Dessert</td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
      </tr>
      <tr>
              <td>Snack</td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
        <td><select>
        <option
          id="recipes"
          v-for="recipe in this.$store.state.recipes"
          v-bind:key="recipe.recipeId"
          tag="option"
        >{{recipe.recipeName}}</option>
      </select></td>
      </tr>
    </table>
    <span>
    <button>Save Meal Plan</button>
    <button>Add Another Recipe</button>
    </span>
  </div>
</template>

<script>
export default {

};
</script>

<style>
</style>