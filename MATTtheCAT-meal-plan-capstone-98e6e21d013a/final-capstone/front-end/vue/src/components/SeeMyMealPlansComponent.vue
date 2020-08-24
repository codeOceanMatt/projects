<template>
  <div>
    <h1>Meal Plans For {{this.$store.state.user.username}}</h1>
    <div>
      <router-link
        v-bind:to="{ name: 'meal-plan-recipe-details' }"
        v-for="mealPlan in this.$store.state.mealPlans"
        v-bind:key="mealPlan.planId"
        tag="li"
      >{{mealPlan.planName}}</router-link>
    </div>
  </div>
</template>

<script>
import mealPlanService from "@/services/MealPlanService";
export default {
  created() {
    mealPlanService.getMealPlans(this.$store.state.user.id).then((response) => {
      this.$store.commit("LIST_MEAL_PLANS", response.data);
    });
  },
};
</script>

<style>
</style>