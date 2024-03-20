<script setup>
import ContactForm from './ContactForm.vue'
import ContactConfirmation from './ContactConfirmation.vue'
</script>

<template>
  <div class="website-container">
    <ContactForm />
    <ContactConfirmation />

<!--    <img src="../assets/linomonkeywallpaper.png" alt="linomonkeywallpaper" class="img" >-->

  </div>
</template>

<style scoped>


.img {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
}

body{

  cursor: url('@/assets/cursor/cursor.png'), auto;
}

</style>

