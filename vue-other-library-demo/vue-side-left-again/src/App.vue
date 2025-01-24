<template>
  <div class="app">
    <Sidebar />
    <section style="width: 100%; height: auto;">
      <header>

      </header>
    </section>
  </div>
</template>

<script setup>
import Sidebar from './components/Sidebar.vue';
// import { ref } from 'vue'
//
//
// const is_expanded = ref(localStorage.getItem("is_expanded") === "true")
//
// const toggleMenu = () => {
//   is_expanded.value = !is_expanded.value
//   localStorage.setItem("is_expanded", is_expanded.value)
// }
</script>

<style lang="scss">
header{
  background-color: #42b883;
  color: var(--light);
  width: 100%;
  height: 4rem;
}

//body {
//  background: var(--light);
//}
//
//button {
//  cursor: pointer;
//  appearance: none;
//  border: none;
//  outline: none;
//  background: none;
//}
//
////css chung ở main.css
//.app {
//  display: flex;
//
//  main {
//    flex: 1 1 0;
//    padding: 2rem;
//
//    @media (max-width: 1024px) {
//      padding-left: 6rem;
//    }
//  }
//}
//
//:root {
//  --primary: #4ade80;
//  --primary-alt: #22c55e;
//  --grey: #64748b;
//  --dark: #1e293b;
//  --dark-alt: #334155;
//  --light: #f1f5f9;
//  --sidebar-width: 300px;
//}
//
//* {
//  margin: 0;
//  padding: 0;
//  box-sizing: border-box;
//  font-family: DM Sans, Avenir, Helvetica, Arial, sans-serif;
//}
</style>