<template>
  <div id="app">
      <NavBar></NavBar>
      <main>
          <RouterView/>
      </main>

      <footer class="footer">
          <p>&copy; Projet Banque Java SpringBoot Vuejs</p>
      </footer>
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue';
export default {
    name: 'App',
    components: {
        NavBar
    }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
    margin: 5px;
    border-radius: 5px;
}

nav a.router-link-exact-active {
    background-color: rgba(13, 109, 85, 0.5);
}

.footer{
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
}
</style>
<script setup>
</script>