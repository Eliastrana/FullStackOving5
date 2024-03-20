<script setup>
import { ref, onMounted, onUnmounted } from 'vue'


import image1 from '../assets/linomonkey.png';
import image2 from '../assets/linomonkeywallpaper.png';
import image3 from '../assets/monkey.png';
import image4 from '../assets/monkeygrey.png';

const images = [image1, image2, image3, image4];


// Example set of images


const currentIndex = ref(0)

const nextImage = () => {
  currentIndex.value = (currentIndex.value + 1) % images.length
}

// Start automatic cycling
let intervalId = null
const startAutoScroll = () => {
  const interval = 3000 // 3000 milliseconds = 3 seconds
  intervalId = setInterval(nextImage, interval)
}

onMounted(() => {
  startAutoScroll()
})

onUnmounted(() => {
  if (intervalId) {
    clearInterval(intervalId)
  }
})
</script>

<template>
  <div>
    <h1>Gallery</h1>
    <div>
      <img :src="images[currentIndex]" alt="Gallery Image" />
    </div>
    <!-- Buttons for manual control -->
    <button @click="nextImage">More Dall-e generation</button>
  </div>
</template>

<style scoped>
img {
  max-width: 50%;
  height: auto;
}


h1 {
  text-align: center;
  color: #333;
  font-family: Futura, sans-serif;
  font-weight: 400;
  font-size: 40px;
}

button {
  margin-top: 20px;
  border-radius: 5px; /* More subtle border-radius */
  padding: 10px 20px; /* Adjust padding for better text display */
  font-size: 16px; /* Set a standard font size */
  font-weight: bold; /* Make text bold */
  color: white; /* Light text color for better contrast */
  background-color: #84e188; /* A more vibrant color */
  border: none; /* Remove border for a flat design */
  cursor: pointer; /* Change cursor to indicate button */
  transition: background-color 0.3s, box-shadow 0.3s; /* Smooth transition for hover and focus */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Subtle shadow for depth */
}


button:hover, button:focus {
  background-color: #45a049; /* Slightly darker shade for hover and focus */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* Larger shadow for hover and focus */
}

</style>
