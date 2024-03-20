<template>
  <div>
    <div class="entries">
      <div v-for="entry in entries" :key="entry.id" class="entry-container">
        <h3>{{ entry.name }}</h3>
<!--        <h4>{{ entry.email }}</h4>-->
        <p>{{ entry.message }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      entries: []
    };
  },
  async created() {
    try {
      const response = await fetch('http://localhost:3005/data');
      if (!response.ok) {
        throw new Error('Failed to load data');
      }
      const jsonData = await response.json();
      this.entries = jsonData.reverse(); // Reversing the array
    } catch (error) {
      console.error('Error loading JSON:', error);
    }
  }
};
</script>

<style>

.entries {
  display: flex;
  flex-wrap: wrap;
  gap: 20px; /* Adds space between containers */
  justify-content: center; /* Centers the containers */
}

.entry-container {
  flex: 1 1 calc(33.333% - 20px); /* Flex grow, flex shrink, base width */
  border: 1px solid #ddd;
  padding: 30px;
  margin-bottom: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  max-width: 300px;
}

.entry-container h3 {
  margin-top: 0;
  color: #333;
  font-family: Futura, sans-serif;
  font-weight: 400;
  font-size: 35px;
}

.entry-container h3, .entry-container h4 {
  margin-top: 0;
  color: #333;
}

.entry-container p {
  /* Add any additional styles for your paragraphs here */
}

body {
  cursor: url('@/assets/cursor/cursor.png'), auto;
}


</style>
