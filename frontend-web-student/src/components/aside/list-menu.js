const itemsMenu = [
    {
        index: 1,
        name: 'Exam coding',
        imageSrc: new URL('@/assets/image/icon-menu/exam-list-icon.png',import.meta.url).href,
        path: '/main-page/list-exams-page',
    },
    {
        index: 2,
        name: 'Exam result',
        imageSrc: new URL('@/assets/image/icon-menu/exam-result-icon.png',import.meta.url).href,
        path: '/main-page/list-result-exams',
    },
];

export default itemsMenu;