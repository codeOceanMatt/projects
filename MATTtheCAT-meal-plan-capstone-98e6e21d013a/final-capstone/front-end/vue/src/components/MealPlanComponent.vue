<template>
  <div>
    <hr id="hr-top" />
    <h1 id="meal-plan-title">Meal Plan</h1>
    <h3 id="meal-plan-h3">Please make a selction</h3>
    <hr id="hr-bottom" />
    <div id="buttons">
    <div>
    <button class="add-meal-plan" v-on:click="showAddMealPlanForm = !showAddMealPlanForm">Add Meal Plan</button>
    <add-meal-plan-component v-show="showAddMealPlanForm" />
    </div>
    <div>
    <button class="view-calendar" v-on:click="showMealPlanCalendar = !showMealPlanCalendar, seeMyMealPlanCalendar()">View Calendar</button>
    <meal-plan-calendar-component v-show="showMealPlanCalendar" />
    </div>
    <div>
    <button class="view-my-meal-plans" v-on:click="showMyMealPlans = !showMyMealPlans, seeMyMealPlans()">See My Meal Plans</button>
    <see-my-meal-plans-component v-show="showMyMealPlans" />
    </div>
    <div>
    <button class="go-back" v-on:click="goBack()">Return to Home</button>
    </div>
    </div>
    <img id="meal-plan-component" src="https://media.discordapp.net/attachments/738847718750945341/743534972799025273/allmealsvertical2small.png" alt="yummy">
  </div>
</template>

<script>
import AddMealPlanComponent from "@/components/AddMealPlanComponent";
import MealPlanCalendarComponent from "@/components/MealPlanCalendarComponent";
import SeeMyMealPlansComponent from "@/components/SeeMyMealPlansComponent";

export default {
  components: {
    AddMealPlanComponent,
    MealPlanCalendarComponent,
    SeeMyMealPlansComponent,
  },
  data() {
    return {
      showAddMealPlanForm: false,
      showMealPlanCalendar: false,
      showMyMealPlans: false,
    };
  },
  methods: {
    seeMyMealPlans() {
      this.$router.push("/mealplan/{id}");
    },
    seeMyMealPlanCalendar() {
      this.$router.push("/mealplan/{id}/calendar");
    },
    goBack() {
      this.$router.push("/");
    },
  },
};
</script>

<style scoped>
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

#meal-plan-title{
  text-align: center;
  font-size: 36px
}

#meal-plan-h3{
  text-align: center;
  font-size: 24px
}

#buttons{
  display: grid;
  grid-template-columns: 1fr, 1fr;
  grid-template-areas: "add-meal-plan       view-calendar"
                       "view-my-meal-plans  go-back";
  grid-gap: 100px;
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 40px;
  align-content: space-around;
}

.add-meal-plan{
  grid-area: add-meal-plan;
  grid-area: add-recipes;
  font-size: 2rem;
  border: 3px solid darkgrey;
  background-color: lightgrey;
  margin-top: 15px;
  padding: 20px;
  padding-top: 15px;
  padding-left: 40px;
  height: 120px;
  text-align: center;
  border-radius: 5px;
  display:block;
  width: 100%
}

.view-calendar{
  grid-area: view-calendar;
  grid-area: add-recipes;
  font-size: 2rem;
  border: 3px solid darkgrey;
  background-color: lightgrey;
  margin-top: 15px;
  padding: 20px;
  padding-top: 15px;
  padding-left: 40px;
  height: 120px;
  text-align: center;
  border-radius: 5px;
  display:block;
  width: 100%
}

.view-my-meal-plans{
  grid-area: view-my-meal-plans;
  grid-area: add-recipes;
  font-size: 2rem;
  border: 3px solid darkgrey;
  background-color: lightgrey;
  margin-top: 15px;
  padding: 20px;
  padding-top: 15px;
  padding-left: 40px;
  height: 120px;
  text-align: center;
  border-radius: 5px;
  display:block;
  width: 100%
}

.go-back{
  grid-area: go-back;
  grid-area: add-recipes;
  font-size: 2rem;
  border: 3px solid darkgrey;
  background-color: lightgrey;
  margin-top: 15px;
  padding: 20px;
  padding-top: 15px;
  padding-left: 40px;
  height: 120px;
  text-align: center;
  border-radius: 5px;
  display:block;
  width: 100%
}

#meal-plan-component{
  margin-left: 33%;
  height: 250px
}
</style>