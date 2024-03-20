<script setup>

import Reviews from "@/components/ReviewDisplay.vue";
</script>

<template>

  <div>
    <h1>Welcome to our homepage</h1>
  </div>

  <img src="../assets/linomonkey.png" alt="Corporate Image">


  <h1>Look at our reviews!</h1>
  <reviews />

</template>

<style scoped>

h1 {
  text-align: center;
  color: #333;
  font-family: Futura, sans-serif;
  font-weight: 400;
  font-size: 40px;
;
}

img {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
}

body {
  cursor: url('@/assets/cursor/cursor.png'), auto;
}

</style>