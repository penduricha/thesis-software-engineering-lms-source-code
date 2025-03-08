<template>
  <div class="skeleton" :style="skeletonStyle"></div>
</template>

<script>
export default {
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
        animation: 'loading 2.5s linear infinite',
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
    background-position: 0 0; /* Đưa vị trí về 0 để tạo hiệu ứng lướt */
  }
}

.skeleton {
  margin: 10px 0;
  overflow: hidden; /* Ẩn những phần ngoài khu vực hiển thị */
}
</style>