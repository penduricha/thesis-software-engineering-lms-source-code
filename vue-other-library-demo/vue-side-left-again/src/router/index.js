import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: Home
        },
        {
            path: '/home',
            component: () => import('../views/Home.vue')
        },
        {
            path: '/about',
            component: () => import('../views/About.vue')
        },
        {
            path: '/contact',
            component: () => import('../views/Contact.vue')
        },
        {
            path: '/team',
            component: () => import('../views/Team.vue')
        },
    ],
})

export default router

// import { createRouter, createWebHistory } from 'vue-router'
// import Home from '../views/Home.vue'
// import { ref } from 'vue'
//
// const historyList = ref([])
//
// const router = createRouter({
//     history: createWebHistory(),
//     routes: [
//         { path: '/', component: Home },
//         { path: '/home', component: () => import('../views/Home.vue') },
//         { path: '/about', component: () => import('../views/About.vue') },
//         { path: '/contact', component: () => import('../views/Contact.vue') },
//         { path: '/team', component: () => import('../views/Team.vue') },
//     ],
// })
//
// // Lắng nghe sự kiện điều hướng
// router.afterEach((to) => {
//     historyList.value.push(to.path) // Lưu lại đường dẫn đã truy cập
// })
//
// export { router, historyList }