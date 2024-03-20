<template>
  <div class="history-container">
  <div class="calculator-history">
    <h3>Calculation History:</h3>
    <ul>
      <li v-for="(record, index) in history" :key="index">{{ record }}</li>
    </ul>
  </div>
  </div>
</template>

<script>
export default {
  props: {
    history: Array
  }
};
</script>

<style scoped>
.history-container {
  display: flex;

  justify-content: center; /* Center horizontally */
  align-items: flex-start; /* Align to top */
  height: 50vh; /* Half viewport height */
  overflow: auto; /* Allows scrolling if content overflows */
}

.calculator-history {
  display: block; /* Use block instead of flex */
  margin-top: 20px;
  background-color: #f5f3f3;
  border-radius: 20px;
  padding: 20px;
  border: 2px solid #cccccc;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 300px; /* Fixed width */
  text-align: left;
  font-family: 'Futura', sans-serif;
  font-size: 20px;
  max-height: 100%; /* Limit height to the parent container */
}
</style>


