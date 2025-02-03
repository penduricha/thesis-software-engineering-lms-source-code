<template>
  <div class="skeleton" :style="skeletonStyle"></div>
</template>

<script>
export default {
  //truyen tham so component Vuejs
  props: {
    width: {
      type: String,
      default: '100%',
    },
    height: {
      type: String,
      default: '20px',
    },
  },
  computed: {
    skeletonStyle() {
      return {
        width: this.width,
        height: this.height,
        background: 'linear-gradient(90deg, #e0e0e0 25%, #f0f0f0 50%, #e0e0e0 75%)',
        backgroundSize: '200% 100%',
        animation: 'loading 1.5s linear infinite',
        borderRadius: '4px',
      };
    },
  },
};
</script>

<style scoped>
@keyframes loading {
  0% {
    background-position: 200% 0;
  }
  100% {
    background-position: -200% 0;
  }
}
.skeleton {
  margin: 10px 0;
}
</style>