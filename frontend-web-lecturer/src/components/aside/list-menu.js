const itemsMenu = [
    {
        index: 1,
        name: 'Home',
        imageSrc: new URL('@/assets/image/icon-menu/home-icon.png',import.meta.url).href,
        path: '/main-page/list-courses',
        pathToSetBackGround: '/main-page/list-courses'
    },
    {
        index: 2,
        name: 'Bank exam',
        imageSrc: new URL('@/assets/image/icon-menu/bank-exams.png',import.meta.url).href,
        path: '/main-page/bank-exams/java-core',
        pathToSetBackGround: '/main-page/bank-exams',
    },
];

export default itemsMenu;