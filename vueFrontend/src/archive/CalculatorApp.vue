<!--<template>-->
<!--  <div id="calculator">-->
<!--    &lt;!&ndash; Calculator Display &ndash;&gt;-->
<!--    <input type="text" v-model="current" id="calculator-display" disabled />-->

<!--    &lt;!&ndash; Calculator Buttons &ndash;&gt;-->
<!--    <div class="button-row">-->
<!--      <button v-on:click="clear()" class="button-clear">C</button>-->
<!--      <div class="spacer" id="empty-space"></div>-->
<!--      <button v-on:click="append('/')" class="button-divide">/</button>-->
<!--    </div>-->
<!--    <div class="button-row">-->
<!--      <button v-on:click="append('7')" class="button-7">7</button>-->
<!--      <button v-on:click="append('8')" class="button-8">8</button>-->
<!--      <button v-on:click="append('9')" class="button-9">9</button>-->
<!--      <button v-on:click="append('*')" class="button-multiply">*</button>-->
<!--    </div>-->
<!--    <div class="button-row">-->
<!--      <button v-on:click="append('4')" class="button-4">4</button>-->
<!--      <button v-on:click="append('5')" class="button-5">5</button>-->
<!--      <button v-on:click="append('6')" class="button-6">6</button>-->
<!--      <button v-on:click="append('-')" class="button-subtract">-</button>-->
<!--    </div>-->
<!--    <div class="button-row">-->
<!--      <button v-on:click="append('1')" class="button-1">1</button>-->
<!--      <button v-on:click="append('2')" class="button-2">2</button>-->
<!--      <button v-on:click="append('3')" class="button-3">3</button>-->
<!--      <button v-on:click="append('+')" class="button-add">+</button>-->
<!--    </div>-->
<!--    <div class="button-row">-->
<!--      <button v-on:click="append('0')" class="button-0">0</button>-->
<!--      <button v-on:click="append('.')" class="button-dot">.</button>-->
<!--      <button v-on:click="calculate()" class="button-equals">=</button>-->
<!--    </div>-->
<!--  </div>-->

<!--  <calculator-history :history="history"></calculator-history>-->
<!--</template>-->


<!--<script>-->

<!--import CalculatorHistory from './CalculatorHistory.vue';-->
<!--export default {-->
<!--  components: {-->
<!--    CalculatorHistory,-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      current: '',-->
<!--      history: []-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    clear() {-->
<!--      this.current = '';-->
<!--    },-->
<!--    append(value) {-->
<!--      if (value === '.' && this.current.split(/[+\-*/]/).pop().includes('.')) {-->
<!--        return;-->
<!--      }-->
<!--      this.current += value;-->
<!--    },-->
<!--    calculate() {-->
<!--      try {-->
<!--        // Check for illegal stuff-->
<!--        if (/[^0-9+\-*/.() ]/.test(this.current)) {-->
<!--          throw new Error("🤨");-->
<!--        }-->

<!--        const result = eval(this.current).toString();-->

<!--        // Catch mistske-->
<!--        if (result === "Infinity" || result === "-Infinity" || result === "NaN") {-->
<!--          throw new Error("🤨");-->
<!--        }-->

<!--        this.history.push(this.current + ' = ' + result);-->
<!--        this.current = result;-->
<!--      } catch (e) {-->
<!--        this.current = 'Error: ' + e.message;-->
<!--      }-->
<!--    },-->
<!--  },-->
<!--};-->
<!--</script>-->


<!--<style>-->
<!--#calculator {-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  max-width: 285px;-->
<!--  margin: auto;-->
<!--  background-color: #AAD5E2;-->
<!--  border-radius: 20px;-->
<!--  padding: 20px;-->
<!--  border-width: 0px;-->

<!--}-->

<!--.button-row {-->
<!--  display: flex;-->
<!--}-->

<!--.button-divide, .button-multiply, .button-subtract, .button-add {-->
<!--  min-width: 60px;-->
<!--  max-width: 60px;-->
<!--  min-height: 60px;-->
<!--  max-height: 60px;-->
<!--  font-family: 'Roboto', sans-serif;-->
<!--  font-size: 30px;-->
<!--  color: #f1ead6;-->
<!--  background-color: #698FB2;-->
<!--  border-radius: 50%;-->
<!--  margin: 5px;-->
<!--  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);-->
<!--  border-width: 0px;-->
<!--}-->

<!--.button-divide:hover, .button-multiply:hover, .button-subtract:hover, .button-add:hover {-->
<!--  background-color: #2c3e50;-->
<!--  color: #f1ead6;-->
<!--}-->



