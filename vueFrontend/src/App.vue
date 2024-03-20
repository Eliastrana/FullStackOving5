<template>
  <div id="app">
    <nav class="navbar">
      <router-link to="/" class="nav-link">Home</router-link>
      <div class="link-group">
<!--      <router-link to="/PrivateCalculatorLogIn" class="nav-link">Private Calculator</router-link>-->

      <router-link to="/CreateUser" class="nav-link">Create User</router-link>
        <router-link to="/LoginUser" class="nav-link">Log in</router-link>
        <router-link to="/AboutPage" class="nav-link">Contact Form</router-link>
<!--      <router-link to="/CalculatorPage" class="nav-link">Calculator</router-link>-->
      <router-link to="/GalleryView" class="nav-link">Gallery</router-link>

      </div>
    </nav>


    <router-view></router-view>


  </div>
</template>

<script>


export default {
  name: 'App',

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  margin-top: 60px;
}


.navbar {
  font-family: Futura, sans-serif;
  overflow: hidden;
  position: fixed;
  top: 0;
  left: 0; /* Ensure navbar starts from the left edge */
  right: 0; /* Ensure navbar extends to the right edge */
  display: flex;
  justify-content: space-between; /* Aligns items to the edges */
  align-items: center;
  z-index: 1000;
  padding: 5px;
  box-sizing: border-box; /* Include padding in the element's total width */
  border-radius: 20px; /* Ensures the shadow has rounded corners */

  /* Removed margin: 10px; to prevent the navbar from extending beyond viewport */
}

.navbar .nav-link {
  color: #000000; /* Ensure this color is visible against your page's background */
  padding: 14px 16px;
  text-decoration: none;
  transition: box-shadow 0.3s; /* Smooth transition for the box-shadow */
}

.navbar .nav-link:hover {
  color: black; /* Keeps the text color black on hover */
  box-shadow: 0 2px 4px rgba(0,0,0,0.2); /* Adds a subtle shadow for depth */
  border-radius: 20px; /* Ensures the shadow has rounded corners */
}

.link-group {
  display: flex;
}

body {
  padding-top: 80px; /* Adjusted to account for the navbar's height */
  cursor: url('@/assets/cursor/cursor.png'), auto;
}


body {
  padding-top: 60px; /* Add padding to prevent content from hiding under the navbar */
  cursor: url('@/assets/cursor/cursor.png'), auto;

}

/* Apply globally */
</style>

