// PrivateCalculatorHistory.vue

<template>
  <div class="history-container">
  <div class="calculator-history">
    <h3>Calculation History:</h3>
    <ul>
      <li v-for="calculation in history" :key="calculation.id">
        {{ calculation.expression }} = {{ calculation.result }},
      </li>
    </ul>

  </div>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  props: {
    history: {
      type: Array,
      required: true
    },
    userId: {
      type: [String, Number],
      required: false
    },
  },
  data() {
    return {
      // If you want to store the history locally as well
      localHistory: []
    };
  },
  methods: {
    async fetchHistory() {

      if (!this.userId) return; // Ensure userId is available
      try {
        const response = await axios.get(`http://localhost:8081/api/calculations/history/${this.userId}`);
        this.localHistory = response.data;
      } catch (error) {
        console.error('There was an error fetching the calculation history:', error);
      }
    }
  },
  mounted() {
    console.log("Fetching history for user ID:", this.userId);
    this.fetchHistory();
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


