
/** When your routing table is too long, you can split it into small modules **/
import Layout from '@/views/layout/index'

const userRouter =  {
    path: '/user',
    name: 'User',
    component: Layout,
    redirect: '/user/profile',
    alwaysShow: true,
    meta: {
        title: '用户管理',
        icon: 'el-icon-s-custom',
        roles: ['sysAdmin']
    },
    children: [
        {
            path: 'profile',
            name: 'UserProfile',
            component: () => import('@/views/admin/user/UserProfile'),
            meta: { title: '用户信息', icon: 'el-icon-user-solid' }
        },
        {
            path: 'role',
            name: 'Role',
            component: () => import('@/views/admin/user/Role'),
            meta: { title: '角色配置', icon: 'el-icon-s-check' }
        }
    ]
}

const contentRouter = {
    path: '/content',
    name: 'Content',
    component: Layout,
    redirect: '/content/article',
    alwaysShow: true,
    meta: {
        title: '内容管理',
        icon: 'el-icon-s-order',
        roles: ['sysAdmin']
    },
    children: [
        {
            path: 'article',
            name: 'ArticleManagement',
            component: () => import('@/views/admin/content/article/ArticleManagement'),
            meta: { title: '文章管理', icon: 'el-icon-document' }
        },
        {
            path: 'article/editor',
            name: 'ArticleEditor',
            hidden: true,
            component: () => import('@/views/admin/content/article/ArticleEditor')
        },
        {
            path: 'article/details',
            name: 'AdminArticleDetails',
            hidden: true,
            component: () => import('@/views/admin/content/article/ArticleDetails')
        },
        {
            path: 'secret',
            name: 'SecretManagement',
            component: () => import('@/views/admin/content/secret/SecretManagement'),
            meta: { title: '秘密花园管理', icon: 'el-icon-camera-solid' }
        }
    ]
}

const toolRouter =  {
    path: '/tool',
    component: Layout,
    redirect: '/tool/markTool',
    alwaysShow: true,
    name: 'Tool',
    meta: {
        title: '图片工具',
        icon: 'el-icon-picture',
        roles: ['sysAdmin']
    },
    children: [
        {
            path: 'markTool',
            name: 'MarkTool',
            component: () => import('@/views/admin/tool/markImage/MarkTool'),
            meta: { title: '水印工具', icon: 'el-icon-s-cooperation' }
        },
        {
            path: 'certTool',
            name: 'CertTool',
            component: () => import('@/views/admin/tool/certImage/CertTool'),
            meta: { title: '证件工具', icon: 'el-icon-s-cooperation' }
        }
    ]
}

export {userRouter, contentRouter, toolRouter}
