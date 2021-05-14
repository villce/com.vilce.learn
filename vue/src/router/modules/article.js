/** When your routing table is too long, you can split it into small modules **/

const articleRouter = {
    path: '/',
    redirect: '/article',
    component: () => import('@/views/article/Articles'),
    children: [
        {
            path: '/article',
            name: 'Article',
            component: () => import('@/views/article/Articles')
        }
    ]
}

const articleDetailsRouter = {
    path: '/article/details',
    name: 'ArticleDetails',
    component: () => import('@/views/article/ArticleDetails')
}

export { articleRouter, articleDetailsRouter }
