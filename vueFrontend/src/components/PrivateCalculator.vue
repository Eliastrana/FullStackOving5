// PrivateCalculator.vue

<template>

  <div v-if="user" class="user-info">
    Logged in as: {{ user.username  }}
  </div>

  <div id="calculator">
    <!-- Calculator Display -->
    <input type="text" v-model="current" id="calculator-display" disabled />

    <!-- Calculator Buttons -->
    <div class="button-row">
      <button v-on:click="clear()" class="button-clear">C</button>
      <div class="spacer" id="empty-space"></div>
      <button v-on:click="append('/')" class="button-divide">/</button>
    </div>
    <div class="button-row">
      <button v-on:click="append('7')" class="button-7">7</button>
      <button v-on:click="append('8')" class="button-8">8</button>
      <button v-on:click="append('9')" class="button-9">9</button>
      <button v-on:click="append('*')" class="button-multiply">*</button>
    </div>
    <div class="button-row">
      <button v-on:click="append('4')" class="button-4">4</button>
      <button v-on:click="append('5')" class="button-5">5</button>
      <button v-on:click="append('6')" class="button-6">6</button>
      <button v-on:click="append('-')" class="button-subtract">-</button>
    </div>
    <div class="button-row">
      <button v-on:click="append('1')" class="button-1">1</button>
      <button v-on:click="append('2')" class="button-2">2</button>
      <button v-on:click="append('3')" class="button-3">3</button>
      <button v-on:click="append('+')" class="button-add">+</button>
    </div>
    <div class="button-row">
      <button v-on:click="append('0')" class="button-0">0</button>
      <button v-on:click="append('.')" class="button-dot">.</button>
      <button v-on:click="calculate()" class="button-equals">=</button>
    </div>
  </div>

  <private-calculator-history :history="history"></private-calculator-history>


</template>


<script>
import PrivateCalculatorHistory from '../archive/PrivateCalculatorHistory.vue';
import CalculationService from '@/services/CalculationService';
import UserService from "@/services/UserService"; // Adjust the path as necessary

export default {
  components: {
    PrivateCalculatorHistory,
  },
  data() {
    return {
      current: '',
      history: [],
      user: null,
    };
  },
  mounted() {
    this.fetchUserData();
    this.fetchCalculationHistory();
  },
  methods: {
    clear() {
      this.current = '';
    },
    append(value) {
      if (value === '.' && this.current.split(/[+\-*/]/).pop().includes('.')) {
        return;
      }
      this.current += value;
    },

    fetchUserData() {
      const username = localStorage.getItem('username');
      if (username) {
        // If the username exists, initialize the user object with the username
        this.user = {username: username};
      } else {
        // If no username is found, redirect to the login page
        this.$router.push('/login');
      }
    },


    async fetchCalculationHistory() {
      if (!this.user || !this.user.username) {
        console.error('Username is not available. User might not be logged in.');
        this.$router.push('/login');
        return;
      }

      try {
        // First, get the user ID by username
        const userId = await UserService.getUserIdByUsername(this.user.username);

        // Then, get the calculation history using the user ID
        const response = await CalculationService.getCalculationHistoryByUserId(userId);

        // Take the last 5 entries from the database content
        const recentCalculations = response.data.slice(-5); // Takes the last 5 elements

        // Reverse the order so the newest is at the top
        const reversedCalculations = recentCalculations.reverse();

        // Update the component's history property with the reversed array
        this.history = reversedCalculations;

        console.log('Recent calculation history (newest at the top) fetched for user:', this.user.username);
      } catch (error) {
        console.error('Failed to fetch calculation history:', error);
      }
    },


// Evaluate the expression
    async calculate() {
      if (this.current.trim() === '') {
        console.error("The expression is empty.");
        return;
      }

      try {
        const response = await CalculationService.evaluate(this.current);

        // Now, handle the result (e.g., display it, add it to history)
        // ...
        const result = response.data;



        console.log('Calculation result:', result);

        this.result = result.toString(); // Convert to string if necessary


        this.history.push(`${this.current} = ${result}`);


        await this.saveCalculation(this.current, result); // Save the calculation

        console.log('Calculation result:', result);

        // If you need to save the calculation, call saveCalculation separately
        // Assuming saveCalculation is implemented to send a POST request to /save
        // this.saveCalculation(this.current, result);

        this.current = result; // Clear the current expression


      } catch (error) {
        console.error("Calculation error:", error);
        this.current = 'Error';
      }
    },


    // In your component where you need to save the calculation
    async saveCalculation(expression, result) {
      if (!this.user || !this.user.username) {
        console.error('Username is not available. Cannot save calculation.');
        return;
      }

      console.log('Saving calculation:', expression, result);
      console.log('User:', this.user);


      try {
        const userId = await UserService.getUserIdByUsername(this.user.username);

        console.log('User ID:', userId);

        const saveRequest = {
          userId: userId, // Retrieved from the async call above
          expression: expression,
          result: result
        };

        console.log('Save request:', saveRequest);

        await CalculationService.saveCalculation(saveRequest);
        await this.fetchCalculationHistory();

        // Handle success...
        console.log('Calculation saved successfully.');


      } catch (error) {
        console.error("Failed to save calculation:", error);
        // Handle error...
      }
    }




  }

};
</script>