<!--.button-divide:focus, .button-multiply:focus, .button-subtract:focus, .button-add:focus {-->
<!--  background-color: #2c3e50;-->
<!--  color: #f1ead6;-->
<!--}-->

<!--.button-clear {-->
<!--  min-width: 60px;-->
<!--  max-width: 60px;-->
<!--  min-height: 60px;-->
<!--  max-height: 60px;-->
<!--  font-family: 'Roboto', sans-serif;-->
<!--  font-size: 30px;-->
<!--  color: #f1ead6;-->
<!--  background-color: #e58a2b;-->
<!--  border-radius: 50%;-->
<!--  margin: 5px;-->
<!--  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);-->
<!--  border-width: 0px;-->
<!--}-->

<!--.button-clear:hover {-->
<!--  background-color: #8f581c;-->
<!--  color: #f1ead6;-->
<!--}-->

<!--.button-dot {-->
<!--  min-width: 60px;-->
<!--  max-width: 60px;-->
<!--  min-height: 60px;-->
<!--  max-height: 60px;-->
<!--  font-family: 'Roboto', sans-serif;-->
<!--  font-size: 30px;-->
<!--  color: #e58a2b;-->
<!--  background-color: #FDFFEC;-->
<!--  border-radius: 50%;-->
<!--  margin: 5px;-->
<!--  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);-->
<!--  border-width: 0px;-->
<!--}-->

<!--.button-dot:hover {-->
<!--  background-color: #efe3c2;-->
<!--  color: #545353;-->
<!--}-->

<!--.button-equals {-->
<!--  min-width: 60px;-->
<!--  max-width: 60px;-->
<!--  min-height: 60px;-->
<!--  max-height: 60px;-->
<!--  font-family: 'Roboto', sans-serif;-->
<!--  font-size: 30px;-->
<!--  color: #f1ead6;-->
<!--  background-color: #698FB2;-->
<!--  border-radius: 50%;-->
<!--  margin: 5px;-->
<!--  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);-->
<!--  border-width: 0px;-->
<!--}-->

<!--.button-equals:hover {-->
<!--  background-color: #2c3e50;-->
<!--  color: #f1ead6;-->
<!--}-->

<!--.button-1, .button-2, .button-3, .button-4, .button-5, .button-6, .button-7, .button-8, .button-9 {-->
<!--  min-width: 60px;-->
<!--  max-width: 60px;-->
<!--  min-height: 60px;-->
<!--  max-height: 60px;-->
<!--  font-family: 'Roboto', sans-serif;-->
<!--  font-size: 30px;-->
<!--  color: #e58a2b;-->
<!--  background-color: #FDFFEC;-->
<!--  border-radius: 50%;-->
<!--  margin: 5px;-->
<!--  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);-->
<!--  border-color: #FDFFEC;-->
<!--  border-width: 0px;-->
<!--}-->

<!--.button-1:hover, .button-2:hover, .button-3:hover, .button-4:hover, .button-5:hover, .button-6:hover, .button-7:hover, .button-8:hover, .button-9:hover {-->
<!--  background-color: #efe3c2;-->
<!--  color: #545353;-->
<!--}-->

<!--.button-0 {-->
<!--  width: 130px;-->
<!--  height: 60px;-->

<!--  font-family: 'Roboto', sans-serif;-->
<!--  font-size: 30px;-->
<!--  color: #e58a2b;-->
<!--  background-color: #FDFFEC;-->
<!--  border-radius: 40px;-->
<!--  margin: 5px;-->
<!--  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);-->
<!--  border-width: 0px;-->
<!--}-->

<!--.button-0:hover {-->
<!--  background-color: #efe3c2;-->
<!--  color: #545353;-->
<!--}-->

<!--.spacer {-->
<!--  width: 140px;-->
<!--  height: 70px;-->
<!--}-->

<!--#calculator-display {-->
<!--  position: relative;-->
<!--  width: 235px;-->
<!--  height: 50px;-->
<!--  font-family: 'Roboto', sans-serif;-->
<!--  font-size: 30px;-->
<!--  color: #e58a2b;-->
<!--  background-color: #FDFFEC;-->
<!--  margin: 5px;-->
<!--  border-radius: 30px;-->
<!--  box-shadow: 3px 3px 5px 0px rgba(0, 0, 0, 0.3);-->
<!--  border-width: 0px;-->
<!--  text-align : Right;-->
<!--  padding-left: 20px;-->
<!--  padding-right: 20px;-->

<!--}-->



<!--</style>-->
