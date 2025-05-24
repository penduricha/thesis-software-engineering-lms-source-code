<script>
import listMenu from "@/components/aside/list-menu.js";
import '../../pages/main/list-courses.scss';
import './aside-menu-style.scss';
import {onMounted, ref} from "vue";

export default {
  name: 'AsideMenu',

  props: {

  },

  components: {

  },

  data(){
    return {
      isExpanded: false,
    }
  },

  created() {
    this.setExpanded();
  },

  mounted() {

  },

  methods: {
    getRoute() {
      console.log(this.$route.path);
      return this.$route.path;
    },

    setExpanded() {
      console.log(localStorage.getItem("isExpanded"));
      if (localStorage.getItem("isExpanded") === null) {
        this.isExpanded = true;
        localStorage.setItem("isExpanded", true);
      } else {
        this.isExpanded = localStorage.getItem("isExpanded") === 'true';
      }
    },

    handleButtonMenu(i) {
      this.$router.replace({
        path: i.path,
        // query: {
        // }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    toggleAsideMenu() {
      this.isExpanded = !this.isExpanded;
      localStorage.setItem('isExpanded',this.isExpanded);
    }
  },

  computed: {
    setBackGroundColor() {
      return (path) => {
        return (this.getRoute().includes(path))
            ? 'background-grey'
            : 'background-no-grey';
      };
    },

    setExpandAsideMenu() {
      return (this.isExpanded)
          ? 'is-expanded'
          : 'is-not-expanded';
    },

    setExpandTextMenu() {
      return (this.isExpanded)
          ? 'is-expanded'
          : 'is-not-expanded';
    },

    setExpandButtonBar() {
      return (this.isExpanded)
          ? 'is-expanded'
          : 'is-not-expanded';
    }
  },

  setup(){
    const itemsMenu = ref(null)
    const getItems_Menu = () => {
      itemsMenu.value = listMenu;
    }

    onMounted(() => {
      getItems_Menu();
    });
    return { itemsMenu };
  }
}
</script>


<template>
  <aside class="aside-menu"
        :class="['expand-aside-menu', setExpandAsideMenu]"
  >
    <div class="header-menu">
      <img src="@/assets/image/icon-menu/logo-iuh-menu.png"
           alt="iuh-logo"
           class="image-iuh-logo"
      >
    </div>

    <h1
        :class="['expand-button-bar', setExpandButtonBar]"
    >
      <button class="btn-toggle-menu" @click="toggleAsideMenu()">
        <svg
             xmlns="http://www.w3.org/2000/svg"
             width="16" height="16"
             fill="currentColor" class="bi bi-list icon-bar"
             viewBox="0 0 16 16"
        >
          <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
        </svg>
      </button>
    </h1>
    <div class="section-menu">
      <button v-for="i in itemsMenu"
              :key="i.index"
              class="style-button-item-menu"
              @click="handleButtonMenu(i)"
              :class="['background-color-button',
              setBackGroundColor(i.pathToSetBackGround)]"
      >
        <img :src="i.imageSrc" alt="exam-list-icon" class="icon-menu">
        <span
              :class="['expand-text-menu',
              setExpandTextMenu]"
        >{{i.name}}</span>
      </button>
    </div>
  </aside>
</template>

<style scoped lang="scss">
@use 'aside-menu-style';
.icon-bar {
  width: 2rem;
  height: 2rem;
}

.expand-button-bar {
  &.is-expanded {
    text-align: right;
    margin-right: 1rem;
  }

  &.is-not-expanded {
    text-align: center;
  }
}
</style>