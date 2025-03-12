<template>
  <div>
    <h1>User Profile</h1>
    <div v-if="loading">
      <SkeletonLoader width="20rem" height="3rem" />
      <SkeletonLoader width="20rem" height="3rem" />
      <SkeletonLoader width="20rem" height="3rem" />
    </div>
    <div v-else>
      <h2>{{ user.name }}</h2>
      <p>{{ user.bio }}</p>
    </div>
  </div>
</template>

<script>
import SkeletonLoader from './SkeletonLoader.vue';

export default {
  components: {
    SkeletonLoader,
  },
  data() {
    return {
      loading: true,
      user: null,
    };
  },
  mounted() {
    setTimeout(() => {
      this.user = { name: 'John Doe', bio: 'Lorem ipsum dolor sit amet.' };
      this.loading = false;
    }, 2000); // Simulate an API call
  },
};
</script>