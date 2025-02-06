<script>
import listMenu from "@/components/aside/list-menu.js";
import '../../pages/list-exams/list-exams-components.scss';
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

    }
  },

  created() {

  },

  mounted() {

  },

  methods: {
    getRoute() {
      console.log(this.$route.path);
      return this.$route.path;
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

    navigateTo_LoginPage() {
      this.$router.replace({
        path: '/login-page',
        // query: {
        // }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    handleLogout() {
      this.navigateTo_LoginPage();
    }
  },

  computed: {
    setBackGroundColor() {
      return (path) => {
        return (path === this.getRoute())
            ? 'background-grey'
            : 'background-no-grey';
      };
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
  <aside class="aside-menu">
    <div class="header-menu">
      <img src="@/assets/image/icon-menu/logo-iuh-menu.png"
           alt="iuh-logo"
           class="image-iuh-logo"
      >
    </div>
    <div class="section-menu">
      <button v-for="i in itemsMenu"
              :key="i.index"
              class="style-button-item-menu"
              @click="handleButtonMenu(i)"
              :class="['background-color-button',
              setBackGroundColor(i.path)]"
      >
        <img :src="i.imageSrc" alt="exam-list-icon" class="icon-menu">
        <span class="text-menu">{{i.name}}</span>
      </button>
    </div>
    <div class="footer-menu">
      <button
              class="style-button-item-menu"
              @click="handleLogout()"
              style="padding-left: 3rem"
      >
        <img src="@/assets/image/icon-menu/icon-logout.png"
             alt="log out"
             class="icon-menu"
        >
        <span class="text-menu">Log out</span>
      </button>
    </div>
  </aside>
</template>

<style scoped lang="scss">
@use 'aside-menu-style';
//@media screen and (max-width: 1068px) {
//  .section-menu{
//    flex-direction: row;
//  }
//}

</style>