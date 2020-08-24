<template>
  <div>
      <table class="blueTable">
<thead>
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
</thead>
<tbody>
<tr>
<td class="MealTitless">Breakfast</td><td>Mixed Berry French Toast Bake</td><td>Amish Breakfast Casserole
</td><td>French Toast Casserole</td><td>Hot Fruit and Sausage
</td><td>Breakfast Biscuit Cups
</td><td>Spicy Egg Bake
</td><td>Grandmother's Toad in a Hole
</td></tr>
<tr>
<td class="MealTitless">Lunch </td><td>Green Goddess Crunch Sandwich</td><td>Kadhi (Turmeric-Yogurt Soup)
</td><td>Roasted Squash and Grains with Tahini-Honey
</td><td>Warm Chickpea Bowls with Lemony Yogurt
</td><td>Just-Keeps-Getting-Better Lentil Salad
</td><td>Crispy Tofu with Coconut Quinoa and Broccolini
</td><td>Roadie Hoagie
</td></tr>
<tr>
<td class="MealTitless">Dinner</td><td>Herbes de Provence Shrimp with Basil and Pea Couscous
</td><td>Ground Turkey Sloppy Joes
</td><td>Quinoa Chicken Salad
</td><td>Spring Pasta with Salmon, Peas, and Dill
</td><td>Caramelized Chicken Vegetable Stir Fry
</td><td>Shaved Brussels Sprouts Salad
</td><td>Cucumber-Salmon Panzanella
</td></tr>
<tr>
<td class="MealTitless">Dessert</td><td>Raspberry Cream Pie
</td><td>Lemon Thyme Pudding
</td><td>Black Forest Cake For Two
</td><td>Confetti Blondies
</td><td>Top Secret Chocolate Cake
</td><td>Frozen Custard
</td><td>Apple Crisp
</td></tr>
<tr>
<td class="MealTitless">Snack</td><td>Crackers</td><td></td><td>Ice Cream</td><td></td><td>Crackers and Wine</td><td></td><td>Wine</td></tr>
</tbody>

</table>
  </div>
</template>

<script>
export default {

}
</script>

<style>
table.blueTable {
  border: 1px solid rgb(28, 110, 164);
  background-color: #26b762;
  width: 100%;
  text-align: center;
  border-collapse: collapse;
  height:500px;
  
}
table.blueTable td, table.blueTable th {
  border: 1px solid #AAAAAA;
  padding: 3px 2px;
}
table.blueTable tbody td {
  font-size: 13px;
}
table.blueTable tr:nth-child(even) {
  background:  #f0f0f0c2;
}
table.blueTable thead {
  background: #d90b38f1;
  border-bottom: 2px solid #444444;
  height: 175px;
}
table.blueTable thead th {
  font-size: 15px;
  font-weight: bold;
  color: #FFFFFF;
  text-align: center;
  border-left: 2px solid #D0E4F5;
}
table.blueTable thead th:first-child {
  border-left: none;
}

table.blueTable tfoot td {
  font-size: 14px;
}
table.blueTable tfoot .links {
  text-align: right;
}
table.blueTable tfoot .links a{
  display: inline-block;
  background: #1C6EA4;
  color: #FFFFFF;
  padding: 2px 8px;
  border-radius: 5px;
}

</style>