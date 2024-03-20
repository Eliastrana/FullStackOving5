<template>
  <div class="success-message">
    {{ message }}
  </div>
</template>

<script>
export default {
  props: {
    message: String
  }
};
</script>

<style scoped>
.success-message {
  margin-top: 20px;
  color: green;
}
</style>