<style>
#calculator {
  display: flex;
  flex-direction: column;
  max-width: 285px;
  margin: auto;
  background-color: #AAD5E2;
  border-radius: 20px;
  padding: 20px;
  border-width: 0px;

}

.button-row {
  display: flex;
}

.button-divide, .button-multiply, .button-subtract, .button-add {
  min-width: 60px;
  max-width: 60px;
  min-height: 60px;
  max-height: 60px;
  font-family: 'Roboto', sans-serif;
  font-size: 30px;
  color: #f1ead6;
  background-color: #698FB2;
  border-radius: 50%;
  margin: 5px;
  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);
  border-width: 0px;
}

.button-divide:hover, .button-multiply:hover, .button-subtract:hover, .button-add:hover {
  background-color: #2c3e50;
  color: #f1ead6;
}



.button-divide:focus, .button-multiply:focus, .button-subtract:focus, .button-add:focus {
  background-color: #2c3e50;
  color: #f1ead6;
}

.button-clear {
  min-width: 60px;
  max-width: 60px;
  min-height: 60px;
  max-height: 60px;
  font-family: 'Roboto', sans-serif;
  font-size: 30px;
  color: #f1ead6;
  background-color: #e58a2b;
  border-radius: 50%;
  margin: 5px;
  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);
  border-width: 0px;
}

.button-clear:hover {
  background-color: #8f581c;
  color: #f1ead6;
}

.button-dot {
  min-width: 60px;
  max-width: 60px;
  min-height: 60px;
  max-height: 60px;
  font-family: 'Roboto', sans-serif;
  font-size: 30px;
  color: #e58a2b;
  background-color: #FDFFEC;
  border-radius: 50%;
  margin: 5px;
  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);
  border-width: 0px;
}

.button-dot:hover {
  background-color: #efe3c2;
  color: #545353;
}

.button-equals {
  min-width: 60px;
  max-width: 60px;
  min-height: 60px;
  max-height: 60px;
  font-family: 'Roboto', sans-serif;
  font-size: 30px;
  color: #f1ead6;
  background-color: #698FB2;
  border-radius: 50%;
  margin: 5px;
  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);
  border-width: 0px;
}

.button-equals:hover {
  background-color: #2c3e50;
  color: #f1ead6;
}

.button-1, .button-2, .button-3, .button-4, .button-5, .button-6, .button-7, .button-8, .button-9 {
  min-width: 60px;
  max-width: 60px;
  min-height: 60px;
  max-height: 60px;
  font-family: 'Roboto', sans-serif;
  font-size: 30px;
  color: #e58a2b;
  background-color: #FDFFEC;
  border-radius: 50%;
  margin: 5px;
  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);
  border-color: #FDFFEC;
  border-width: 0px;
}

.button-1:hover, .button-2:hover, .button-3:hover, .button-4:hover, .button-5:hover, .button-6:hover, .button-7:hover, .button-8:hover, .button-9:hover {
  background-color: #efe3c2;
  color: #545353;
}

.button-0 {
  width: 130px;
  height: 60px;

  font-family: 'Roboto', sans-serif;
  font-size: 30px;
  color: #e58a2b;
  background-color: #FDFFEC;
  border-radius: 40px;
  margin: 5px;
  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);
  border-width: 0px;
}

.button-0:hover {
  background-color: #efe3c2;
  color: #545353;
}

.spacer {
  width: 140px;
  height: 70px;
}

#calculator-display {
  position: relative;
  width: 235px;
  height: 50px;
  font-family: 'Roboto', sans-serif;
  font-size: 30px;
  color: #e58a2b;
  background-color: #FDFFEC;
  margin: 5px;
  border-radius: 30px;
  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);
  border-width: 0px;
  text-align : Right;
  padding-left: 20px;
  padding-right: 20px;

}



</style>
